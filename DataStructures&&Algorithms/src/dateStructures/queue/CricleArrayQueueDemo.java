package dateStructures.queue;

import java.util.Scanner;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class CricleArrayQueueDemo {
    public static void main(String[] args) {
        /**
         * 测试
         */
        int maxSize = 4;
        CircleArrayQueue circleArrayQueue = new CircleArrayQueue(maxSize);
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
                        circleArrayQueue.addQueue(item);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try {
                        circleArrayQueue.getQueue();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'p':
                    try {
                        circleArrayQueue.peep();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 's':
                    try {
                        circleArrayQueue.showQueue();
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

class CircleArrayQueue {
    private int maxSize;    //环形对列最大容量
    private int front;      //头指针
    private int rear;       //尾指针
    private int[] arr;      //模拟环形队列

    /**
     * 初始化模拟环形队列
     *
     * @param maxSize 环形队列最大元素个数
     */
    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }


    /**
     * 功能:判断环形队列是否已满
     *
     * @return 标志
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    /**
     * 功能:判断环形队列是否为空
     *
     * @return 标志
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 功能:入队
     * rear:指向队列的最后一个元素的后一个元素
     *
     * @param item 待入队元素
     */
    public void addQueue(int item) {
        if (isFull()) {
            throw new RuntimeException("对列已满,无法添加数据...");
        }
        arr[rear] = item;
        //rear后移
        rear = (rear + 1) % maxSize;
    }

    /**
     * 功能:出队
     * front:指向队列的第一个元素
     *
     * @return 队头元素
     */
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空,无法出队...");
        }
        int item = arr[front];
        //front后移
        front = (front + 1) % maxSize;
        return item;
    }

    /**
     * 功能:显示队列信息
     */
    public void showQueue() {
        if (isEmpty()) {
            throw new RuntimeException("对列为空,无法显示信息");
        }
        //从front开始遍历,下标变动
        for (int i = front; i < front + validDataSize(); i++) {
            System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    /**
     * 功能:返回环形队列中有效数据的个数
     *
     * @return 有效数据个数
     */
    public int validDataSize() {
        return (rear + maxSize - front) % maxSize;
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

        return arr[front];
    }
}
