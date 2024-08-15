import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import tree.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static TreeNode tree = null;
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        //declaring objects(service,panel,frame,text area.....) and setting them up with their corresponding components
        TreeService service = new TreeService(tree);
        JPanel sidepanel = new JPanel();
        JTextArea sidepanelarea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(sidepanelarea);
        scrollPane.setPreferredSize(new Dimension(200, 500));
        RecursiveDraw bottomPanel = new RecursiveDraw(tree);
        sidepanel.add(scrollPane);
        JFrame frame = new JFrame("Split Panel Example");
        frame.setSize(1200, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new GridLayout(1, 4));
        bottomPanel.add(new JScrollPane());
        bottomPanel.setVisible(true);

        //declaring buttons and text fields
        JButton button1 = new JButton("Add Node");
        JTextField textField1 = new JTextField();
        JButton button2 = new JButton("Delete Node");
        JTextField textField2 = new JTextField();
        JButton button3 = new JButton("DFS Search");
        JTextField textField3 = new JTextField();
        JButton button4 = new JButton("Exit");


        //making text fields activate corresponding buttons
        textField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button1.doClick();
            }
        });
        textField2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button2.doClick();
            }
        });
        textField3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button3.doClick();
            }
        });
        Action escapeAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button4.doClick();
            }
        };


        //making the frame exist using button "escape"
        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "escapeAction");
        frame.getRootPane().getActionMap().put("escapeAction", escapeAction);

        //adding buttons and text fields to the top panel
        topPanel.add(button1);
        topPanel.add(textField1);
        topPanel.add(button2);
        topPanel.add(textField2);
        topPanel.add(button3);
        topPanel.add(textField3);
        topPanel.add(button4);

        //making buttons do their corresponding work
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.revalidate();
                frame.repaint();

                String valueholder = textField1.getText();
                try {
                    int value = Integer.parseInt(valueholder);
                    tree = service.addNode(tree, value);
                    sidepanelarea.append("value " + value + " added.\n");
                    bottomPanel.setTree(tree);
                    bottomPanel.repaint();
                } catch (NumberFormatException asba) {
                    sidepanelarea.append("brother,give a number please.\n");
                }


            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String valueholder = textField2.getText();

                try {
                    int value = Integer.parseInt(valueholder);
                    if (!(service.searchTree(tree, value, null, sidepanelarea) instanceof Boolean) && tree != null) {
                        tree = service.deleteNode(tree, value, sidepanelarea);
                        sidepanelarea.append("value " + value + " deleted.\n");
                        bottomPanel.setTree(tree);
                        bottomPanel.repaint();
                    }
                } catch (NumberFormatException |  NullPointerException p) {
                    if (tree == null)
                        sidepanelarea.append("tree is empty.\n");
                    else
                        sidepanelarea.append("brother,give a number please.\n");
                }
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                try {
                    String valueholder = textField3.getText();
                    int value = Integer.parseInt(valueholder);
                    if (service.searchTreeDFS(tree, value, list)) {
                        sidepanelarea.append("path is " + list + " \n");

                    }
                } catch (NumberFormatException asba) {
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

        //setting up the frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(bottomPanel, BorderLayout.CENTER);
        frame.add(sidepanel, BorderLayout.EAST);
        frame.setVisible(true);
    }
}