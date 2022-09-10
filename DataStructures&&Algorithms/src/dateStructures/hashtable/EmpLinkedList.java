package dateStructures.hashtable;


/**
 * @author Muffin_Head
 * @version 1.8.0_131
 * 管理雇员的链表
 */
public class EmpLinkedList {
    private Employee head;//头指针，存放第一个雇员信息

    /**
     * 添加雇员
     *
     * @param employee 雇员
     */
    public void add(Employee employee) {
        if (head == null) {//添加第一个节点
            head = employee;
            return;
        }
        //找到最后一个节点
        Employee curEmp = head;
        while (curEmp.next != null) {
            curEmp = curEmp.next;
        }
        curEmp.next = employee;
    }

    /**
     * 显示雇员信息
     *
     * @param no 处于HashTable的位置
     */
    public void list(int no) {
        if (head == null) {
            System.out.printf("第%d条链表为空\n", no + 1);
            return;
        }
        Employee curEmp = head;
        System.out.printf("第%d条链表信息：",no + 1);
        while(curEmp != null){
            System.out.print(" -> " + curEmp);
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    /**
     * 查找雇员
     * @param id    雇员Id
     * @return  雇员信息
     */
    public Employee findById(int id){
        if(head == null){
            System.out.println("当前链表为空...");
            return null;
        }
        Employee curEmp = head;
        while(curEmp != null){
            if(curEmp.getId() == id){
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }

    /**
     * 删除雇员
     * @param id    待删除雇员Id
     */
    public void deleteById(int id){
        if(head == null){
            System.out.println("当前链表为空...");
            return;
        }
        //要删除的头节点
        if(head.getId() == id){
            head = head.next;
            return;
        }
        //要删除的其他节点
        Employee curEmp = head;
        Employee nextEmp = head.next;
        while(nextEmp!= null){
            if(nextEmp.getId() ==id){
                curEmp.next = nextEmp.next;
                return;
            }
            curEmp = nextEmp;
            nextEmp = nextEmp.next;
        }
        System.out.printf("未找到Id为%d的雇员\n",id);
    }
}
