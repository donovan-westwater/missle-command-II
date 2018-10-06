
public class Ground extends GameObject {
	public Ground(Vec2d loc, GameEngine gEngine, double xwidth, double maxh) {
		super(new GfxGround(xwidth, maxh), 
				new PhyBox(new Vec2d(0.0, 0.0), loc, 0.0, 100000000000.0),
				gEngine);
		this.getpObj().setNonsolid(true);
	}

}

