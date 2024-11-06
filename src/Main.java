import Collection.ArrayFilter;
import Collection.Filter;
import Collection.SquareFilter;
import MyStringBuilder.MyStringBuilder;
import MyStringBuilder.MementoStorage;

public class Main {
    public static void main(String[] args) {

        Integer[] array = {1, 2, 3, 4, 5};

        // Применяем фильтр, который преобразует строки в верхний регистр
        Integer[] result = ArrayFilter.filter(array, new SquareFilter());

        // Вывод результата
        for (Object item : result) {
            System.out.println(item);
        }

    }
}

