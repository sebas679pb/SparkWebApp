package co.edu.escuelaing.arem.api;

public class Weekly extends AlphaGetter {

    /**
     * Constructor de la clase
     * @param symbol
     */
    public Weekly(String symbol) {
        super("TIME_SERIES_WEEKLY", symbol);
    }

}
