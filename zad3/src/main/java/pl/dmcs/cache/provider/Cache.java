package pl.dmcs.cache.provider;

/**
 * Created by Dawid on 04.12.2018 at 21:54.
 */
public interface Cache {
    public static final int MAX_ENTRIES = 100;

    <T> T get(String cacheKey);
    void putData(String cacheKey, Object value);
}
