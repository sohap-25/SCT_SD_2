import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GuessTheNumberGame extends JFrame implements ActionListener {

    JLabel titleLabel, instructionLabel, resultLabel, attemptsLabel;
    JTextField guessField;
    JButton guessButton;

    int randomNumber;
    int attempts = 0;

    GuessTheNumberGame() {

        // Generate random number
        generateRandomNumber();

        // Frame settings
        setTitle("Guess The Number Game");
        setSize(400, 300);
        setLayout(new FlowLayout());
        getContentPane().setBackground(Color.LIGHT_GRAY);

        // Title
        titleLabel = new JLabel("Guess The Number Game");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Instructions
        instructionLabel = new JLabel("Guess a number between 1 and 100");

        // Text field
        guessField = new JTextField(15);

        // Guess button
        guessButton = new JButton("Guess");
        guessButton.addActionListener(this);

        // Result label
        resultLabel = new JLabel("Start guessing...");

        // Attempts label
        attemptsLabel = new JLabel("Attempts: 0");

        // Add components
        add(titleLabel);
        add(instructionLabel);
        add(guessField);
        add(guessButton);
        add(resultLabel);
        add(attemptsLabel);

        // Frame settings
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Generate random number
    void generateRandomNumber() {
        Random rand = new Random();
        randomNumber = rand.nextInt(100) + 1;
    }

    // Button action
    public void actionPerformed(ActionEvent e) {

        try {
            int guess = Integer.parseInt(guessField.getText());
            attempts++;

            attemptsLabel.setText("Attempts: " + attempts);

            if (guess < randomNumber) {
                resultLabel.setText("Too Low! Try Again.");
            } 
            else if (guess > randomNumber) {
                resultLabel.setText("Too High! Try Again.");
            } 
            else {

                int option = JOptionPane.showConfirmDialog(
                        this,
                        "Correct! You guessed the number in " + attempts +
                                " attempts.\nDo you want to play again?",
                        "You Won!",
                        JOptionPane.YES_NO_OPTION);

                if (option == JOptionPane.YES_OPTION) {

                    // Restart game
                    generateRandomNumber();
                    attempts = 0;

                    guessField.setText("");
                    resultLabel.setText("New Game Started!");
                    attemptsLabel.setText("Attempts: 0");

                } else {
                    System.exit(0);
                }
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a valid number!");
        }
    }

    // Main method
    public static void main(String[] args) {
        new GuessTheNumberGame();
    }
}