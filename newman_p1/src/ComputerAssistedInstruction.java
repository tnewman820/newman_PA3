import java.security.SecureRandom;
import java.util.Scanner;

public class ComputerAssistedInstruction {
    public static void randResponse(SecureRandom rand, boolean right){
        int num;
        num = rand.nextInt(4);
        if(right) {
            switch (num) {
                case 0:
                    System.out.println("Very good!");
                    break;
                case 1:
                    System.out.println("Excellent!");
                    break;
                case 2:
                    System.out.println("Nice Work!");
                    break;
                case 3:
                    System.out.println("Keep up the good work!");
                    break;
            }
        }
        else {
            switch (num) {
                case 0:
                    System.out.println("No. Please try again.");
                    break;
                case 1:
                    System.out.println("Wrong. Try once more.");
                    break;
                case 2:
                    System.out.println("Don't give up!");
                    break;
                case 3:
                    System.out.println("No. Keep trying.");
                    break;
            }
        }
    }
    public static int askQuestion(Scanner scnr, SecureRandom rand, int diffLevel, int questionType){
        int num1, num2;
        double userAnswer = 0, rightAnswer = 0, withinError;
        boolean right;
        //set random number bound by difficulty level
        if(diffLevel == 1) {
            num1 = rand.nextInt(10);
            num2 = rand.nextInt(10);
        }
        else if(diffLevel == 2) {
            num1 = rand.nextInt(100);
            num2 = rand.nextInt(100);
        }
        else if(diffLevel == 3) {
            num1 = rand.nextInt(1000);
            num2 = rand.nextInt(1000);
        }
        else{
            num1 = rand.nextInt(10000);
            num2 = rand.nextInt(10000);
        }
        //if the type is random, replace question type with a random number from 0 - 4
        if(questionType == 5){
            questionType = (rand.nextInt(4) + 1);
        }
        //if the type is 1-4 set right answer, print question and get answer
        if(questionType == 1) {
            rightAnswer = num1 + num2;
            System.out.printf("How much is %d plus %d?\n", num1, num2);
            userAnswer = scnr.nextDouble();
        }
        else if(questionType == 2) {
            rightAnswer = num1 * num2;
            System.out.printf("How much is %d times %d?\n", num1, num2);
            userAnswer = scnr.nextDouble();
        }
        else if(questionType == 3) {
            rightAnswer = num1 - num2;
            System.out.printf("How much is %d minus %d?\n", num1, num2);
            userAnswer = scnr.nextDouble();
        }
        else {
            //no dividing by 0
            if(num2 == 0){
                num2++;
            }
            rightAnswer = (double)num1 / (double)num2;
            System.out.printf("How much is %d divided by %d?\n", num1, num2);
            userAnswer = scnr.nextDouble();
        }
        //check user answer against the right answer
        //added answer is correct if within 2 sig figs for division
        withinError = Math.abs(rightAnswer - userAnswer);
        if(withinError <= 1e-2) {
            randResponse(rand, true);
            return 1;
        }
        else if(rightAnswer == userAnswer){
            //if right pass to response method
            randResponse(rand, true);
            return 1;
        }
        else {
            //if wrong pass to response method
            randResponse(rand, false);
            return 0;
        }
    }
    //method to select the desired difficulty, returns the difficulty level
    public static int diffSelect(Scanner scnr){
        int diffLevel;
        System.out.println("Select a difficulty level:\n1:One digit 2:Two digits 3:Three digits 4:Four Digits");
        diffLevel = scnr.nextInt();
        return diffLevel;
    }
    //method to select the desired question type, returns the question type number
    public static int typeSelect(Scanner scnr){
        int questionType;
        System.out.println("Select a question type:\n1:Addition 2:Multiplication 3:Subtraction 4:Division 5:Random mix");
        questionType = scnr.nextInt();
        return questionType;
    }
    //main method
    public static void main(String[] args) {
        SecureRandom rand = new SecureRandom();
        Scanner scnr = new Scanner(System.in);
        int correct, diffLevel, questionType, newSession;
        double average;
        boolean session = true;
        //while loop, one per session
        while(session) {
            correct = 0;
            average = 0;
            //get diffLevel and questionType from their respective methods
            diffLevel = diffSelect(scnr);
            questionType = typeSelect(scnr);
            //for loop to ask 10 questions per session;
            for (int i = 0; i < 10; i++) {
                correct += askQuestion(scnr, rand, diffLevel, questionType);
            }
            //calculate average
            average = (correct / 10.0) * 100.0;
            //tell user how they did
            System.out.printf("\nYou got %d correct and %d incorrect out of 10 or %.02f%%.\n", correct, (10 - correct), average);
            //determine if ready to continue or needs help
            if (average < 75.0) {
                System.out.println("Please ask your teacher for extra help.");
            } else {
                System.out.println("Congratulations, you are ready to go to the next level!");
            }
            //ask if user wants to start a new session
            System.out.println("Would you like to start a new session?");
            System.out.println("To start a new session, enter 1, to exit enter any other number.");
            newSession = scnr.nextInt();
            if(newSession != 1){
                session = false;
            }
        }
        //indicate when program is exiting
        System.out.println("Goodbye!");
        scnr.close();
    }
}
