package dateStructures.huffmancode;


/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class HuffmanCodeDemo {
    public static void main(String[] args) {
        HuffmanCode huffmanCode = new HuffmanCode();

//        String srcPath = "E:\\HuffmanCode.java";
//        String dstPath = "E:\\zipPic.java";
//        huffmanCode.zipFile(srcPath,dstPath);
//        System.out.println("压缩文件完成");
        String zipPath = "E:\\zipPic.java";
        String dstPath = "E:\\unZip.java";
        huffmanCode.unZipFile(zipPath,dstPath);
        System.out.println("解压文件成功");
    }
}