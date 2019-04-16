package algorithm.baseAlgorithm1;

import java.util.Stack;

/**
 * Dijkstra的双栈算术表达式求值算法
 * Created by llq on 2018/5/29.
 */
public class DijkstraEvaluate {

    /**
     * 两个栈：ops是算术符号栈。vals是变量栈
     */
    public static void evaluate(String[] sArray){
        Stack<String> ops=new Stack<>();
        Stack<Double> vals=new Stack<>();
        for(String s:sArray) {
            if (s.equals("(")) {
            } else if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("sqrt")) {
                ops.push(s);
            }
            //遇到右括号，把数据拿出来进行相乘。
            else if (s.equals(")")) {
                String op = ops.pop();
                Double val = vals.pop();
                if (op.equals("+")) {
                    val = vals.pop() + val;
                } else if (op.equals("-")) {
                    val = vals.pop() - val;
                } else if (op.equals("*")) {
                    val = vals.pop() * val;
                } else if (op.equals("/")) {
                    val = vals.pop() / val;
                } else if (op.equals("sqrt")) {
                    val = Math.sqrt(val);
                }
                vals.push(val);
            } else {
                vals.push(Double.parseDouble(s));
            }
        }
        System.out.println(vals.pop());
    }

    public static void main(String[] args){
        String s="( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )";
        String[] sArray=s.split(" ");
        evaluate(sArray);
    }
}
