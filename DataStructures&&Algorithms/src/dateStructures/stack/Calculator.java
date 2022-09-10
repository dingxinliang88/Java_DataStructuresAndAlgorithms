package dateStructures.stack;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class Calculator {
    public static void main(String[] args) {
        String expression = "38+2*5-8/2";
        ArrayStackforCal numStack = new ArrayStackforCal(10);
        ArrayStackforCal operStack = new ArrayStackforCal(10);
        int index = 0;//用于扫描
        int num1, num2, oper, res;
        char ch = ' ';//存放扫描的结果
        String keepNum = "";
        while (true) {
            ch = expression.substring(index, index + 1).charAt(0);
            if (operStack.isOper(ch)) {//是操作符
                if (!operStack.isEmpty()) {//不为空
                    //判断优先级
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        //计算
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //结果入数栈
                        numStack.push(res);
                    }
                    //当前操作符入栈
                    operStack.push(ch);
                } else {//为空
                    operStack.push(ch);
                }
            } else {//是数字
                //numStack.push(ch - 48);//字符转成数字
                //处理多位数
                keepNum += ch;
                //ch是最后一个字符
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //往后多扫描一位
                    //是操作符
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        //keepNum清空
                        keepNum = "";
                    }
                }
            }
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        while (!operStack.isEmpty()) {
            //计算
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            //结果入数栈
            numStack.push(res);
        }
        System.out.printf("%s = %d", expression, numStack.pop());
    }
}


class ArrayStackforCal {
    private int maxSize;//栈的最大容量
    private int[] stack;
    private int top = -1;//栈顶

    public ArrayStackforCal(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    /**
     * 功能:判断栈是否满
     *
     * @return 标志
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 功能:判断栈是否为空
     *
     * @return 标志
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 功能:入栈
     *
     * @param num 待入栈数据
     */
    public void push(int num) {
        if (isFull()) {
            throw new RuntimeException("栈满...");
        }
        stack[++top] = num;
    }

    /**
     * 功能:出栈
     *
     * @return 栈顶元素
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空...");
        }
        return stack[top--];
    }

    /**
     * 功能:显示栈顶元素
     *
     * @return int
     */
    public int peek() {
        return stack[top];
    }

    /**
     * 功能:显示栈的信息
     */
    public void list() {
        if (isEmpty()) {
            throw new RuntimeException("栈空...");
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d\n", i, stack[i]);
        }
    }

    /**
     * 功能:判断运算符优先级
     *
     * @param oper 运算符
     * @return 数字(越大表示优先级更高)
     */
    public int priority(int oper) {
        if (oper == '(') {
            return 2;
        } else if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        }
        return -1;//目前只有 + - * /
    }

    /**
     * 功能:判断是否是运算符
     *
     * @param oper 待判断
     * @return boolean
     */
    public boolean isOper(char oper) {
        return oper == '*' || oper == '/' || oper == '+' || oper == '-';
    }

    /**
     * 功能:计算函数
     *
     * @param num1 计算数
     * @param num2 计算数
     * @param oper 运算符
     * @return int
     */
    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
        }

        return res;
    }
}


