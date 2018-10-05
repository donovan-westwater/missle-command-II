
import java.awt.Color;
import java.util.ArrayList;

import edu.princeton.cs.algs4.StdDraw;

public class GfxGround extends GfxObject {
	private double size;
	private int number = 7;
	private int[] colorR;
	private int[] colorG;
	private int[] colorB;
	
	private double ymin = -5, xwidth = 10, ygroundheightmax = 2.0;
	
	private double[] xback;
	private double[] yback;
	private double[] xmid;
	private double[] ymid;
	private double[] xfront;
	private double[] yfront;
	
	public GfxGround(double xwid, double yheight) {
		this.xwidth = xwid;
		this.ygroundheightmax = yheight;
		// first two numbers are the base
		double[] xb = {5,  -5,              -5,  -4.75, -4.5, -4.25, -4.0, -3.75, -3.5, -3.25, -3.0, -2.75, -2.5, -2.25, -2.0, -1.75, -1.5, -1.25, -1.0, -0.75,  -0.5, -0.25, 0.0, 0.25,   0.5,  0.75, 1.0, 1.25, 1.5, 1.75, 2.0, 2.25, 2.5, 2.75,  3.0, 3.25,  3.5,  3.75,  4.0, 4.25,   4.5, 4.75, 5.0};
		double[] yb = {-5, -5,             3.25,  3.75,  3.5,   3.4,   3.2,    3,  2.5,   2.2,  2.5,  2.75,    2.2,  2.3,   2.5,  2.7,  2.2,  2.3,   2.5,   3.1,  3.5,   3.6,   3.3, 3.2,  3.3,   2.5,  2.3,  2.5,  2.8,  2.7,  2.2,   2.3,  2.5,   2.4,   2.3,  2.1,  2.3,  3.1,    3,   3.5,   3.3,   4,  3.5,   3};

		double[] xm = {5, -5, -5, -3, -3, -1, -1, 1, 1, 3, 3, 5, 5};
		double[] ym = {-5, -5, -2,  -2,  0,  0,  -1, -1, 2, 2, -3, -3, -5};
		double[] xf = {5, -5, -5, -3, -3, -1, -1, 1, 1, 3, 3, 5, 5};
		double[] yf = {-5, -5, -4,  -4,  -3,  -3,  -2, -2, -3, -3, -4, -3, -5};
		// size / x = 10
		// size / 10 = x 
		double xscale = xwidth / 10;
		double yscale = ygroundheightmax / 10; 
		for (int i = 0 ; i < xb.length; i++) {
			xb[i] = xb[i]*xscale;
			if (yb[i] != -5) yb[i] = yb[i]*yscale;
			//xm[i] = xm[i]*xscale;
			//ym[i] = ym[i]*yscale;
			//xf[i] = xf[i]*xscale;
			//yf[i] = yf[i]*yscale;
		}
		this.xback = xb;
		this.yback = yb;
		//this.xmid = xm;
		//this.ymid = ym;
		//this.xfront = xf;
		//this.yfront = yf;
	}
	public void draw() {
		int length = xback.length;
		double[] xb = new double[length];
		double[] yb = new double[length];
		double[] xm = new double[length];
		double[] ym = new double[length];
		double[] xf = new double[length];
		double[] yf = new double[length];
		
		for (int i = 0; i < xback.length; i++) {
			xb[i] = xback[i] + this.loc.getX();
			yb[i] = yback[i] + this.loc.getY() ;
			//xm[i] = xmid[i] + this.loc.getX();
			//ym[i] = ymid[i] + this.loc.getY();
			//xf[i] = xfront[i] + this.loc.getX();
			//yf[i] = yfront[i] + this.loc.getY();
		}
		
		StdDraw.setPenColor(Color.YELLOW);
		StdDraw.filledPolygon(xb,  yb);
		//StdDraw.setPenColor(Color.CYAN);
		//StdDraw.filledPolygon(xm,  ym);
		//StdDraw.setPenColor(Color.BLACK);
		//StdDraw.filledPolygon(xf,  yf);
		
	}
	public void setGraphicPosition(Vec2d newPos) {
		this.loc = newPos;
	}
	public void setSize(double s){
		size = s;
	}
}

