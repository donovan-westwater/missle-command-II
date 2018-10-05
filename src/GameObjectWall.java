
public class GameObjectWall extends GameObject {
	public GameObjectWall(GfxObject gfx, PhyObject phy, GameEngine gEngine) {
		super(gfx, phy, gEngine);
	}

	public GameObjectWall(GameEngine gEng) {
		super(gEng);
	}

	public void updateEvents() {
		if (gEvents == null) return;
		for (GameEvent ge : gEvents) {
			if (ge.getFlag() == GameEvent.GameEventFlag.TOUCH) {
		        // play using standard audio
				//Sound test = new Sound("ship_flip_effect.mp3");
				//test.play();
				System.err.println("Success");
			}
		}
		gEvents.clear();
	}

}
