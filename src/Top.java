
public class Top extends GameObject {
	public Top(Vec2d loc, GameEngine gEngine,double size) {
		super(new GfxBox(size), 
				new PhyBox(new Vec2d(0.0, 0.0), loc, 0.0, 100000000000.0),
				gEngine);
		this.getpObj().setNonsolid(true);
	}
}
