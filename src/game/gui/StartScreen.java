package game.gui;

import java.io.IOException;
import game.engine.Battle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;



public class StartScreen {
	public Scene StartScreen;
	public Main window;
	public Stage stage;
	
    public StartScreen(Stage stage ,Main window) throws IOException{
    	this.stage = stage;
    	this.window = window;
    	createScene();
    }
    	//SCENE 1(START SCREEN)
    	//Title
    public void createScene() throws IOException {
    	 
        Font fontL = Font.loadFont(getClass().getResource("Dittytitle.ttf").toExternalForm(), 120);
        Font fontm = Font.loadFont(getClass().getResource("Dittytitle.ttf").toExternalForm(), 50);
        Font fontsm = Font.loadFont(getClass().getResource("Ditty.ttf").toExternalForm(), 30);
        Font fonts = Font.loadFont(getClass().getResource("Dittytitle.ttf").toExternalForm(), 25);
        Text mainTitle = new Text("Attack On Titan:");
        Text subTitle = new Text("Utopia");
        
        //buttons
        Button infoButton = new Button("Game Instructions");
        RadioButton easyButton = new RadioButton("Easy Mode");
        RadioButton hardButton = new RadioButton("Hard Mode");
        Button startButton = new Button("Start");
        
        ToggleGroup difficultyGroup = new ToggleGroup();
        easyButton.setToggleGroup(difficultyGroup);
        hardButton.setToggleGroup(difficultyGroup);
        
        //styling


        infoButton.setOnMouseEntered(e -> infoButton.setCursor(Cursor.HAND));
        infoButton.setOnMouseExited(e -> infoButton.setCursor(Cursor.DEFAULT));
        startButton.setOnMouseEntered(e -> startButton.setCursor(Cursor.HAND));
        startButton.setOnMouseExited(e -> startButton.setCursor(Cursor.DEFAULT));
        easyButton.setOnMouseEntered(e -> easyButton.setCursor(Cursor.HAND));
        easyButton.setOnMouseExited(e -> easyButton.setCursor(Cursor.DEFAULT));
        hardButton.setOnMouseEntered(e -> hardButton.setCursor(Cursor.HAND));
        hardButton.setOnMouseExited(e -> hardButton.setCursor(Cursor.DEFAULT));
        
        
        mainTitle.getStyleClass().add("mainTitle");
        subTitle.getStyleClass().add("mainTitle");
        startButton.getStyleClass().add("startButton");
        startButton.setPadding(new Insets(0,30,0,30));
        infoButton.getStyleClass().add("infoButton");
        easyButton.getStyleClass().add("modeButton");
        hardButton.getStyleClass().add("modeButton");
   
        mainTitle.setFont(fontL);
        subTitle.setFont(fontm);
        startButton.setFont(fontsm);
        infoButton.setFont(fontsm);
        easyButton.setFont(fonts);
        hardButton.setFont(fonts);
        
       //layout
        
        HBox TitleBox = new HBox(mainTitle);
        TitleBox.setAlignment(Pos.CENTER);
        
        
        HBox subTitleBox = new HBox(subTitle);
        subTitleBox.setAlignment(Pos.CENTER);
        subTitleBox.setPadding(new Insets(0,0,30,0));
        
        VBox Title = new VBox(TitleBox,subTitleBox);
        Title.setAlignment(Pos.CENTER);
        TitleBox.setPadding(new Insets(40,0,0,0));
        
        VBox Head = new VBox(Title,infoButton);
        Head.setAlignment(Pos.CENTER);
        
        VBox difficulty = new VBox(10,easyButton,hardButton);
        difficulty.setAlignment(Pos.CENTER);
        
        VBox menuBox = new VBox(10, startButton, difficulty);
        menuBox.setAlignment(Pos.CENTER);
        
        //double width = window.getWidth();
        

        Media StartScreenMusic = new Media(getClass().getResource("Sound/Start-screen-music.mp3").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(StartScreenMusic);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();
        
      //startButton press
		startButton.setOnAction(e -> {
		    RadioButton selectedRadioButton = (RadioButton) difficultyGroup.getSelectedToggle();
		    if (selectedRadioButton != null) {
		        String selectedText = selectedRadioButton.getText();
		        if(selectedText.equals("Easy Mode")) {
		            Battle GameFlowEasy = null;
					try {
						GameFlowEasy = new Battle(1,0,100,3,250);
					} catch (IOException e1) {
					}
		            GameScreen gameScreen = new GameScreen(GameFlowEasy, window);
		            Scene GameScreen=gameScreen.createScene();
		            mediaPlayer.stop();
		            window.setGameScreen(GameFlowEasy);
		        } else if(selectedText.equals("Hard Mode")) {
		            Battle GameFlowHard = null;
					try {
						GameFlowHard = new Battle(1,0,100,5,125);
					} catch (IOException e1) {
					}
		            GameScreen gameScreen = new GameScreen(GameFlowHard, window);
		            Scene GameScreen=gameScreen.createScene();
		            mediaPlayer.stop();
		            window.setGameScreen(GameFlowHard);
		        }
		    }
		});
	    //infoButton press
	    infoButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Image image = new Image(getClass().getResource("Images/GameInfo.jpeg").toExternalForm());
			    ImageView imageView = new ImageView(image);
			    imageView.setFitWidth(1200);
			    imageView.setFitHeight(700);
			    Pane pane = new Pane(imageView);
			    pane.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
			    pane.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
			    alert.setGraphic(imageView);
			    alert.setHeaderText(null);
			    alert.getDialogPane().setContent(pane);
			    ButtonType okButtonType = alert.getButtonTypes().stream()
					    .filter(buttonType -> buttonType.getText().equals("OK"))
					    .findFirst()
					    .orElse(null);

					if (okButtonType != null) {
					    Button okButton = (Button) alert.getDialogPane().lookupButton(okButtonType);
					    okButton.setId("okButton");
					    okButton.setStyle("-fx-background-color: #33200d; -fx-text-fill: #ffffff;");
					    okButton.setOnMouseEntered(event -> okButton.setStyle("-fx-background-color: #4e2d15; -fx-text-fill: #ffffff;"));
					    okButton.setOnMouseExited(event -> okButton.setStyle("-fx-background-color: #33200d; -fx-text-fill: #ffffff;"));
					}
			    alert.showAndWait();
			}
	    });
        BorderPane StartScreenLayout = new BorderPane();
        StartScreenLayout.setTop(Head);
        StartScreenLayout.setCenter(menuBox);
        StartScreenLayout.getStyleClass().add("startScreen-background");
        StartScreen = new Scene(StartScreenLayout);
        StartScreen.getStylesheets().add(getClass().getResource("styles.css").toExternalForm()); 
       
    }
    public Scene getScene() {
        return StartScreen;
    }
        
}
