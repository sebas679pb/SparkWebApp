package co.edu.escuelaing.arem.api;

public class Daily extends AlphaGetter {

    /**
     * Constructor de la clase
     * @param symbol
     */
    public Daily(String symbol) {
        super("TIME_SERIES_DAILY", symbol);
    }

}
