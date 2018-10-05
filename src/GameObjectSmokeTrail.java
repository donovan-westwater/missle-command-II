import edu.princeton.cs.algs4.StdDraw;

public class GameObjectSmokeTrail extends GameObject{
	int frameLife = 0;
	GfxCircle circ;

	public GameObjectSmokeTrail (Vec2d loc, GameEngine gEng) {
		super(new GfxCircle(0.05),
				new PhyBox(new Vec2d(0.0, 0.0), new Vec2d(loc), 0.00000, 0.000001),
				gEng);
		this.getpObj().setNonsolid(true);
		circ = (GfxCircle)this.getGfxObj();
		circ.setColor(StdDraw.BLUE); // add color later
		circ.setVisible(false);
	}
	
	public void update() {
		frameLife += 1;
		if (frameLife > 2) {
			this.getGfxObj().setVisible(true);
		}
		if (frameLife > 100) {
			this.getgEng().removeDuringFrame(this);
		}
	}
	
	
	
}
