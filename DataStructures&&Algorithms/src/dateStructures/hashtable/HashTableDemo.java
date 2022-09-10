package dateStructures.hashtable;

import java.util.Scanner;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class HashTableDemo {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(7);
        Scanner scanner = new Scanner(System.in);
        String key = "";
        boolean loop = true;
        while(loop){
            System.out.println("add    --> 添加雇员");
            System.out.println("list   --> 显示雇员");
            System.out.println("find   --> 查找雇员");
            System.out.println("delete --> 删除雇员");
            System.out.println("exit   --> 退出系统");
            key = scanner.next();

            switch (key){
                case "add":
                    System.out.print("请输入雇员Id:");
                    int id = scanner.nextInt();
                    System.out.print("请输入雇员姓名：");
                    String name = scanner.next();
                    hashTable.add(new Employee(id,name));
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "find":
                    System.out.print("请输入要查找的雇员Id:");
                    hashTable.findById(scanner.nextInt());
                    break;
                case "delete":
                    System.out.print("请输入要删除的雇员Id:");
                    hashTable.deleteById(scanner.nextInt());
                    break;
                case "exit":
                    loop = false;
                    break;
                default:
                    System.out.println("输入有误，请重新输入");
            }
        }
    }
}
