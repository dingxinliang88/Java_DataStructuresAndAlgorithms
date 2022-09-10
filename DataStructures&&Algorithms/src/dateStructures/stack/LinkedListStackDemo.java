package dateStructures.stack;

import java.util.Stack;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class LinkedListStackDemo {
    public static void main(String[] args) {
        /**
         * 测试
         */
        LinkedListStack linkedListStack = new LinkedListStack();
        for (int i = 0; i < 10; i++) {
            linkedListStack.push(i);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(linkedListStack.pop());
        }
    }
}

class LinkedListStack{
    //创建头节点
    private Node headNode = null;

    /**
     * 功能:入栈
     *
     * @param num   带入栈元素
     */
    public void push(int num){
        Node node = new Node();
        if(headNode == null){
            //第一个节点
            node.setData(num);
            headNode = node;
            return;
        }
        Node cur = headNode;
        while(cur.next != null){
            cur = cur.next;
        }
        node.setData(num);
        cur.next = node;
    }

    /**
     * 功能:出栈
     * @return  栈顶元素
     */
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈为空");
        }
        Node cur = headNode;
        if(cur.next == null){
            int value = cur.getData();
            cur = null;
            return value;
        }
        while(cur.next.next != null){
            cur = cur.next;
        }
        int value = cur.next.getData();
        cur.next = null;
        return value;
    }

    /**
     * 功能:显示栈信息
     */
    public void list(){
        if(isEmpty()){
            throw new RuntimeException("栈空...");
        }
        Node cur = headNode;
        Stack<Node> nodes = new Stack<>();
        while(cur != null){
            nodes.push(cur);
            cur = cur.next;
        }
        while(nodes.size() > 0){
            System.out.println(nodes.pop().getData());
        }
    }

    /**
     * 功能:判断栈是否为空
     * @return  标志
     */
    public boolean isEmpty(){
        return headNode == null;
    }
}

/**
 * 节点类
 */
class Node{
    private int data;
    public Node next;

    public Node() {
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }
}