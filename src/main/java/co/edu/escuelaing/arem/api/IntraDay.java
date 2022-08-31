package co.edu.escuelaing.arem.api;

public class IntraDay extends AlphaGetter {

    /**
     * Constructor de la clase
     * @param symbol
     * @param interval
     */
    public IntraDay(String symbol, String interval) {
        super("TIME_SERIES_INTRADAY", symbol);
        parameters.add(2, "&interval=");
        input.add(2, interval);
        buildQuery();
    }

}
