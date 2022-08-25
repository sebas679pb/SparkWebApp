package co.edu.escuelaing.arem;

import static spark.Spark.*;

public class SparkWebApp {

    public static void main(String[] args) {
        port(getPort());
        staticFiles.location("/public");
        get("/hello", (req, res) -> "Hello " + req.queryParams("name"));
        get("/stock", (req, res) -> {
            res.type("application/json");
            return HttpConnectionExample.getStock();
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000;
    }
    
}
