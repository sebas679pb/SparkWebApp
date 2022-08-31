package co.edu.escuelaing.arem;

import static spark.Spark.*;
import java.io.IOException;
import co.edu.escuelaing.arem.api.IntraDay;
import co.edu.escuelaing.arem.api.Daily;
import co.edu.escuelaing.arem.api.Weekly;
import co.edu.escuelaing.arem.api.Monthly;
import spark.Request;

public class SparkWebApp {

    public static void main(String[] args) {
        port(getPort());
        staticFiles.location("/public");
        post("/hello", (req, res) -> "Hello " + req.queryParams("name"));
        get("/hello", (req, res) -> "Hello " + req.queryParams("name"));
        get("/stock", (req, res) -> {
            res.type("application/json");
            return identifyFunction(req);
        });
    }

    /**
     * Identifica la función que se debe ejecutar para obtener la consulta
     * @param req 
     * @return
     */
    private static String identifyFunction(Request req) throws IOException {
        String res = null;
        if (req.queryParams("function").equals("intraday")){
            res = new IntraDay(req.queryParams("symbol"), req.queryParams("interval")).getStock();
        }else if (req.queryParams("function").equals("daily")){
            res = new Daily(req.queryParams("symbol")).getStock();
        }else if (req.queryParams("function").equals("weekly")){
            res = new Weekly(req.queryParams("symbol")).getStock();
        }else if (req.queryParams("function").equals("monthly")){
            res = new Monthly(req.queryParams("symbol")).getStock();
        }else{
            res = "Invalid function";
        }
        return res;
    }

    /**
     * Obtiene el puerto del servidor
     * @return
     */
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8080;
    }
    
}
