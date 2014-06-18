/**
 * Project: PunGen
 * File: Main.java
 * @author Nick Trunkey, Michael Hannon
 * @version 061814
 *
 *
 */
import com.sun.javafx.scene.layout.region.BackgroundImage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.text.*;

public class Main extends Application {
    private TextField userTextField = new TextField();
    private Text textDisplay = new Text();
    private Button yesBtn = new Button();
    private Button noBtn = new Button();
    private GridPane grid = new GridPane();
    private Scene punLandia;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    /**
     * Starts the program
     * @param primaryStage sets a base stage for the nodes
     */
    public void start(Stage primaryStage) {

        primaryStage.setTitle("PunGen");
        final Button addBtn = new Button();
        final Button getBtn = new Button();


        addBtn.setText("Add Pun");
        yesBtn.setText("Yes");
        noBtn.setText("No");
        addBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("You pressed the Add Pun button");
                textDisplay.setText("Coming soon!");
                //punAdd();
            }
        });

        getBtn.setText("Get Pun");
        getBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                System.out.println("You pressed the Get Pun button");
                String keyword = userTextField.getText().toLowerCase();

                if (keyword.equals("add")) {
                    punAdd();
                } else {
                    if (keyword.equals("end")) {
                        System.exit(0);
                    } else {
                        Pun newPun = new Pun(keyword);
                        newPun.punFinder();
                        if (newPun.punFinder() == null) {
                            textDisplay.setText("Pun not found!");

                        } else {
                            textDisplay.setText(newPun.toString());
                            String answer = userTextField.getText().toLowerCase();
                            //String answer = input.next().toLowerCase();
                            if (addBtn.isPressed()) {
                                ratePun(newPun);
                            }
                        }
                    }
                }
            }
        });


        //Pane root = new Pane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        punLandia = new Scene(grid, 800, 600);
        punLandia.getStylesheets().addAll(this.getClass().getResource("styles.css").toExternalForm());
        textDisplay.setFont(new Font(14));
        grid.add(userTextField, 5, 3);
        grid.add(addBtn, 6, 4);
        grid.add(getBtn, 5, 4);
        grid.add(yesBtn, 5, 5);
        grid.add(noBtn, 6, 5);
        grid.add(textDisplay, 5, 7);
        getBtn.setLayoutX(80);
        getBtn.setLayoutY(200);
        addBtn.setLayoutX(170);
        addBtn.setLayoutY(200);
        userTextField.setLayoutX(80);
        userTextField.setLayoutY(180);
        primaryStage.setScene(punLandia);
        primaryStage.show();
        
    }

    //TODO
    private void punAdd() {
        String userKey = "";
        String userPun = "";
        textDisplay.setText("What is the keyword of your pun? (You can have more than one. Please separate each word with a ',')");
        userTextField.clear();
        if(yesBtn.isPressed()) {

            userKey = userTextField.getText();
            if(userKey.equals("")){
                textDisplay.setText("That is not a valid word!");
                userTextField.clear();
            }
        }
        textDisplay.setText("Okay, what about the pun to go with it?");
        userTextField.clear();
        if(yesBtn.isPressed()) {
            userPun = userTextField.getText();
            if(userPun.equals("")){
                textDisplay.setText("That is not a valid pun!");
                userTextField.clear();
            }
        }
        textDisplay.setText("Are you sure this is what you want: \n" + userKey+ ": " + userPun);
        userTextField.clear();
        if(yesBtn.isPressed()){
            Pun userAddition = new Pun(userKey, userPun);
            userAddition.punAdder();
            textDisplay.setText("Thank you for you contribution! Your pun has been added.");
        }
        //String answer = userTextField.getText().toLowerCase();
        yesBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                yesBtn.setEffect(null);
            }
        });

        noBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                textDisplay.setText("Okay... Never mind then.");
            }
        });

    }

    //TODO
    /**
     * Should rate the pun
     * @param newPun Rates the pun
     */
    private void ratePun(Pun newPun) {
        System.out.println("What do you rate this pun? Enter 1(low)-5(high).");
        String tempRate = userTextField.getText();
        int rating = Integer.parseInt(tempRate);
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
            tempRate = userTextField.getText();
            rating = Integer.parseInt(tempRate);
            count++;
        }
        //Ah, back to important code!
        if (rating > 0 && rating <= 5) {
            newPun.ratePun(rating);
            System.out.println("Rating accepted!");
        }
    }



}
