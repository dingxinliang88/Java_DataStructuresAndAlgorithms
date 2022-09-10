package dateStructures.linkedlist;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class Josepfu {
    public static void main(String[] args) {
        /**
         * 测试
         */
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.add(5);
        circleSingleLinkedList.list();
        circleSingleLinkedList.countBoy(2,3,5);

    }
}

class CircleSingleLinkedList {
    private BoyNode first = null;//第一个节点

    /**
     * 功能:添加节点
     * <p>
     * 思路:
     * 1. 对num进行数据合理性校验
     * 2. 创建一个curBoy辅助添加,curBoy指向当前环形队列中最后一次添加的节点
     * 3. 若添加的对象编号是1,则first = boy
     * 4. 其他情况 curBoy.next = boy,boy.next = first构成环形
     * 5. 每次添加完成后curBoy位置变换
     *
     * @param num 待添加节点的个数
     */
    public void add(int num) {
        //校验数据合理性
        if (num < 1) {
            System.out.println("num数据不合理");
            return;
        }

        BoyNode curBoy = null;

        for (int i = 1; i <= num; i++) {
            BoyNode boyNode = new BoyNode(i);
            if (i == 1) {
                first = boyNode;
                first.next = first;//形成环形
            } else {
                curBoy.next = boyNode;
                boyNode.next = first;//形成环形
            }
            curBoy = boyNode;   //指向当前节点
        }
    }

    /**
     * 功能:遍历当前链表
     */
    public void list(){
        //判断链表是否为空
        if(first == null){
            System.out.println("链表为空...");
            return;
        }

        BoyNode curBoy = first;

        while(true){
            System.out.println(curBoy);
            if(curBoy.next == first){
                break;
            }
            curBoy = curBoy.next;
        }
    }

    /**
     * 功能:出圈
     *
     * @param startNo   起始数数节点
     * @param countNo   数数次数
     * @param num   起初节点个数
     */
    public void countBoy(int startNo,int countNo,int num){

        //数据合理性校验
        if(first == null || startNo < 1 || startNo > num){
            System.out.println("参数输入不合理");
            return;
        }

        BoyNode helper = first;
        while(helper.next != first){
            //helper指向当前最后一个节点的位置
            helper = helper.next;
        }

        //数数前,先让first 和 helper 移动startNo - 1 次
        for (int i = 0; i < startNo - 1; i++) {
            first = first.next;
            helper = helper.next;
        }

        //开始报数
        while(helper != first){
            //让helper和first移动countNo-1次
            for (int i = 0; i < countNo - 1; i++) {
                first = first.next;
                helper = helper.next;
            }

            //当前first指向的节点出圈
            System.out.printf("编号为%d的节点出圈\n",first.getNo());
            first = first.next;
            helper.next = first;
        }

        System.out.printf("最后留在圈的节点编号为%d\n",first.getNo());
    }
}


/**
 * 节点类
 */
class BoyNode {
    private int no;
    public BoyNode next;

    public BoyNode(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    @Override
    public String toString() {
        return "BoyNode{" +
                "no=" + no +
                '}';
    }
}