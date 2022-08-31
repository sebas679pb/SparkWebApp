package co.edu.escuelaing.arem.api;

import java.util.HashMap;

public class Cache {

    private static final Cache _instance = new Cache();
    private HashMap<String, String> cache = new HashMap<>();

    /**
     * Constructor de la clase
     */
    private Cache() {
    }

    /**
     * Adicion de una consulta
     * @param key
     * @param value
     */
    public void insert(String key, String value) {
        cache.put(key, value);
    }

    /**
     * Obtener una consulta con su llave
     * @param key
     * @return
     */
    public String get(String key) {
        return cache.get(key);
    }

    /**
     * Verificar si la consulta se encuentra en el cache
     * @param key
     * @return
     */
    public boolean contains(String key) {
        return cache.containsKey(key);
    }

    /**
     * Retorna el cache
     * @return
     */
    public static Cache getInstance() {
        return _instance;
    }
    
}
