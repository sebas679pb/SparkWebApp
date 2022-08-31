package co.edu.escuelaing.arem.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public abstract class ApiGetter {

    private static final String USER_AGENT = "Mozilla/5.0";
    private String get_url = "https://www.alphavantage.co/query?";
    private String api_key = "&apikey=ZJRMVE96BMQKZ48Q";
    protected ArrayList<String> parameters = new ArrayList<>();
    protected ArrayList<String> input = new ArrayList<>();
    private StringBuilder query;
    private Cache cache = Cache.getInstance();

    /**
     * Constructor de la clase
     * @param url
     * @param key
     */
    public ApiGetter(String url, String key) {
        this.get_url = url;
        this.api_key = key;
        buildQuery();
    }

    /**
     * Constructor de la clase
     */
    public ApiGetter() {
    }

    /**
     * Metodo que define los parametros de la consulta
     */
    public abstract void defineParameters();

    /**
     * Constructor de la consulta
     */
    public void buildQuery() {
        query = new StringBuilder();
        byte counter = 0;
        query.append(get_url);
        for (String parameter : parameters) {
            query.append(parameter);
            try {
                query.append(input.get(counter));
            } catch (IndexOutOfBoundsException indexBounds) {
                System.out.println("Index out of bounds");
            }
            counter++;
        }
        query.append(api_key);
    }

    /**
     * Getter de la consulta
     * @return String con la consulta
     * @throws IOException
     */
    public String getStock() throws IOException {
        if (cache.contains(query.toString())) {
            return cache.get(query.toString());
        } else {
            URL obj = new URL(query.toString());
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                cache.insert(query.toString(), response.toString());
                return response.toString();
            } else {
                System.out.println("GET request not worked: " + query.toString());
                return "GET request not worked";
            }
        }
    }
    
}
