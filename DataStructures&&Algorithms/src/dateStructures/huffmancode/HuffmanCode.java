package dateStructures.huffmancode;

import java.io.*;
import java.util.*;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class HuffmanCode {
    private Map<Byte, String> huffmanCodes = new HashMap<>();
    private StringBuilder stringBuilder = new StringBuilder();

    /**
     * 前序遍历
     *
     * @param root 根节点
     */
    public void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("此二叉树为空...");
        }

    }

    /**
     * 将字符串对应的字符数组存入List容器
     *
     * @param bytes 字节数组
     * @return List<Node>
     */
    public List<Node> getNodes(byte[] bytes) {
        //遍历bytes，统计每一个字符出现的次数
        Map<Byte, Integer> map = new HashMap<>();
        for (byte b : bytes) {
            map.merge(b, 1, Integer::sum);
        }
        List<Node> nodes = new ArrayList<>();
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    /**
     * 创建哈夫曼树
     *
     * @param nodes 存放节点的容器
     * @return Node
     */
    public Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            //排序
            Collections.sort(nodes);

            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            Node parent = new Node(null, leftNode.getWeight() + rightNode.getWeight());
            parent.setLeft(leftNode);
            parent.setRight(rightNode);

            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    /**
     * 重载getCodes
     *
     * @param root 节点
     * @return map
     */
    public Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        //处理左右子树
        getCodes(root.getLeft(), "0", stringBuilder);
        getCodes(root.getRight(), "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 生成赫夫曼树对应的赫夫曼编码
     *
     * @param node          节点
     * @param code          路径
     * @param stringBuilder 用于拼接路径
     */
    private void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder temp = new StringBuilder(stringBuilder);
        temp.append(code);
        if (node != null) {
            if (node.getData() == null) {
                //非叶子节点
                //向左递归
                getCodes(node.getLeft(), "0", temp);
                //向右递归
                getCodes(node.getRight(), "1", temp);
            } else {
                //叶子节点
                huffmanCodes.put(node.getData(), temp.toString());
            }
        }
    }

    /**
     * 将字符串对应的byte[]数组，通过生成的赫夫曼编码表，压缩成byte[]数组
     *
     * @param bytes        原始字符串对应的byte[]
     * @param huffmanCodes 赫夫曼编码表
     * @return byte[]
     */
    public byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //将bytes通过赫夫曼编码表转成对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        //统计压缩后字节数组的长度
        int len = 0;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;//记录这是第几个Byte
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String substring;
            if (i + 8 > stringBuilder.length()) {
                //不够8个字节
                substring = stringBuilder.substring(i);
            } else {
                substring = stringBuilder.substring(i, i + 8);
            }
            huffmanCodeBytes[index++] = (byte) Integer.parseInt(substring, 2);//以二进制方式转换
        }
        return huffmanCodeBytes;
    }

    /**
     * 赫夫曼压缩
     *
     * @param bytes 原始字符串对应的字节数组
     * @return 压缩后的数组
     */
    public byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);//得到节点
        Node huffmanTreeRoot = createHuffmanTree(nodes);//得到赫夫曼树的根节点
        Map<Byte, String> huffumanCodes = getCodes(huffmanTreeRoot);//得到赫夫曼编码
        return zip(bytes, huffmanCodes);//得到压缩后的字节数组
    }

    /**
     * 将byte转成二进制字符串
     *
     * @param b    待转化字节
     * @param flag 标志：是否需要补高位
     * @return 二进制字符串
     */
    public String byteToString(byte b, boolean flag) {
        int temp = b;
        if (flag) {
            temp |= 256;//temp |= 1 0000 0000
        }
        String binaryString = Integer.toBinaryString(temp);
        if (flag) {
            return binaryString.substring(binaryString.length() - 8);
        } else {
            return binaryString;
        }
    }

    /**
     * 重载decode
     *
     * @param huffumanBytes 待解压字节数组
     * @return
     */
    public byte[] decode(byte[] huffumanBytes) {
        return decode(huffumanBytes, huffmanCodes);
    }

    /**
     * 对压缩数据的解码
     *
     * @param huffumanBytes 压缩数据
     * @param huffmanCodes  赫夫曼编码
     * @return 解码后的字节数组
     */
    public byte[] decode(byte[] huffumanBytes, Map<Byte, String> huffmanCodes) {
        //先得到huffumanBytes对应的二进制数组
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffumanBytes.length; i++) {
            boolean flag = (i == huffumanBytes.length - 1);//判断是不是最后一个字节
            stringBuilder.append(byteToString(huffumanBytes[i], !flag));
        }
        //将字符串按照特定的赫夫曼编码解码
        //将赫夫曼编码进行反转，用于反向查询
        Map<String, Byte> reverseHuffmanCodes = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            reverseHuffmanCodes.put(entry.getValue(), entry.getKey());
        }

        List<Byte> list = new ArrayList<>();
        //扫描stringBuilder
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;//每次扫描的个数
            boolean flag = true;
            Byte b = null;
            while (flag) {
                String key = stringBuilder.substring(i, i + count);
                b = reverseHuffmanCodes.get(key);
                if (b == null) {
                    //未查找到
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }

        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }


    /**
     * 压缩文件
     *
     * @param srcFile 源文件路径
     * @param dstFile 目标路径
     */
    public void zipFile(String srcFile, String dstFile) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            //创建文件输入流
            inputStream = new FileInputStream(srcFile);
            //创建一个和文件相同大小的字节数组
            byte[] bytes = new byte[inputStream.available()];
            //读取文件
            inputStream.read(bytes);
            //压缩文件
            byte[] zipBytes = huffmanZip(bytes);
            //创建文件输出流
            outputStream = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的对象输出流
            objectOutputStream = new ObjectOutputStream(outputStream);
            //将压缩文件数组和赫夫曼编码写入对象输出流
            objectOutputStream.writeObject(bytes);
            objectOutputStream.writeObject(huffmanCodes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 解压文件
     *
     * @param zipFile 待解压文件
     * @param dstFile 目标路径
     */
    public void unZipFile(String zipFile, String dstFile) {
        InputStream inputStream = null;
        OutputStream outputStream =null;
        ObjectInputStream objectInputStream = null;

        try {
            inputStream = new FileInputStream(zipFile);
            objectInputStream = new ObjectInputStream(inputStream);

            byte[] huffmanBytes = (byte[])objectInputStream.readObject();
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) objectInputStream.readObject();

            //解码
            byte[] bytes = decode(huffmanBytes, huffmanCodes);

            outputStream = new FileOutputStream(dstFile);
            outputStream.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}