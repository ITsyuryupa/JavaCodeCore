package Collection;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayFilter {

    public static <T> T[] filter(T[] array, Filter<T> filter) {
        List<T> resultList = new ArrayList<>();

        for (T element : array) {
            resultList.add(filter.apply(element));
        }

        // Преобразуем список обратно в массив нужного типа
        return resultList.toArray(array);
    }

}

