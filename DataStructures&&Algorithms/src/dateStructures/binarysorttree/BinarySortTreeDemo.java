package dateStructures.binarysorttree;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int item : arr) {
            binarySortTree.add(new Node(item));
        }
        binarySortTree.infixOrder();
        binarySortTree.delNode(7);
        System.out.println("===================================");
        binarySortTree.infixOrder();
    }
}
