import java.lang.reflect.Array;
import java.util.Arrays;

public class MyStringBuilder {

    char[] value;

    //кол-во символов в строке
    int count;

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
        if (chars == null) {
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
        if (str == null) {
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
        if (chars  == null) {
            throw new IllegalArgumentException("Input char array cannot be null");
        }
        int newCount = count + chars.length;
        ensureCapacity(newCount); // Увеличиваем емкость, если нужно

        // Копируем символы из строки в массив value
        System.arraycopy(chars, 0, value, count, chars.length);
        count = newCount;
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

}
