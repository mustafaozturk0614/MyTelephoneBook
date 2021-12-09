package com.bilgeadam.boost.java.telephonebook.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.bilgeadam.boost.java.telephonebook.model.Person;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PersonEditDialogController {
	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField streetField;
	@FXML
	private TextField postalCodeField;
	@FXML
	private TextField cityField;
	@FXML
	private TextField birthdayField;
	@FXML
	private TextField telephone;
	
	private Stage dialogStage;
	private Person person;
	private boolean okClicked = false;
	
	@FXML
	private void initialize() {
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void setPerson(Person person) {
		this.person = person;
		
		firstNameField.setText(person.getFirstName());
		lastNameField.setText(person.getLastName());
		streetField.setText(person.getStreet());
		postalCodeField.setText(Integer.toString(person.getZip()));
		cityField.setText(person.getCity());
		birthdayField.setText(getFormattedDate(person.getBirthDay()));
		telephone.setText(person.getTelephone());
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
	
	public boolean isOkClicked() {
		return okClicked;
	}
	
	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	private void handleOk() {
		if (isInputValid()) {
			person.setFirstName(firstNameField.getText());
			person.setLastName(lastNameField.getText());
			person.setStreet(streetField.getText());
			person.setZip(Integer.parseInt(postalCodeField.getText()));
			person.setCity(cityField.getText());
			// person.setBirthday(getFormattedDate(birthdayField);
			person.setTelephone(telephone.getText());
			okClicked = true;
			dialogStage.close();
		}
	}
	
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
	
	private boolean isInputValid() {
		String errorMessage = "";
		
		if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
			errorMessage += "İsim Boş bırakılamaz!\n";
		}
		if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
			errorMessage += "Soy isim boş bırakılamaz!\n";
		}
		
		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Geçersiz Alanlar");
			alert.setHeaderText("Lütfen geçersiz alanları düzeltin");
			alert.setContentText(errorMessage);
			
			alert.showAndWait();
			
			return false;
		}
	}
}
