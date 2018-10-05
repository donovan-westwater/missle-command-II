import edu.princeton.cs.algs4.StdDraw;

public class GameEngineTest {
	
	public static void drawVector(Vec2d vec){
		StdDraw.line(0.0, 0.0, vec.getX(), vec.getY());
	}
	public static void main(String[] args) {
		double minX = -5.0;
		double minY = -5.0;
		double maxX = 5.0;
		double maxY = 5.0;
		// set the scale of the coordinate system
		
		GameEngine gEngine = new GameEngine(minX, minY, maxX, maxY);

		PhyBox b1 = new PhyBox(new Vec2d(0.2, 0.2), new Vec2d(0.0, 3.0), 0.5, 10.0);
		PhyBox b2 = new PhyBox(new Vec2d(-0.0, 0.0), new Vec2d(0.0, -3.0), 1.0, 20.0);
		PhyBox b3 = new PhyBox(new Vec2d(0.0, -0.3), new Vec2d(0.0, 0.0), 0.5, 10.0);
		
		PhyBox topwall = new PhyBox(new Vec2d(0.0, 0.0), new Vec2d(0.0, 9.9), 10.0, 100000000.0);
		PhyBox bottomwall = new PhyBox(new Vec2d(0.0, 0.0), new Vec2d(0.0, -9.9), 10.0, 100000000.0);
		PhyBox rightwall = new PhyBox(new Vec2d(0.0, 0.0), new Vec2d(9.9, 0.0), 10.0, 100000000.0);
		PhyBox leftwall = new PhyBox(new Vec2d(0.0, 0.0), new Vec2d(-9.9, 0.0), 10.0, 100000000.0);
		
		// Gfx has only size; its just a box centered on zero
		GfxBox gBox1 = new GfxBox(b1.getSize());
		GfxBox gBox2 = new GfxBox(b2.getSize());
		GfxBox gBox3 = new GfxBox(b3.getSize());
		
		GfxBox gBoxtop = new GfxBox(topwall.getSize());
		GfxBox gBoxbottom = new GfxBox(bottomwall.getSize());
		GfxBox gBoxright = new GfxBox(rightwall.getSize());
		GfxBox gBoxleft = new GfxBox(leftwall.getSize());
		
		GameObjectBox firstBox = new GameObjectBox(gBox1, b1, gEngine);
		GameObjectBox secondBox = new GameObjectBox(gBox2, b2, gEngine);
		GameObjectBox thirdBox = new GameObjectBox(gBox3, b3, gEngine);
		
		GameObjectWall topwallObj = new GameObjectWall(gBoxtop, topwall, gEngine);
		GameObjectWall bottomwallObj = new GameObjectWall(gBoxbottom, bottomwall, gEngine);
		GameObjectWall rightwallObj = new GameObjectWall(gBoxright, rightwall, gEngine);
		GameObjectWall leftwallObj = new GameObjectWall(gBoxleft, leftwall, gEngine);
		
		GfxCircle gMissileShape = new GfxCircle(0.2);
		PhyBox pMissile = new PhyBox(new Vec2d(0.0, 0.0), new Vec2d (0.0, 0.0), 0.2, 10);
		GameObjectAlienMRV multiMissile = new GameObjectAlienMRV(gMissileShape, pMissile, gEngine, minX, maxX, maxY);
		
		GfxCircle firstCirc = new GfxCircle(0.5);
		PhyBox pCirBox = new PhyBox(new Vec2d(0.0, 0.0), new Vec2d(0.0, -4.0), 0.2, 10);
		GameObjectBox aCirc = new GameObjectBox(firstCirc, pCirBox, gEngine);
		
		gEngine.add(aCirc);
		gEngine.add(multiMissile);
		
		//gEngine.add(firstBox);
		//gEngine.add(secondBox);
		//gEngine.add(thirdBox);

		gEngine.add(topwallObj);
		gEngine.add(bottomwallObj);
		gEngine.add(rightwallObj);
		gEngine.add(leftwallObj);
		
		while(true) {
			gEngine.update();
		
		}
	}
}
