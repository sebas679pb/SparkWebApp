class Api extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            function: "intraday",
            symbol: "",
            jsonRes: {},
            interval: "",
        };
        this.handleChangeSelect = this.handleChangeSelect.bind(this);
        this.handleChangeInput = this.handleChangeInput.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChangeInputInterval = this.handleChangeInputInterval.bind(this);
    }

    handleChangeInput(event) {
        this.setState({ symbol: event.target.value });
    }
    handleChangeInputInterval(event) {
        this.setState({ interval: event.target.value });
    }

    handleChangeSelect(event) {
        this.setState({ function: event.target.value });
    }

    handleSubmit(event) {
        this.loadPostMsg();
        event.preventDefault();
    }

    loadPostMsg() {
        let url =
            "/stock?function=" + this.state.function + "&symbol=" + this.state.symbol;
        if (this.state.function == "intraday") {
            url += "&interval=" + this.state.interval;
        }
        fetch(url, { method: "GET" })
            .then((x) => x.json())
            .then((y) => {
                var _visualizer = new visualizer($("#resMSG"));
                _visualizer.visualize(y);
            });
    }

    render() {
        return (
            <div>
                <h1>Mercado de valores</h1>
                <form onSubmit={this.handleSubmit}>
                    <label>Function:</label>
                    <select
                        value={this.state.function}
                        onChange={this.handleChangeSelect}
                    >
                        <option value="intraday">Intraday</option>
                        <option value="daily">Daily</option>
                        <option value="weekly">Weekly</option>
                        <option value="monthly">Monthly</option>
                    </select>
                    <label>Symbol:</label>
                    <input
                        required
                        type="text"
                        placeholder="IBM, MSFT, etc."
                        value={this.state.symbol}
                        onChange={this.handleChangeInput}
                    ></input>
                    {this.state.function == "intraday" ? (
                        <div>
                            <label>Interval:</label>
                            <input
                                required
                                type="text"
                                placeholder="1min, 5min, etc."
                                value={this.state.interval}
                                onChange={this.handleChangeInputInterval}
                            ></input>
                        </div>
                    ) : (
                        <div></div>
                    )}
                    <input type="submit" value="Submit" />
                </form>
            </div>
        );
    }
}

ReactDOM.render(<Api />, document.getElementById("root"));

function visualizer($node) {
    var base = this;

    //Create a new visualizer attached to the result tab
    base.node = $node;

    //Transform used to convert into HTML
    base.transform = {
        "<>": "div",
        class: "visual-package visual-${show} visual-${type}",
        html: [
            {
                "<>": "div",
                class: "visual-header",
                html: [
                    {
                        "<>": "div",
                        class: function (obj) {
                            var classes = ["visual-arrow"];

                            if (base.getValue(obj.value) !== undefined) classes.push("hide");

                            return classes.join(" ");
                        },
                    },
                    { "<>": "span", class: "visual-name", text: "${name}" },
                    {
                        "<>": "span",
                        class: "visual-value",
                        text: function (obj) {
                            var value = base.getValue(obj.value);
                            if (value !== undefined) return " : " + value;
                            else return "";
                        },
                    },
                    { "<>": "span", class: "visual-type", text: "${type}" },
                ],
            },
            {
                "<>": "div",
                class: "visual-children",
                html: function (obj) {
                    return base.children(obj.value);
                },
            },
        ],
    };
}

visualizer.prototype = {
    visualize: function (json) {
        var base = this;

        //Convert json into html
        base.node
            .empty()
            .json2html(base.convert("json", json, "open"), base.transform);

        //Set the events
        base.events();
    },

    //Get a specific value from the json object
    getValue: function (obj) {
        var type = $.type(obj);

        //Determine if this object has children
        switch (type) {
            case "array":
            case "object":
                return undefined;
                break;

            case "function":
                //none
                return "function";
                break;

            case "string":
                return "'" + obj + "'";
                break;

            default:
                return obj;
                break;
        }
    },

    //Transform the children
    children: function (obj) {
        var base = this;

        var type = $.type(obj);

        //Determine if this object has children
        switch (type) {
            case "array":
            case "object":
                return json2html.transform(obj, base.transform);
                break;

            default:
                //This must be a litteral
                break;
        }
    },

    //Convert this json object to HTML
    convert: function (name, obj, show) {
        var base = this;

        var type = $.type(obj);

        if (show === undefined) show = "closed";

        var children = [];

        //Determine the type of this object
        switch (type) {
            case "array":
                //Transform array
                //Itterrate through the array and add it to the elements array
                var len = obj.length;
                for (var j = 0; j < len; ++j) {
                    //Concat the return elements from this objects tranformation
                    children[j] = base.convert(j, obj[j]);
                }
                break;

            case "object":
                //Transform Object
                var j = 0;
                for (var prop in obj) {
                    children[j] = base.convert(prop, obj[prop]);
                    j++;
                }
                break;

            default:
                //This must be a litteral (or function)
                children = obj;
                break;
        }

        return { name: name, value: children, type: type, show: show };
    },

    //Set the events
    events: function () {
        var base = this;

        //Set the header click event
        base.node.find(".visual-header").click(function () {
            var $parent = $(this).parent();

            //Toggle the closed element
            if ($parent.hasClass("visual-closed")) {
                $parent.removeClass("visual-closed");
                $parent.addClass("visual-open");
            } else {
                $parent.removeClass("visual-open");
                $parent.addClass("visual-closed");
            }
        });
    },
};