package ru.spbau.mit;

import java.util.HashMap;

public class Environment {
    private HashMap<String, String> env = new HashMap<String, String>();

    /**
     * Associates the specified value with the specified key
     * @param key the key whose associated value is to be returned
     * @param value value to be associated with the specified key
     */
    public void write(String key, String value) {
        env.put(key, value);
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if this map contains no mapping for the key
     */
    public String read(String key) {
        return env.get(key);
    }
}
