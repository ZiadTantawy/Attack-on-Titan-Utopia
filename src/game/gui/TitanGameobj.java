package game.gui;


import game.engine.titans.AbnormalTitan;
import game.engine.titans.ArmoredTitan;
import game.engine.titans.ColossalTitan;
import game.engine.titans.PureTitan;
import game.engine.titans.Titan;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.layout.Region;

public class TitanGameobj implements Comparable<TitanGameobj> {
	public final ImageView ImageView;
	public final Image Image;
	public Titan titan;
	public VBox finaltitan;
	public TitanGameobj(Titan titan) {
		
		if(titan instanceof AbnormalTitan) {
		this.Image = new Image(getClass().getResource("Images/AbnormalTitan.png").toExternalForm());
		this.ImageView = new ImageView(this.Image);
		ImageView.setFitWidth(100);
		ImageView.setFitHeight(100);
		Text tithealth = new Text("Health:" + titan.getCurrentHealth());
		tithealth.getStyleClass().add("tithealth");
		Region spacer = new Region();
		VBox.setVgrow(spacer, Priority.ALWAYS);
		finaltitan = new VBox(5,spacer,tithealth,ImageView);
		this.titan=titan;
	}
		else if(titan instanceof ArmoredTitan) {
			this.Image = new Image(getClass().getResource("Images/ArmoredTitan.png").toExternalForm());
			this.ImageView = new ImageView(this.Image);
			ImageView.setFitWidth(120);
			ImageView.setFitHeight(120);
			Text tithealth = new Text("Health:" + titan.getCurrentHealth());
			tithealth.getStyleClass().add("tithealth");
			Region spacer = new Region();
			VBox.setVgrow(spacer, Priority.ALWAYS);
			finaltitan = new VBox(5,spacer,tithealth,ImageView);
			this.titan=titan;
		}
		else if(titan instanceof ColossalTitan) {
			this.Image = new Image(getClass().getResource("Images/ColossalTitan.png").toExternalForm());
			this.ImageView = new ImageView(this.Image);
			ImageView.setFitWidth(160);
			ImageView.setFitHeight(160);
			Text tithealth = new Text("Health:" + titan.getCurrentHealth());
			tithealth.getStyleClass().add("tithealth");
			Region spacer = new Region();
			VBox.setVgrow(spacer, Priority.ALWAYS);
			finaltitan = new VBox(5,spacer,tithealth,ImageView);
			this.titan=titan;
		}
		else if (titan instanceof PureTitan) {
			this.Image = new Image(getClass().getResource("Images/femaleTitan.png").toExternalForm());
			this.ImageView = new ImageView(this.Image);
			ImageView.setFitWidth(120);
			ImageView.setFitHeight(120);
			Text tithealth = new Text("Health:" + titan.getCurrentHealth());
			tithealth.getStyleClass().add("tithealth");
			Region spacer = new Region();
			VBox.setVgrow(spacer, Priority.ALWAYS);
			finaltitan = new VBox(5,spacer,tithealth,ImageView);
			this.titan=titan;
		}
		else {
			this.Image = new Image(getClass().getResource("Images/ArmoredTitan.png").toExternalForm());
			this.ImageView = new ImageView(this.Image);
			ImageView.setFitWidth(100);
			ImageView.setFitHeight(100);
			Text tithealth = new Text("Health:" + titan.getCurrentHealth());
			tithealth.getStyleClass().add("tithealth");
			Region spacer = new Region();
			VBox.setVgrow(spacer, Priority.ALWAYS);
			finaltitan = new VBox(5,spacer,tithealth,ImageView);
			this.titan=titan;
			
		}
		//finaltitan.setPadding(new Insets(10));
		finaltitan.setFillWidth(false);
		

}
	
	@Override
	public int compareTo(TitanGameobj o) {
		if(o instanceof TitanGameobj) {
			return this.titan.compareTo((Titan) o.titan);
		}
		return 0;
	}
	
}