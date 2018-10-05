public class City extends GameObject {
	private int health;
	public City (GfxObject gfx, PhyObject phy, GameEngine gEngine) {
		super(gfx, phy, gEngine);
	}

	public City(int num, Vec2d loc, GameEngine gEngine) {
		super(new GfxCity(0.4, num), 
				new PhyBox(new Vec2d(0.0, 0.0), loc, 0.0, 100000000000.0),
				gEngine);
		health = 50;
	}

	public void update(){	
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

