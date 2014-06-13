import java.util.Scanner;

/**
 * Created by Michael Hannon on 6/4/2014.
 * Alpha 1
 * Runs the PunGen.  Code like asking the user questions is handled here, and then it relays the answers to the
 * questions to the Pun class.
 */
public class PunClient {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to PunGen.");
        String keyword = "";
        while(!keyword.startsWith("n")) {
            System.out.println("Please enter a keyword, or type add to input your own pun! To exit type \"end.\"");
            keyword = input.next().toLowerCase();
            if(keyword.equals("add")) {
                punAdd();
            } else if (keyword.equals("ratings") || keyword.equals("rating")) {
                Pun ratingFinder = new Pun("rating");
                ratingFinder.toString();
            }else {
                if (keyword.equals("end")) {
                    System.exit(0);
                } else {
                    Pun newPun = new Pun(keyword);
                    newPun.punFinder();
                    if (newPun.punFinder() == null) {
                        System.out.println("Pun not found!");
                    } else {
                        System.out.println("\n" + newPun.toString() + "\n \n" + "Would you like to rate this pun?(yes or no)");
                        String answer = input.next().toLowerCase();
                        if (answer.startsWith("y")) {
                            ratePun(newPun, input);
                        }
                    }
                }
            }
        }
    } //    ^^^ look at all of those end braces!
    //Rates the pun
    private static void ratePun(Pun newPun, Scanner input) {
        System.out.println("What do you rate this pun? Enter 1(low)-5(high).");
        int rating = input.nextInt();
        int count = 0;
        // This entire while loop is for people who can't follow instructions. :)
        while (rating < 0 || rating > 5) {
            if (count < 3)
                System.out.println("Wrong! ");
            else if (count < 6)
                System.out.println("You'll get it eventually... ");
            else if (count < 9)
                System.out.println("What is wrong? Don't you understand? ");
            else if (count > 12)
                System.out.println("I believe in you. ");
            System.out.print("A number one through five! \n");
            rating = input.nextInt();
            count++;
        }
        //Ah, back to important code!
        if (rating > 0 && rating <= 5) {
            newPun.ratePun(rating);
            System.out.println("Rating accepted!");
        }
    }
    //Prompts the user to add a pun
    private static void punAdd() {
        Scanner input = new Scanner(System.in);
        System.out.println("What is the keyword of your pun? (You can have more than one)");
        String userKey = input.nextLine();
        System.out.println("Okay, what about the pun to go with it?");
        String userPun = input.nextLine();
        System.out.println("Are you sure this is what you want: \n" + userKey+ ": " + userPun);
        String answer = input.nextLine().toLowerCase();
        if(answer.startsWith("y")) {
            Pun userAddition = new Pun(userKey, userPun);
            userAddition.punAdder();
            System.out.println("Thank you for you contribution! Your pun has been added.");
        } else {
            System.out.println("Okay... Never mind then.");
        }
    }
}
