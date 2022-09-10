package dateStructures.linkedlist;

import java.util.Stack;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        /**
         * 测试
         */
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);

        singleLinkedList1.addByOrder(hero1);
        singleLinkedList1.addByOrder(hero3);
        singleLinkedList2.addByOrder(hero2);
        singleLinkedList2.addByOrder(hero4);

//        singleLinkedList1.list();
//        singleLinkedList2.list();


        SingleLinkedList singleLinkedList3 = mergeLinkedList(singleLinkedList1, singleLinkedList2);
        singleLinkedList3.list();

        System.out.println("=============逆序输出=================");
        reversePrintLinkedList(singleLinkedList.getHeadNode());

//        System.out.println("=========反转前============");
//        singleLinkedList.list();
//
//        reverseLinkedList(singleLinkedList.getHeadNode());
//
//        System.out.println("=========反转后============");
//        singleLinkedList.list();
//        //singleLinkedList.addByOrder(hero2);
//        System.out.println("============================");
//        HeroNode hero5 = new HeroNode(5, "卢俊义", "yuqiling");
//        //singleLinkedList.update(hero5);
//        singleLinkedList.addByOrder(hero5);
//        singleLinkedList.list();
//        singleLinkedList.delete(5);
//        System.out.println("=================================");
//        singleLinkedList.list();
//        System.out.println(getLength(singleLinkedList.getHeadNode()));
//        System.out.println(getLastIndexNode(singleLinkedList.getHeadNode(), 2));

    }

    /**
     * 功能:获取到单链表有效节点的个数
     * 注意:如果有头节点,头节点不计入
     *
     * @param headNode 单链表的第一个节点
     * @return 单链表有效节点的个数
     */
    public static int getLength(HeroNode headNode) {
        if (headNode.next == null) {//空链表
            return 0;
        }
        HeroNode temp = headNode.next;
        int length = 0;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    /**
     * 功能:返回链表中的倒数第index个节点
     * <p>
     * 思路:
     * 1. 遍历单链表,得到有效节点的个数
     * 2. 第二次遍历,找到length - size的位置
     *
     * @param headNode 单链表的头节点
     * @param index    倒数的元素的下标
     * @return 找到的节点
     */
    public static HeroNode getLastIndexNode(HeroNode headNode, int index) {
        if (headNode.next == null) {//链表为空
            return null;
        }
        int length = getLength(headNode);
        //校验index的合理性
        if (index <= 0 || index > length) {
            return null;
        }
        HeroNode temp = headNode.next;
        //第二次遍历
        for (int i = 0; i < length - index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 功能:反转单链表
     * <p>
     * 思路:
     * 1. 定义一个新(头)节点 reverseNode;
     * 2. 从头到尾遍历原来的单链表,每遍历一个节点,就将其取出,挂在reverseNode的后面
     * 3. headNode.next = reverseNode.next
     *
     * @param headNode 待反转链表的头节点
     */
    public static void reverseLinkedList(HeroNode headNode) {
        // 链表为空 || 链表只有一个节点
        if (headNode.next == null || headNode.next.next == null) {
            return;
        }
        HeroNode reverseNode = new HeroNode(0, "", "");
        HeroNode cur = headNode.next;
        HeroNode next = null;//指向cur的下一个位置

        //遍历节点
        while (cur != null) {
            next = cur.next;
            cur.next = reverseNode.next;
            reverseNode.next = cur;
            cur = next;
        }
        //改变原头节点的指向
        headNode.next = reverseNode.next;
    }

    /**
     * 功能:逆序打印单链表
     * <p>
     * 思路:
     * 使用栈先入后出的特点
     *
     * @param headNode 待逆序输出链表的头节点
     */
    public static void reversePrintLinkedList(HeroNode headNode) {
        if (headNode.next == null) {
            //空链表
            System.out.println("链表为空,无法打印...");
            return;
        }
        Stack<HeroNode> heroNodes = new Stack<>();
        HeroNode cur = headNode.next;

        while (cur != null) {
            heroNodes.push(cur);//压栈
            cur = cur.next;
        }

        while (heroNodes.size() > 0) {
            System.out.println(heroNodes.pop());//出栈
        }
    }

    /**
     * 功能:合并两个有序列表
     *
     * 思路
     *  双指针
     * @param list1 待合并链表1
     * @param list2 待合并链表2
     * @return  合并后的链表
     */
    public static SingleLinkedList mergeLinkedList(SingleLinkedList list1, SingleLinkedList list2) {
        if(list1 == null){
            return list2;
        }

        if(list2 == null){
            return list1;
        }
        SingleLinkedList mergeLinkedList = new SingleLinkedList();//创建新的链表
        HeroNode headNode = mergeLinkedList.getHeadNode();
        HeroNode preNode = headNode;//指向待排序节点的前一个节点

        HeroNode headNode1 = list1.getHeadNode();
        HeroNode headNode2 = list2.getHeadNode();
        HeroNode cur1 = headNode1.next;
        HeroNode cur2 = headNode2.next;

        //遍历两个链表
        while (cur1 != null && cur2 != null) {
            if (cur1.getNo() < cur2.getNo()) {
                preNode.next = cur1;
                cur1 = cur1.next;
            } else if (cur1.getNo() > cur2.getNo()) {
                preNode.next = cur2;
                cur2 = cur2.next;
            } else if (cur1.getNo() == cur2.getNo()) {
                preNode.next = cur1;
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            //preNode指针后移
            preNode = preNode.next;
        }
        //处理未遍历完的节点
        preNode.next = (cur1 == null) ? cur2 : cur1;

        return mergeLinkedList;
    }
}


class SingleLinkedList {
    //头节点
    private HeroNode headNode = new HeroNode(0, "", "");

    public HeroNode getHeadNode() {
        return headNode;
    }


    /**
     * 功能:添加节点到链表
     * <p>
     * 思路:
     * 1. 头节点不能动 --> 需要一个辅助变量辅助遍历单链表
     * 2. 找到单链表的尾节点,将新的节点挂在尾节点之后
     *
     * @param heroNode 待加入节点
     */
    public void add(HeroNode heroNode) {
        HeroNode temp = headNode;

        //遍历单链表,找到尾节点
        //当退出while循环时,temp指向尾节点
        while (temp.next != null) {
            temp = temp.next;
        }
        //将新的节点挂在尾节点之后即可
        temp.next = heroNode;
    }

    /**
     * 功能:按照指定的顺序插入,若编号已然存在,添加失败
     * <p>
     * 思路:
     * 1. 头节点不能动 --> 需要一个辅助变量辅助遍历单链表
     * 2. 待插入的节点位置 --> temp节点的后一个位置
     * 3. 一个布尔变量 --> 标识待插入的节点的编号是否存在,默认值为false
     *
     * @param heroNode 待插入的节点
     */
    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = headNode;
        boolean flag = false;
        //遍历单链表,确定插入节点的位置
        while (temp.next != null) {
            if (temp.next.getNo() > heroNode.getNo()) {
                //找到位置
                break;
            } else if (temp.next.getNo() == heroNode.getNo()) {
                //编号已然存在
                flag = true;
                break;
            }
            //temp后移
            temp = temp.next;
        }

        if (flag) {
            System.out.printf("编号%d已存在,无法添加...\n", heroNode.getNo());
        } else {
            //将待插入节点挂在temp的后一个位置
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /**
     * 功能:修改节点信息(除编号外)
     * <p>
     * 思路:
     * 1. 头节点不能动 --> 需要一个辅助变量辅助遍历单链表
     * 2. 判断单链表是否为空
     * 3. 一个布尔变量 --> 标识是否找到待修改的节点
     *
     * @param newHeroNode 新节点信息
     */
    public void update(HeroNode newHeroNode) {
        HeroNode temp = headNode.next;
        boolean flag = false;
        //判断链表是否为空
        if (headNode.next == null) {
            System.out.println("链表为空,无法修改...");
            return;
        }
        //遍历链表
        while (temp != null) {
            if (temp.getNo() == newHeroNode.getNo()) {
                //找到了待修改的节点
                flag = true;
                break;
            }
            //temp后移
            temp = temp.next;
        }

        if (!flag) {
            System.out.printf("编号%d的节点不存在,无法修改...\n", newHeroNode.getNo());
        } else {
            //修改节点信息
            temp.setName(newHeroNode.getName());
            temp.setNickName(newHeroNode.getNickName());
        }
    }

    /**
     * 功能:删除指定节点
     * <p>
     * 思路:
     * 1. 头节点不能动 --> 需要一个辅助变量辅助遍历单链表
     * 2. 需要找到待删除节点的前一个节点
     * 3. 一个布尔变量 --> 标识是否找到待删除节点
     *
     * @param no 待删除节点的编号
     */
    public void delete(int no) {
        HeroNode temp = headNode;
        boolean flag = false;

        while (temp.next != null) {
            if (temp.next.getNo() == no) {
                //找到了待删除节点
                flag = true;
                break;
            }
            //temp后移
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("未找到编号为%d的节点,无法删除", no);
        }
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
        HeroNode temp = headNode.next;

        //遍历输出
        //注意:temp后移!!!
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }
}


/**
 * 节点类
 */
class HeroNode {
    private int no;
    private String name;
    private String nickName;
    public HeroNode next;//指向下一个节点

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }


    public int getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
