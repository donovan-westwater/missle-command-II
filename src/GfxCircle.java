import java.awt.Color;

import edu.princeton.cs.algs4.StdDraw;

public class GfxCircle extends GfxObject{
	private double size;
	private Color col;
	
	public GfxCircle(double size) {
		this.size = size;
		col = StdDraw.RED;
	}
	public void setColor(Color c) {
		col = c;
	}

	public void draw() {
		StdDraw.setPenColor(this.col);
		StdDraw.filledCircle(loc.getX(), loc.getY(), size/2);
	}
	public void setGraphicPosition(Vec2d newPos) {
		this.loc = newPos;
	}
	public void setSize(double s){
		size = s;
	}
}
