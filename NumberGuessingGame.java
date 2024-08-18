import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame {
    static int random_number;
    static int TotalRounds;
    static int GuessesLeft;
    static int userGuess;
    static JLabel label, guessLabel, headLabel;
    static JButton checkButton;
    static JTextField userText;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Number Guessing Game");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Random random = new Random();
        random_number = random.nextInt(100) + 1;

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(0, 240, 240)); // Set background color
        frame.add(panel);

        TotalRounds = 10;
        GuessesLeft = 10;

        headLabel = new JLabel("The number generated can be any one from 1 to 100.");
        headLabel.setFont(new Font("Arial", Font.BOLD, 28));
        headLabel.setForeground(new Color(0, 102, 204)); // Set text color

        label = new JLabel("You have " + GuessesLeft + " chances to guess the number.");
        label.setFont(new Font("Arial", Font.PLAIN, 22));
        label.setForeground(new Color(100, 100, 100)); // Set text color

        guessLabel = new JLabel("Start guessing the number.");
        guessLabel.setFont(new Font("Arial", Font.ITALIC, 22));
        guessLabel.setForeground(new Color(200, 100, 50)); // Set text color

        userText = new JTextField(24);
        userText.setFont(new Font("Arial", Font.PLAIN, 22));
        userText.setBackground(new Color(255, 255, 255)); // Set background color
        userText.setForeground(new Color(0, 0, 0)); // Set text color

        checkButton = new JButton("Check");
        checkButton.setFont(new Font("Arial", Font.BOLD, 22));
        checkButton.setBackground(new Color(200, 102, 204)); // Set background color
        checkButton.setForeground(new Color(255, 255, 255)); // Set text color
        checkButton.setFocusPainted(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(headLabel, gbc);

        gbc.gridy = 1;
        panel.add(label, gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel text = new JLabel("Your guess: ");
        text.setFont(new Font("Arial", Font.BOLD, 22));
        gbc.insets = new Insets(10, 10, 10, 2); // Reduce right inset to 2
        panel.add(text, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 2, 10, 10); // Reduce left inset to 2
        panel.add(userText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; // Center the guessLabel
        gbc.insets = new Insets(10, 10, 10, 10); // Reset insets for other components
        panel.add(guessLabel, gbc);

        gbc.gridy = 4;
        panel.add(checkButton, gbc);

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkButton.getText().equals("Restart")) {
                    restart();
                    return;
                }
                try {
                    userGuess = Integer.parseInt(userText.getText());
                    if (userGuess < random_number) {
                        if ((random_number - userGuess) > 50) {
                            guessLabel.setText("Your guess is too low!");
                        } else if ((random_number - userGuess) > 10) {
                            guessLabel.setText("Your guess is lower.");
                        } else if ((random_number - userGuess) > 5) {
                            guessLabel.setText("Your guess is bit lower.");
                        } else {
                            guessLabel.setText("Almost there, your guess is a little bit lower.");
                        }
                    } else if (userGuess > random_number) {
                        if ((userGuess - random_number) > 50) {
                            guessLabel.setText("Your guess is too high!");
                        } else if ((userGuess - random_number) > 10) {
                            guessLabel.setText("Your guess is higher.");
                        } else if ((userGuess - random_number) > 5) {
                            guessLabel.setText("Your guess is bit higher.");
                        } else {
                            guessLabel.setText("Almost there, your guess is a little bit higher.");
                        }
                    } else {
                        headLabel.setText("Congratulations, you guessed correctly!");
                        finalScore(true);
                        checkButton.setText("Restart");
                    }
                    GuessesLeft--;
                    if ((GuessesLeft > 0) && (userGuess != random_number)) {
                        label.setText("You have " + GuessesLeft + " more guesses. Keep guessing.");
                    } else if ((GuessesLeft <= 0) && (userGuess != random_number)) {
                        checkButton.setText("Restart");
                        finalScore(false);
                    }
                } catch (NumberFormatException ex) {
                    guessLabel.setText("Please enter a valid number.");
                }
            }
        });

        frame.setVisible(true);
    }

    public static void finalScore(boolean flag) {
        if (!flag) {
            headLabel.setText("You lost. You have no more guesses left.");
            guessLabel.setText("Better luck next time.");
            label.setText("Your final score is 0.");
            return;
        }
        if (GuessesLeft > 6) {
            guessLabel.setText("You got lucky...");
    } else if (GuessesLeft > 4) {
            guessLabel.setText("You were terrific.");
        } else if (GuessesLeft> 2) {
            guessLabel.setText("You were on point.");
        } else {
            guessLabel.setText("You did well.");
        }
        label.setText("Your final score is " + ((GuessesLeft > 0) ? (GuessesLeft * 100) : 100));
    }

    public static void restart() {
        Random random = new Random();
        random_number = random.nextInt(100) + 1;
        userText.setText("");
        GuessesLeft = 10;
        checkButton.setText("Check");
        label.setText("You have " + GuessesLeft + " guesses to guess the number.");
        guessLabel.setText("Let's start again.");
        headLabel.setText("The number generated can be any one from 1 to 100.");
    }
}
