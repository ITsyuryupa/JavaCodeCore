package MyStringBuilder;

import java.util.Arrays;

public class MyStringBuilder implements Comparable<MyStringBuilder>{

    private char[] value;

    //кол-во символов в строке
    private int count;

    public MyStringBuilder() {
        this(16);
    }

    public MyStringBuilder(int capacity) {
        value = new char[capacity];
        count = 0;
    }

    public MyStringBuilder(String str) {
        value = new char[str.length() * 2 + 2];
        this.append(str);
        count = str.length();
    }

    public MyStringBuilder(char[] chars) {
        if (chars.equals(null)) {
            chars = new char[] {'n', 'u', 'l', 'l'};//throw new IllegalArgumentException("Input char array cannot be null");
        }
        value = new char[chars.length * 2 + 2];
        this.append(chars);
        count = chars.length;
    }

    //гарантирует, что емкости будет достаточно
    private void ensureCapacity(int minimumCapacity) {
        if (minimumCapacity - value.length > 0) {
            expandCapacity(minimumCapacity);
        }
    }

    private void expandCapacity(int minimumCapacity) {
        int newCapacity = value.length * 2 + 2;
        if (newCapacity - minimumCapacity < 0) {
            newCapacity = minimumCapacity;
        }
        value = Arrays.copyOf(value, newCapacity);;
    }

    public MyStringBuilder append(String str) {
        if (str.equals(null)) {
            throw new IllegalArgumentException("Input string cannot be null");
        }
        int newCount = count + str.length();
        ensureCapacity(newCount); // Увеличиваем емкость, если нужно

        // Копируем символы из строки в массив value
        str.getChars(0, str.length(), value, count);
        count = newCount;
        return this;
    }

    public MyStringBuilder append(char[] chars) {
        if (chars.equals(null)) {
            throw new IllegalArgumentException("Input char array cannot be null");
        }
        int newCount = count + chars.length;
        ensureCapacity(newCount); // Увеличиваем емкость, если нужно

        // Копируем символы из строки в массив value
        System.arraycopy(chars, 0, value, count, chars.length);
        count = newCount;
        return this;
    }

    public MyStringBuilder insert(int index, String str) {
        if (index < 0 || index > count) {
            throw new StringIndexOutOfBoundsException(index);
        }

        if (str.equals(null)) {
            throw new IllegalArgumentException("Input string cannot be null");
        }

        int len = str.length();
        int newCount = count + len;
        ensureCapacity(newCount); // Увеличиваем емкость, если нужно

        // Сдвигаем элементы вправо, освобождая место для вставки
        System.arraycopy(value, index, value, index + len, count - index);

        // Копируем символы из str в массив value
        str.getChars(0, len, value, index);
        count = newCount; // Обновляем текущую длину строки
        return this;
    }


    public MyStringBuilder insert(int index, char[] chars) {
        if (index < 0 || index > count) {
            throw new StringIndexOutOfBoundsException(index);
        }

        if (chars.equals(null)) {
            throw new IllegalArgumentException("Input char[] cannot be null");
        }

        int len = chars.length;
        int newCount = count + len;
        ensureCapacity(newCount); // Увеличиваем емкость, если нужно

        // Сдвигаем элементы вправо, освобождая место для вставки
        System.arraycopy(value, index, value, index + len, count - index);

        // Копируем символы из chars в массив value
        System.arraycopy(chars, 0, value, index, len);
        count = newCount;
        return this;
    }

    public MyStringBuilder delete(int start, int end) {
        if (start < 0 || end < start || end > count) {
            throw new StringIndexOutOfBoundsException("Invalid start or end index");
        }

        int lengthToDelete = end - start + 1;
        if (lengthToDelete > 0) {
            // Сдвигаем элементы, чтобы удалить символы
            System.arraycopy(value, end + 1, value, start, count - end);
            count -= lengthToDelete;
        }

        return this;
    }

    /**
     * Удаляет подстроку с началом в индексе start и коноцом в индексе end
     * Вставляет на место с индексом start переданную строку
     * @param  start  начало подстроки для замены (включительно)
     * @param  end конец подстроки для замены (включительно)
     * @param  str строка для вставки
     */
    public MyStringBuilder replace(int start, int end, String str) {

        if (start < 0 || end < start || end > count) {
            throw new StringIndexOutOfBoundsException("Invalid start or end index");
        }


        if (str.equals(null)) {
            throw new IllegalArgumentException("Input string cannot be null");
        }

        int len = str.length();
        int newCount = count - (end - start + 1) + len;
        ensureCapacity(newCount);

        delete(start, end);
        insert(start, str);

        count = newCount;
        return this;
    }

    public int indexOf(String str, int index) {

        if (index < 0 || index >= count) {
            return -1;
        }

        if (str == null || str.length() == 0) {
            return -1;
        }

        int strLength = str.length();
        for (int i = index; i <= count - strLength; i++) {
            if (equalsSubstring(i, str)) {
                return i; // Если нашли совпадение, возвращаем индекс
            }
        }
        return -1; // Если не нашли подстроку, возвращаем -1
    }

    // Вспомогательный метод для сравнения подстроки
    private boolean equalsSubstring(int startIndex, String str) {
        if (str.length() == 0) return false;

        for (int j = 0; j < str.length(); j++) {
            if (value[startIndex + j] != str.charAt(j)) {
                return false; // Если символы не совпадают, возвращаем false
            }
        }
        return true; // Все символы совпадают
    }

    public String substring(int start, int end) {
        if (start < 0 || end < start || end > count) {
            throw new StringIndexOutOfBoundsException("Invalid start or end index");
        }

        int length = end - start;
        char[] result = new char[length];
        System.arraycopy(value, start, result, 0, length);

        return new String(result);
    }

    public MyStringBuilder reverse() {

        int left = 0;
        int right = count - 1;

        while (left < right) {

            char temp = value[left];
            value[left] = value[right];
            value[right] = temp;

            left++;
            right--;
        }

        return this;
    }

    @Override
    public String toString() {
        return new String(value, 0, count);
    }

    public int length() {
        return count;
    }

    public int capacity() {
        return value.length;
    }

    @Override
    public int compareTo(MyStringBuilder other) {
        if (other == null) {
            return 1; // Текущий объект больше, чем null
        }

        return equalsSubstring(0, other.toString()) ? 0 :
                (count == other.count ? value[0] - other.value[0] : count - other.count);

    }

    public class Memento {

        private final char[] value;

        private final int count;

        public Memento(char[] value, int count) {
            this.value = value;
            this.count = count;
        }

        public char[] getValue() {
            return value;
        }

        public int getCount(){
            return count;
        }
    }

    public Memento save(){
        return new Memento(Arrays.copyOf(value, value.length) , count);
    }

    public MyStringBuilder restore(Memento memento){
        value = memento.getValue();
        count = memento.getCount();

        return this;
    }



}
