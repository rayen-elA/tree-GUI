package tree;


import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;
import java.util.List;

public class TreeService {
    public TreeNode tree;
    public boolean check=true;
    public Hashtable<Integer,Integer[]> hash=new Hashtable<>();

    public TreeService(TreeNode tree) {
        this.tree = tree;

    }

    public TreeNode setTreeRoot(int val, TreeNode tree) {
        TreeNode tree1=new TreeNode(val);

        return tree1;
    }

    public void printTree(TreeNode tree) {

        if (tree != null) {
            System.out.print(tree.getVal() + " | ");
            printTree(tree.getRight());
            printTree(tree.getLeft());
        }
        else
            return;


    }

    public TreeNode addNode(TreeNode tree, int val) {
        TreeNode current = tree;
        TreeNode newNode = new TreeNode(val);

        TreeNode parent;
        if (tree == null) {
            tree = newNode;
        } else {
            while (true) {

                parent = current;
                if (val < current.getVal()) {

                    current = current.getLeft();
                    if (current == null) {
                        parent.setLeft(newNode);
                        break;
                    }

                } else {
                    current = current.getRight();
                    if (current == null) {
                        parent.setRight(newNode);
                        break;
                    }

                }
            }

        }
        return  tree;


        //printTree(tree);



    }


    public Object searchTree(TreeNode current,int val, TreeNode parent,TreeView view){
        while (true) {//looking for node to delete
            if (current.getVal() == val) {
                return parent;
            }
            parent = current;
            if (val < current.getVal()) {

                current = current.getLeft();
                if (current == null) {
                    check=false;
                    JOptionPane.showMessageDialog(view,"sorry" + val+" doesn't exist ","error occurred",JOptionPane.WARNING_MESSAGE);

                    return false;
                }
            } else {
                current = current.getRight();
                if (current == null) {
                    JOptionPane.showMessageDialog(view,"sorry" + val+" doesn't exist ","error occurred",JOptionPane.WARNING_MESSAGE);
                    check=false;
                    return false;}
            }
        }
    }
    public TreeNode deleteNode(TreeNode tree, int val, TreeView view) {
        TreeNode current = tree;
        TreeNode parent=null;




        if (tree == null) {// tree is empty nothing to delete
            check=false;
            JOptionPane.showMessageDialog(view,"brother,tree is empty.","error occurred",JOptionPane.WARNING_MESSAGE);

        } else {
            if ( searchTree(tree, val,parent,view) instanceof Boolean){
                return tree;
            }else {
                parent = (TreeNode) searchTree(tree, val, parent,view);
                if (parent != null) {

                    if (parent.getLeft() != null) {
                        if (parent.getLeft().getVal() == val)
                            current = parent.getLeft();
                    }
                    if(parent.getRight()!=null) {
                        if (parent.getRight().getVal() == val)
                            current = parent.getRight();
                    }


                }
            }


            if (current.getRight() == null && current.getLeft() == null) {//if deleting a leaf
                if (parent == null) {
                    return null;
                } else if (parent.getLeft() == current) {
                    parent.setLeft(null);
                } else {
                    parent.setRight(null);
                }
                return tree;
            }
            if (current.getRight() == null && current.getLeft() != null) {//if there's one child on the left
                if (parent != null) {//node to delete isnt the root
                    if (parent.getLeft() == current) {//if node to delete on the left of the parent
                        parent.setLeft(current.getLeft());//skipping over the node to delete
                    } else {
                        parent.setRight(current.getLeft());//skipping over the node to delete
                    }
                } else {//node to delete is root so just skip over
                    tree = tree.getLeft();
                }
                return tree;
            } else if (current.getLeft() == null && current.getRight() != null) {//node to delete has a child on the right
                if (parent != null) {
                    if (parent.getLeft() == current) {
                        parent.setLeft(current.getRight());
                    } else {
                        parent.setRight(current.getRight());
                    }
                } else {
                    tree = tree.getRight();

                }

            }
            if ((current.getLeft() != null) && (current.getRight() != null)) {//node to delete has 2 children
                if (parent == null) {//node to delete is the root
                    TreeNode x = searchSmallestNode(current.getRight());
                    current.setVal(x.getVal());
                    if(x.getLeft()!=null) {
                        x.getLeft().setRight(x.getRight());
                        current.setRight(x.getLeft());
                    }
                    else
                        current.setRight(x.getRight());
                    return tree;
                } else {
                    TreeNode treeNode = searchSmallestNode(current.getRight());
                    if (treeNode == current.getRight()) {
                        if (current.getRight().getLeft() != null) {
                            current.setVal(current.getRight().getLeft().getVal());
                            current.getRight().setLeft(null);
                        } else {
                            current.setVal(current.getRight().getVal());
                            current.setRight(current.getRight().getRight());
                        }
                    } else {
                        current.setVal(treeNode.getLeft().getVal());
                        treeNode.setLeft(treeNode.getLeft().getRight());
                    }
                }
                return tree;
            }
        }
        return tree;
    }

    public boolean searchTreeDFS(TreeNode tree, int val, List<Integer> path) {
        if (tree == null)
            return false;

        path.add(tree.getVal());
        if (tree.getVal() == val)
            return true;
        if (searchTreeDFS(tree.getRight(), val, path))
            return true;
        if (searchTreeDFS(tree.getLeft(), val, path))
            return true;
        path.removeLast();

        return false;

    }

    public TreeNode searchSmallestNode(TreeNode tree) {
        TreeNode treeNode = tree;
        while (treeNode.getLeft() != null) {

            while ((treeNode.getLeft().getLeft()) != null) {

                treeNode = treeNode.getLeft();
            }
            if(treeNode.getLeft() != null && ((treeNode.getLeft().getLeft()) == null) )
                break;
        }
        return treeNode;
    }

}

