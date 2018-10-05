
import java.awt.Color;

import edu.princeton.cs.algs4.StdDraw;

public class GfxAmmo extends GfxObject{
	private double size;
	private Color col;
	private double[] xs;
	private double[] ys;
	
	public GfxAmmo(double size) {
		this.size = size;
		col = StdDraw.BLUE;
		double[] xvals = {3,   1, 1,  0,  -1, -1,  -3, -3, -1, -1,  0, 1, 1,  3};
		double[] yvals = {-4, -4, -2, -1, -2, -4,  -4, -1,  1,  3,  4, 3, 1, -1};
		// 6 * scale  = size
		// scale = size / 6
		double scale = size / 6;
		for (int i = 0; i < xvals.length; i++) {
			xvals[i] = xvals[i] * scale;
			yvals[i] = yvals[i] * scale;
		}
		xs = xvals;
		ys = yvals;
	}
	public void setColor(Color c) {
		col = c;
	}

	public void draw() {
		int length = xs.length;
		double[] x = new double[length];
		double[] y = new double[length];
		
		for (int i = 0; i < x.length; i++) {
			x[i] = xs[i] + this.loc.getX();
			y[i] = ys[i] + this.loc.getY();
		}
		
		StdDraw.setPenColor(col);
		StdDraw.filledPolygon(x, y);
	}

	public void setGraphicPosition(Vec2d newPos) {
		this.loc = newPos;
	}
	
}
