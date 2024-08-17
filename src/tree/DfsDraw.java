package tree;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DfsDraw extends JPanel {
    private  TreeNode tree;
    private List<Integer> path;

    public DfsDraw(TreeNode tree, List<Integer> path) {
        this.tree=tree;
        this.path = path;

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Assuming `tree` and `path` are properly initialized
        AnimateTree(g, tree, 600, 30, path);
    }


    public boolean pathfinder(TreeNode tree, List<Integer> path) {
        // Create an iterator for the list
        Iterator<Integer> iterator = path.iterator();

        // Iterate over the elements of the list
        while (iterator.hasNext()) {
            int pat = iterator.next();
            if (tree.getVal() == pat) {
                return true;
            }
        }

        return false;
    }

    public void AnimateTree(Graphics g, TreeNode tree, int x, int y, List<Integer> path) {
        if (tree == null) return;

        String holder = String.valueOf(tree.getVal());
        if (pathfinder(tree, path)) {
            g.setColor(Color.red);
        } else {
            g.setColor(Color.black);
        }

        g.drawString(holder, x, y - 10);
        g.drawOval(x - 22, y - 40, 50, 50);

        if (tree.getLeft() != null) {
            g.drawLine(x, y, x - 70, y + 50);
            AnimateTree(g, tree.getLeft(), x - 70, y + 50, path);
        }
        if (tree.getRight() != null) {
            g.drawLine(x, y, x + 70, y + 50);
            AnimateTree(g, tree.getRight(), x + 70, y + 50, path);
        }
    }
}
