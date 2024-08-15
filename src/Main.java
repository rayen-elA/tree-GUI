import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import tree.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static TreeNode tree = null;
    static List<Integer> list= new ArrayList<>();
    public static void main(String[] args) {

        TreeService service=new TreeService(tree);
        JPanel sidepanel=new JPanel();
        JTextArea sidepanelarea =new JTextArea();
       JScrollPane scrollPane=new JScrollPane(sidepanelarea);
       scrollPane.setPreferredSize(new Dimension(200,500));
        sidepanel.add( scrollPane);

        // Create the main frame
        JFrame frame = new JFrame("Split Panel Example");
        frame.setSize(1200, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create the top panel with GridLayout
        JPanel topPanel = new JPanel(new GridLayout(1, 4));

        // Create buttons and text fields
        JButton button1 = new JButton("Add Node");
        JTextField textField1 = new JTextField();
        JButton button2 = new JButton("Delete Node");
        JTextField textField2 = new JTextField();
        JButton button3 = new JButton("DFS Search");
        JTextField textField3 = new JTextField();
        JButton button4 = new JButton("Exit");
        textField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button1.doClick(); // Simulate a button click
            }
        });

// Adding action listener to textField2 to trigger button2's action
        textField2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button2.doClick(); // Simulate a button click
            }
        });

// Adding action listener to textField3 to trigger button3's action
        textField3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button3.doClick(); // Simulate a button click
            }
        });
        Action escapeAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button4.doClick(); // Simulate button4 click
            }
        };

// Bind the Escape key to the escapeAction in the root pane of the frame
        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "escapeAction");
        frame.getRootPane().getActionMap().put("escapeAction", escapeAction);

        // Add buttons and text fields to the top panel
        topPanel.add(button1);
        topPanel.add(textField1);
        topPanel.add(button2);
        topPanel.add(textField2);
        topPanel.add(button3);
        topPanel.add(textField3);
        topPanel.add(button4);

        // Create the bottom panel for drawing
        RecursiveDraw bottomPanel = new RecursiveDraw(tree);


        // Add action listeners to the buttons to display the text from the text fields
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.revalidate();
                frame.repaint();

                String valueholder=textField1.getText();
                try{
                    int value=Integer.parseInt(valueholder);
                    tree=service.addNode(tree,value);
                    sidepanelarea.append("value "+value+ " added.\n");
                    bottomPanel.setTree(tree);
                    bottomPanel.repaint();
                }
                catch(NumberFormatException asba){
                    sidepanelarea.append("brother,give a number please.\n");
                }



            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String valueholder=textField2.getText();
                TreeNode parent=null;
                try{
                    int value=Integer.parseInt(valueholder);
                    Thread.sleep(30);


                    if(!(service.searchTree(tree,value,parent,sidepanelarea)instanceof Boolean) && tree!=null) {
                        tree =service.deleteNode(tree,value,sidepanelarea);
                        sidepanelarea.append("value " + value + " deleted.\n");
                        bottomPanel.setTree(tree);
                        bottomPanel.repaint();
                    }
                }
                catch(NumberFormatException | InterruptedException | NullPointerException asba ){
                    if(tree==null)
                        sidepanelarea.append("tree is empty.\n");
                    else
                        sidepanelarea.append("brother,give a number please.\n");
                }
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                try{
                    String valueholder=textField3.getText();
                    int value=Integer.parseInt(valueholder);
                    if(service.searchTreeDFS(tree,value,list))
                    {
                        sidepanelarea.append("path is " +list+" \n");

                    }
                }
                catch(NumberFormatException asba){
                    sidepanelarea.append("brother,give a number please.\n");
                }


            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Close the frame
            }
        });

    bottomPanel.add(new JScrollPane());
        bottomPanel.setVisible(true);
        // Add the panels to the frame

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(bottomPanel, BorderLayout.CENTER);
        frame.add(sidepanel,BorderLayout.EAST);

        // Make the frame visible
        frame.setVisible(true);
    }
}