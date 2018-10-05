import java.util.ArrayList;

import edu.princeton.cs.algs4.StdDraw;

public class GfxEngine {
	private ArrayList<GfxObject> gfxObjs;
	private ArrayList<GfxUI> gfxUIs;
	private double minX;
	private double maxX;
	private double minY;
	private double maxY;
	private int pausetime = 20;

	public GfxEngine(double minX, double minY, double maxX, double maxY) {
		// TODO Auto-generated constructor stub
		this.minX = minX;
		this.minY = minY;
		this.maxX = maxX;
		this.maxY = maxY;
		
		this.gfxObjs = new ArrayList<GfxObject>();
		this.gfxUIs = new ArrayList<GfxUI>();

		StdDraw.setCanvasSize(800, 800);
		StdDraw.setXscale(minX, maxX);
		StdDraw.setYscale(minY, maxY);
		StdDraw.enableDoubleBuffering();
	}
	public void add(GfxObject gfxObj) {
		this.gfxObjs.add(gfxObj);
	}
	public void remove(GfxObject gfxObj) {
		this.gfxObjs.remove(gfxObj);
	}
	public void addUI(GfxUI gfxUI) {
		this.gfxUIs.add(gfxUI);
	}
	public void removeUI(GfxUI gfxUI) {
		this.gfxUIs.remove(gfxUI);
	}

	public void draw() {
		StdDraw.clear(StdDraw.BLACK);

		for (GfxObject gfx : gfxObjs) {
			if (gfx.isVisible())
				gfx.draw();
		}
		for (GfxObject gfx : gfxUIs) {
			if (gfx.isVisible())
				gfx.draw();
		}
		
		// show the update
		StdDraw.show();

		// pause for pausetime ms
		StdDraw.pause(pausetime);
	}
}
