package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ToloGameFrame extends JFrame {
    private JComboBox<String> gameTypeComboBox;
    private JPanel inputPanel;
    private JTextField num1BetField, num2BetField, num3BetField, num4BetField, betMoneyBetField;
    private JTextField num1SuperBetField, num2SuperBetField, num3SuperBetField, num4SuperBetField, luckyNumField, betMoneySuperBetField;
    private JButton playButton;
    private JButton clearButton;
    private Tolo tolo;

    public ToloGameFrame() {
        tolo = new Tolo();
        setTitle("Tolo Lottery Game");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        panel.add(new JLabel("Choose Game Type:"));
        String[] gameTypes = {"Bet", "Super Bet"};
        gameTypeComboBox = new JComboBox<>(gameTypes);
        panel.add(gameTypeComboBox);

        // Create an input panel for dynamic input fields
        inputPanel = new JPanel(new CardLayout());

        JPanel betPanel = createBetPanel();
        JPanel superBetPanel = createSuperBetPanel();

        inputPanel.add(betPanel, "Bet");
        inputPanel.add(superBetPanel, "Super Bet");

        // Initially, show the Bet panel
        CardLayout cardLayout = (CardLayout) inputPanel.getLayout();
        cardLayout.show(inputPanel, "Bet");

        panel.add(inputPanel);

        // Add a change listener to the gameTypeComboBox
        gameTypeComboBox.addActionListener(new GameTypeChangeListener());

        playButton = new JButton("Play");
        playButton.addActionListener(new PlayButtonListener());
        panel.add(playButton);

        add(panel);
        setVisible(true);
    }

    private JPanel createBetPanel() {
        JPanel betPanel = new JPanel();
        betPanel.setLayout(new GridLayout(5, 2));

        betPanel.add(new JLabel("Number 1:"));
        num1BetField = new JTextField();
        betPanel.add(num1BetField);

        betPanel.add(new JLabel("Number 2:"));
        num2BetField = new JTextField();
        betPanel.add(num2BetField);

        betPanel.add(new JLabel("Number 3:"));
        num3BetField = new JTextField();
        betPanel.add(num3BetField);

        betPanel.add(new JLabel("Number 4:"));
        num4BetField = new JTextField();
        betPanel.add(num4BetField);

        betPanel.add(new JLabel("Bet Money:"));
        betMoneyBetField = new JTextField();
        betPanel.add(betMoneyBetField);

        return betPanel;
    }

    private JPanel createSuperBetPanel() {
        JPanel superBetPanel = new JPanel();
        superBetPanel.setLayout(new GridLayout(6, 2));

        superBetPanel.add(new JLabel("Number 1:"));
        num1SuperBetField = new JTextField();
        superBetPanel.add(num1SuperBetField);

        superBetPanel.add(new JLabel("Number 2:"));
        num2SuperBetField = new JTextField();
        superBetPanel.add(num2SuperBetField);

        superBetPanel.add(new JLabel("Number 3:"));
        num3SuperBetField = new JTextField();
        superBetPanel.add(num3SuperBetField);

        superBetPanel.add(new JLabel("Number 4:"));
        num4SuperBetField = new JTextField();
        superBetPanel.add(num4SuperBetField);

        superBetPanel.add(new JLabel("Lucky Number:"));
        luckyNumField = new JTextField();
        superBetPanel.add(luckyNumField);

        superBetPanel.add(new JLabel("Bet Money:"));
        betMoneySuperBetField = new JTextField();
        superBetPanel.add(betMoneySuperBetField);

        return superBetPanel;
    }

    private class PlayButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedGameType = (String) gameTypeComboBox.getSelectedItem();

            try {
                if (selectedGameType.equals("Bet")) {
                    // Handle Bet game input
                    int num1 = Integer.parseInt(num1BetField.getText());
                    int num2 = Integer.parseInt(num2BetField.getText());
                    int num3 = Integer.parseInt(num3BetField.getText());
                    int num4 = Integer.parseInt(num4BetField.getText());
                    int userBetMoney = Integer.parseInt(betMoneyBetField.getText());

                    if (isValidNumber(num1, num2, num3, num4)) {
                        ArrayList<Integer> userNumbers = new ArrayList<>();
                        userNumbers.add(num1);
                        userNumbers.add(num2);
                        userNumbers.add(num3);
                        userNumbers.add(num4);

                        if(isValidBet(userBetMoney)){
                            Bet bet = new Bet(userNumbers, userBetMoney);
                            int gain = bet.computeGain(tolo);
                            JOptionPane.showMessageDialog(ToloGameFrame.this, "Your gain is: " + gain);
                        } else {
                            JOptionPane.showMessageDialog(ToloGameFrame.this, "Invalid bet. Please enter bet amount greater than zero.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(ToloGameFrame.this, "Invalid input. Please enter valid numbers between 1 to 20.");
                    }
                } else if (selectedGameType.equals("Super Bet")) {
                    // Handle Super Bet game input
                    int num1 = Integer.parseInt(num1SuperBetField.getText());
                    int num2 = Integer.parseInt(num2SuperBetField.getText());
                    int num3 = Integer.parseInt(num3SuperBetField.getText());
                    int num4 = Integer.parseInt(num4SuperBetField.getText());
                    int userLuckyNumber = Integer.parseInt(luckyNumField.getText());
                    int userBetMoney = Integer.parseInt(betMoneySuperBetField.getText());

                    if (isValidNumber(num1, num2, num3, num4)) {
                        ArrayList<Integer> userNumbers = new ArrayList<>();
                        userNumbers.add(num1);
                        userNumbers.add(num2);
                        userNumbers.add(num3);
                        userNumbers.add(num4);

                        if (isValidLuckyNumber(userLuckyNumber)) {
                            if(isValidBet(userBetMoney)) {
                                SuperBet superBet = new SuperBet(userNumbers, userLuckyNumber, userBetMoney);
                                int gain = superBet.computeGain(tolo);
                                JOptionPane.showMessageDialog(ToloGameFrame.this, "Your gain is: " + gain);
                            } else {
                                JOptionPane.showMessageDialog(ToloGameFrame.this, "Invalid bet. Please enter bet amount greater than zero.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(ToloGameFrame.this, "Invalid input. Please enter valid lucky number between 1 to 10.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(ToloGameFrame.this, "Invalid input. Please enter valid numbers between 1 to 20.");
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(ToloGameFrame.this, "Invalid input. Please enter valid numbers.");
            }
        }
    }

    private class GameTypeChangeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Switch the input panel based on the selected game type
            String selectedGameType = (String) gameTypeComboBox.getSelectedItem();
            CardLayout cardLayout = (CardLayout) inputPanel.getLayout();
            cardLayout.show(inputPanel, selectedGameType);
        }
    }

    private boolean isValidNumber(int... values) {
        for (int value : values) {
            if (value < 1 || value > 20) {
                return false; // Numbers should be between 1 and 20
            }
        }
        return true;
    }

    private boolean isValidLuckyNumber(int... values) {
        for (int value : values) {
            if (value < 1 || value > 10) {
                return false; // Numbers should be between 1 and 10
            }
        }
        return true;
    }

    private boolean isValidBet(int value) {
        if(value < 1){
            return false;
        }
        return true;
    }
}

