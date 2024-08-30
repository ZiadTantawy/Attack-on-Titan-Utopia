package game.gui;
import java.io.IOException;
import game.engine.Battle;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {
	public Stage window;
	public GameScreen gameScreen;
    public EndScreen endScreen;
    public StartScreen startScreen;
    public Battle gameFlow;

//	
		@Override
	public void start(Stage primaryStage) throws IOException{
		window = primaryStage; 	
    	startScreen = new StartScreen(window ,this);
        Image applicationIcon = new Image(getClass().getResourceAsStream("Images/AppIcon.png"));
        window.setMinWidth(800);
        window.setMinHeight(600);
        window.getIcons().add(applicationIcon);
        window.setFullScreen(true);
        window.setTitle("Attack On Titan: Utopia");
        window.setScene(startScreen.getScene());
        window.setFullScreen(true);
        window.show();
		}
 
    public static void main(String[] args) {
        launch(args);
    }

    public void setStartScreen() {
        window.setScene(startScreen.getScene());
        window.setFullScreen(true);
    }

    public void setGameScreen(Battle gameFlow) {
            GameScreen gameScreen = new GameScreen(gameFlow, this);
            Scene gameScene = gameScreen.createScene();
            window.setScene(gameScene);
            window.setFullScreen(true);
     }

    public void setEndScreen(Battle gameFlow) throws IOException {
    	EndScreen endScreen = new EndScreen(window, this, gameFlow);
        window.setScene(endScreen.getScene());
        window.setFullScreen(true);
    }



}