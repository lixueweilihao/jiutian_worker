package com.play.data.tree.sn;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/2/27  22:19
 */
public class TreeNode<T> {
    T value;

    TreeNode<T> leftChild;
    TreeNode<T> rightChild;

    TreeNode(T value) {
        this.value = value;
    }
    TreeNode() {
    }

    /**   增加左子节点
     * addLeft:
     * @param value
     * void  返回类型
     */
    public void addLeft(T value){
        TreeNode<T> leftChild = new TreeNode<T>(value);
        this.leftChild = leftChild;
    }
    /**
     * addRight: 增加右子节点
     * @param value
     * void  返回类型
     */
    public void addRight(T value){
        TreeNode<T> rightChild = new TreeNode<T>(value);
        this.rightChild = rightChild;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     * 重载equal方法
     */
    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        if(!(obj instanceof TreeNode)){
            return false;
        }
        return this.value.equals(((TreeNode<?>)obj).value);
    }
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     * 重载hashCode方法
     */
    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return this.value.hashCode();
    }
    @Override
    public String toString(){
        return this.value==null?"":this.value.toString();
    }


}
