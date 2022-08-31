package co.edu.escuelaing.arem.api;

public class Monthly extends AlphaGetter {

    /**
     * Constructor de la clase
     * @param symbol
     */
    public Monthly(String symbol) {
        super("TIME_SERIES_MONTHLY", symbol);
    }

}
