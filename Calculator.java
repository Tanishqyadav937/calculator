import java.awt.*;
import javax.swing.*;

public class Calculator {
    int boardwidth = 360;
    int boardheight = 600;
    JFrame frame = new JFrame("Calculator");
    JTextField display;

    Calculator() {
        frame.setSize(boardwidth, boardheight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Display field at the top
        display = new JTextField("0");
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 32));
        frame.add(display, BorderLayout.NORTH);

        // Buttons panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 5, 5));
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 24));
            button.addActionListener(e -> handleButton(text));
            panel.add(button);
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // Simple button handler for demonstration
    private void handleButton(String text) {
        if (text.matches("[0-9]")) {
            if (display.getText().equals("0")) {
                display.setText(text);
            } else {
                display.setText(display.getText() + text);
            }
        } else if (text.equals("C")) {
            display.setText("0");
        } else if (text.equals(".")) {
            if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
        }
        // Additional operator logic can be added here
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculator());
    }
}
