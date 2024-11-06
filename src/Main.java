import MyStringBuilder.MyStringBuilder;
import MyStringBuilder.MementoStorage;

public class Main {
    public static void main(String[] args) {
        StringBuilder s = new StringBuilder();

        MyStringBuilder sb = new MyStringBuilder();
        MementoStorage m = new MementoStorage(sb);

        sb.append("Hello, ");
        char[] worldArray = {'w', 'o', 'r', 'l', 'd', '!'};
        sb.append(worldArray);
        System.out.println(sb);


        m.save();

        sb.delete(1,2);
        System.out.println(sb);

        sb.replace(2,3,"q");
        System.out.println(sb);

        sb.replace(2,2,"h");
        System.out.println(sb);
        m.undo();
        System.out.println(sb);


    }
}