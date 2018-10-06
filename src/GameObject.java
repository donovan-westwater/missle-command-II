import java.util.ArrayList;

abstract class GameObject {
	private GameEngine gEng;
	private boolean active;
	private GameEvent flag;
	private GfxObject gfxObj;
	private Object lastHit;
	double frameTime; 

	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public GameEngine getgEng() {
		return gEng;
	}
	public void setgEng(GameEngine gEng) {
		this.gEng = gEng;
	}

	
	public GfxObject getGfxObj() {
		return gfxObj;
	}
	public void setGfxObj(GfxObject gfxObj) {
		this.gfxObj = gfxObj;
	}

	private PhyObject pObj;
	public PhyObject getpObj() {
		return pObj;
	}
	public void setpObj(PhyObject pObj) {
		this.pObj = pObj;
		pObj.setgObj(this);
	}

	protected ArrayList<GameEvent> gEvents;
	
	public GameObject(GfxObject gfx, PhyObject phy, GameEngine gEngine) {
		this.active = true;
		this.gfxObj = gfx;
		phy.setgObj(this);
		this.pObj = phy;
		this.gEng = gEngine;
		gEvents = new ArrayList<GameEvent>();
	}

	public GameObject(GameEngine gEng) {
		this.active = false;
		this.gEng = gEng;
		gEvents = new ArrayList<GameEvent>();
	}
	public void setLast(Object x) {
		lastHit = x;
	}
	public Object getlastHit() {
		return lastHit;
	}
	public void update() {
		this.frameTime = frameTime + 1;
	}
	public void updateEvents() {
		if (gEvents == null) return;
		for (GameEvent ge : gEvents) {
			if (ge.getFlag() == GameEvent.GameEventFlag.TOUCH) {
		        // play using standard audio
				//Sound test = new Sound("explode.mp3");
				//test.play();
				System.err.println("Success");
			}
		}
		gEvents.clear();
	}
	public void addEvent(GameEvent gEvent) {
		gEvents.add(gEvent);
	}

	public void remove() {
		// we are being removed from the game
		// deal with it 
	}
	
	public Vec2d getPhysicsPos() {
		return this.getpObj().getLoc();
	}
	public void setGraphicPosition() {
		this.gfxObj.setGraphicPosition(this.pObj.getLoc());
	}
	
}
