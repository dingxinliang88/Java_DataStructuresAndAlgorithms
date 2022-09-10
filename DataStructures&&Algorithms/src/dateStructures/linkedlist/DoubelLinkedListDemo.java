package dateStructures.linkedlist;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class DoubelLinkedListDemo {
    public static void main(String[] args) {
        /**
         * 测试
         */
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        Node node1 = new Node(1, "唐玄奘");
        Node node2 = new Node(2, "孙悟空");
        Node node3 = new Node(3, "猪八戒");
        Node node4 = new Node(4, "沙和尚");
        Node node5 = new Node(4, "沙僧");
        doubleLinkedList.addByOrder(node2);
        doubleLinkedList.addByOrder(node1);
        doubleLinkedList.addByOrder(node4);
        doubleLinkedList.addByOrder(node3);
        doubleLinkedList.addByOrder(node3);

        doubleLinkedList.list();

//        System.out.println("================================");
//        doubleLinkedList.update(node5);
//        doubleLinkedList.list();
//
//        System.out.println("=================================");
//        doubleLinkedList.delete(4);
//        doubleLinkedList.list();

    }
}


class DoubleLinkedList {
    private Node headNode = new Node(0, "");

    public Node getHeadNode() {
        return headNode;
    }

    /**
     * 功能:显示单链表信息
     */
    public void list() {
        //判断链表是否为空
        if (headNode.next == null) {
            System.out.println("链表为空...");
            return;
        }
        //创建一个辅助变量辅助遍历
        Node temp = headNode.next;

        //遍历输出
        //注意:temp后移!!!
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    /**
     * 功能:添加节点到链表
     *
     * @param node 待添加节点
     */
    public void add(Node node) {
        Node temp = headNode;

        while (temp.next != null) {
            temp = temp.next;
        }

        //将新节点挂在最后
        temp.next = node;
        node.pre = temp;
    }

    /**
     * 功能:按照指定的顺序插入,若编号已然存在,添加失败
     *
     * @param node 待插入的节点
     */
    public void addByOrder(Node node) {
        Node temp = headNode;
        boolean flag = false;
        //遍历单链表,确定插入节点的位置
        while (temp.next != null) {
            if (temp.next.getNo() > node.getNo()) {
                //找到位置
                break;
            } else if (temp.next.getNo() == node.getNo()) {
                //编号已然存在
                flag = true;
                break;
            }
            //temp后移
            temp = temp.next;
        }

        if (flag) {
            System.out.printf("编号%d已存在,无法添加...\n", node.getNo());
        } else {
            //将待插入节点挂在temp的后一个位置
            if(temp.next!=null){
                //挂的位置是最后一个位置
                temp.next.pre = node;
                node.next = temp.next;
            }
            temp.next = node;
            node.pre = temp;
        }
    }

    /**
     * 功能:修改节点信息(除编号外)
     *
     * @param newNode 新节点信息
     */
    public void update(Node newNode) {
        Node temp = headNode.next;
        boolean flag = false;
        //判断链表是否为空
        if (headNode.next == null) {
            System.out.println("链表为空,无法修改...");
            return;
        }
        //遍历链表
        while (temp != null) {
            if (temp.getNo() == newNode.getNo()) {
                //找到了待修改的节点
                flag = true;
                break;
            }
            //temp后移
            temp = temp.next;
        }

        if (!flag) {
            System.out.printf("编号%d的节点不存在,无法修改...\n", newNode.getNo());
        } else {
            //修改节点信息
            temp.setData(newNode.getData());
        }
    }

    /**
     * 功能:删除指定节点
     *  自我删除
     *
     * @param no 待删除节点的编号
     */
    public void delete(int no) {
        if(headNode.next==null){
            System.out.println("链表为空...");
            return;
        }
        Node temp = headNode.next;
        boolean flag = false;

        while (temp != null) {
            if (temp.getNo() == no) {
                //找到了待删除节点
                flag = true;
                break;
            }
            //temp后移
            temp = temp.next;
        }
        if (flag) {
            //自我删除
            temp.pre.next = temp.next;
            if(temp.next != null){
                //删除的节点不是最后一个节点
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("未找到编号为%d的节点,无法删除", no);
        }
    }
}

/**
 * 节点类
 */
class Node {
    private int no;
    private String data;
    public Node next;//指向后一个节点
    public Node pre;//指向前一个节点

    public Node(int no, String data) {
        this.no = no;
        this.data = data;
    }

    public int getNo() {
        return no;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", data='" + data + '\'' +
                '}';
    }
}