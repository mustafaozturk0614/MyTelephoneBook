package com.bilgeadam.boost.java.telephonebook.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.bilgeadam.boost.java.telephonebook.TelephoneBook;
import com.bilgeadam.boost.java.telephonebook.model.Person;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PersonOverviewController {
	
	private TelephoneBook telephoneBook; /// all data kept in main aplication
	
	@FXML
	private TableView<Person> tablePerson;
	@FXML
	private TableColumn<Person, String> columnFirstName;
	@FXML
	private TableColumn<Person, String> columnLastName;
	@FXML
	private Label lblFirstName;
	@FXML
	private Label lblLastName;
	@FXML
	private Label lblTelephone;
	@FXML
	private Label lblStreet;
	@FXML
	private Label lblCity;
	@FXML
	private Label lblZip;
	@FXML
	private Label lblBirhtday;
	
	public PersonOverviewController() {
		super();
	}
	
	@FXML
	private void initialize() {
		columnFirstName.setCellValueFactory(cellData -> cellData.getValue().getFirstNameProperty());
		columnLastName.setCellValueFactory(cellData -> cellData.getValue().getLastNameProperty());
		showPersonDetails(null);
		
		tablePerson.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));
		
	}
	
	public void setTelephoneBook(TelephoneBook telephoneBook) {
		this.telephoneBook = telephoneBook;
		tablePerson.setItems(telephoneBook.getPeople());
	}
	
	public String getFormattedDate(LocalDate date) {
		String retVal = "";
		final String DATE_FORMAT = "dd.MMM.yyyy";
		final DateTimeFormatter DATE_TİME_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
		if (date != null) {
			retVal = DATE_TİME_FORMATTER.format(date);
		}
		return retVal;
	}
	
	public void showPersonDetails(Person person) {
		if (person == null) {
			lblFirstName.setText("");
			lblLastName.setText("");
			lblTelephone.setText("");
			lblStreet.setText("");
			lblCity.setText("");
			lblZip.setText("");
			lblBirhtday.setText("");
		} else {
			lblFirstName.setText(person.getFirstName());
			lblLastName.setText(person.getLastName());
			lblTelephone.setText(person.getTelephone());
			lblStreet.setText(person.getStreet());
			lblCity.setText(person.getCity());
			lblZip.setText(String.valueOf(person.getZip()));
			lblBirhtday.setText(getFormattedDate(person.getBirthDay()));
		}
		
	}
	
	@FXML
	private void delete() {
		int selected = tablePerson.getSelectionModel().getSelectedIndex();
		if (selected >= 0) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.initOwner(telephoneBook.getPrimary());
			alert.setTitle("SİL");
			alert.setHeaderText("Silmek İstediğinize Eminmisiniz???");
			alert.showAndWait();
			ButtonType type = alert.getResult();
			System.out.println(type.getText());
			if (type.getText().equalsIgnoreCase("ok")) {
				tablePerson.getItems().remove(selected);
			}
			
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(telephoneBook.getPrimary());
			alert.setTitle("DİKKAT");
			alert.setHeaderText("Kişi Seçili Değil");
			alert.setContentText("Lütfen Bir Kişi Seçiniz");
			alert.show();
		}
		
	}
	
	@FXML
	private void handleNewPerson() {
		Person tempPerson = new Person();
		boolean okClicked = telephoneBook.showPersonEditDialog(tempPerson);
		if (okClicked) {
			telephoneBook.getPeople().add(tempPerson);
		}
	}
	
	@FXML
	private void handleEditPerson() {
		Person selectedPerson = tablePerson.getSelectionModel().getSelectedItem();
		if (selectedPerson != null) {
			boolean okClicked = telephoneBook.showPersonEditDialog(selectedPerson);
			if (okClicked) {
				showPersonDetails(selectedPerson);
			}
			
		} else {
			
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(telephoneBook.getPrimary());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Person Selected");
			alert.setContentText("Please select a person in the table.");
			
			alert.showAndWait();
		}
	}
	
}
