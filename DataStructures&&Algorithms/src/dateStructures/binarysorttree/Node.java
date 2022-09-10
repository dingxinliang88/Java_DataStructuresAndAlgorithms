package dateStructures.binarysorttree;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class Node {
    private int value;
    private Node left;
    private Node right;

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 添加节点
     *
     * @param node 待添加节点
     */
    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (node.value < this.value) {//小值挂在左子树
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);//向左递归查找
            }
        } else {//大值挂在右子树
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);//向右递归查找
            }
        }
    }

    /**
     * 查找要删除的节点
     *
     * @param value 删除节点的值
     * @return Node
     */
    public Node search(int value) {
        if (this.value == value) {
            return this;
        } else if (this.value > value) {
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);//向左递归查找
        } else {
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);//向右递归查找
        }
    }


    /**
     * 查找要删除节点的父节点
     *
     * @param value 删除节点的值
     * @return Node
     */
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value)
                || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            if (this.left != null && this.value > value) {
                return this.left.searchParent(value);//向左递归查找
            } else if (this.right != null && this.value < value) {
                return this.right.searchParent(value);//向右递归查找
            } else {//未查找到父节点
                return null;
            }
        }
    }
}
