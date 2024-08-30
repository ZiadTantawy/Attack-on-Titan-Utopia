module milestone3 {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires javafx.media;
	
	opens game.gui to javafx.graphics, javafx.fxml;
}
