package tree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class NText extends JTextField implements FocusListener {
    private final String placeholder;
    private boolean showingPlaceholder;

    public NText(String placeholder) {
        this.placeholder = placeholder;
        this.showingPlaceholder = true;
        this.setText(placeholder);
        this.setForeground(Color.GRAY);  // Placeholder color
        this.addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (showingPlaceholder) {
            this.setText("");
            this.setForeground(Color.BLACK);  // Text color
            showingPlaceholder = false;
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (this.getText().isEmpty()) {
            this.setText(placeholder);
            this.setForeground(Color.GRAY);  // Placeholder color
            showingPlaceholder = true;
        }
    }

    @Override
    public String getText() {
        return showingPlaceholder ? "" : super.getText();
    }
}


