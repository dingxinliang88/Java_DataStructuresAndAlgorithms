package dateStructures;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class Test {
}
//定义BinaryTree
//实现了线索化功能的二叉树
class BinaryTree{
    private DataNode root;
    //为了实现线索化，需要定义一个指向当前节点的前驱节点的指针
    private DataNode pre=null;

    public void setPre(DataNode pre) {
        this.pre = pre;
    }

    public DataNode getPre() {
        return pre;
    }

    public void setRoot(DataNode root) {
        this.root = root;
    }
    public  void threadedNodes(){
        threadedNodes(this.root);
    }
    public  void threadedPostNodes(){
        threadedPostNodes(this.root);
    }
    public  void threadPreNodes(){
        threadedPreNodes(this.root);
    }

    //前序线索遍历二叉树的方法
    public void threadedPreList(){
        //定义节点，存储当前遍历的节点，初始为root；
        DataNode node=root;
        while(node!=null){
            while (node.getLeftType()==0){
                System.out.println(node);
                node=node.getLeft();
            }
            System.out.println(node);
            node=node.getRight();
        }
    }
    //中序遍历线索化二叉树的方法
    public void threadMidList(){
        //定义一个节点，存储当前遍历的节点，初始时为root
        DataNode node=root;
        while(node!=null){
            //找到lefttype==1的节点，第一个就是8节点
            //后面随着遍历node会变化，因为当lefttype==1时，说明当前节点是按照线索化处理后的有效节点
            while (node.getLeftType()==0){
                node=node.getLeft();
            }
            System.out.println(node);
            //如果当前节点的右指针指的是后继节点
            while (node.getRightType()==1){
                //获取当前节点的后继节点
                node=node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的节点
            node=node.getRight();

        }
    }
    //后序遍历线索化二叉树的方法

    /**
     * 1.若节点是二叉树的根，则其后继节点为空
     * 2.若节点是其双亲的右孩子，或是其双亲的左孩子，且其双亲没有右子树，则其后继节点为其双亲节点
     * 3.若节点是其双亲的左孩子，且其双亲有右子树，则其节点为双亲右子树上，按后序遍历列出的第一个节点
     */
    public void ThreadedPostList(){
        //定义一个节点存储遍历的节点，初始化时间为root
        DataNode node=root;
        while(node!=null){
            while(node.getLeftType()==0){
                node=node.getLeft();
            }
            System.out.println(node);
            //处理后继节点
            while(node.getRightType()==1){
                node=node.getRight();
                System.out.println(node);
            }
            if(node==root){
                node=null;
            }else if(node==node.getParent().getRight()
                    || (node==node.getParent().getLeft() && node.getParent().getRight()==null)){
                node=node.getParent();
            }else if(node==node.getParent().getLeft() && node.getParent().getRight()!=null){
                List<DataNode> dataNodes=new ArrayList();
                node.getParent().getRight().postOrderList(dataNodes);
                node=dataNodes.get(0);
            }

        }

    }
    //前序线索化
    public void threadedPreNodes(DataNode node){
        if(node==null){
            return;
        }
        if(node.getLeft()==null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if(pre!=null && pre.getRight()==null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre=node;
        if(node.getLeftType()==0){
            threadedPreNodes(node.getLeft());
        }
        if(node.getRightType()==0){
            threadedPreNodes(node.getRight());
        }


    }
    //中序线索化
    public  void threadedNodes(DataNode node){
        if(node==null){
            return;
        }
        //一：先线索化左子树
        threadedNodes(node.getLeft());
        //二：线索化当前节点
        //处理当前节点的前驱节点
        if(node.getLeft()==null){
            //让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针的类型
            node.setLeftType(1);
        }
        //处理后继节点
        if(pre!=null && pre.getRight()==null){
            //让前驱节点的右指针指向当前节点
            pre.setRight(node);
            //修改前驱节点的右指针类型
            pre.setRightType(1);
        }
        //更新pre
        pre=node;
        //三：线索化右子树
        threadedNodes(node.getRight());
    }
    //后序线索化
    public void threadedPostNodes(DataNode node){
        if(node==null){
            return;
        }
        //线索化左子树
        threadedPostNodes(node.getLeft());
        //线索化右子树
        threadedPostNodes(node.getRight());
        //处理当前节点
        if(node.getLeft()==null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        //处理后继节点
        if(pre!=null && pre.getRight()==null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre=node;
    }
    //前序遍历
    public void preOrder(){
        if(this.root!=null){
            this.root.preOrder();
        }else{
            System.out.println("二叉树为空");
        }
    }
    //中序遍历
    public void midOrder(){
        if(this.root!=null){
            this.root.midOrder();
        }else{
            System.out.println("nullpointexception");
        }
    }
    //后序遍历
    public void postOrder(){
        if(this.root!=null){
            this.root.postOrder();
        }else{
            System.out.println("nullpointexception");
        }
    }
    //前序查找
    public DataNode preOrdersearch(int num){
        if(this.root!=null){
            DataNode dataNode = this.root.preOrdersearch(num);
            return  dataNode;
        }else{
            System.out.println("null ");
            return null;
        }
    }
    //中序查找
    public DataNode midOrdersearch(int num){
        if(this.root!=null){
            DataNode dataNode = this.root.midOrdersearch(num);
            return dataNode;
        }else{
            System.out.println("null");
            return null;
        }
    }
    //后序查找
    public DataNode postOrdersearch(int num){
        if(this.root!=null){
            DataNode dataNode = this.root.postOrdersearch(num);
            return  dataNode;
        }else{
            System.out.println("null");
            return null;
        }
    }
    /**删除节点
     * 如果是叶子节点就直接删除该节点
     *如果不是叶子节点 就直接删除该子树
     */
    public void delNode(int num){
        if(root==null){
            System.out.println("树 为空");
            return;
        }
        if(root.getNum()==num){
            root=null;
            return;
        }
        root.delNode(num);

    }

}
//定义节点
class DataNode {
    private int num;
    private String name;
    private DataNode left;
    private DataNode right;
    private int leftType;
    private int rightType;
    private DataNode parent;

    //如果lefttype为0   则表示指向左子树   为1则为指向前驱节点
    //如果righttype为0  则表示指向右子树   为1则表示为指向后继节点
    public DataNode(int num, String name) {
        this.num = num;
        this.name = name;
    }

    public DataNode getParent() {
        return parent;
    }

    public void setParent(DataNode parent) {
        this.parent = parent;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataNode getLeft() {
        return left;
    }

    public void setLeft(DataNode left) {
        this.left = left;
    }

    public DataNode getRight() {
        return right;
    }

    public void setRight(DataNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "DataNode{" +
                "num=" + num +
                ", name='" + name + '\'' +
                '}';
    }

    //编写前序遍历的方法
    public void preOrder() {
        System.out.println(this);
        //递归向左子树
        if (this.left != null) {
            this.left.preOrder();
        }
        //递归右子树
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历的方法
    public void midOrder() {
        if (this.left != null) {
            this.left.midOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.midOrder();
        }
    }

    //后序遍历的方法
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //后序遍历添加进list
    public void postOrderList(List<DataNode> dataNodes) {
        if (this.left != null && this.leftType == 0) {
            this.left.postOrderList(dataNodes);
        }
        if (this.right != null && this.rightType == 0) {
            this.right.postOrderList(dataNodes);
        }
        dataNodes.add(this);
    }

    //前序遍历查找
    public DataNode preOrdersearch(int num) {
        //比较的次数
        int count = 0;
        System.out.println(++count);
        if (this.num == num) {
            return this;
        }
        DataNode resNode = null;
        //判断左子节点是否为空
        if (this.left != null) {
            resNode = this.left.preOrdersearch(num);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.preOrdersearch(num);
        }
        return resNode;


    }

    //中序遍历查找
    public DataNode midOrdersearch(int num) {
        DataNode resNode = null;
        if (this.left != null) {
            resNode = this.left.midOrdersearch(num);
        }
        if (resNode != null) {
            return resNode;
        }
        //比较的次数
        int count = 0;
        System.out.println(++count);
        if (this.num == num) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.midOrdersearch(num);
        }
        return resNode;
    }

    //后序遍历查找
    public DataNode postOrdersearch(int num) {
        DataNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrdersearch(num);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.postOrdersearch(num);
        }
        if (resNode != null) {
            return resNode;
        }
        //比较的次数
        int count = 0;
        System.out.println(++count);
        if (this.num == num) {
            return this;
        }
        return null;

    }

    /**
     * 递归删除节点
     * 如果是叶子节点就删除该节点
     * 如果不是叶子节点就删除该子树
     */
    public void delNode(int num) {
        //判断左子节点是否为空，是否权等于我们要删除的
        if (this.left != null && this.left.num == num) {
            this.left = null;
            return;
        }
        //判断右子节点是否为空
        if (this.right != null && this.right.num == num) {
            this.right = null;
            return;
        }
        //向左子树递归
        if (this.left != null) {
            this.left.delNode(num);
        }
        //向右子树递归
        if (this.right != null) {
            this.right.delNode(num);
        }
    }
}

