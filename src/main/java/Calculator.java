import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Deque;

public class Calculator {

    private Deque<Operator> undo = new FILO<>(10);
    private Deque<Operator> redo = new FILO<>(10);
    private BigDecimal result = new BigDecimal(0);



    public Calculator add(double num) {
        BigDecimal augend = new BigDecimal(num);

        // 入undo栈，并置空redo栈
        undo.push(new Add(result,augend));
        redo.clear();

        // 加
        result = result.add(augend);
        return this;
    }

    public Calculator subtract(double num) {
        BigDecimal subtrahend = new BigDecimal(num);

        // 入undo栈，并置空redo栈
        undo.push(new Subtract(result,subtrahend));
        redo.clear();

        // 减
        result = result.subtract(subtrahend);
        return this;
    }


    public Calculator multiply(double num) {

        BigDecimal multiplicand = new BigDecimal(num);

        // 先入undo栈，并置空redo栈
        undo.push(new Multiply(result,multiplicand));
        redo.clear();

        // 乘
        result = result.multiply(multiplicand);

        return this;
    }


    public Calculator divide(double num) {
        if (num == 0) {
            System.err.println("0无法作为除数");
            return this;
        }
        BigDecimal divisor = new BigDecimal(num);

        // 入undo栈，并置空redo栈
        undo.push(new Divide(result,divisor));
        redo.clear();

        // 除以
        result = result.divide(divisor);
        return this;
    }

    public Calculator undo() {
        if (undo.size() == 0) {
            System.err.println("undo栈为空，无法undo");
            return this;
        }
        Operator operation = undo.pop();
        redo.push(operation);
        this.result = operation.result;
        System.err.println("undo:["+operation.toString()+"]");
        return this;
    }


    public Calculator redo() {
        if (redo.size() == 0) {
            System.err.println("redo栈为空，无法redo");
            return this;
        }
        // 根据业务场景，不重放动作，直接覆盖结果，减少CPU消耗
        Operator operation = redo.pop();
        undo.push(operation);
        this.result = operation.result;
        System.err.println("redo:["+operation.toString()+"]");
        return this;
    }

    public double getResult() {
        return result.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.add(2).add(3.0);

        System.err.println("相加结果（预期为5.0）实际："+calculator.getResult());

        calculator.redo();
        calculator.undo();
        System.err.println("undo结果（预期为2.0）实际："+calculator.getResult());

        calculator.multiply(10);
        System.err.println("相乘结果（预期为20.0）实际："+calculator.getResult());

        calculator.divide(4);
        System.err.println("相乘结果（预期为5.0）实际："+calculator.getResult());

        calculator.undo();
        System.err.println("undo第一次结果（预期为20.0）实际："+calculator.getResult());
        calculator.undo();
        System.err.println("undo第二次结果（预期为2.0）实际："+calculator.getResult());
        calculator.redo();
        System.err.println("redo第一次结果（预期为2.0）实际："+calculator.getResult());
        calculator.redo();
        System.err.println("redo第二次结果（预期为20.0）实际："+calculator.getResult());

    }
}



