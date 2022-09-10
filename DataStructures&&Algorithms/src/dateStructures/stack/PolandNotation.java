package dateStructures.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class PolandNotation {
    public static void main(String[] args) {
        //expression = 1+((2+3)*4)-5
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        //System.out.println(infixExpressionList);
        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        //System.out.println(suffixExpressionList);
        int res = calculator(suffixExpressionList);
        System.out.printf("%s = %d",expression,res);
    }


    /**
     * 功能:将中缀表达式存入list容器
     *
     * @param expression 中缀表达式
     * @return list<String></String>
     */
    public static List<String> toInfixExpressionList(String expression) {
        List<String> list = new ArrayList<>();
        int index = 0;//用于遍历
        String str = "";//处理多位数
        char ch = ' ';//存放遍历的每一位
        do {
            if ((ch = expression.charAt(index)) < 48
                    || (ch = expression.charAt(index)) > 57) {//非数字
                list.add("" + ch);
                index++;
            } else {
                str = "";//置空
                while (index < expression.length()
                        && (ch = expression.charAt(index)) >= 48
                        && (ch = expression.charAt(index)) <= 57) {
                    str += ch;
                    index++;
                }
                list.add(str);
            }
        } while (index < expression.length());
        return list;
    }

    /**
     * 功能:将中缀表达式转成后缀表达式
     *
     * @param infixExpressionList 中缀表达式容器
     * @return List<String></String>
     */
    public static List<String> parseSuffixExpressionList(List<String> infixExpressionList) {
        Stack<String> s1 = new Stack<>();//符号栈
        List<String> s2 = new ArrayList<>();//存放结果

        //遍历infixExpressionList
        for (String item : infixExpressionList) {
            if (item.matches("\\d+")) {
                //是数字,直接加入s2
                s2.add(item);
            } else if ("(".equals(item)) {
                //左括号直接加入s1
                s1.add(item);
            } else if (")".equals(item)) {
                //如果是右括号")",则依次弹出s1栈顶的运算符压入s2,直到遇到左括号为止,此时将这一对括号丢弃
                while (!"(".equals(s1.peek())) {
                    s2.add(s1.pop());
                }
                s1.pop();//处理掉左括号
            } else {//运算符
                //优先级比栈顶元素高,也将运算符入栈s1
                while (s1.size() != 0 && Operation.getValue(s1.peek()) > Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                //item入库
                s1.add(item);
            }
        }
        //剩余的运算符压入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }

    /**
     * 功能:将逆波兰表达式存入list容器
     *
     * @param suffixExpression 逆波兰表达式
     * @return list<String></String>
     */
    public static List<String> getListString(String suffixExpression) {
        //对suffixExpression进行分割
        String[] s = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String str : s) {
            list.add(str);
        }
        return list;
    }

    /**
     * 功能:完成对逆波兰表达式的计算
     *
     * @param list 存放逆波兰表达式的list容器
     * @return int
     */
    public static int calculator(List<String> list) {
        //创建一个栈存放数字
        Stack<String> stack = new Stack<>();
        int num1, num2, res;//临时变量
        //遍历list
        for (String item : list) {
            if (item.matches("\\d+")) {//如果匹配数字
                stack.push(item);
            } else {//是操作符
                num1 = Integer.parseInt(stack.pop());
                num2 = Integer.parseInt(stack.pop());
                if ("+".equals(item)) {
                    res = num2 + num1;
                } else if ("-".equals(item)) {
                    res = num2 - num1;
                } else if ("*".equals(item)) {
                    res = num2 * num1;
                } else if ("/".equals(item)) {
                    res = num2 / num1;
                } else {
                    throw new RuntimeException("操作符有误");
                }
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}

/**
 * 运算符优先级类
 */
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            case "(":
                break;
            default:
                System.out.println("运算符出错...");
        }
        return result;
    }
}