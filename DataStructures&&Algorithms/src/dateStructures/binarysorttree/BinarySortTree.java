package dateStructures.binarysorttree;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class BinarySortTree {
    private Node root;//根节点

    /**
     * 添加节点
     *
     * @param node 待添加节点
     */
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("当前二叉树为空，无法遍历...");
        }
    }

    /**
     * 查找要删除的节点
     *
     * @param value 删除节点的值
     * @return Node
     */
    public Node search(int value) {
        if (root != null) {
            return root.search(value);
        } else {
            return null;
        }
    }

    /**
     * 查找要删除节点的父节点
     *
     * @param value 删除节点的值
     * @return Node
     */
    public Node searchParent(int value) {
        if (root != null) {
            return root.searchParent(value);
        } else {
            return null;
        }
    }

    /**
     * 删除节点
     *
     * @param value 待删除节点的值
     */
    public void delNode(int value) {
        if (root != null) {
            Node target = search(value);//查找要删除的节点
            if (target == null) {//未找到
                return;
            }
            //二叉树只有一个节点即根节点
            if (root.getLeft() == null && root.getRight() == null) {
                root = null;
                return;
            }

            Node parent = searchParent(value);//找到目标节点的父节点

            if (target.getLeft() == null && target.getRight() == null) {//要删除的叶子节点
                if (parent.getLeft() != null && parent.getLeft().getValue() == value) {
                    parent.setLeft(null);
                } else if (parent.getRight() != null && parent.getRight().getValue() == value) {
                    parent.setRight(null);
                }
            } else if (target.getLeft() != null && target.getRight() != null) {//要删除的节点有两棵子树
                //寻找以目标节点的右子节点为根节点的二叉树的节点最小值
                int rightTreeMin = delRightTreeMin(target.getRight());
                target.setValue(rightTreeMin);
                //此处亦可找到左子树的最大值
//                int leftTreeMax = delLeftTreeMax(target.getLeft());
//                target.setValue(leftTreeMax);
            } else {//要删除的节点只有一棵子树
                if (target.getLeft() != null) {//存在左子树
                    if (parent != null) {
                        if (parent.getLeft().getValue() == value) {
                            //要删除的节点是父节点的左子节点
                            parent.setLeft(target.getLeft());
                        } else {
                            //要删除的节点是父节点的右子节点
                            parent.setRight(target.getLeft());
                        }
                    } else {//此时二叉树只有两个节点
                        root = target.getLeft();
                    }
                } else if (target.getRight() != null) {//存在右子树
                    if (parent != null) {
                        if (parent.getLeft().getValue() == value) {
                            parent.setLeft(target.getRight());
                        } else {
                            parent.setRight(target.getRight());
                        }
                    } else {//此时二叉树只有两个节点
                        root = target.getRight();
                    }
                }
            }
        }
    }

    /**
     * 删除以node为根节点的二叉树最小值
     *
     * @param node node（当作二叉树的根节点）
     * @return 以node为根结点的二叉排序树的最小结点的值
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        //找到以node为根节点的二叉树的最小值
        while (target.getLeft() != null) {
            target = target.getLeft();
        }
        delNode(target.getValue());
        return target.getValue();
    }

    /**
     * 删除以node为根节点的最大值
     *
     * @param node node
     * @return 以node为根节点的二叉排序的最大值
     */
    public int delLeftTreeMax(Node node) {
        Node target = node;
        //找到最大值
        while (target.getRight() != null) {
            target = target.getRight();
        }
        delNode(target.getValue());
        return target.getValue();
    }
}
