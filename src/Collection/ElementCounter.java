package Collection;

import java.util.HashMap;
import java.util.Map;

public class ElementCounter {

    public static <T> Map<T, Integer> elementCount(T[] array) {
        Map<T, Integer> occurrencesMap = new HashMap<>();

        for (T element : array) {
            occurrencesMap.put(element, occurrencesMap.getOrDefault(element, 0) + 1);
        }

        return occurrencesMap;
    }
}

