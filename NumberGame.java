import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        final int MAX_ATTEMPTS = 7;
        int score = 0;
        int round = 1;

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String playAgain;

        do {
            int numberToGuess = random.nextInt(100) + 1;  // Random number between 1 and 100
            int attemptsLeft = MAX_ATTEMPTS;
            boolean guessedCorrectly = false;

            System.out.println("\n--- Round " + round + " ---");
            System.out.println("Guess the number between 1 and 100. You have " + MAX_ATTEMPTS + " attempts.");

            while (attemptsLeft > 0) {
                System.out.print("Enter your guess: ");

                // Check for valid integer input
                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.next();  // Clear invalid input
                    continue;
                }

                int userGuess = scanner.nextInt();

                // Check if guess is within range
                if (userGuess < 1 || userGuess > 100) {
                    System.out.println("Your guess is out of range. Please guess between 1 and 100.");
                    continue;
                }

                if (userGuess == numberToGuess) {
                    System.out.println("🎉 Congratulations! You guessed the correct number!");
                    guessedCorrectly = true;
                    score++;
                    break;
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low.");
                } else {
                    System.out.println("Too high.");
                }

                attemptsLeft--;
                System.out.println("Attempts left: " + attemptsLeft);
            }

            if (!guessedCorrectly) {
                System.out.println("❌ You've run out of attempts. The correct number was: " + numberToGuess);
            }

            System.out.println("Your current score: " + score);

            System.out.print("Do you want to play another round? (yes/no): ");
            scanner.nextLine();  // Consume newline left-over
            playAgain = scanner.nextLine().trim().toLowerCase();
            round++;

        } while (playAgain.equals("yes") || playAgain.equals("y"));

        System.out.println("Thanks for playing! Your final score is: " + score);
        scanner.close();
    }
}
