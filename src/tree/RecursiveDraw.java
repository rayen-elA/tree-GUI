package tree;

import javax.swing.*;
import java.awt.*;

public class RecursiveDraw extends JPanel {
    private TreeNode tree;

    public RecursiveDraw(TreeNode tree){
        this.tree = tree;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(new Font("Arial", Font.PLAIN, 18));
        g.setColor(Color.BLACK);

        // Start the recursive drawing from the root position
        drawTree(g, tree, 600, 30);  // Adjust the initial x and y as necessary
    }
    public void setTree(TreeNode tree) {
        this.tree = tree;
    }
    public void drawTree(Graphics g, TreeNode tree, int x, int y) {
        if (tree == null) return;

        String holder = String.valueOf(tree.getVal());
        g.drawString(holder, x, y);

        if (tree.getLeft() != null) {
            drawTree(g, tree.getLeft(), x - 70, y + 50);
            g.drawLine(x,y,x-70,y+50);
        }
        if (tree.getRight() != null) {
            drawTree(g, tree.getRight(), x +70, y + 50);
            g.drawLine(x,y,x+70,y+50);
        }
    }
}
