import java.util.Scanner;

public class numberGame {
 public static void main(String[] args) {
Scanner scanner = new Scanner(System.in);

int randomNumber = (int) (Math.random()*100)+1;
System.out.println("Enter the number between 1 to 100");
while(true) {
System.out.println("Enter user's guess: ");
 int userGuess =  scanner.nextInt();
//compare the users guess with the generated number and provide feedback
if(userGuess == randomNumber) {
System.out.println("Congratulations! You guessed the correct number:"+randomNumber);
break;
}
else if(userGuess<randomNumber) {
System.out.println("Sorry, Your guess is too low. Try again");
} else {
System.out.println("Sorry, Your guess is too high. Try agin.");
}
}
}
}
