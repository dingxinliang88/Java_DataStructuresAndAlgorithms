package dateStructures.tree;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        //创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建结点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");
        HeroNode node6 = new HeroNode(6, "muffin");

        binaryTree.setRoot(root);
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        node2.setLeft(node6);

//        System.out.println("前序遍历");
//        binaryTree.preOrder();
//
//        System.out.println("中序遍历");
//        binaryTree.infixOrder();
//
//        System.out.println("后序遍历");
//        binaryTree.postOrder();

//        System.out.println("前序遍历查找");
//        HeroNode heroNode = binaryTree.preOrderSearch(5);
//        System.out.println(heroNode);
//        System.out.println("中序遍历查找");
//        HeroNode heroNode = binaryTree.infixOrderSearch(5);
//        System.out.println(heroNode);
//        System.out.println("后序遍历查找");
//        HeroNode heroNode = binaryTree.postOrderSearch(5);
//        System.out.println(heroNode);

        System.out.println("删除前：");
        binaryTree.preOrder();
        binaryTree.delNode2(6);
        System.out.println("删除后：");
        binaryTree.preOrder();
    }
}

/**
 * 二叉树树类
 */
class BinaryTree {
    private HeroNode root;//根结点

    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("当前二叉树为空...");
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("当前二叉树为空...");
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("当前二叉树为空...");
        }
    }

    /**
     * 前序遍历查找
     *
     * @param id id
     * @return HeroNode
     */
    public HeroNode preOrderSearch(int id) {
        if (this.root != null) {
            return this.root.preOrderSearch(id);
        } else {
            return null;
        }
    }

    /**
     * 中序遍历查找
     *
     * @param id id
     * @return HeroNode
     */
    public HeroNode infixOrderSearch(int id) {
        if (this.root != null) {
            return this.root.infixOrderSearch(id);
        } else {
            return null;
        }
    }

    /**
     * 后序遍历查找
     *
     * @param id id
     * @return HeroNode
     */
    public HeroNode postOrderSearch(int id) {
        if (this.root != null) {
            return this.root.postOrderSearch(id);
        } else {
            return null;
        }
    }

    /**
     * 删除节点
     * 规定
     * 如果删除的节点是叶子节点，则删除该节点
     * 如果删除的节点是非叶子节点，则删除该子树
     *
     * @param id 待删除节点id
     */
    public void delNode(int id) {
        if (this.root != null) {
            //先判断根节点是否是要删除的节点
            if (this.root.getId() == id) {
                this.root = null;
            } else {
                this.root.delNode(id);
            }
        } else {
            System.out.println("当前二叉树为空，无法删除...");
        }
    }

    /**
     * 删除节点
     * 规定：
     *  不删除根节点
     * @param id    待删除节点id
     */
    public void delNode2(int id){
        if(root != null){
            root.delNode2(id);
        } else {
            System.out.println("当前二叉树为空，无法删除");
        }
    }
}


/**
 * 节点类
 */
class HeroNode {
    private int id;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        //输出当前节点
        System.out.println(this);

        //向左递归
        if (this.left != null) {
            this.left.preOrder();
        }
        //向右递归
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        //向左递归
        if (this.left != null) {
            this.left.infixOrder();
        }
        //输出节点信息
        System.out.println(this);
        //向右递归
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        //向左递归
        if (this.left != null) {
            this.left.postOrder();
        }
        //向右递归
        if (this.right != null) {
            this.right.postOrder();
        }
        //输出当前节点信息
        System.out.println(this);
    }

    /**
     * 前序遍历查找
     *
     * @param id id
     * @return HeroNode
     */
    public HeroNode preOrderSearch(int id) {
        //先比较当前节点
        if (this.id == id) {
            return this;
        }
        //向左递归查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(id);
        }
        if (resNode != null) {
            return resNode;
        }
        //向右递归查找
        if (this.right != null) {
            resNode = this.right.preOrderSearch(id);
        }
        return resNode;
    }

    /**
     * 中序遍历查找
     *
     * @param id id
     * @return HeroNode
     */
    public HeroNode infixOrderSearch(int id) {
        //向左递归查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(id);
        }
        if (resNode != null) {
            return resNode;
        }
        //判断当前节点
        if (this.id == id) {
            return this;
        }
        //向右递归查找
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(id);
        }
        return resNode;
    }

    /**
     * 后序遍历查找
     *
     * @param id id
     * @return HeroNode
     */
    public HeroNode postOrderSearch(int id) {
        HeroNode resNode = null;
        //向左遍历查找
        if (this.left != null) {
            resNode = this.left.postOrderSearch(id);
        }
        if (resNode != null) {
            return resNode;
        }
        //向右递归查找
        if (this.right != null) {
            resNode = this.right.postOrderSearch(id);
        }
        if (resNode != null) {
            return resNode;
        }
        //判断当前节点
        if (this.id == id) {
            return this;
        }
        return resNode;
    }

    /**
     * 删除节点
     * 规定
     * 如果删除的节点是叶子节点，则删除该节点
     * 如果删除的节点是非叶子节点，则删除该子树
     *
     * @param id 待删除结点id
     */
    public void delNode(int id) {
        //判断删除是否是左子节点
        if (this.left != null && this.left.getId() == id) {
            this.left = null;
            return;
        }
        //判断删除是否是右子节点
        if (this.right != null && this.right.getId() == id) {
            this.right = null;
            return;
        }
        //向左递归
        if (this.left != null) {
            this.left.delNode(id);
        }
        //向右递归
        if (this.right != null) {
            this.right.delNode(id);
        }
    }

    /**
     * 删除节点
     *  规定
     *  如果该非叶子节点A只有一个子节点B，则子节点B替代节点A
     *  如果该非叶子节点A有左子节点B和右子节点C，则让左子节点B替代节点A
     * @param id 待删除节点id
     */
    public void delNode2(int id) {
        //判断删除的是否是左子节点
        if (this.left != null && this.left.getId() == id) {
            if (this.left.left != null && this.left.right != null) {
                this.left.left.left = this.left.right;
                this.left = this.left.left;
            } else if (this.left.left != null) {
                this.left = this.left.left;
            } else if (this.left.right != null) {
                this.left = this.left.right;
            } else {
                this.left = null;
            }
            return;
        }
        //判断要删除的是否是右子节点
        if(this.right != null && this.right.getId() == id) {
            if(this.right.left != null && this.right.right != null) {
                this.right.left.right = this.right.right;
                this.right = this.right.left;
            } else if(this.right.left != null){
                this.right = this.right.left;
            } else if(this.right.right != null){
                this.right = this.right.right;
            } else {
                this.right = null;
            }
            return;
        }
        //向左递归
        if(this.left != null){
            this.left.delNode2(id);
        }
        //向右递归
        if(this.right != null){
            this.right.delNode2(id);
        }
    }
}
