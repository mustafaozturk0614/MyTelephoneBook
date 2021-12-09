package com.bilgeadam.boost.java.telephonebook;

import java.io.IOException;
import java.time.LocalDate;

import com.bilgeadam.boost.java.telephonebook.model.Person;
import com.bilgeadam.boost.java.telephonebook.view.PersonEditDialogController;
import com.bilgeadam.boost.java.telephonebook.view.PersonOverviewController;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TelephoneBook extends Application {
	private Stage primary;
	private BorderPane rootPane;
	private ObservableList<Person> people;
	
	public ObservableList<Person> getPeople() {
		if (this.people == null) {
			this.people = FXCollections.observableArrayList();
		}
		return this.people;
	}
	
	@Override
	public void start(Stage primaryStage) {
		
		initPrimaryStage(primaryStage);
		initRootLayout();
		initTelephoneEntries();
		showPersonOverview();
		
	}
	
	private void initTelephoneEntries() {
		this.getPeople().add(new Person.Builder().firstname("Mustafa").lastname("Öztürk").telephone("5069848926")
				.street("Baldıran Sokak").city("Ankara").zip(310).birthDay(LocalDate.parse("1987-12-17")).build());
		this.getPeople().add(new Person.Builder().firstname("Ali").lastname("Ergül").build());
		this.getPeople().add(new Person.Builder().firstname("Sercan").lastname("Üstün").build());
		this.getPeople().add(new Person.Builder().firstname("Çağlayan").lastname("Kaya").build());
		this.getPeople().add(new Person.Builder().firstname("Melih").lastname("Dumanlı").build());
		this.getPeople().add(new Person.Builder().firstname("Canan").lastname("Havva").build());
		this.getPeople().add(new Person.Builder().firstname("Babür").lastname("Somer").build());
		this.getPeople().add(new Person.Builder().firstname("Onur").lastname("Gündoğdu").build());
		this.getPeople().add(new Person.Builder().firstname("Mert Can").lastname("Aydın").build());
		this.getPeople().add(new Person.Builder().firstname("Görekm").lastname("Sönmez").build());
		this.getPeople().add(new Person.Builder().firstname("Gülten").lastname("Ulukal").build());
		this.getPeople().add(new Person.Builder().firstname("Cemil").lastname("Duman").build());
		this.getPeople().add(new Person.Builder().firstname("Çağrı").lastname("Türkmen").build());
		this.getPeople().add(new Person.Builder().firstname("Burak").lastname("Güneş").build());
		for (Person person : people) {
			System.out.println(person);
		}
	}
	
	private void initPrimaryStage(Stage primaryStage) {
		this.primary = primaryStage;
		this.primary.getIcons().add(new Image(getClass().getResourceAsStream("resources/telefonbook.jpg")));
		this.primary.setTitle("Benim Güzel Telefon Rehberim");
		initRootLayout();
	}
	
	private void initRootLayout() {
		try {
			this.rootPane = (BorderPane) FXMLLoader.load(getClass().getResource("view/TelephoneBook.fxml"));
			Scene scene = new Scene(this.rootPane, 600, 400);
			scene.getStylesheets().add(getClass().getResource("view/application.css").toExternalForm());
			this.primary.setScene(scene);
			
			this.primary.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void showPersonOverview() {
		FXMLLoader loader = new FXMLLoader(); // herhangi bir scene i ekranda göstermek için
		loader.setLocation(getClass().getResource("view/PersonOverView.fxml"));
		
		try {
			AnchorPane personOverview = loader.load();
			this.rootPane.setCenter(personOverview);
			PersonOverviewController controller = loader.getController();
			controller.setTelephoneBook(this);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public boolean showPersonEditDialog(Person person) {
		
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("view/PersonEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.getIcons().add(new Image(getClass().getResourceAsStream("resources/edit.png")));
			dialogStage.setTitle("kişi Düzenleme");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primary);
			Scene scene = new Scene(page);
			
			dialogStage.setScene(scene);
			
			// Set the person into the controller.
			PersonEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setPerson(person);
			
			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();
			
			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public Stage getPrimary() {
		return primary;
	}
	
	public void setPrimary(Stage primary) {
		this.primary = primary;
	}
	
	public BorderPane getRootPane() {
		return rootPane;
	}
	
	public void setRootPane(BorderPane rootPane) {
		this.rootPane = rootPane;
	}
	
	public void setPeople(ObservableList<Person> people) {
		this.people = people;
	}
	
}
