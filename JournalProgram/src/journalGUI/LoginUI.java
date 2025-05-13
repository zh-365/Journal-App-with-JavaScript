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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginUI extends Application {
	
	public void start(Stage primaryStage) {
		// Setting the background of login page
		Image backgroundImage = new Image("file:/C:/Users/zoele/Downloads/library.jpg");
		ImageView backgroundImageView = new ImageView(backgroundImage);
		backgroundImageView.fitWidthProperty().bind(primaryStage.widthProperty());
		backgroundImageView.fitHeightProperty().bind(primaryStage.heightProperty());
		
		StackPane mainPanel = new StackPane(backgroundImageView);
		
		// Creating the login panel
		VBox loginPanel = new VBox(20);
		loginPanel.setAlignment(Pos.CENTER);
		BackgroundFill backgroundFill = new BackgroundFill(Color.rgb(191, 148, 92, 0.7), null, null);
		Background background = new Background(backgroundFill);
		loginPanel.setBackground(background);
		loginPanel.setMaxWidth(800);
		loginPanel.setMaxHeight(400);
		
		// Creating the login form components
		Label titleLabel = new Label("Welcome to Hopes of Memories!");
		titleLabel.setFont(Font.font("Bookman Old Style", FontWeight.BOLD, 45));
		titleLabel.setTextFill(Color.BLACK);
		
		Label usernameLabel = new Label("Username:");
		usernameLabel.setTextFill(Color.BLACK);
		usernameLabel.setFont(Font.font("Bookman Old Style", FontWeight.BOLD, 25));
		TextField usernameField = new TextField();
		usernameField.setPromptText("Username");
		usernameField.setMaxWidth(350);
		usernameField.setPrefHeight(40);
		
		Label passwordLabel = new Label("Password:");
		passwordLabel.setTextFill(Color.BLACK);
		passwordLabel.setFont(Font.font("Bookman Old Style", FontWeight.BOLD, 25));
		PasswordField passwordField = new PasswordField();
		passwordField.setPromptText("Password");
		passwordField.setMaxWidth(350);
		passwordField.setPrefHeight(40);
		
		Button loginButton = new Button("Login!");
		loginButton.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-min-width: 120px; -fx-min-height: 40px;");
		loginButton.setOnAction(e -> {
			// Login validation to make sure username and password matches
			String enteredUsername = usernameField.getText();
			String enteredPassword = passwordField.getText();
			
			try {
				BufferedReader reader = new BufferedReader(new FileReader("usersInformation.txt"));
				String line;
				boolean found = false;
				
				while ((line = reader.readLine()) != null) {
					String[] parts = line.split(",");
					if(parts.length == 2 && parts[0].equals(enteredUsername) && parts[1].equals(enteredPassword)){
						found = true;
						break;
					}
				}
				reader.close();
				
				if(found) {
					System.out.println("Successful Login!");
					// Open MainUI when button is clicked
					MainUI.show(primaryStage);
					
				} else {
				System.out.println("Failed Login!");
				
				// Alert message if username and password fail to match
				Alert loginAlert = new Alert(Alert.AlertType.WARNING);
				loginAlert.setTitle("Error");
				loginAlert.setHeaderText("Login Error");
				loginAlert.setContentText("Please input the right username and password.");
				loginAlert.showAndWait();
				}
			} catch(IOException e1) {
				e1.printStackTrace();
			}
		});
		
		Hyperlink signUpLink = new Hyperlink("Click to Sign-Up!");
		signUpLink.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-min-width: 120px; -fx-min-height: 40px;");
		signUpLink.setOnAction(e -> {
			SignUpUI.show(primaryStage);
		});
		
		// Adding components to login panel
		loginPanel.getChildren().addAll(titleLabel, usernameLabel, usernameField, passwordLabel, passwordField, loginButton, signUpLink);
		
		// Centering the login panel within the main panel
		StackPane.setAlignment(loginPanel,  Pos.CENTER);
		mainPanel.getChildren().add(loginPanel);
		
		// Creating the scene and setting it to the stage
		Scene scene = new Scene(mainPanel, 600, 400);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Hopes of Memories");
		primaryStage.setMaximized(true);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
