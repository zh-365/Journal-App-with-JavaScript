package journalGUI;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class MainUI {

	public static void show(Stage primaryStage) {
		// TODO Auto-generated method stub
		System.out.println("Redirecting to Main Page.");
		
		// Creating an AnchorPane as the main layout
		AnchorPane root = new AnchorPane();
		root.setPrefSize(600, 400);
		
		// Creating a MenuBar with all its items
		MenuBar menuBar = new MenuBar();
		Menu fileMenu = new Menu("File");
		fileMenu.setStyle("-fx-font-family: Bookman Old Style; -fx-font-weight: bold; -fx-font-size: 20px;");
		MenuItem newFile = new MenuItem("New File");
		MenuItem saveFile = new MenuItem("Save File");
		MenuItem openFile = new MenuItem("Open File");
		MenuItem importFile = new MenuItem("Import");
		MenuItem exportFile = new MenuItem("Export");
		MenuItem restart = new MenuItem("Restart");
		MenuItem exit = new MenuItem("Exit");
		fileMenu.getItems().addAll(newFile, saveFile, openFile, importFile, exportFile, restart, exit);
		
		Menu editMenu = new Menu("Edit");
		editMenu.setStyle("-fx-font-family: Bookman Old Style; -fx-font-weight: bold; -fx-font-size: 20px;");
		MenuItem undo = new MenuItem("Undo");
		MenuItem copy = new MenuItem("Copy");
		MenuItem paste = new MenuItem("Paste");
		MenuItem cut = new MenuItem("Cut");
		MenuItem delete = new MenuItem("Delete");
		MenuItem selectAll = new MenuItem("selectAll");
		editMenu.getItems().addAll(undo, copy, paste, cut, delete, selectAll);
		
		Menu helpMenu = new Menu("Help");
		helpMenu.setStyle("-fx-font-family: Bookman Old Style; -fx-font-weight: bold; -fx-font-size: 20px;");
		MenuItem about = new MenuItem("About");
		helpMenu.getItems().addAll(about);
		
		menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);
		
		// Set MenuBar at the top of AnchorPane
		root.getChildren().add(menuBar);
		
		// Adjusting size and position of MenuBar
		AnchorPane.setTopAnchor(menuBar, 0.0);
		AnchorPane.setLeftAnchor(menuBar, 0.0);
		AnchorPane.setRightAnchor(menuBar, 0.0);
		
		// Create ListView Pane
		ListView<String> entryList = new ListView<>();
		entryList.setStyle("-fx-font-weight: bold; -fx-font-size: 20px;");
		entryList.setOnMouseClicked(e -> {
			if(e.getClickCount() == 2) {
				// Add actions that will occur after the item is clicked
				String selectedItem = entryList.getSelectionModel().getSelectedItem();
				if(selectedItem != null) {
					System.out.println("Selected" + selectedItem);
				}
			}
		});
		
		// Create Journal Entry Space
		AnchorPane entrySpace = new AnchorPane();
		BackgroundFill backgroundFill = new BackgroundFill(Color.rgb(191, 148, 92, 0.5), null, null);
		Background background = new Background(backgroundFill);
		entrySpace.setBackground(background);
		
		Label entryDate = new Label("Entry Date:");
		entryDate.setTextFill(Color.BLACK);
		entryDate.setFont(Font.font("Bookman Old Style", FontWeight.BOLD, 20));
		DatePicker entryDateField = new DatePicker();
		entryDateField.setMaxWidth(500);
		entryDateField.setPrefHeight(40);
		entryDateField.setStyle("-fx-font-size: 20");
		
		Label entryTitle = new Label("Title:");
		entryTitle.setTextFill(Color.BLACK);
		entryTitle.setFont(Font.font("Bookman Old Style", FontWeight.BOLD, 20));
		TextField titleField = new TextField();
		titleField.setMaxWidth(500);
		titleField.setPrefHeight(40);
		titleField.setStyle("-fx-font-size: 20");
		
		TextArea journalEntry = new TextArea();
		journalEntry.setStyle("-fx-font-size: 15");
		journalEntry.setPrefColumnCount(50);
		journalEntry.setPrefRowCount(20);
		journalEntry.setWrapText(true);
		
		Button saveButton = new Button("Save Entry!");
		saveButton.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-min-width: 120px; -fx-min-height: 40px;");
		saveButton.setOnAction(e ->{
			// Action upon clicking button
			LocalDate inputDate = entryDateField.getValue();
			String formattedDate = inputDate != null ? inputDate.toString(): "";
			String inputTitle = titleField.getText();
			String savedJournalEntry = journalEntry.getText();
			
			
			Map<String,String> entryMap = new HashMap<>();
			entryMap.put("EntryDate", formattedDate);
			entryMap.put("Title", inputTitle);
			entryMap.put("JournalEntry", savedJournalEntry);
			
			System.out.println("Entry successfully saved.");
			
			// Saving journal entries to one single text file
			try {
				FileWriter journalEntries = new FileWriter("journalEntries.txt", true);
				journalEntries.write(formattedDate + "\n" + inputTitle + "\n" + savedJournalEntry + "\n");
				journalEntries.close();
			} catch(IOException e1) {
				e1.printStackTrace();
			}
			
			entryList.getItems().add(inputTitle);

		});
		
		AnchorPane.setTopAnchor(entryDate, 45.0);
		AnchorPane.setLeftAnchor(entryDate, 30.0);
		AnchorPane.setTopAnchor(entryDateField, 40.0);
		AnchorPane.setLeftAnchor(entryDateField, 200.0);
		AnchorPane.setTopAnchor(entryTitle, 105.0);
		AnchorPane.setLeftAnchor(entryTitle, 30.0);
		AnchorPane.setTopAnchor(titleField, 100.0);
		AnchorPane.setLeftAnchor(titleField, 200.0);
		AnchorPane.setTopAnchor(journalEntry, 170.0);
		AnchorPane.setLeftAnchor(journalEntry, 30.0);
		AnchorPane.setTopAnchor(saveButton, 620.0);
		AnchorPane.setLeftAnchor(saveButton, 30.0);
		
		entrySpace.getChildren().addAll(entryDate, entryDateField, entryTitle, titleField, journalEntry, saveButton);
		
		// Create SplitPane
		SplitPane sp = new SplitPane();
		sp.getItems().addAll(entryList, entrySpace);
		sp.setDividerPositions(0.3);
		
		AnchorPane.setTopAnchor(sp, 40.0);
		AnchorPane.setBottomAnchor(sp, 0.0);
		AnchorPane.setLeftAnchor(sp, 0.0);
		AnchorPane.setRightAnchor(sp, 0.0);
		
		root.getChildren().add(sp);
		
		
		// Executing the scene of the window
		Scene mainscene = new Scene(root, 600, 400);
		primaryStage.setScene(mainscene);
		primaryStage.setTitle("Hopes of Memories");
		primaryStage.setMaximized(true);
		primaryStage.show();	
		
	}

}
