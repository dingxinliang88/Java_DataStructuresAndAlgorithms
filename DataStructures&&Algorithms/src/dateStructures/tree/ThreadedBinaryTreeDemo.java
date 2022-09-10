package dateStructures.tree;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        Node root = new Node(1, "tom");
        Node node2 = new Node(3, "jack");
        Node node3 = new Node(6, "smith");
        Node node4 = new Node(8, "mary");
        Node node5 = new Node(10, "king");
        Node node6 = new Node(14, "dim");
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);


//        threadedBinaryTree.infixThreadedNode();
//        Node leftNode = node5.getLeft();
//        Node rightNode = node5.getRight();
//        System.out.println("node5的前驱节点是" + leftNode);
//        System.out.println("node5的后继节点是" + rightNode);
//        System.out.println("中序线索化输出二叉树：");
//        threadedBinaryTree.indixThreadedList();


//        threadedBinaryTree.preThreadNode();
//        Node leftNode = node5.getLeft();
//        Node rightNode = node5.getRight();
//        System.out.println("node5的前驱节点是" + leftNode);
//        System.out.println("node5的后继节点是" + rightNode);
//        System.out.println("前序线索化输出二叉树");
//        threadedBinaryTree.preTreadedList();

        threadedBinaryTree.postThreadedNode();
        Node leftNode = node5.getLeft();
        Node rightNode = node5.getRight();
        System.out.println("node5的前驱节点是" + leftNode);
        System.out.println("node5的后继节点是" + rightNode);
        System.out.println("后序线索化输出二叉树");
        threadedBinaryTree.postThreadedList();
    }
}

/**
 * 线索化二叉树
 */
class ThreadedBinaryTree {
    private Node root;//根结点
    private Node pre;//线索化时总是指向前一个结点

    public void setRoot(Node root) {
        this.root = root;
    }

    /**
     * 重载threadedNode
     */
    public void infixThreadedNode() {
        this.infixThreadedNode(root);
    }

    /**
     * 线索化中序二叉树
     *
     * @param node 当前需要线索化的结点
     */
    private void infixThreadedNode(Node node) {
        if (node == null) {
            return;
        }
        //线索化左子树
        infixThreadedNode(node.getLeft());

        //线索化当前节点
        if (node.getLeft() == null) {
            node.setLeft(pre);//当前节点的左指针指向前驱节点
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);//前驱节点的右指针指向当前节点
            pre.setRightType(1);
        }

        //每处理完一个节点，当前节点成为下一个节点的前驱节点
        pre = node;
        //线索化右子树
        infixThreadedNode(node.getRight());
    }

    /**
     * 遍历输出线索化中序二叉树
     */
    public void indixThreadedList() {
        Node node = root;
        while (node != null) {
            //找到leftType == 1 的节点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            //输出当前节点
            System.out.println(node);

            //输出有后继节点的节点
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }

    /**
     * 重载preThreadNode
     */
    public void preThreadNode() {
        this.preThreadNode(root);
    }

    /**
     * 线索化前序二叉树
     *
     * @param node 当前需要线索化的节点
     */
    private void preThreadNode(Node node) {
        if (node == null) {
            return;
        }

        //线索化当前节点
        if (node.getLeft() == null) {
            node.setLeft(pre);//当前节点的左指针指向前驱节点
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);//前驱节点的右指针指向当前节点
            pre.setRightType(1);
        }
        pre = node;
        //线索化左子树
        if (node.getLeftType() == 0) {
            preThreadNode(node.getLeft());
        }
        //线索化右子树
        if (node.getRightType() == 0) {
            preThreadNode(node.getRight());
        }
    }

    /**
     * 遍历输出线索化前序二叉树
     */
    public void preTreadedList() {
        Node node = root;
        //输出当前节点
        while (node != null) {
            while (node.getLeftType() == 0) {
                System.out.println(node);
                node = node.getLeft();
            }
            System.out.println(node);
            node = node.getRight();
        }
    }

    /**
     * 重载postThreadedNode
     */
    public void postThreadedNode(){
        this.postThreadedNode(root);
    }

    /**
     * 线索化后序二叉树
     *
     * @param node 当前需要线索化的节点
     */
    private void postThreadedNode(Node node) {
        if(node == null){
            return;
        }
        postThreadedNode(node.getLeft());
        postThreadedNode(node.getRight());
        //线索化当前节点
        if(node.getLeft() == null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if(pre != null && pre.getRight() == null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
    }

    /**
     * 遍历输出线索化后序二叉树
     */
    public void postThreadedList(){
        /**
         * 待完成
         */
    }
}

/**
 * 节点类
 */
class Node {
    private int id;
    private String name;
    private Node left;
    private Node right;

    //规定：1 --> 指向的前驱结点，0 --> 指向的左子树
    private int leftType;
    //规定：1 --> 指向的后继结点，0 --> 指向的右子树

    private int rightType;

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
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

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
