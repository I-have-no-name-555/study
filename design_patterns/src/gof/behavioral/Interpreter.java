package gof.behavioral;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

/**
 * @author :Xuan
 * @date :Create in 2020/12/20 17:53
 * @description 解释器
 */
public class Interpreter {
    /*
        Spring中的解释器模式：SpelExpressionParser
        解释器模式：
            当有一个语言需要解释执行，可将该语言中的句子表示为一个抽象语法树，使用解释器模式使程序具有良好的扩展性
            应用场景：
                编译器
                运算表达式计算
                正则表达式
                机器人等
            问题：
                引起类膨胀
                递归导致调试复杂、效率较低
     */
}
abstract class Expression{
    public abstract int interpreter(HashMap<String , Integer> var);
}
class VarExpression extends Expression{
    private String key;

    public VarExpression(String key) {
        this.key = key;
    }

    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return var.get(this.key);
    }
}
class SymbolExpression extends Expression{
    protected Expression left;
    protected Expression right;

    public SymbolExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return 0;
    }
}
class SubExpression extends SymbolExpression{
    public SubExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return super.left.interpreter(var) - super.right.interpreter(var);
    }
}
class AddExpression extends SymbolExpression{
    public AddExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return super.left.interpreter(var) + super.right.interpreter(var);
    }
}
class Calculator{
    private Expression expression;

    public Calculator(String expStr) {
        Stack<Expression> stack = new Stack<>();
        char[] charArray = expStr.toCharArray();
        Expression left = null;
        Expression right = null;
        for (int i = 0; i < charArray.length; i++) {
            switch (charArray[i]){
                case '+':
                    left = stack.pop();
                    right = new VarExpression(String.valueOf(charArray[++i]));
                    stack.push(new AddExpression(left,right));
                    break;
                case '-':
                    left = stack.pop();
                    right = new VarExpression(String.valueOf(charArray[++i]));
                    stack.push(new SubExpression(left,right));
                    break;
                default:
                    stack.push(new VarExpression(String.valueOf(charArray[i])));
                    break;
            }
        }
        this.expression = stack.pop();
    }
    public int run(HashMap<String , Integer> var){
        return this.expression.interpreter(var);
    }
}
class CalculatorClient{
    public static void main(String[] args) throws Exception{
        String expStr = getExpStr();
        HashMap<String ,Integer> var = getValue(expStr);
        Calculator calculator = new Calculator(expStr);
        System.out.println(expStr + " = " + calculator.run(var));
    }
    public static String getExpStr() throws IOException{
        System.out.print("输入表达式:");
        return new BufferedReader(new InputStreamReader(System.in)).readLine();
    }
    public static HashMap<String , Integer> getValue(String expStr) throws IOException{
        HashMap<String , Integer> map = new HashMap<>();
        for (char ch : expStr.toCharArray()) {
            if (ch != '+' && ch != '-') {
                if (!map.containsKey(String.valueOf(ch))) {
                    System.out.print("请输入" + ch + "的值:");
                    String in = (new BufferedReader(new InputStreamReader(System.in))).readLine();
                    map.put(String.valueOf(ch), Integer.valueOf(in));
                }
            }
        }
        return map;
    }
}