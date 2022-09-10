package dateStructures.huffmantree;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class HuffmanTree {
    /**
     * 创建赫夫曼树
     *
     * @param arr 待处理数组
     */
    public Node createHuffmanTree(int[] arr) {
        //为了操作便利，根据arr数组中的每一个元素创建节点存入ArrayList
        ArrayList<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        while (nodes.size() > 1) {
            //排序
            Collections.sort(nodes);
            //取值
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            
            //创建新的二叉树
            Node newNode = new Node(leftNode.getValue() + rightNode.getValue());
            newNode.setLeft(leftNode);
            newNode.setRight(rightNode);

            //更新ArrayList
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(newNode);
        }
        return nodes.get(0);
    }

    /**
     * 前序遍历
     *
     * @param root 根节点
     */
    public void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("当前树为空...");
        }
    }
}
