package dateStructures.stack;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        /**
         * 测试
         */
        ArrayStack arrayStack = new ArrayStack(10);
        for (int i = 0; i < 10; i++) {
            arrayStack.push(i);
        }

        try {
            arrayStack.push(10);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        arrayStack.list();

        for (int i = 0; i < 10; i++) {
            System.out.println("出栈的元素为: " + arrayStack.pop());
        }

        try {
            arrayStack.list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}


class ArrayStack {
    private int maxSize;//栈的最大容量
    private int[] stack;
    private int top = -1;//栈顶

    public ArrayStack(int maxSize) {
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
}
