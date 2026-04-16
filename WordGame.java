import java.util.Random;
import java.util.Scanner;

public class WordGame {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    static String[] animals = {"tiger", "elephant", "giraffe"};
    static String[] food = {"pizza", "burger", "pasta"};
    static String[] movies = {"frozen", "avatar", "barbie"};

    public static void main(String[] args) {
        boolean playAgain = true;

        while (playAgain) {
            System.out.println("\n--- Word Guess Game ---");
            System.out.println("Choose a category:");
            System.out.println("1. Animals");
            System.out.println("2. Food");
            System.out.println("3. Movies");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            String word = getWord(choice);

            if (word == null) {
                System.out.println("Invalid category.");
                continue;
            }

            playRound(word);

            System.out.print("\nPlay again? (y/n): ");
            String answer = scanner.nextLine().trim();

            if (!answer.equalsIgnoreCase("y")) {
                playAgain = false;
            }
        }

        System.out.println("Thanks for playing!");
        scanner.close();
    }

    static String getWord(int choice) {
        switch (choice) {
            case 1:
                return animals[random.nextInt(animals.length)];
            case 2:
                return food[random.nextInt(food.length)];
            case 3:
                return movies[random.nextInt(movies.length)];
            default:
                return null;
        }
    }

    static void playRound(String word) {
        char[] guessedWord = new char[word.length()];
        int remainingGuesses = 6;
        String guessedLetters = "";

        for (int i = 0; i < guessedWord.length; i++) {
            if (word.charAt(i) == ' ') {
                guessedWord[i] = ' ';
            } else {
                guessedWord[i] = '_';
            }
        }

        while (remainingGuesses > 0) {
            System.out.print("\nWord: ");
            for (char c : guessedWord) {
                System.out.print(c + " ");
            }

            System.out.println("\nGuessed letters: " + guessedLetters);
            System.out.println("Remaining guesses: " + remainingGuesses);
            System.out.print("Guess a letter: ");

            String input = scanner.nextLine().trim().toLowerCase();

            if (input.isEmpty()) {
                System.out.println("Please enter a letter.");
                continue;
            }

            char guess = input.charAt(0);

            if (!Character.isLetter(guess)) {
                System.out.println("Please enter a valid letter.");
                continue;
            }

            if (guessedLetters.indexOf(guess) != -1) {
                System.out.println("You already guessed that letter!");
                continue;
            }

            guessedLetters += guess + " ";

            boolean found = false;

            for (int i = 0; i < word.length(); i++) {
                if (Character.toLowerCase(word.charAt(i)) == guess) {
                    guessedWord[i] = word.charAt(i);
                    found = true;
                }
            }

            if (found) {
                System.out.println("Good guess!");
            } else {
                remainingGuesses--;
                System.out.println("Wrong guess!");
            }

            if (String.valueOf(guessedWord).equalsIgnoreCase(word)) {
                System.out.println("\nYou won! The word was: " + word);
                return;
            }
        }

        System.out.println("\nGame Over!");
        System.out.println("The correct word was: " + word);
    }
}