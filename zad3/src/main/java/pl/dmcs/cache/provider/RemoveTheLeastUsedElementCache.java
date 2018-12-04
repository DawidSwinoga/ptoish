package pl.dmcs.cache.provider;

import lombok.EqualsAndHashCode;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Dawid on 04.12.2018 at 21:58.
 */
public class RemoveTheLeastUsedElementCache extends LinkedHashMap<String, RemoveTheLeastUsedElementCache.CounterUsageWrapper> implements Cache {

    public RemoveTheLeastUsedElementCache() {
        super(MAX_ENTRIES);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T get(String cacheKey) {
        CounterUsageWrapper counterUsageWrapper = super.get(cacheKey);
        if (counterUsageWrapper != null) {
            counterUsageWrapper.incrementUsage();
            return (T) counterUsageWrapper.value;
        }

        return null;
    }

    @Override
    public void putData(String cacheKey, Object value) {
        if (size() > MAX_ENTRIES) {
            removeTheLeastUsedElement();
        }
        put(cacheKey, new CounterUsageWrapper(value));
    }

    private void removeTheLeastUsedElement() {
        entrySet().stream().min(Comparator.comparing(Map.Entry::getValue)).ifPresent(it -> remove(it.getKey()));
    }


    @EqualsAndHashCode(exclude = "usageCount")
    protected static class CounterUsageWrapper implements Comparable<CounterUsageWrapper> {
        private int usageCount = 0;
        private Object value;

        public CounterUsageWrapper(Object data) {
            this.value = data;
        }


        void incrementUsage() {
            usageCount = usageCount + 1;
        }

        @Override
        public int compareTo(CounterUsageWrapper o) {
            return Long.compare(usageCount, o.usageCount);
        }
    }
}
