import Collection.ArrayFilter;
import Collection.ElementCounter;
import Collection.Filter;
import Collection.SquareFilter;
import MyStringBuilder.MyStringBuilder;
import MyStringBuilder.MementoStorage;

import java.util.Map;

public class Main {
    public static void main(String[] args) {

//        Integer[] array = {1, 2, 3, 4, 5};
//
//
//        Integer[] result = ArrayFilter.filter(array, new SquareFilter());
//
//        // Вывод результата
//        for (Object item : result) {
//            System.out.println(item);
//        }

        String[] words = {"a", "b", "a", "c", "a", "c"};

        Map<String, Integer> wordCount = ElementCounter.elementCount(words);

        System.out.println(wordCount);

    }
}

