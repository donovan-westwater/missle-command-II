import java.awt.Color;

import edu.princeton.cs.algs4.StdDraw;

public class GfxBox extends GfxObject{
	private double size;
	private Color col;
	
	public GfxBox(double size) {
		this.size = size;
		col = StdDraw.BLUE;
	}
	public void setColor(Color c) {
		col = c;
	}

	public void draw() {
		StdDraw.setPenColor(this.col);
		StdDraw.filledSquare(loc.getX(), loc.getY(), size/2);
	}
	public void setGraphicPosition(Vec2d newPos) {
		this.loc = newPos;
	}
	
}
