package dateStructures.avlTree;

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
     * 统计树的高度
     *
     * @return int
     */
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    /**
     * 统计左子树的高度
     *
     * @return int
     */
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();

    }

    /**
     * 统计右子树的高度
     * @return  int
     */
    public int rightHeight() {
        if(right == null){
            return 0;
        }
        return right.height();
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

        //添加完一个节点后，若 leftHeight - rightHeight > 1 --> 进行调整（右旋转）
        if(leftHeight() - rightHeight() > 1){
            //当左节点的右子树大于左节点的左子树的高度时
            if(left != null && left.rightHeight() > left.leftHeight()){
                //先对左节点进行左旋转
                left.leftRotate();
            }
            //再进行右旋转
            rightRotate();
            return;
        }
        //添加完一个节点后，若 rightHeight - leftHeight > 1 --> 进行调整（左旋转）
        if(rightHeight() - leftHeight() > 1){
            //当右节点的左子树大于右节点的右子树的高度时
            if(left != null && left.rightHeight() > left.leftHeight()){
                //先对右节点进行右旋转
                right.rightRotate();
            }
            //再进行右旋转
            rightRotate();
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

    /**
     * 左旋转
     */
    public void leftRotate(){
        //以当前节点的值创建一个新节点
        Node newNode = new Node(this.value);
        //将新节点的左子树设置成当前节点的左子树
        newNode.setLeft(this.left);
        //将新节点的右子树设置成当前节点的右子树的左子树
        newNode.setLeft(this.right.left);
        //当前节点的值替换成当前节点右子节点的值
        this.value = this.right.value;
        //当前节点的右子树设置成当前节点右子树的右子树
        this.right = this.right.right;
        //把当前节点的左子树（左子节点）设置成新的节点
        this.left = newNode;
    }

    /**
     * 右旋转
     */
    public void rightRotate(){
        Node newNode = new Node(this.value);
        newNode.setRight(this.right);
        newNode.setLeft(this.left.right);
        this.value = this.left.value;
        this.left = this.left.left;
        this.right = newNode;
    }
}
