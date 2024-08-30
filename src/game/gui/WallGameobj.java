package game.gui;

import game.engine.base.Wall;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class WallGameobj {
	public final ImageView ImageView;
	public final Image Image;
	public Wall wall;
	public WallGameobj(Wall wall) {
		this.Image = new Image(getClass().getResource("Images/BaseWall.jpg").toExternalForm());
		this.ImageView = new ImageView(this.Image);
		ImageView.setFitWidth(110);
		this.wall=wall;
	}

}
