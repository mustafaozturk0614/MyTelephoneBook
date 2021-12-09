module MyTelephoneBook {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	
	opens com.bilgeadam.boost.java.telephonebook.view to javafx.graphics, javafx.fxml;
	
	exports com.bilgeadam.boost.java.telephonebook.view to javafx.fxml;
	
	exports com.bilgeadam.boost.java.telephonebook;
}
