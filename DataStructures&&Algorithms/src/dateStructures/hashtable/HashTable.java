package dateStructures.hashtable;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 * 哈希表
 */
public class HashTable {
    private EmpLinkedList[] empLinkedLists;
    private int size;

    public HashTable(int size) {
        this.size = size;
        empLinkedLists = new EmpLinkedList[size];

        //初始化每一条链表
        for (int i = 0; i < size; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    /**
     * 添加雇员
     * @param employee  雇员
     */
    public void add(Employee employee){
        //根据雇员Id确定其链表的位置
        int empInsertNo = hashFun(employee.getId());
        empLinkedLists[empInsertNo].add(employee);
    }

    /**
     * 显示雇员信息
     */
    public void list(){
        for (int i = 0; i < size; i++) {
            empLinkedLists[i].list(i);
        }
    }

    /**
     * 查找雇员
     * @param id    雇员Id
     */
    public void findById(int id){
        int empInsertNo = hashFun(id);
        Employee employee = empLinkedLists[empInsertNo].findById(id);
        if(employee == null){
            System.out.printf("未找到id为%d的雇员\n",id);
        }
        System.out.printf("在第%d条链表中查找到该雇员，雇员信息为：",empInsertNo + 1);
        System.out.println(employee);
    }

    /**
     * 删除雇员
     * @param id    待删除雇员id
     */
    public void deleteById(int id){
        int empInsertNo = hashFun(id);
        empLinkedLists[empInsertNo].deleteById(id);
    }

    /**
     * 散列函数
     * @param id    雇员Id
     * @return  散列值
     */
    public int hashFun(int id){
        return id % size;
    }
}
