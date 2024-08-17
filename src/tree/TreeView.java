package tree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class TreeView extends JFrame implements ActionListener {
    public static  TreeNode tree = null;
    public static List<Integer> list = new ArrayList<>();
    JButton button4;
    JButton button3;
    JButton button2;
    JButton button1;
    NText textField1;
    NText textField2;
    NText textField3;
    RecursiveDraw bottomPanel ;
    JPanel topPanel;
    TreeService service;
    DfsDraw dfsDraw;
    private ActionEvent e;

    public TreeView(){
      //declaring objects(service,panel,frame,text area.....) and setting them up with their corresponding components
       service = new TreeService(tree);



       bottomPanel = new RecursiveDraw(tree);


        this.setTitle("Tree-GUI");
      this.setSize(1200, 400);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setLayout(new BorderLayout());
       topPanel = new JPanel(new GridLayout(1, 4));
      bottomPanel.add(new JScrollPane());
      bottomPanel.setVisible(true);

      //declaring buttons and text fields

       button1 = new JButton("Add Node");
       textField1 = new NText("give node to add");
       button2 = new JButton("Delete Node");
      textField2 = new NText("give node to delete");
       button3 = new JButton("DFS Search");
       textField3 = new NText("give node to search for");
       button4 = new JButton("Exit");
       dfsDraw=new DfsDraw(tree,list);

      //making the frame exist using button "escape"
      this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "escapeAction");
      this.getRootPane().getActionMap().put("escapeAction", escapeAction);

      //adding buttons and text fields to the top panel
      topPanel.add(button1);
      topPanel.add(textField1);
      topPanel.add(button2);
      topPanel.add(textField2);
      topPanel.add(button3);
      topPanel.add(textField3);
      topPanel.add(button4);
      //setting up the frame
      this.add(topPanel, BorderLayout.NORTH);
      this.add(bottomPanel, BorderLayout.CENTER);
    this.add(dfsDraw,BorderLayout.EAST);
      this.setVisible(true);
        textField1.addActionListener(this);
        textField2.addActionListener(this);
        textField3.addActionListener(this);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
  }
    //making text fields activate corresponding buttons
    public void actionPerformed(ActionEvent e) {
        //text fields
        if(e.getSource()==textField1) {
            button1.doClick();
            textField1.setText("");
        }
        else if(e.getSource()==textField2){
            button2.doClick();
            textField2.setText("");
        }
        else if(e.getSource()==textField3) {
            button3.doClick();
            textField3.setText("");
        }
        //buttons
        if(e.getSource()==button1) {
            this.revalidate();
            this.repaint();

            String valueholder = textField1.getText();
            try {
                int value = Integer.parseInt(valueholder);
                tree = service.addNode(tree, value);

                bottomPanel.setTree(tree);
                bottomPanel.repaint();
            } catch (NumberFormatException asba) {
                JOptionPane.showMessageDialog(this,"brother,give a number please.","error occured",JOptionPane.WARNING_MESSAGE);

            }
        }
        else if(e.getSource()==button2){
            String valueholder = textField2.getText();

            try {
                int value = Integer.parseInt(valueholder);
                if (!(service.searchTree(tree, value, null,this) instanceof Boolean) && tree != null) {
                    tree = service.deleteNode(tree, value, this);
                     bottomPanel.setTree(tree);
                    bottomPanel.repaint();
                }
            } catch (NumberFormatException |  NullPointerException p) {
                if (tree == null) {
                     JOptionPane.showMessageDialog(this,"brother,tree is empty.","error occurred",JOptionPane.WARNING_MESSAGE);

                }
                else {
                     JOptionPane.showMessageDialog(this,"brother,give a number please.","error occured",JOptionPane.WARNING_MESSAGE);
                }
            }
        }else if(e.getSource()==button3){
            try {
                String valueholder = textField3.getText();
                int value = Integer.parseInt(valueholder);
                if (tree == null) {

                    JOptionPane.showMessageDialog(this,"brother,tree is empty.","error occurred",JOptionPane.WARNING_MESSAGE);

                }
                if (service.searchTreeDFS(tree, value, list)) {

                         JOptionPane.showMessageDialog(this,"path is "+ list ,"Node path",JOptionPane.INFORMATION_MESSAGE);



                    list.clear();

                }
            } catch (NumberFormatException asba) {
                JOptionPane.showMessageDialog(this,"brother,give a number please.","error occured",JOptionPane.WARNING_MESSAGE);

             }

        }
        else if (e.getSource()==button4) {
            int x=JOptionPane.showOptionDialog(this,"هل أنت واثق?","\uD83E\uDD28 \uD83E\uDD28 \uD83E\uDD28 \uD83E\uDD28 \uD83E\uDD28",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,null,null,0);
            if(x==0) {
                System.exit(0);
            }
        }

    }

    Action escapeAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            button4.doClick();
        }
    };








}
