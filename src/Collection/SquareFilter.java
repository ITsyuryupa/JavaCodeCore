package Collection;

public class SquareFilter implements Filter<Integer> {
    @Override
    public Integer apply(Integer num) {
        return num * num;
    }
}
