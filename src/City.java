import edu.princeton.cs.algs4.StdDraw;

public class City extends GameObject {
	private int health;
	public City (GfxObject gfx, PhyObject phy, GameEngine gEngine) {
		super(gfx, phy, gEngine);
	}

	public City(int num, Vec2d loc, GameEngine gEngine) {
		super(new GfxCircle(0.4), 
				new PhyBox(new Vec2d(0.0, 0.0), loc, 0.0, 100000000000.0),
				gEngine);
		((GfxCircle) this.getGfxObj()).setColor(StdDraw.BLUE);
		health = 50;
	}

	public void update(){
		this.getpObj().setDir(this.getgEng().getGravity());
		if(health == 0){
			this.getgEng().removeDuringFrame(this);
		}
	}
	public void updateEvents(){
		if (gEvents == null) return;
		for (GameEvent ge : gEvents) {
			if (ge.getFlag() == GameEvent.GameEventFlag.TOUCH) {
				System.out.println("Base has taken some damage!");
				health -= 1;
			}
		}
	}
}

