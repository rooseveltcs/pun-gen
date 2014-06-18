
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
//Making of all button variables

    private TextField userTextField = new TextField();
    private Text textDisplay = new Text();
    private Button yesBtn = new Button();
    private Button noBtn = new Button();
    private GridPane grid = new GridPane();
    private GridPane punAddGrid = new GridPane();
    private Scene punLandia;
    private Scene addPun;
    private Button nextBtn = new Button();
    private Button thenBtn = new Button();
    private Button finallyBtn = new Button();
    private Button addBtn = new Button();
    private Button getBtn = new Button();
    private Stage secondaryStage = new Stage();
    private GridPane punAddGrid2 = new GridPane();
    private GridPane punAddGrid3 = new GridPane();
    private Stage thirdStage = new Stage();
    private Stage third = new Stage();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("PunGen");
        addBtn.setText("Add Pun");
        yesBtn.setText("Yes");
        noBtn.setText("No");
        addBtn.setOnAction(new EventHandler<ActionEvent>() { //button to initialize adding a new pun
            public void handle(ActionEvent event) {
                primaryStage.hide();
                System.out.println("You pressed the Add Pun button");
                punAddGrid.setAlignment(Pos.CENTER);
                punAddGrid.setHgap(10);
                punAddGrid.setVgap(10);
                punAddGrid.setPadding(new Insets(25, 25, 25, 25));
                addPun = new Scene(grid, 300, 100);
                Text punDisplay = new Text();
                punDisplay.setText("Enter the word you wish to add deary");
                TextField punTextField = new TextField();
                punAddGrid.add(punTextField, 5, 5);
                punAddGrid.add(punDisplay, 5, 4);
                nextBtn.setText("Next");
                punAddGrid.add(nextBtn, 6, 5);
                secondaryStage.setScene(addPun);
                secondaryStage.show(); //Does this
            }
        });
        nextBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                secondaryStage.hide();
                punAddGrid2.setAlignment(Pos.CENTER);
                punAddGrid2.setHgap(10);
                punAddGrid2.setVgap(10);
                punAddGrid2.setPadding(new Insets(25, 25, 25, 25));
                Scene punAdd2 = new Scene(grid, 300, 100);
                Text punDisplay = new Text();
                punDisplay.setText("Enter the pun you wish to add");
                TextField punTextField = new TextField();
                punAddGrid2.add(punTextField, 5, 5);
                punAddGrid2.add(punDisplay, 5, 4);
                thenBtn.setText("Next");
                punAddGrid2.add(nextBtn, 6, 5);
                thirdStage.setScene(punAdd2);
                thirdStage.show();
            }
        });

        thenBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                thirdStage.hide();
                punAddGrid3.setAlignment(Pos.CENTER);
                punAddGrid3.setHgap(10);
                punAddGrid3.setVgap(10);
                punAddGrid3.setPadding(new Insets(25, 25, 25, 25));
                Scene punAdd3 = new Scene(grid, 300, 100);
                Text punDisplay = new Text();
                punDisplay.setText("Here is your addition, thank you!");
                TextField punTextField = new TextField();
                punAddGrid3.add(punTextField, 5, 5);
                punAddGrid3.add(punDisplay, 5, 4);
                finallyBtn.setText("Finish");
                punAddGrid3.add(nextBtn, 6, 5);
                third.setScene(punAdd3);
                third.show();
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
