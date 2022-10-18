import java.math.BigDecimal;
import java.util.LinkedList;

public class FILO<E> extends LinkedList<E> {

    private int depth;

    public FILO(int depth) {
        this.depth = depth;
    }

    @Override
    public void push(E o) {
        if (this.size() >= depth) {
            super.removeLast();
        }
        super.push(o);
    }

    public static void main(String[] args) {
        FILO<Integer> filo = new FILO(4);
        filo.push(1);
        filo.push(2);
        filo.push(3);
        filo.push(4);
        System.err.println(filo.pop());
        System.err.println(filo.pop());
        System.err.println(new BigDecimal(0).divide(new BigDecimal(1)));
    }
}
