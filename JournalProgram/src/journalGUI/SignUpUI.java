package journalGUI;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.image.*;

import java.util.HashMap;
import java.util.Map;

import java.io.FileWriter;
import java.io.IOException;

public class SignUpUI extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		show(primaryStage);
	}

	public static void show(Stage primaryStage) {
		// TODO Auto-generated method stub
		System.out.println("Redirect to Sign-Up Page.");
		
		// Setting the background of sign-up page
		Image backgroundImage = new Image(" ");
		ImageView backgroundImageView = new ImageView(backgroundImage);
		backgroundImageView.fitWidthProperty().bind(primaryStage.widthProperty());
		backgroundImageView.fitHeightProperty().bind(primaryStage.heightProperty());
				
		StackPane mainPanel = new StackPane(backgroundImageView);
				
		// Creating the sign-up panel
		VBox signUpPanel = new VBox(20);
		signUpPanel.setAlignment(Pos.CENTER);
		BackgroundFill backgroundFill = new BackgroundFill(Color.rgb(191, 148, 92, 0.7), null, null);
		Background background = new Background(backgroundFill);
		signUpPanel.setBackground(background);
		signUpPanel.setMaxWidth(800);
		signUpPanel.setMaxHeight(450);
				
		// Creating the sign-up form components
		Label titleLabel = new Label("Sign-Up");
		titleLabel.setFont(Font.font("Bookman Old Style", FontWeight.BOLD, 45));
		titleLabel.setTextFill(Color.BLACK);
				
		TextField fullName = new TextField();
		TextField username = new TextField();
		TextField phoneNumber = new TextField();
		TextField address = new TextField();
		PasswordField newPassword = new PasswordField();
		PasswordField confirmPassword = new PasswordField();
		
		HBox fullNameBox = createLabeledTextField("Full Name: ", fullName);
		fullNameBox.setStyle("-fx-font-family: Bookman Old Style; -fx-font-weight: bold; -fx-font-size: 15px;");
		HBox usernameBox = createLabeledTextField("Username: ", username);
		usernameBox.setStyle("-fx-font-family: Bookman Old Style; -fx-font-weight: bold; -fx-font-size: 15px;");
		HBox phoneNumberBox = createLabeledTextField("Phone Number: ", phoneNumber);
		phoneNumberBox.setStyle("-fx-font-family: Bookman Old Style; -fx-font-weight: bold; -fx-font-size: 15px;");
		HBox addressBox = createLabeledTextField("Address: ", address);
		addressBox.setStyle("-fx-font-family: Bookman Old Style; -fx-font-weight: bold; -fx-font-size: 15px;");
		HBox newPasswordBox = createLabeledPasswordField("New Password: ", newPassword);
		newPasswordBox.setStyle("-fx-font-family: Bookman Old Style; -fx-font-weight: bold; -fx-font-size: 15px;");
		HBox confirmPasswordBox = createLabeledPasswordField("Confirm Password: ", confirmPassword);
		confirmPasswordBox.setStyle("-fx-font-family: Bookman Old Style; -fx-font-weight: bold; -fx-font-size: 15px;");
				
		Button signUpButton = new Button("Sign-Up!");
		signUpButton.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-min-width: 120px; -fx-min-height: 40px;");
		signUpButton.setOnAction(e ->{
			// Perform sign-up validation here
			if (signUpValidation(fullName.getText(), username.getText(), phoneNumber.getText(), address.getText(), newPassword.getText(), confirmPassword.getText())) {
				if (newPassword.getText().equals(confirmPassword.getText())) {
					// Save user input
					Map<String, String> userMap = new HashMap<>();
					// Important inputs for sign-up page
					userMap.put("Username", username.getText());
					userMap.put("Password", newPassword.getText());
					// Other saved inputs
					userMap.put("Full Name", fullName.getText());
					userMap.put("Phone Number", phoneNumber.getText());
					userMap.put("Address", address.getText());
					
					System.out.println("Successful Sign-Up!");
					
					try {
						FileWriter writer = new FileWriter("usersInformation.txt", true);
						writer.write(username.getText() + "," + newPassword.getText()+ "\n");
						writer.close();
					} catch(IOException e1) {
						e1.printStackTrace();
					}
					
					System.out.println("Redirecting to Login Page.");
					openLoginPage(primaryStage);
					
				}
			} else {
				System.out.println("Failed Sign-Up!");
				
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Sign-Up Error");
				alert.setContentText("Please fill all fields and confirm password.");
				alert.showAndWait();
			}
		});
				
		// Adding labeled text field pairs and login button to the sign-up panel
		signUpPanel.getChildren().addAll(titleLabel, fullNameBox, usernameBox, phoneNumberBox, addressBox, newPasswordBox, confirmPasswordBox, signUpButton);
				
		// Centering the sign-up panel within the main panel
		StackPane.setAlignment(signUpPanel, Pos.CENTER);
		mainPanel.getChildren().add(signUpPanel);
				
		// Creating the scene and setting it to the stage
		Scene scene = new Scene(mainPanel, 600, 400);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Hopes of Memories");
		primaryStage.setMaximized(true);
		primaryStage.show();	
		}
			
		// Method to create an HBox containing a labeled text field
		private static HBox createLabeledTextField(String labelText, TextField textField) {
			Label label = new Label(labelText);
				
			HBox hbox = new HBox(10);
			hbox.getChildren().addAll(label, textField);
			hbox.setAlignment(Pos.CENTER);
				
			return hbox;
		}
		
		private static HBox createLabeledPasswordField(String labelText, PasswordField passwordField) {
			Label label = new Label(labelText);
				
			HBox hbox = new HBox(10);
			hbox.getChildren().addAll(label, passwordField);
			hbox.setAlignment(Pos.CENTER);
				
			return hbox;
		}
		
		private static boolean signUpValidation(String fullName, String username, String phoneNumber, String address, String newPassword, String confirmPassword) {
			return !fullName.isEmpty() && !username.isEmpty() && !phoneNumber.isEmpty() && !address.isEmpty() && !newPassword.isEmpty() && newPassword.equals(confirmPassword);
		}
		
		private static void openLoginPage(Stage primaryStage) {
			primaryStage.close();
			
			Stage loginStage = new Stage();
			LoginUI loginUI = new LoginUI();
			loginUI.start(loginStage);
		}
}

		
