import java.awt.Color;
import java.util.ArrayList;

import edu.princeton.cs.algs4.StdDraw;

public class GfxCity extends GfxObject {
	private double size;
	private int number = 7;
	private Color col;
	private ArrayList<GfxCircle> habitats;
	private double[] offsetx;
	private double[] offsety;
	private int[] colorR;
	private int[] colorG;
	private int[] colorB;
	
	private double[] xback;
	private double[] yback;
	private double[] xmid;
	private double[] ymid;
	private double[] xfront;
	private double[] yfront;
	
	public GfxCity(double size, int number) {
		double[] xb = {5, -5, -5, -3, -3, -1, -1, 1, 1, 3, 3, 5, 5};
		double[] yb = {-5, -5, 0,  0,  4,  4,  1, 1, 3, 3, -1, -1, -5};
		double[] xm = {5, -5, -5, -3, -3, -1, -1, 1, 1, 3, 3, 5, 5};
		double[] ym = {-5, -5, -2,  -2,  0,  0,  -1, -1, 2, 2, -3, -3, -5};
		double[] xf = {5, -5, -5, -3, -3, -1, -1, 1, 1, 3, 3, 5, 5};
		double[] yf = {-5, -5, -4,  -4,  -3,  -3,  -2, -2, -3, -3, -4, -3, -5};
		// size / x = 10
		// size / 10 = x 
		double scale = size/10;
		this.size = size;
		this.number = number;
		for (int i = 0 ; i < xb.length; i++) {
			xb[i] = xb[i]*scale;
			yb[i] = yb[i]*scale;
			xm[i] = xm[i]*scale;
			ym[i] = ym[i]*scale;
			xf[i] = xf[i]*scale;
			yf[i] = yf[i]*scale;
		}
		this.xback = xb;
		this.yback = yb;
		this.xmid = xm;
		this.ymid = ym;
		this.xfront = xf;
		this.yfront = yf;
		
		
		double segWidth = size / number;

		this.offsetx = new double[number];
		this.offsety = new double[number];
		this.colorR = new int[number];
		this.colorG = new int[number];
		this.colorB = new int[number];
		for (int i = 0; i < number; i++) {
			offsetx[i] = size / 2 - Math.random()*size;
			offsety[i] = size / 2 - Math.random()*size;
			colorR[i] = (int)(Math.random() * 255);
			colorG[i] = (int)(Math.random() * 255);
			colorB[i] = (int)(Math.random() * 255);
		}
	}
	public void setColor(Color c) {
		col = c;
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
			yb[i] = yback[i] + this.loc.getY();
			xm[i] = xmid[i] + this.loc.getX();
			ym[i] = ymid[i] + this.loc.getY();
			xf[i] = xfront[i] + this.loc.getX();
			yf[i] = yfront[i] + this.loc.getY();
		}
		
		StdDraw.setPenColor(Color.BLUE);
		StdDraw.filledPolygon(xb,  yb);
		StdDraw.setPenColor(Color.CYAN);
		StdDraw.filledPolygon(xm,  ym);
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.filledPolygon(xf,  yf);
		
		/*
		for (int i = 0; i < this.number; i++) {
			StdDraw.setPenColor(colorR[i], colorG[i], colorB[i]);
			StdDraw.filledCircle(loc.getX()+offsetx[i], loc.getY()+offsety[i], size/2);
		}
		*/
	}
	public void setGraphicPosition(Vec2d newPos) {
		this.loc = newPos;
	}
	public void setSize(double s){
		size = s;
	}
}

