package game.gui;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import game.engine.Battle;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import game.engine.lanes.Lane;
import game.engine.titans.Titan;
import game.engine.weapons.Weapon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.media.Media;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.RowConstraints;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


public class GameScreen {
	public Scene scene;
	public Battle gameFlow;
	public Main window;
	public BorderPane GameLayout;
	
	public GameScreen(Battle gameFlow,Main window) {
		this.gameFlow=gameFlow;
        this.window = window;
        this.scene = createScene();
        
    }
	public Scene getScene() {
		return scene;
	}

	
	
	
	public ArrayList<LaneGameobj> createLanes() {
		ArrayList<LaneGameobj> x = new ArrayList<>();
			if(gameFlow.getOriginalLanes().size()==3) {
				ArrayList<Lane> lanes = gameFlow.getOriginalLanes();
	        	for(int i = 0;i<3;i++) {
	        		LaneGameobj current = new LaneGameobj(lanes.get(i));
	        		x.add(current);
	        	}
	        }
	        else if(gameFlow.getOriginalLanes().size()==5) {
	        	ArrayList<Lane> lanes = gameFlow.getOriginalLanes();
	        	for(int i = 0;i<5;i++) {
	        		LaneGameobj current = new LaneGameobj(lanes.get(i));
	        		x.add(current);
	        	}
	        }
			return x;
	}
	

	public Scene createScene() {
		Font fontL = Font.loadFont(getClass().getResource("Ditty.ttf").toExternalForm(), 120);
        Font fontm = Font.loadFont(getClass().getResource("Ditty.ttf").toExternalForm(), 50);
        Font fontsm = Font.loadFont(getClass().getResource("Ditty.ttf").toExternalForm(), 30);
        Font fonts = Font.loadFont(getClass().getResource("Ditty.ttf").toExternalForm(), 25);
        Font fontxs = Font.loadFont(getClass().getResource("Ditty.ttf").toExternalForm(), 18);
        Font fontxxs = Font.loadFont(getClass().getResource("Ditty.ttf").toExternalForm(), 152);
        
    	if (fontL == null || fontm == null || fontsm == null || fonts == null || fontxs == null) {
            System.err.println("Failed to load font, using default font.");
            fontL = Font.font("Arial", 120);
            fontm = Font.font("Arial", 50);
            fontsm = Font.font("Arial", 30);
            fonts = Font.font("Arial", 25);  
            fontxs = Font.font("Arial", 18);
         }
		Scene GameScreen;
		GameLayout = new BorderPane();
		ArrayList<LaneGameobj> x = this.createLanes();
		final ArrayList<LaneGameobj> LaneRefList = new ArrayList<>();
		GridPane WallLaneGrid = new GridPane();
    	GridPane TitanWeaponMap = new GridPane();
    	RowConstraints rowConstraints = new RowConstraints();
        ColumnConstraints colConstraints = new ColumnConstraints(107);
        TitanWeaponMap.getColumnConstraints().addAll(colConstraints,colConstraints,colConstraints,colConstraints,colConstraints,colConstraints,colConstraints,colConstraints,colConstraints,colConstraints,colConstraints);
        if(gameFlow.getOriginalLanes().size()==5) {
        	double rowheight = 100/5;
			rowConstraints.setPercentHeight(rowheight-3);
			TitanWeaponMap.getRowConstraints().addAll(rowConstraints, rowConstraints, rowConstraints,rowConstraints,rowConstraints);
        }
        if(gameFlow.getOriginalLanes().size()==3) {
        	double rowheight = 100/3;
			rowConstraints.setPercentHeight(rowheight-4.75);
			TitanWeaponMap.getRowConstraints().addAll(rowConstraints, rowConstraints, rowConstraints);
        }
    	Boolean PlayersTurn = true;	
    	
    	

       
   
        
        ToggleGroup LanesToggle = new ToggleGroup();
        HBox lanesToggleBox = new HBox();
    	if(gameFlow!=null) {
			if(gameFlow.getOriginalLanes().size()==3) {
				for(int i = 0; i<3;i++) {
					LaneGameobj currentLane = x.get(i);
					WallGameobj currentWall = new WallGameobj(currentLane.lane.getLaneWall());
					ImageView img = currentWall.ImageView;
					img.setFitHeight(250);
					WallLaneGrid.add(currentWall.ImageView,0,i);
					//WallLaneGrid.add(currentLane.ImageView, 1, i);
					LaneRefList.add(currentLane);
					RadioButton CurrentLaneButton = new RadioButton("Lane"+(i+1));
					CurrentLaneButton.setTextFill(Color.WHITE);
					CurrentLaneButton.setToggleGroup(LanesToggle);
					lanesToggleBox.getChildren().add(CurrentLaneButton);
					VBox WeaponsOnWall = new VBox();
					WeaponsOnWall.setPadding(new Insets(10));
					WeaponsOnWall.setFillWidth(false); 
					TitanWeaponMap.add(WeaponsOnWall, 0, i);
	
					
				}
	        }
	        else if(gameFlow.getOriginalLanes().size()==5) {
	        	for(int i = 0; i<5;i++) {
					LaneGameobj currentLane = x.get(i);
					WallGameobj currentWall = new WallGameobj(currentLane.lane.getLaneWall());
					ImageView img = currentWall.ImageView;
					img.setFitHeight(150);
					WallLaneGrid.add(currentWall.ImageView,0,i);
					//WallLaneGrid.add(currentLane.ImageView, 1, i);
					LaneRefList.add(currentLane);
					RadioButton CurrentLaneButton = new RadioButton("Lane"+(i+1));
					CurrentLaneButton.setTextFill(Color.WHITE);
					CurrentLaneButton.setToggleGroup(LanesToggle);
					lanesToggleBox.getChildren().add(CurrentLaneButton);
					VBox WeaponsOnWall = new VBox();
					WeaponsOnWall.setPadding(new Insets(10));
					WeaponsOnWall.setFillWidth(false); 
					TitanWeaponMap.add(WeaponsOnWall, 0, i);

				
		
		
				
				}
	        }
        }
    
    	RadioButton PiercingCannonButton = new RadioButton("Anti Titan Shell");
        RadioButton SniperCannonButton= new RadioButton("Long Range Spear");
        RadioButton VolleySpreadCannonButton = new RadioButton("Wall Spread Cannon");
        RadioButton WallTrapButton = new RadioButton("Proximity Trap");
        ToggleGroup weaponGroup= new ToggleGroup();
        Button BuyButton = new Button("Buy");
        Button SkipButton = new Button("Skip");
        
        PiercingCannonButton.setToggleGroup(weaponGroup);
        SniperCannonButton.setToggleGroup(weaponGroup);
        VolleySpreadCannonButton.setToggleGroup(weaponGroup);
        WallTrapButton.setToggleGroup(weaponGroup);
        Text Pcprice = new Text("25");
        Text Scprice = new Text("25");
        Text Vscprice = new Text("100");
        Text Wtprice = new Text("75");
        Text PcDamage = new Text("Damage:10");
        Text ScDamage= new Text("Damage:35");
        Text VscDamage = new Text("Damage:5");
        Text WtDamage = new Text("Damage:100");
        

        BuyButton.getStyleClass().add("weaponshopinside");
        SkipButton.getStyleClass().add("weaponshopinside");
        
        
        
        PiercingCannonButton.setTextFill(Color.WHITE);
        SniperCannonButton.setTextFill(Color.WHITE);
        VolleySpreadCannonButton.setTextFill(Color.WHITE);
        WallTrapButton.setTextFill(Color.WHITE);
        
        PiercingCannonButton.setFont(fontxs);
        SniperCannonButton.setFont(fontxs);
        VolleySpreadCannonButton.setFont(fontxs);
        WallTrapButton.setFont(fontxs);
        BuyButton.setFont(fontxs);
        SkipButton.setFont(fontxs);
        
        
        
        Pcprice.setFill(Color.WHITE);
        Scprice.setFill(Color.WHITE);
        Vscprice.setFill(Color.WHITE);
        Wtprice.setFill(Color.WHITE);
        PcDamage.setFill(Color.WHITE);
        ScDamage.setFill(Color.WHITE);
        VscDamage.setFill(Color.WHITE);
        WtDamage.setFill(Color.WHITE);
        
        
        Pcprice.setFont(fontxs);
        Scprice.setFont(fontxs);
        Vscprice.setFont(fontxs);
        Wtprice.setFont(fontxs);
        PcDamage.setFont(fontxs);
        ScDamage.setFont(fontxs);
        VscDamage.setFont(fontxs);
        WtDamage.setFont(fontxs);
        
        //Text WTitle = new Text("Select weapon");
        Image image = new Image(getClass().getResource("Images/weapon12-removebg-preview.png").toExternalForm());
        ImageView imageView = new ImageView(image); 
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(150);
        imageView.setFitWidth(150);
        Image image2 = new Image(getClass().getResource("Images/vsp.png").toExternalForm());
        ImageView imageView2 = new ImageView(image2); 
        imageView2.setPreserveRatio(true);
        imageView2.setFitHeight(150);
        imageView2.setFitWidth(150);
        Image image3 = new Image(getClass().getResource("Images/newpc.png").toExternalForm());
        ImageView imageView3 = new ImageView(image3); 
        imageView3.setPreserveRatio(true);
        imageView3.setFitHeight(150);
        imageView3.setFitWidth(150);
        Image image4 = new Image(getClass().getResource("Images/wallt.png").toExternalForm());
        ImageView imageView4 = new ImageView(image4); 
        imageView4.setPreserveRatio(true);
        imageView4.setFitHeight(100);
        imageView4.setFitWidth(100);
        
        HBox PcBox1 = new HBox(5,PiercingCannonButton ,Pcprice);
        VBox PcBox = new VBox(5,imageView,PcBox1,PcDamage);
        
        HBox ScBox1 = new HBox(5,SniperCannonButton ,Scprice);
        VBox ScBox = new VBox(5,imageView2, ScBox1,ScDamage);
        
        HBox VscBox1 = new HBox(5,VolleySpreadCannonButton ,Vscprice); 
        VBox VscBox = new VBox(5,imageView3,VscBox1,VscDamage);
        
        HBox WtBox1 = new HBox(5,WallTrapButton ,Wtprice);
        VBox WtBox = new VBox(5,imageView4, WtBox1,WtDamage);
        //HBox WtitleBox = new HBox(10,WTitle)
        HBox DecisionBox = new HBox(5,BuyButton,SkipButton);
        VBox BigDecisionBox = new VBox(0,DecisionBox,lanesToggleBox);
        VBox ContainerBox = new VBox(5,PcBox,ScBox, VscBox,WtBox,BigDecisionBox);
        DecisionBox.setSpacing(10);
        PcBox.setPadding(new Insets(20,0,0,10));
        ScBox.setPadding(new Insets(20,0,0,10));
        VscBox.setPadding(new Insets(20,0,0,10));
        WtBox.setPadding(new Insets(20,0,0,10));
        DecisionBox.setPadding(new Insets(0,20,0,0));
        BigDecisionBox.setSpacing(10);
        
        Media GameScreenMusic = new Media(getClass().getResource("Sound/gamescreen.mp3").toExternalForm());
        MediaPlayer mediaPlayergame = new MediaPlayer(GameScreenMusic);
        mediaPlayergame.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayergame.play();
    	
    	if (GameLayout == null) {
            GameLayout = new BorderPane();
            int resources = gameFlow.getResourcesGathered();
			String phase = gameFlow.getBattlePhase().name();
			int score = gameFlow.getScore();
			int turn = gameFlow.getNumberOfTurns();
			Button currentscore = new Button("SCORE:" +  " " +score);
	        Button currentturn = new Button("TURN:" + " " + turn);
	        Button currentphase = new Button("" + phase);
	        Button currentresources = new Button("RESOURCES:" + " " +resources );
	        Button weaponShop = new Button("WEAPON SHOP");
	        currentscore.getStyleClass().add("currentbuttons");
	        currentturn.getStyleClass().add("currentbuttons");
	        currentphase.getStyleClass().add("currentbuttons");
	        currentresources.getStyleClass().add("currentbuttons");
	        weaponShop.getStyleClass().add("weaponshopbutton");
	        
	        weaponShop.setOnMouseEntered(e ->  weaponShop.setCursor(Cursor.HAND));
	        weaponShop.setOnMouseExited(e ->  weaponShop.setCursor(Cursor.DEFAULT));
	        
	        currentscore.setFont(fontxs);
	        currentturn.setFont(fontxs);
	        currentphase.setFont(fontxs);
	        currentresources.setFont(fontxs);
	        weaponShop.setFont(fontxs);
	        
	        HBox currents = new HBox(40,currentscore,currentturn,currentphase,currentresources);
	        currents.setPadding(new Insets(10,0,0,20));
	        currents.setAlignment(Pos.TOP_LEFT);
	        
	        HBox weaponShopBox = new HBox(weaponShop);
            weaponShopBox.setAlignment(Pos.TOP_RIGHT);
            weaponShopBox.setPadding(new Insets(10, 20, 0, 0));
            
            HBox top = new HBox(currents,weaponShopBox);
            HBox.setHgrow(currents, Priority.ALWAYS);
            top.setAlignment(Pos.TOP_CENTER);
            
            ContainerBox.setVisible(false);
			weaponShop.setOnAction(e -> ContainerBox.setVisible(!ContainerBox.isVisible()));
			StackPane center = new StackPane(WallLaneGrid,TitanWeaponMap);
			center.setPadding(new Insets(20,0,0,20));
			GameLayout.setTop(top);
			GameLayout.setCenter(center);
			GameLayout.setRight(ContainerBox);
			GameScreen = new Scene(GameLayout);
			WallLaneGrid.setGridLinesVisible(true);
			if(gameFlow.getOriginalLanes().size()==5) {
				for(int i = 0; i<5;i++) {
						LaneGameobj current = x.get(i);
						//current.ImageView.setFitHeight(150);
				}
			}
			GameScreen.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
			return GameScreen;
        }
    	else {
    		int resources = gameFlow.getResourcesGathered();
			String phase = gameFlow.getBattlePhase().name();
			int score = gameFlow.getScore();
			int turn = gameFlow.getNumberOfTurns();
			Button currentscore = new Button("SCORE:" +  " " +score);
	        Button currentturn = new Button("TURN:" + " " + turn);
	        Button currentphase = new Button("" + phase);
	        Button currentresources = new Button("RESOURCES:" + " " +resources );
	        Button weaponShop = new Button("WEAPON SHOP");
	        currentscore.getStyleClass().add("currentbuttons");
	        currentturn.getStyleClass().add("currentbuttons");
	        currentphase.getStyleClass().add("currentbuttons");
	        currentresources.getStyleClass().add("currentbuttons");
	        weaponShop.getStyleClass().add("weaponshopbutton");
	        weaponShop.setOnMouseEntered(e ->  weaponShop.setCursor(Cursor.HAND));
	        weaponShop.setOnMouseExited(e ->  weaponShop.setCursor(Cursor.DEFAULT));
	        currentscore.setFont(fontxs);
	        currentturn.setFont(fontxs);
	        currentphase.setFont(fontxs);
	        currentresources.setFont(fontxs);
	        weaponShop.setFont(fontxs);
	        HBox currents = new HBox(40,currentscore,currentturn,currentphase,currentresources);
	        currents.setPadding(new Insets(10,0,0,20));
	        currents.setAlignment(Pos.TOP_LEFT);
	        HBox weaponShopBox = new HBox(weaponShop);
            weaponShopBox.setAlignment(Pos.TOP_RIGHT);
            weaponShopBox.setPadding(new Insets(10, 20, 0, 0));
            HBox top = new HBox(currents,weaponShopBox);
            HBox.setHgrow(currents, Priority.ALWAYS);
            top.setAlignment(Pos.TOP_CENTER);
            ContainerBox.setVisible(false);
			weaponShop.setOnAction(e -> ContainerBox.setVisible(!ContainerBox.isVisible()));
			StackPane center = new StackPane(WallLaneGrid,TitanWeaponMap);
			center.setMaxWidth(600);
			center.setPadding(new Insets(20,0,0,20));
			center.setMaxWidth(600);
			GameLayout.setTop(top);
			GameLayout.setCenter(center);
			GameLayout.setRight(ContainerBox);
			GameScreen = new Scene(GameLayout);
			GameLayout.getStyleClass().add("GameScreen-background");
			GameScreen.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
			WallLaneGrid.setGridLinesVisible(true);
			if(gameFlow.getOriginalLanes().size()==5) {
				for(int i = 0; i<5;i++) {
						LaneGameobj current = x.get(i);
						//current.ImageView.setFitHeight(150);
				}
			}
			lanesToggleBox.setVisible(false);
			weaponGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
	            @Override
	            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
	            	 if (weaponGroup.getSelectedToggle() != null) {
	                     lanesToggleBox.setVisible(true);
	                 } else {
	                     lanesToggleBox.setVisible(false);
	                 }
	             
	            }
	        });
			BuyButton.setOnAction(e -> {
				if (gameFlow != null) {
				ArrayList<Lane> OGlanes = gameFlow.getOriginalLanes();
				Image img = null;
			    if (!gameFlow.isGameOver()) {
			    	VBox foundNode = null;
			        Lane selectLane = null;
			        int WeaponCode = 0;
			        int LaneNumber = 0;
			        LaneGameobj graphselectLane = null;
			        if (weaponGroup.getSelectedToggle() != null) {
			            RadioButton selectedRadioButton = (RadioButton) weaponGroup.getSelectedToggle();
			            String selectedWeapon = selectedRadioButton.getText();
			            switch (selectedWeapon) {
			                case ("Anti Titan Shell"):{
			                    WeaponCode = 1;
			                    img = image;
			                    break;
			                }
			                case ("Long Range Spear"):{
			                    WeaponCode = 2;
			                    img = image2;
			                    break;
			                }
			                case ("Wall Spread Cannon"):{
			                    WeaponCode = 3;
			                    img = image3;
			                    break;
			                }
			                case ("Proximity Trap"):{
			                    WeaponCode = 4;
			                    img = image4;
			                    break;
			                }
			            }
			            if (LanesToggle.getSelectedToggle() != null) {
			                RadioButton selectedLaneButton = (RadioButton) LanesToggle.getSelectedToggle();
			                String selectedLaneNum = selectedLaneButton.getText();
			                char LaneNumChar = selectedLaneNum.charAt(4);
			                LaneNumber = Character.getNumericValue(LaneNumChar);
			                System.out.println("Selected Lane Number: " + LaneNumber);
			                graphselectLane = LaneRefList.get(LaneNumber-1);
			                selectLane = LaneRefList.get(LaneNumber-1).lane;
			            }
			        }
			        int desiredRow = LaneNumber-1;
			        int desiredColumn = 0;  

			        for (Node node : TitanWeaponMap.getChildren()) {
			            Integer rowIndex = GridPane.getRowIndex(node);
			            Integer columnIndex = GridPane.getColumnIndex(node);
			            if (rowIndex!=null && columnIndex!=null && rowIndex == desiredRow && columnIndex == desiredColumn) {
			                foundNode = (VBox)node;
			                break;
			            }
			        }

			        if (foundNode != null && selectLane != null && !selectLane.isLaneLost()) {
			            ArrayList<Weapon> LaneWeapons = selectLane.getWeapons();
			            if (LaneWeapons.size() < 5) {
			                try {
			                    gameFlow.purchaseWeapon(WeaponCode, selectLane);
			                    ImageView imageViewb = new ImageView(img); 
			                    if(gameFlow.getOriginalLanes().size()==5) {
			                    	 imageViewb.setFitWidth(27);
			                    	 imageViewb.setFitHeight(27);

			                    }
			                    if(gameFlow.getOriginalLanes().size()==3) {
			                    	 imageViewb.setFitWidth(40);
			                    	 imageViewb.setFitHeight(40);
			                    }
			                   
	                        	foundNode.getChildren().add(imageViewb);
	                        	GridPane gp = SpawnTitans(TitanWeaponMap,LaneRefList,OGlanes);
	                        	gp.setGridLinesVisible(true);
	                        	StackPane newCenter = new StackPane(WallLaneGrid,gp);
	                        	newCenter.setPadding(new Insets(20,0,0,20));
	                			GameLayout.setCenter(newCenter);
	                			currentscore.setText("Score:"+""+gameFlow.getScore()+"");
	                			currentturn.setText("Turn:"+""+gameFlow.getNumberOfTurns()+"");
	                			currentphase.setText("Phase:"+""+(gameFlow.getBattlePhase().name()));
	                			currentresources.setText("Resources:"+""+gameFlow.getResourcesGathered()+"");
	                			for (int i = 0; i < OGlanes.size(); i++) {
	                        	    Lane lane = OGlanes.get(i);
	                        	    if (!gameFlow.getLanes().contains(lane)) {
	                        	        final int row = OGlanes.indexOf(LaneRefList.get(i).lane);
	                        	        WallLaneGrid.getChildren().removeIf(node -> {
	                        	            Integer rowIndex = GridPane.getRowIndex(node);
	                        	            Integer columnIndex = GridPane.getColumnIndex(node);
	                        	            return rowIndex != null && columnIndex != null && rowIndex == row && columnIndex == 0;
	                        	        });
	                        	    }
	                        	}
	                			
	                        	
			                } catch (InsufficientResourcesException e1) {
			                	Alert alert = new Alert(Alert.AlertType.WARNING);
			                	alert.setTitle("Insufficient Resources");
			                	alert.setHeaderText(null);
			                	alert.setContentText("You Can't purchase this weapon.");
			                	alert.showAndWait();

			                } catch (InvalidLaneException e1) {
			                	Alert alert = new Alert(Alert.AlertType.WARNING);
			                	alert.setTitle("The Lane is Lost");
			                	alert.setHeaderText(null);
			                	alert.setContentText("You Can't place a weapon here.");
			                	alert.showAndWait();
			                }
			            }
			        }
			    }
			    else {
			    	mediaPlayergame.stop();
			    	 try {
			    		
						window.setEndScreen(gameFlow);
					} catch (IOException e1) {
					}
				        
			    }
			    
				}
			     
			});
			SkipButton.setOnAction(e ->{
				if (gameFlow != null) {
				ArrayList<Lane> OGlanes = gameFlow.getOriginalLanes();
				if(!gameFlow.isGameOver()) {
					gameFlow.passTurn();
					GridPane gp = SpawnTitans(TitanWeaponMap,LaneRefList,OGlanes);
                	gp.setGridLinesVisible(true);
                	StackPane newCenter = new StackPane(WallLaneGrid,gp);
                	newCenter.setPadding(new Insets(20,0,0,20));
        			GameLayout.setCenter(newCenter);
        			currentscore.setText("Score:"+""+gameFlow.getScore()+"");
        			currentturn.setText("Turn:"+""+gameFlow.getNumberOfTurns()+"");
        			currentphase.setText("Phase:"+""+(gameFlow.getBattlePhase().name()));
        			currentresources.setText("Resources:"+""+gameFlow.getResourcesGathered()+"");
        			for (int i = 0; i < OGlanes.size(); i++) {
                	    Lane lane = OGlanes.get(i);
                	    if (!gameFlow.getLanes().contains(lane)) {
                	        final int row = OGlanes.indexOf(LaneRefList.get(i).lane);
                	        WallLaneGrid.getChildren().removeIf(node -> {
                	            Integer rowIndex = GridPane.getRowIndex(node);
                	            Integer columnIndex = GridPane.getColumnIndex(node);
                	            return rowIndex != null && columnIndex != null && rowIndex == row && columnIndex == 0;
                	        });
                	    }
                	}
				}
				else {
					mediaPlayergame.stop();
				       try {
				    	
						window.setEndScreen(gameFlow);
					} catch (IOException e1) {
						
					}
				        
					
				}
				}
			});
			TitanWeaponMap.setGridLinesVisible(true);
			WallLaneGrid.setGridLinesVisible(true);
			
			return GameScreen;
    	}
    	
    }
	
	public PriorityQueue<TitanGameobj> getlaneTitans(Lane lane){
		PriorityQueue<TitanGameobj> Titanpic = new PriorityQueue<TitanGameobj>();
		PriorityQueue<Titan> Titans = lane.getTitans();
		for (Titan t : Titans) {
			TitanGameobj currentTitan = new TitanGameobj(t);
			Titanpic.add(currentTitan);	
		}
		return Titanpic;
          
		
	}
	public GridPane SpawnTitans(GridPane OGP, ArrayList<LaneGameobj> laneReference, ArrayList<Lane> originalLanes) {
	    GridPane gp = OGP;
	    ArrayList<LaneGameobj> thisRef = new ArrayList<>();
	    PriorityQueue<Lane> Lane = gameFlow.getLanes();
	    PriorityQueue<TitanGameobj> LaneTitanPQ;
	    int i = 0;
	    for (Lane lanes : Lane) {
	        LaneGameobj y = new LaneGameobj(lanes);
	        thisRef.add(i, y);
	        i++;
	    }
	    for (Lane lane : Lane) {
	        LaneTitanPQ = getlaneTitans(lane);
	        for (int j = 0; j < thisRef.size(); j++) {
	            if (thisRef.get(j).lane == lane) {
	                PriorityQueue<Titan> tref = lane.getTitans();
	                final int Row = originalLanes.indexOf(thisRef.get(j).lane);
	                gp.getChildren().removeIf(node -> {
	                    Integer rowIndex = GridPane.getRowIndex(node);
	                    Integer columnIndex = GridPane.getColumnIndex(node);
	                    return rowIndex != null && columnIndex != null && rowIndex.intValue() == Row && columnIndex.intValue() != 0;
	                });
	            }
	        } 
	    }
	    for (LaneGameobj lane : laneReference) {
	        LaneTitanPQ = getlaneTitans(lane.lane);
	        PriorityQueue<Titan> tref = lane.lane.getTitans();
	        if(Lane.contains(lane.lane)) {
		        for (TitanGameobj thisTitan : LaneTitanPQ) {
		            int LaneNum = (originalLanes.indexOf(lane.lane));
		            int currentColumn = thisTitan.titan.getDistance();
		            if (tref.contains(thisTitan.titan) && !thisTitan.titan.isDefeated()) { 
		                gp.add(thisTitan.finaltitan, currentColumn/10, LaneNum);
		                
		            }
		        }
	        }
	    } 
	    return gp;
	
	   

	
	}


}
