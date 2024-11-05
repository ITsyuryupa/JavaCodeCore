package MyStringBuilder;

import MyStringBuilder.MyStringBuilder.Memento;
import MyStringBuilder.MyStringBuilder;
import java.util.Stack;

public class MementoStorage {

    private final MyStringBuilder myStringBuilder;
    private final Stack<Memento> history;

    public MementoStorage(MyStringBuilder myStringBuilder) {
        this.myStringBuilder = myStringBuilder;
        history = new Stack<>();
    }


    public void save(){
        Memento memento = myStringBuilder.save();
        history.push(memento);
    }

    public void undo() {
        if (!history.isEmpty()) {
            Memento memento = history.pop();
            myStringBuilder.restore(memento);
        } else {
            System.out.println("No states to undo.");
        }
    }

}
