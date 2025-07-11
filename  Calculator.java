import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private double firstNum = 0, secondNum = 0, result = 0;
    private String operator = "";
    private boolean startNewNumber = true;

    private final JButton[] numButtons = new JButton[10];
    private final JButton addButton = new JButton("+");
    private final JButton subButton = new JButton("-");
    private final JButton mulButton = new JButton("*");
    private final JButton divButton = new JButton("/");
    private final JButton decButton = new JButton(".");
    private final JButton equButton = new JButton("=");
    private final JButton delButton = new JButton("<");
    private final JButton clrButton = new JButton("C");
    private final JButton perButton = new JButton("%");
    private final JRadioButton onButton = new JRadioButton("On");
    private final JRadioButton offButton = new JRadioButton("Off");
    private final ButtonGroup group = new ButtonGroup();

    public Calculator() {
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(5, 4, 5, 5));
        for (int i = 1; i <= 9; i++) {
            numButtons[i] = createButton(String.valueOf(i));
            panel.add(numButtons[i]);
        }
        numButtons[0] = createButton("0");
        panel.add(decButton);  panel.add(numButtons[0]);
        panel.add(equButton);  panel.add(addButton);
        panel.add(subButton);  panel.add(mulButton);
        panel.add(divButton);  panel.add(perButton);
        panel.add(clrButton);  panel.add(delButton);

        add(panel, BorderLayout.CENTER);

        JPanel togglePanel = new JPanel();
        group.add(onButton); group.add(offButton);
        togglePanel.add(onButton); togglePanel.add(offButton);
        add(togglePanel, BorderLayout.SOUTH);

        for (JButton button : numButtons) button.addActionListener(this);
        addButton.addActionListener(this); subButton.addActionListener(this);
        mulButton.addActionListener(this); divButton.addActionListener(this);
        decButton.addActionListener(this); equButton.addActionListener(this);
        delButton.addActionListener(this); clrButton.addActionListener(this);
        perButton.addActionListener(this);

        onButton.addActionListener(e -> setCalculatorEnabled(true));
        offButton.addActionListener(e -> setCalculatorEnabled(false));
        onButton.setSelected(true);

        setVisible(true);
    }

    private void setCalculatorEnabled(boolean enabled) {
        display.setEnabled(enabled);
        for (JButton button : numButtons) button.setEnabled(enabled);
        addButton.setEnabled(enabled); subButton.setEnabled(enabled);
        mulButton.setEnabled(enabled); divButton.setEnabled(enabled);
        decButton.setEnabled(enabled); equButton.setEnabled(enabled);
        delButton.setEnabled(enabled); clrButton.setEnabled(enabled);
        perButton.setEnabled(enabled);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.matches("[0-9]")) {
            if (startNewNumber || display.getText().equals("0")) {
                display.setText(cmd);
                startNewNumber = false;
            } else {
                display.setText(display.getText() + cmd);
            }
        } else if (cmd.equals(".")) {
            if (startNewNumber) {
                display.setText("0.");
                startNewNumber = false;
            } else if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
        } else if (cmd.equals("C")) {
            display.setText("0");
            firstNum = secondNum = result = 0;
            operator = "";
            startNewNumber = true;
        } else if (cmd.equals("<")) {
            String text = display.getText();
            if (text.length() > 1) {
                display.setText(text.substring(0, text.length() - 1));
            } else {
                display.setText("0");
                startNewNumber = true;
            }
        } else if (cmd.equals("%")) {
            double value = Double.parseDouble(display.getText()) / 100.0;
            display.setText(String.valueOf(value));
            startNewNumber = true;
        } else if (cmd.equals("=")) {
            if (!operator.isEmpty()) {
                secondNum = Double.parseDouble(display.getText());
                switch (operator) {
                    case "+": result = firstNum + secondNum; break;
                    case "-": result = firstNum - secondNum; break;
                    case "*": result = firstNum * secondNum; break;
                    case "/":
                        if (secondNum == 0) {
                            display.setText("Error");
                            operator = "";
                            startNewNumber = true;
                            return;
                        }
                        result = firstNum / secondNum; break;
                }
                display.setText(String.valueOf(result));
                firstNum = result;
                operator = "";
                startNewNumber = true;
            }
        } else if ("+-*/".contains(cmd)) {
            firstNum = Double.parseDouble(display.getText());
            operator = cmd;
            startNewNumber = true;
        }
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculator::new);
    }