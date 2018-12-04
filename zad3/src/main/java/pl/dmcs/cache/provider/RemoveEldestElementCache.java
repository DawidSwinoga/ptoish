package pl.dmcs.cache.provider;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Dawid on 04.12.2018 at 21:46.
 */
public class RemoveEldestElementCache extends LinkedHashMap<String, Object> implements Cache {

    public RemoveEldestElementCache() {
        super(MAX_ENTRIES);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<String, Object> eldest) {
        return size() > MAX_ENTRIES;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T get(String cacheKey) {
        return (T) super.get(cacheKey);
    }

    @Override
    public void putData(String cacheKey, Object value) {
        put(cacheKey, value);
    }


}
