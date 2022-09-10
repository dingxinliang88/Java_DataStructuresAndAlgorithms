package dateStructures.spasearray;


import java.io.*;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 * 稀疏数组
 */
public class SparseArray {
    public static void main(String[] args) {
        /**
         * 读写操作(待完成)
         */
        String chessFile = "E:\\chess.txt";

        int[][] chess = new int[11][11];
        chess[1][2] = 1;
        chess[2][3] = 2;
        int[][] sparseArray = parseSparseArray(chess);
        BufferedWriter bufferedWriter = null;

            try {
                bufferedWriter = new BufferedWriter(new FileWriter(chessFile));
                for (int[] rows : sparseArray) {
                    for (int item : rows) {
                        bufferedWriter.write(item + "\t");
                    }
                    bufferedWriter.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(bufferedWriter!=null){
                    try {
                        bufferedWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

    /**
     * 功能:二维数组 --> 稀疏数组
     *
     * @param chess 待稀疏的数组
     * @return 稀疏数组
     */
    public static int[][] parseSparseArray(int[][] chess) {
        int sum = 0;
        //遍历数组获得有效数据
        for (int[] row : chess) {
            for (int item : row) {
                if (item != 0) {
                    sum++;
                }
            }
        }
        //创建稀疏数组
        int[][] sparseArray = new int[sum + 1][3];
        //初始化稀疏数组
        sparseArray[0][0] = sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;

        int count = 0;
        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess[i].length; j++) {
                if (chess[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chess[i][j];
                }
            }
        }

        return sparseArray;
    }


    /**
     * 功能:稀疏数组 --> 二维数组
     *
     * @param sparseArray 待转换数组
     * @return 二维数组
     */
    public static int[][] parseDyadicArray(int[][] sparseArray) {
        int row = sparseArray[0][0];
        int col = sparseArray[0][1];
        int[][] chess = new int[row][col];

        for (int i = 1; i < sparseArray.length; i++) {
            chess[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }

        return chess;
    }
}
