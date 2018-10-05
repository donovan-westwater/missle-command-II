import java.awt.Color;
import edu.princeton.cs.algs4.StdDraw;

public class GfxSprite extends GfxObject{
	private String filename;
	private double size;
	private Color col;

	public GfxSprite(double size, String filename) {
		this.size = size;
		col = StdDraw.RED;
	}
	public void setColor(Color c) {
		col = c;
	}
	public void setGraphicPosition(Vec2d newPos) {
		this.loc = newPos;
	}
	public void setSize(double s){
		size = s;
	}
	@Override
	void draw() {
		StdDraw.picture(loc.getX(), loc.getY(), filename);
	}
}
