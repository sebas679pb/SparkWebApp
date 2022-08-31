package co.edu.escuelaing.arem.api;

public class AlphaGetter extends ApiGetter {

    private static final String ALPHA_URL = "https://www.alphavantage.co/query?";
    private static final String ALPHA_KEY = "&apikey=ZJRMVE96BMQKZ48Q";

    /**
     * Constructor de la clase
     * @param function
     * @param symbol 
     */
    public AlphaGetter(String function, String symbol) {
        super(ALPHA_URL, ALPHA_KEY);
        defineParameters();
        input.add(function);
        input.add(symbol);
        buildQuery();
    }

    /**
     * Metodo que define los parametros de la consulta
     */
    public void defineParameters() {
        parameters.add("function=");
        parameters.add("&symbol=");
    }

}
