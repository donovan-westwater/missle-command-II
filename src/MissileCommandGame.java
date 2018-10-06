
public class MissileCommandGame {
	double minX = -5.0;
	double minY = -5.0;
	double maxX = 5.0;
	double maxY = 5.0;
	// set the scale of the coordinate system
	
	GameEngine gEngine = new GameEngine(minX, minY, maxX, maxY);

	public MissileCommandGame() {
		initialize();
		
		while(true) {
			gEngine.update();
		}
	}
	
	public static void main(String[] args) {
		MissileCommandGame game = new MissileCommandGame();
	}
	private void initialize() {
		GameMaster joke = new GameMaster(gEngine);
		/*
		PhyBox topwall = new PhyBox(new Vec2d(0.0, 0.0), new Vec2d(0.0, 9.9), 10.0, 100000000.0);
		PhyBox bottomwall = new PhyBox(new Vec2d(0.0, 0.0), new Vec2d(0.0, -9.9), 10.0, 100000000.0);
		PhyBox rightwall = new PhyBox(new Vec2d(0.0, 0.0), new Vec2d(9.9, 0.0), 10.0, 100000000.0);
		PhyBox leftwall = new PhyBox(new Vec2d(0.0, 0.0), new Vec2d(-9.9, 0.0), 10.0, 100000000.0);
		GfxBox gBoxtop = new GfxBox(topwall.getSize());
		GfxBox gBoxbottom = new GfxBox(bottomwall.getSize());
		GfxBox gBoxright = new GfxBox(rightwall.getSize());
		GfxBox gBoxleft = new GfxBox(leftwall.getSize());
		GameObjectWall topwallObj = new GameObjectWall(gBoxtop, topwall, gEngine);
		GameObjectWall bottomwallObj = new GameObjectWall(gBoxbottom, bottomwall, gEngine);
		GameObjectWall rightwallObj = new GameObjectWall(gBoxright, rightwall, gEngine);
		GameObjectWall leftwallObj = new GameObjectWall(gBoxleft, leftwall, gEngine);
		
		gEngine.add(topwallObj);
		gEngine.add(bottomwallObj);
		gEngine.add(rightwallObj);
		gEngine.add(leftwallObj);
		*/
		
	}

}
