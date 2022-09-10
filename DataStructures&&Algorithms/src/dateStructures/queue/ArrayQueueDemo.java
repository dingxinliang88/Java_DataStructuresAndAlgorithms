package dateStructures.queue;

import java.util.Scanner;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        /**
         * 测试
         */
        int maxSize = 3;
        ArrayQueue arrayQueue = new ArrayQueue(maxSize);
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        char key = ' ';
        int item = 0;
        System.out.println("=====测试数组模拟队列=====");
        System.out.println("a(addQueue):添加数据");
        System.out.println("g(getQueue):获取元素");
        System.out.println("p(peep):显示队头元素");
        System.out.println("s(showQueue):显示队列信息");
        System.out.println("e(exit):退出");


        while(loop){
            System.out.print("请输入您的选择:");
            key = scanner.next().toLowerCase().charAt(0);
            switch (key){
                case 'a':
                    System.out.print("请输入您要添加的数据");
                    item = scanner.nextInt();
                    try {
                        arrayQueue.addQueue(item);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try {
                        arrayQueue.getQueue();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'p':
                    try {
                        arrayQueue.peep();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 's':
                    try {
                        arrayQueue.showQueue();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    loop = false;
                    scanner.close();
                    break;
            }
        }

        System.out.println("退出测试系统...");
    }
}

/**
 * 数组模拟队列
 */
class ArrayQueue {
    private int maxSize;    //对列最大容量
    private int front;      //头指针
    private int rear;       //尾指针
    private int[] arr;      //模拟队列

    /**
     * 初始化模拟队列
     * @param maxSize   队列最大元素个数
     */
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        front = -1;//指向队列头部(队头前一个位置[不包含])
        rear = -1;//指向队列尾部(最后一个位置[包含])
        arr = new int[maxSize];//创建队列
    }

    /**
     * 功能:判断队列是否为空
     *
     * @return  标志
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 功能:判断对列是否满
     *
     * @return  标志
     */
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    /**
     * 功能:入队
     *
     * @param item 待入队元素
     */
    public void addQueue(int item) {
        if (isFull()) {
            throw new RuntimeException("队列已满,无法添加数据...");
        }
        arr[++rear] = item;
    }

    /**
     * 功能:出队
     *
     * @return 队头元素
     */
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空,无法出队...");
        }
        return arr[++front];
    }

    /**
     * 功能:显示队列信息
     */
    public void showQueue() {
        if (isEmpty()) {
            throw new RuntimeException("对列为空,无法显示信息");
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d] = %d\n", i, arr[i]);
        }
    }

    /**
     * 功能:显示队头信息
     *
     * @return 队头元素
     */
    public int peep() {
        if (isEmpty()) {
            throw new RuntimeException("对列为空,无法显示队头信息");
        }

        return arr[front + 1];
    }
}
