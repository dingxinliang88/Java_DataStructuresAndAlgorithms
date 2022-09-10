package dateStructures.avlTree;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();

//        int[] arr = {4,3,6,5,7,8};
        int[] arr = { 10, 11, 7, 6, 8, 9 };
        for (int item : arr) {
            avlTree.add(new Node(item));
        }

        System.out.println("平衡处理后：");
        System.out.println("二叉排序树的高度"+avlTree.getRoot().height());
        System.out.println("二叉排序树左子树的高度"+avlTree.getRoot().leftHeight());
        System.out.println("二叉排序树右子树的高度"+avlTree.getRoot().rightHeight());
        System.out.println("二叉排序树的根节点"+avlTree.getRoot());
    }
}
