package dateStructures.tree;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 * 顺序存储二叉树
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        System.out.println("\n前序输出：");
        arrBinaryTree.preOrder();

        System.out.println("\n中序输出：");
        arrBinaryTree.infixOrder();

        System.out.println("\n后序输出：");
        arrBinaryTree.postOrder();
    }
}

class ArrBinaryTree {
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    /**
     * 重载preOrder
     */
    public void preOrder(){
        this.preOrder(0);
    }

    /**
     * 前序输出
     *
     * @param index 起始下标
     */
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("当前二叉树为空...");
        }
        //先输出当前数
        System.out.printf("%d\t", arr[index]);

        //向左递归
        if ((2 * index + 1) < arr.length){
            this.preOrder(2 * index + 1);
        }
        //向右递归
        if((2 * index + 2) < arr.length){
            this.preOrder(2 * index + 2);
        }
    }

    /**
     * 重载infixOrder
     */
    public void infixOrder(){
        this.infixOrder(0);
    }

    /**
     * 中序输出
     * @param index 起始下标
     */
    public void infixOrder(int index){
        if (arr == null || arr.length == 0) {
            System.out.println("当前二叉树为空...");
        }
        //向左递归
        if ((2 * index + 1) < arr.length){
            this.infixOrder(2 * index + 1);
        }
        //输出当前数
        System.out.printf("%d\t", arr[index]);
        //向右递归
        if((2 * index + 2) < arr.length){
            this.infixOrder(2 * index + 2);
        }
    }


    /**
     * 重载postOrder
     */
    public void postOrder(){
        this.postOrder(0);
    }
    /**
     * 后序输出
     * @param index 起始下标
     */
    public void postOrder(int index){
        if (arr == null || arr.length == 0) {
            System.out.println("当前二叉树为空...");
        }
        //向左递归
        if ((2 * index + 1) < arr.length){
            this.postOrder(2 * index + 1);
        }

        //向右递归
        if((2 * index + 2) < arr.length){
            this.postOrder(2 * index + 2);
        }

        //输出当前数
        System.out.printf("%d\t", arr[index]);
    }
}
