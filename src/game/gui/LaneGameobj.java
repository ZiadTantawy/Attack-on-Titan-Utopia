package game.gui;

import game.engine.lanes.Lane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class LaneGameobj {
	//public final ImageView ImageView;
	//public final Image Image;
	public Lane lane;
	public LaneGameobj(Lane lane) {
//		this.Image = new Image(getClass().getResource("Images/BaseLane.png").toExternalForm());
//		this.ImageView = new ImageView(this.Image);
//		ImageView.setFitWidth(1080);
//		ImageView.setFitHeight(250);
		this.lane=lane;
	}

}

