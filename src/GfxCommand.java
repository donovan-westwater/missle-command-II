import java.awt.Color;
import java.util.ArrayList;

import edu.princeton.cs.algs4.StdDraw;

public class GfxCommand extends GfxObject {
	double size;
	GfxAmmo[] ammo;
	int ammoCount = 2;
	double[] xoffsets;
	double[] yoffsets;
	int[] xIndex = {3, 2, 4, 1, 3, 5, 0, 2, 4, 6};
	int[] yIndex = {0, 1, 1, 2, 2, 2, 3, 3, 3, 3};
	
	public GfxCommand(double size) {
		this.size = size;
		ammo = new GfxAmmo[10];
		xoffsets = new double[7];
		yoffsets = new double[4];
		for (int i = 0; i < ammo.length; i++) {
			ammo[i] = new GfxAmmo(size/4);
		}
		for (int i = 0; i < xoffsets.length; i++) {
			xoffsets[i] = -size/2 + i*size/6;	
		}
		for (int i = 0; i < yoffsets.length; i++) {
			yoffsets[i] = size/2 - i*size/3;
		}
	}
	public void draw() {
		StdDraw.setPenColor(StdDraw.GRAY);
		StdDraw.filledCircle(this.loc.getX(), this.loc.getY(), size);
		StdDraw.setPenColor(StdDraw.BLUE);
		for (int i = 0; i < ammoCount; i++) {
			Vec2d adj = new Vec2d(loc.getX() + xoffsets[xIndex[i]], loc.getY() + yoffsets[yIndex[i]]);
			ammo[i].setGraphicPosition(adj);
			ammo[i].draw();
		}
		
		
	}
	public void setAmmoCount(int ammo){
		this.ammoCount = ammo;
	}
	public void setGraphicPosition(Vec2d newPos) {
		this.loc = newPos;
	}
}