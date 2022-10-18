import java.math.BigDecimal;

public class Operator {
    protected BigDecimal result;
    protected BigDecimal number;
    protected String action;

    public Operator(BigDecimal result, BigDecimal number, String action) {
        this.result = result;
        this.number = number;
        this.action = action;
    }

    @Override
    public String toString() {
        return "Operator{" +
                "result=" + result +
                ", number=" + number +
                ", action='" + action + '\'' +
                '}';
    }
}

class Add extends Operator {
    public Add(BigDecimal result, BigDecimal number) {
        super(result, number, "Add");
    }
}

class Subtract extends Operator {
    public Subtract(BigDecimal result, BigDecimal number) {
        super(result, number, "Subtract");
    }
}

class Multiply extends Operator {
    public Multiply(BigDecimal result, BigDecimal number) {
        super(result, number, "Multiply");
    }
}

class Divide extends Operator {
    public Divide(BigDecimal result, BigDecimal number) {
        super(result, number, "Divide");
    }
}