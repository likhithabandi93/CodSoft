import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

public class QuizApplicationWithTimer1 {
    private static Scanner scanner = new Scanner(System.in);
    private static Timer timer = new Timer();
    private static int timeLimit = 10; // Time limit for each question (in seconds)
    private static int score = 0;
    private static int correctAnswers = 0;
    private static int incorrectAnswers = 0;
    private static int currentQuestionIndex = 0;

    static class Question {
        String questionText;
        String[] options;
        int correctOptionIndex;

        public Question(String questionText, String[] options, int correctOptionIndex) {
            this.questionText = questionText;
            this.options = options;
            this.correctOptionIndex = correctOptionIndex;
        }
    }

    private static Question[] questions = {
        new Question("What is the capital of France?", new String[]{"A. London", "B. Paris", "C. Rome", "D. Berlin"}, 1),
        new Question("Who is the author of Harry Potter series?", new String[]{"A. J.R.R. Tolkien", "B. J.K. Rowling", "C. George R.R. Martin", "D. Dan Brown"}, 1),
        new Question("What is the chemical symbol for water?", new String[]{"A. H2O", "B. CO2", "C. O2", "D. CH4"}, 0)
    };

    public static void main(String[] args) {
        System.out.println("Welcome to the Quiz!");
        System.out.println("You have " + timeLimit + " seconds to answer each question.\n");

        askQuestion();
    }

    private static void askQuestion() {
        if (currentQuestionIndex < questions.length) {
            Question currentQuestion = questions[currentQuestionIndex];
            System.out.println("Question " + (currentQuestionIndex + 1) + ": " + currentQuestion.questionText);
            // Display options
            for (int i = 0; i < currentQuestion.options.length; i++) {
                System.out.println(currentQuestion.options[i]);
            }

            CountDownLatch latch = new CountDownLatch(1);
            Thread inputThread = new Thread(() -> {
                getUserAnswer(latch);
            });
            inputThread.start();

            try {
                latch.await(); // Wait for user input or timeout
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                inputThread.interrupt(); // Interrupt input thread to prevent it from running after timeout
            }
        } else {
            finishQuiz();
        }
    }

    private static void startTimer() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up!");
                checkAnswer(-1); // Time's up, check with invalid answer index
            }
        }, timeLimit * 1000);
    }

    private static void getUserAnswer(CountDownLatch latch) {
        System.out.print("Your answer (enter option letter): ");
        String userAnswer = scanner.nextLine().toUpperCase();
        int userAnswerIndex = userAnswer.charAt(0) - 'A';
        checkAnswer(userAnswerIndex);
        latch.countDown();
    }

    private static void checkAnswer(int userAnswerIndex) {
        timer.cancel(); // Cancel the timer

        Question currentQuestion = questions[currentQuestionIndex];

        if (userAnswerIndex == currentQuestion.correctOptionIndex) {
            System.out.println("Correct!");
            score++;
            correctAnswers++;
        } else if (userAnswerIndex == -1) {
            System.out.println("Time's up! The correct answer is: " + currentQuestion.options[currentQuestion.correctOptionIndex]);
            incorrectAnswers++;
        } else {
            System.out.println("Incorrect. The correct answer is: " + currentQuestion.options[currentQuestion.correctOptionIndex]);
            incorrectAnswers++;
        }

        System.out.println();
        currentQuestionIndex++;
        askQuestion();
    }

    private static void finishQuiz() {
        System.out.println("Quiz completed!");
        System.out.println("Your score: " + score + " out of " + questions.length);
        System.out.println("Summary:");
        System.out.println("Correct answers: " + correctAnswers);
        System.out.println("Incorrect answers: " + incorrectAnswers);
        scanner.close();
    }
}
