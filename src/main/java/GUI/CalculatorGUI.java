package GUI;

import Operations.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorGUI extends JFrame {

    /**I am considering that this is the UI class. I have to have write more than 30 lines in the body of some methods because
     * for describing the functionality of each button. */
    private final JPanel myPanel = new JPanel();
    private final JButton[] digits = new JButton[10]; // 10 digits
    private final JButton[] operations = new JButton[6]; //-, +, *, /, integration, derivation => 6 operations
    private final JButton[] innerOperations = new JButton[9]; //-, +, *, /, ., X, ^, delete and clear => 9 operations
    private final JButton shortcut = new JButton("*x^");
    private final JTextField poly1 = new JTextField();
    private final JTextField poly2 = new JTextField();
    private final JTextField result = new JTextField();

    private final JLabel poly1Label = new JLabel("1st polynomial:");
    private final JLabel poly2Label = new JLabel("2nd polynomial:");
    private final JLabel resultLabel = new JLabel("Result:");

    private final JLabel selectedPoly = new JLabel("Selected poly.:");
    private final JRadioButton poly1sel = new JRadioButton("1st");
    private final JRadioButton poly2sel = new JRadioButton("2nd");
    private final ButtonGroup group = new ButtonGroup();

    private static final int WIDTH_BUTTON = 70;
    private static final int HEIGHT_START_BUTTON = 300;
    private static final int HEIGHT_BUTTON = 50;
    private static final int HEIGHT_OPERATION_BUTTON = 40;
    private static final int SPACE_BETWEEN = 5;
    private final Font defaultFont = new Font("Aerial", Font.BOLD, 20);
    private final Font polySelFont = new Font("Serif", Font.PLAIN, 15);

    public CalculatorGUI () {
        Font defaultPolyFont = new Font("Serif", Font.PLAIN, 20);
        poly1Label.setFont(defaultPolyFont);
        poly2Label.setFont(defaultPolyFont);
        resultLabel.setFont(defaultPolyFont);
        poly1Label.setHorizontalAlignment(JLabel.CENTER);
        poly1Label.setVerticalAlignment(JLabel.CENTER);
        poly2Label.setHorizontalAlignment(JLabel.CENTER);
        resultLabel.setHorizontalAlignment(JLabel.CENTER);
        //also disable the keyboard events from text fields
        poly1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyCode() != KeyEvent.VK_BACK_SPACE && e.getKeyCode() != KeyEvent.VK_LEFT && e.getKeyCode() != KeyEvent.VK_RIGHT)
                    e.consume();  // ignore event
            }
        });
        poly2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyCode() != KeyEvent.VK_BACK_SPACE && e.getKeyCode() != KeyEvent.VK_LEFT && e.getKeyCode() != KeyEvent.VK_RIGHT)
                    e.consume();  // ignore event
            }
        });
        result.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                e.consume();  // ignore event
            }
        });
        initComponents();
    }

    private void initComponents() {
        //here I want to create the entire frame or aspect of the actual calculator.
        this.myPanel.setLayout(null);
        this.setVisible(true);
        this.setBounds(400, 200, 6 * WIDTH_BUTTON + 10 * SPACE_BETWEEN + 1, 570);
        this.myPanel.setBounds(0, 0, 6 * WIDTH_BUTTON + 10 * SPACE_BETWEEN + 1, 570);

        this.myPanel.setBackground(new Color(255, 255, 245));

        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("My Polynomial Calculator :)");

        this.initButtons(); //initialize the buttons
        this.buttonsGUI_1(); //now draw the buttons
        this.buttonsGUI_2();
        this.buttonsGUI_3();
        this.polynomials();
        this.polySelection();

        this.addAllComponents();

        this.add(myPanel);
        this.myPanel.setVisible(true);

    }

    private void polySelection() {
        selectedPoly.setFont(polySelFont);
        selectedPoly.setBounds(5, 125, 135, 20);
        selectedPoly.setHorizontalAlignment(JLabel.CENTER);

        group.add(poly1sel);
        group.add(poly2sel);

        poly1sel.setBounds(140, 125, 100, 20);
        poly1sel.setBackground(new Color(255, 255, 245));
        poly2sel.setBounds(310, 125, 100, 20);
        poly2sel.setBackground(new Color(255, 255, 245));
    }

    private void initButtons() {
        //DIGITS
        for (int i = 0; i < 10; i++) {
            digits[i] = new JButton("" + i);
            digits[i].setBackground(new Color(255, 205, 205));
            digits[i].setFont(defaultFont);
        }
        //GENERAL OPERATIONS
        operations[0] = new JButton("Addition");
        operations[1] = new JButton("Subtraction");
        operations[2] = new JButton("Derivation");
        operations[3] = new JButton("Multiplication");
        operations[4] = new JButton("Division");
        operations[5] = new JButton("Integration");
        for (int i = 0; i < 6; i++) {
            operations[i].setBackground(new Color(175, 155, 215));
            operations[i].setFont(defaultFont);
        }
        //INNER OPERATIONS
        innerOperations[0] = new JButton("Del");
        innerOperations[1] = new JButton(".");
        innerOperations[2] = new JButton("x");
        innerOperations[3] = new JButton("^");
        innerOperations[4] = new JButton("+");
        innerOperations[5] = new JButton("-");
        innerOperations[6] = new JButton("*");
        innerOperations[7] = new JButton("/");
        innerOperations[8] = new JButton("Clear");

        for (int i = 0; i < 9; i++) {
            innerOperations[i].setBackground(new Color(209, 255, 210));
            innerOperations[i].setFont(defaultFont);
        }
        shortcut.setBackground(new Color(209, 255, 210));
        shortcut.setFont(defaultFont);
    }

    private void buttonsGUI_1() {
        for (int i = 1; i < 10; i++) {
            if (i % 3 == 0)
                digits[i].setBounds(3 * SPACE_BETWEEN + 2 * WIDTH_BUTTON, HEIGHT_START_BUTTON + (SPACE_BETWEEN + HEIGHT_BUTTON) * ((i - 1)/3), WIDTH_BUTTON, HEIGHT_BUTTON);
            else
                digits[i].setBounds(SPACE_BETWEEN * (i % 3) + WIDTH_BUTTON * (i % 3 - 1), HEIGHT_START_BUTTON + (SPACE_BETWEEN + HEIGHT_BUTTON) * ((i - 1)/3), WIDTH_BUTTON, HEIGHT_BUTTON);
        }

        digits[0].setBounds(SPACE_BETWEEN * 2 + WIDTH_BUTTON, HEIGHT_START_BUTTON + (SPACE_BETWEEN + HEIGHT_BUTTON) * 3, WIDTH_BUTTON, HEIGHT_BUTTON);

        //initialize - and + buttons to the left and right of the 0 button
        innerOperations[0].setBounds(SPACE_BETWEEN, HEIGHT_START_BUTTON + (SPACE_BETWEEN + HEIGHT_BUTTON) * 3, WIDTH_BUTTON, HEIGHT_BUTTON);
        innerOperations[1].setBounds(3 * SPACE_BETWEEN + 2 * WIDTH_BUTTON, HEIGHT_START_BUTTON + (SPACE_BETWEEN + HEIGHT_BUTTON) * 3, WIDTH_BUTTON, HEIGHT_BUTTON);

        shortcut.setBounds(4 * SPACE_BETWEEN + 3 * WIDTH_BUTTON, HEIGHT_START_BUTTON, WIDTH_BUTTON, HEIGHT_BUTTON);
        shortcut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                shortcutPressed();
            }
        });

        digits[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                digit0Pressed();
            }
        });
        digits[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                digit1Pressed();
            }
        });
        digits[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                digit2Pressed();
            }
        });
        digits[3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                digit3Pressed();
            }
        });
        digits[4].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                digit4Pressed();
            }
        });
        digits[5].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                digit5Pressed();
            }
        });
        digits[6].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                digit6Pressed();
            }
        });
        digits[7].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                digit7Pressed();
            }
        });
        digits[8].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                digit8Pressed();
            }
        });
        digits[9].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                digit9Pressed();
            }
        });

        innerOperations[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                delPressed();
            }
        });
        innerOperations[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dotPressed();
            }
        });
    }

    private void buttonsGUI_2() {
        //int DEVIATION = (WIDTH_BUTTON + SPACE_BETWEEN) /2;
        for (int i = 2; i < 8; i++) {
            if (i % 2 == 0)
                innerOperations[i].setBounds(5 * SPACE_BETWEEN + 4 * WIDTH_BUTTON, HEIGHT_START_BUTTON + (SPACE_BETWEEN + HEIGHT_BUTTON) * (i/2 -1), WIDTH_BUTTON, HEIGHT_BUTTON);
            else
                innerOperations[i].setBounds(6 * SPACE_BETWEEN + 5 * WIDTH_BUTTON, HEIGHT_START_BUTTON + (SPACE_BETWEEN + HEIGHT_BUTTON) * (i/2 -1), WIDTH_BUTTON, HEIGHT_BUTTON);
        }
        innerOperations[8].setBounds(5 * SPACE_BETWEEN + 4 * WIDTH_BUTTON, HEIGHT_START_BUTTON + (SPACE_BETWEEN + HEIGHT_BUTTON) * 3, 2 *WIDTH_BUTTON + SPACE_BETWEEN, HEIGHT_BUTTON);

        innerOperations[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                xPressed();
            }
        });
        innerOperations[3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                powerPressed();
            }
        });
        innerOperations[4].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                plusPressed();
            }
        });
        innerOperations[5].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                minusPressed();
            }
        });
        innerOperations[6].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                multiplyPressed();
            }
        });
        innerOperations[7].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                divisionPressed();
            }
        });
        innerOperations[8].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearPressed();
            }
        });

    }

    private void buttonsGUI_3() {
        operations[0].setBounds(SPACE_BETWEEN, 150, 3 * WIDTH_BUTTON + 2 * SPACE_BETWEEN, HEIGHT_OPERATION_BUTTON);
        operations[1].setBounds(4 * SPACE_BETWEEN + 3 * WIDTH_BUTTON, 150, 3 * WIDTH_BUTTON + 2 * SPACE_BETWEEN, HEIGHT_OPERATION_BUTTON);

        operations[3].setBounds(SPACE_BETWEEN, 150 + HEIGHT_OPERATION_BUTTON + SPACE_BETWEEN, 3 * WIDTH_BUTTON + 2 * SPACE_BETWEEN, HEIGHT_OPERATION_BUTTON);
        operations[4].setBounds(4 * SPACE_BETWEEN + 3 * WIDTH_BUTTON, 150+ HEIGHT_OPERATION_BUTTON + SPACE_BETWEEN, 3 * WIDTH_BUTTON + 2 * SPACE_BETWEEN, HEIGHT_OPERATION_BUTTON);

        operations[2].setBounds(SPACE_BETWEEN, 150 + 2 * HEIGHT_OPERATION_BUTTON + 2 * SPACE_BETWEEN, 3 * WIDTH_BUTTON + 2 * SPACE_BETWEEN, HEIGHT_OPERATION_BUTTON);
        operations[5].setBounds(4 * SPACE_BETWEEN + 3 * WIDTH_BUTTON, 150 + 2 * HEIGHT_OPERATION_BUTTON + 2 * SPACE_BETWEEN, 3 * WIDTH_BUTTON + 2 * SPACE_BETWEEN, HEIGHT_OPERATION_BUTTON);

        operations[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                additionPressed();
            }
        });
        operations[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                subtractionPressed();
            }
        });
        operations[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                derivationPressed();
            }
        });
        operations[3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                multiplicationPressed();
            }
        });
        operations[4].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                polyDivisionPressed();
            }
        });
        operations[5].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                integrationPressed();
            }
        });

    }

    private void polynomials() {
        //labels
        poly1Label.setBounds(5, 15, 135, 25);
        poly2Label.setBounds(5, 55, 135, 25);
        resultLabel.setBounds(5, 95, 135, 25);

        poly1.setBounds(140, 15, 6 * (WIDTH_BUTTON + SPACE_BETWEEN) - 140, 25);
        poly2.setBounds(140, 55, 6 * (WIDTH_BUTTON + SPACE_BETWEEN) - 140, 25);
        result.setBounds(140, 95, 6 * (WIDTH_BUTTON + SPACE_BETWEEN) - 140, 25);
    }

    private void addAllComponents() {
        //DIGITS
        for (int i = 0; i < 10; i++) {
            myPanel.add(digits[i]);
            digits[i].setVisible(true);
        }
        //POLYNOMIAL OPERATIONS
        for (int i = 0; i < 6; i++) {
            myPanel.add(operations[i]);
            operations[i].setVisible(true);
        }
        //INNER OPERATIONS
        for (int i = 0; i < 9; i++) {
            myPanel.add(innerOperations[i]);
            innerOperations[i].setVisible(true);
        }

        myPanel.add(poly1sel);
        myPanel.add(poly2sel);
        myPanel.add(selectedPoly);
        myPanel.add(poly1);
        myPanel.add(poly1Label);
        myPanel.add(poly2);
        myPanel.add(poly2Label);
        myPanel.add(result);
        myPanel.add(resultLabel);
        myPanel.add(shortcut);

        poly1sel.setVisible(true);
        poly2sel.setVisible(true);
        selectedPoly.setVisible(true);
        poly1.setVisible(true);
        poly2.setVisible(true);
        resultLabel.setVisible(true);
        poly1Label.setVisible(true);
        poly2Label.setVisible(true);
        result.setVisible(true);
        shortcut.setVisible(true);
        myPanel.setVisible(true);

    }

    private boolean polySelected () {
        if (poly1sel.isSelected() || poly2sel.isSelected())
            return true;
        result.setText("Please select a polynomial.");
        return false;
    }

    //DIGITS
    private void digit0Pressed() {
        if(polySelected()) {
            String temp = poly1.getText();
            int len = temp.length();
            if (poly1sel.isSelected()) {
                if (temp.equals("0") || (len > 1 && ((temp.charAt(len - 2) == '+' || temp.charAt(len - 2) == '-' || temp.charAt(len - 2) == '*' || temp.charAt(len - 2) == '/' || temp.charAt(len - 2) == '^') && (temp.charAt(len - 1) == '0'))))
                    return;
                poly1.setText(poly1.getText() + "0");
            } else {
                temp = poly2.getText();
                len = temp.length();
                if (temp.equals("0") || (len > 1 && (temp.charAt(len - 2) == '+' || temp.charAt(len - 2) == '-' || temp.charAt(len - 2) == '*' || temp.charAt(len - 2) == '/' || temp.charAt(len - 2) == '^') && (temp.charAt(len - 1) == '0')))
                    return;
                poly2.setText(poly2.getText() + "0");
            }
        }
    }

    private void digit1Pressed() {
        if(polySelected()) {
            if (poly1sel.isSelected()) {
                poly1.setText(poly1.getText() + "1");
            }
            else
                poly2.setText(poly2.getText() + "1");
        }
    }

    private void digit2Pressed() {
        if(polySelected()) {
            if (poly1sel.isSelected()) {
                poly1.setText(poly1.getText() + "2");
            }
            else
                poly2.setText(poly2.getText() + "2");

        }
    }

    private void digit3Pressed() {
        if(polySelected()) {
            if (poly1sel.isSelected()) {
                poly1.setText(poly1.getText() + "3");
            }
            else
                poly2.setText(poly2.getText() + "3");
        }
    }

    private void digit4Pressed() {
        if(polySelected()) {
            if (poly1sel.isSelected()) {
                poly1.setText(poly1.getText() + "4");
            }
            else
                poly2.setText(poly2.getText() + "4");
        }
    }

    private void digit5Pressed() {
        if(polySelected()) {
            if (poly1sel.isSelected()) {
                poly1.setText(poly1.getText() + "5");
            }
            else
                poly2.setText(poly2.getText() + "5");
        }
    }

    private void digit6Pressed() {
        if(polySelected()) {
            if (poly1sel.isSelected()) {
                poly1.setText(poly1.getText() + "6");
            }
            else
                poly2.setText(poly2.getText() + "6");
        }
    }

    private void digit7Pressed() {
        if(polySelected()) {
            if (poly1sel.isSelected()) {
                poly1.setText(poly1.getText() + "7");
            }
            else
                poly2.setText(poly2.getText() + "7");
        }
    }

    private void digit8Pressed() {
        if(polySelected()) {
            if (poly1sel.isSelected()) {
                poly1.setText(poly1.getText() + "8");
            }
            else
                poly2.setText(poly2.getText() + "8");
        }
    }

    private void digit9Pressed() {
        if(polySelected()) {
            if (poly1sel.isSelected()) {
                poly1.setText(poly1.getText() + "9");
            }
            else
                poly2.setText(poly2.getText() + "9");
        }
    }

    //INNER OPERATIONS
    private void dotPressed() {
        if(polySelected()) {
            if (poly1sel.isSelected()) {
                poly1.setText(poly1.getText() + ".");
            }
            else
                poly2.setText(poly2.getText() + ".");
        }
    }

    private void delPressed() {
        if(polySelected()) {
            String temp = poly1.getText();
            int len = temp.length();
            if (poly1sel.isSelected() && len > 0) {
                temp = temp.substring(0, len - 1);
                poly1.setText(temp);
            }
            else {
                temp = poly2.getText();
                len = temp.length();
                if (poly2sel.isSelected() && len > 0) {
                    temp = temp.substring(0, len - 1);
                    poly2.setText(temp);
                }
            }
        }
    }

    private void xPressed() {
        if(polySelected()) {
            if (poly1sel.isSelected()) {
                poly1.setText(poly1.getText() + "x");
            }
            else
                poly2.setText(poly2.getText() + "x");
        }
    }

    private void powerPressed() {
        if(polySelected()) {
            if (poly1sel.isSelected()) {
                poly1.setText(poly1.getText() + "^");
            }
            else
                poly2.setText(poly2.getText() + "^");
        }
    }

    private void plusPressed() {
        if(polySelected()) {
            if (poly1sel.isSelected()) {
                poly1.setText(poly1.getText() + "+");
            }
            else
                poly2.setText(poly2.getText() + "+");
        }
    }

    private void minusPressed() {
        if(polySelected()) {
            if (poly1sel.isSelected()) {
                poly1.setText(poly1.getText() + "-");
            }
            else
                poly2.setText(poly2.getText() + "-");
        }
    }

    private void multiplyPressed() {
        if(polySelected()) {
            if (poly1sel.isSelected()) {
                poly1.setText(poly1.getText() + "*");
            }
            else
                poly2.setText(poly2.getText() + "*");
        }
    }

    private void divisionPressed() {
        if(polySelected()) {
            if (poly1sel.isSelected()) {
                poly1.setText(poly1.getText() + "/");
            }
            else
                poly2.setText(poly2.getText() + "/");
        }
    }

    private void shortcutPressed() {
        if(polySelected()) {
            if (poly1sel.isSelected()) {
                poly1.setText(poly1.getText() + "*x^");
            }
            else
                poly2.setText(poly2.getText() + "*x^");
        }
    }

    private void clearPressed() {
        result.setText("");
        poly1.setText("");
        poly2.setText("");
    }

    //POLYNOMIAL OPERATIONS
    private void additionPressed() {
        try {
            Addition add = new Addition(this.poly1.getText(), this.poly2.getText());
            if(add.getResult().polyToString().charAt(0) == '+')
                this.result.setText(add.getResult().polyToString().substring(1));
            else
                this.result.setText(add.getResult().polyToString());
        } catch (Exception e) {
            this.result.setText("The polynomials are not correctly introduced!");
        }
    }

    private void subtractionPressed() {
        try {
            Subtraction sub = new Subtraction(this.poly1.getText(), this.poly2.getText());
            if(sub.getResult().polyToString().charAt(0) == '+')
                this.result.setText(sub.getResult().polyToString().substring(1));
            else
                this.result.setText(sub.getResult().polyToString());
        } catch (Exception e) {
            this.result.setText("The polynomials are not correctly introduced!");
        }
    }

    private void derivationPressed() {
        try {
            if(poly1sel.isSelected()) {
                Derivation der1 = new Derivation(this.poly1.getText());
                this.setDerivationResult(der1);
            }
            else {
                Derivation der2 = new Derivation(this.poly2.getText());
                this.setDerivationResult(der2);
            }
        } catch (Exception e) {
            this.result.setText("The polynomials are not correctly introduced!");
        }
    }

    private void setDerivationResult (Derivation der) {
        if (der.getResult().polyToString().length() == 0) {
            this.result.setText("0");
            return;
        }
        if (der.getResult().polyToString().charAt(0) == '+')
            this.result.setText(der.getResult().polyToString().substring(1));
        else
            this.result.setText(der.getResult().polyToString());
    }

    private void multiplicationPressed() {
        try {
            Multiplication mult = new Multiplication(this.poly1.getText(), this.poly2.getText());
            if(mult.getResult().polyToString().charAt(0) == '+')
                this.result.setText(mult.getResult().polyToString().substring(1));
            else
                this.result.setText(mult.getResult().polyToString());
        } catch (Exception e) {
            this.result.setText("The polynomials are not correctly introduced!");
        }
    }

    private void polyDivisionPressed() {
        try {
            if(poly1sel.isSelected()) {
                Division div1= new Division(this.poly1.getText(), this.poly2.getText());
                this.setDivisionResult(div1);
            }
            else {
                Division div2 = new Division(this.poly2.getText(), this.poly1.getText());
                this.setDivisionResult(div2);
            }
        } catch (Exception e) {
            this.result.setText("The polynomials are not correctly introduced!");
        }
    }

    private void setDivisionResult(Division div) {
        if (div.getResult().polyToString().charAt(0) == '+')
            this.result.setText(div.getResult().polyToString().substring(1));
        else
            this.result.setText(div.getResult().polyToString());
        if(div.getRest().polyToString().length() != 0)
            result.setText(result.getText() + "  ;  " + div.getRest().polyToString());
        else
            result.setText(result.getText() + "  ;  0");
    }

    private void integrationPressed() {
        try {
            if(poly1sel.isSelected()) {
                Integration int1 = new Integration(this.poly1.getText());
                this.setIntegrationResult(int1);
            }
            else {
                Integration int2 = new Integration(this.poly2.getText());
                this.setIntegrationResult(int2);
            }
        } catch (Exception e) {
            this.result.setText("The polynomials are not correctly introduced!");
        }
    }

    private void setIntegrationResult(Integration integration) {
        if (integration.getResult().polyToString().charAt(0) == '+')
            this.result.setText(integration.getResult().polyToString().substring(1));
        else
            this.result.setText(integration.getResult().polyToString());
    }

}

