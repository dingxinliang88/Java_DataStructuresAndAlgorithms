package dateStructures.huffmantree;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class HuffmanTreeDemo {
    public static void main(String[] args) {
        int[] arr = { 13, 7, 8, 3, 29, 6, 1 };
        HuffmanTree huffmanTree = new HuffmanTree();
        Node root = huffmanTree.createHuffmanTree(arr);
        huffmanTree.preOrder(root);
    }
}
