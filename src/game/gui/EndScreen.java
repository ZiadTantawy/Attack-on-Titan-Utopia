package game.gui;

import java.io.IOException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import game.engine.Battle;

public class EndScreen {
	public Stage stage;
	public Scene EndScreen;
	public Main window;
	private Battle gameFlow;
	
	public EndScreen(Stage stage,Main window ,Battle gameFlow) throws IOException{
		this.stage  = stage;
		this.window = window;
		this.gameFlow = gameFlow;
		createScene();
	}
	
    
   //EndScreen
     //Title
	public void createScene() {
		Font font = Font.loadFont(getClass().getResource("Ditty.ttf").toExternalForm(), 70);
    
	Media EndScreenMusic = new Media(getClass().getResource("Sound/endscreen.mp3").toExternalForm());
    MediaPlayer mediaPlayerend = new MediaPlayer(EndScreenMusic);
    mediaPlayerend.setCycleCount(MediaPlayer.INDEFINITE);
    mediaPlayerend.play();
     Text score ;
     if (gameFlow != null) {
         int finalScoreValue = gameFlow.getScore();
         score = new Text(String.valueOf(finalScoreValue));
     } else {
         score = new Text("N/A");
     }
     Text finalscore = new Text("Score:" + score.getText()); 
     Text youdied = new Text("GAME OVER.");
     
     
     //buttons
     Button homebutton = new Button();
     
     //styling

    
     


     homebutton.getStyleClass().add("homebutton");
     finalscore.getStyleClass().add("endtext");
     youdied.getStyleClass().add("endtext");
     finalscore.setFont(font);
     youdied.setFont(font);
     homebutton.setOnMouseEntered(e ->  homebutton.setCursor(Cursor.HAND));
     homebutton.setOnMouseExited(e ->  homebutton.setCursor(Cursor.DEFAULT));
     homebutton.setOnAction(e -> {
    	 mediaPlayerend.stop();
    	window.setStartScreen();
     
     	
     });
   
     //layout

     VBox Endscorebox = new VBox(5,youdied,finalscore);
     Endscorebox.setAlignment(Pos.TOP_CENTER);
     Endscorebox.setPadding(new Insets(20,0,0,0));
     homebutton.setPrefSize(100, 100);
     HBox fin = new HBox(485,homebutton,Endscorebox);
     fin.setAlignment(Pos.TOP_LEFT);
     fin.setPadding(new Insets(20));

     
     BorderPane endroot = new BorderPane();
     endroot.setTop(fin);
     endroot.getStyleClass().add("endScreen-background");
     stage.setMinWidth(800);
     stage.setMinHeight(600);

     
     EndScreen = new Scene(endroot,800,600);
     EndScreen.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
    
	 }
	 public Scene getScene() {
	        return EndScreen;
	    }
}
