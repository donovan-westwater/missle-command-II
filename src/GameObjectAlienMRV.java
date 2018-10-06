import java.util.ArrayList;

import edu.princeton.cs.algs4.StdOut;

public class GameObjectAlienMRV extends GameObject {
	private int frameLife;
	private double xMin = -5.0, xMax = 5.0;
	private double yMax = 5.0;
	private Vec2d[] targets;
	private int maxSpawns = 8;
	private Vec2d myTarget;
	private double speed = 0.015;
	private ArrayList<GameObjectSmokeTrail> trail;
	private int spawnerTime = 45;
	
	public GameObjectAlienMRV(GfxObject gfx, PhyObject phy, GameEngine gEng, double xMin, double xMax, double yMax) {
		super(gfx, phy, gEng);
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMax = yMax;
		this.frameLife = 0;
		trail = new ArrayList<GameObjectSmokeTrail>();
		initialize();
	}
	
	public GameObjectAlienMRV(Vec2d target, GameEngine gEng, Vec2d[] tars) {
		super(new GfxCircle(0.2), new PhyBox(target, new Vec2d(10.0, 10.0), 0.0, 10.0), gEng); 
		this.frameLife = 0;
		this.targets = tars;
		this.myTarget = target;
		this.getpObj().setDir(new Vec2d(0.0, 0.0));
		trail = new ArrayList<GameObjectSmokeTrail>();
		initialize();
	}
	private void initialize() {
		double xScale = Math.random();
		double xrange = Math.abs(xMax - xMin);
		double xLoc = xMin + xrange * xScale;
		Vec2d startLocation = new Vec2d(xLoc, yMax - 2.5);
		this.getpObj().setLoc(startLocation);
		Vec2d direction = Vec2d.subtract(this.myTarget, startLocation);
		direction = Vec2d.getUnitVec(direction);
		direction = Vec2d.scaledVector(direction, this.speed);
		this.getpObj().setDir(direction);
		this.getpObj().setNonsolid(true);
	}

	public void update() {
		GameObjectSmokeTrail t = new GameObjectSmokeTrail(super.getPhysicsPos(), this.getgEng());
		trail.add(t);
		this.getgEng().addDuringFrame(t);

		if (frameLife == spawnerTime) {
			int spawns = (int) (Math.random() * maxSpawns);
			
			for (int i = 0; i < spawns; i++) {
				int tarIndex = ((int) (Math.random() * targets.length));
				if (tarIndex > targets.length - 1 ||
						tarIndex < 0) {
					System.out.println("Bad index of :" + tarIndex);
				}
				Vec2d newTarget = targets[tarIndex];
				Vec2d dir = Vec2d.subtract(newTarget, this.getpObj().getLoc());
				dir = Vec2d.getUnitVec(dir);
				dir = Vec2d.scaledVector(dir, 0.01);
				PhyBox pMissile = new PhyBox(dir, this.getpObj().getLoc(), 0.2, 10);
				GameObjectAlienRocket missile = new GameObjectAlienRocket(new GfxCircle(0.2), pMissile, super.getgEng(), newTarget);
				super.getgEng().addDuringFrame(missile);
			}
			super.getgEng().removeDuringFrame(this);
		}
		frameLife = frameLife + 1;
	}
	
}
