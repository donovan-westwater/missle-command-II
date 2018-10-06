import edu.princeton.cs.algs4.StdDraw;

public class City extends GameObject {
	private int health;
	private boolean isTarget;
	public City (GfxObject gfx, PhyObject phy, GameEngine gEngine) {
		super(gfx, phy, gEngine);
		this.isTarget = true;
	}

	public City(int num, Vec2d loc, GameEngine gEngine) {
		super(new GfxCircle(0.4), 
				new PhyBox(new Vec2d(0.0, 0.0), loc, 0.0, 00000001.0),
				gEngine);
		((GfxCircle) this.getGfxObj()).setColor(StdDraw.BLUE);
		health = 50;
	}
	public boolean getTarget() {
		return isTarget;
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
				if(this.getlastHit() instanceof GameObjectAlienMRV) {
					System.out.println("Base has taken some damage!");
					health -= 1;
				}
			}
		}
	}
}

