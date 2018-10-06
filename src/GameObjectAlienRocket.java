import java.util.ArrayList;

import edu.princeton.cs.algs4.StdOut;
//for need a non solid timer before switchig to collision mode. Create no collide for missles
public class GameObjectAlienRocket extends GameObject {
	private int frameLife = 0;
	private double speed = 0.04;
	private ArrayList<GameObjectSmokeTrail> trail;
	private Vec2d target;
	private double triggerDist = 0.15;
	private int timer;

	public GameObjectAlienRocket(GfxObject gfx, PhyObject phy, GameEngine gEng, Vec2d target) {
		super(gfx, phy, gEng);
		trail = new ArrayList<GameObjectSmokeTrail>();
		this.frameLife = 0;
		this.target = target;
		timer = 4;
		initialize();
	}
	
	public GameObjectAlienRocket(GameEngine gEng, Vec2d target) {
		super(gEng);
		trail = new ArrayList<GameObjectSmokeTrail>();
		this.target = target;
		initialize();
	}

	private void initialize() {
		this.getpObj().setNonsolid(true);
		Vec2d dir = this.getpObj().getDir();
		dir = Vec2d.getUnitVec(dir);
		dir = Vec2d.scaledVector(dir, speed);
		this.getpObj().setDir(dir);
	}
	
	@Override
	public void update() {
		GameObjectSmokeTrail t = new GameObjectSmokeTrail(this.getPhysicsPos(), this.getgEng());
		trail.add(t);
		this.getgEng().addDuringFrame(t);

		// we need to see if we reached destination
		// we need to see if we hit some something first
		Vec2d currentPosition = super.getPhysicsPos();
		Vec2d subbedVec = Vec2d.subtract(currentPosition, this.target);
		
		double distSqrd = Vec2d.dotAB(subbedVec, subbedVec);
		if(timer > 0) {
			timer -= 1;
		}
		if(timer <= 0) {
			this.getpObj().setNonsolid(false);
		}
		if (distSqrd < (triggerDist*triggerDist) || this.getlastHit() instanceof City) {
			this.remove();
			this.getgEng().removeDuringFrame(this);
			Boom ball = new Boom(this.getPhysicsPos(), new GfxCircle(0.5), this.getgEng(), 0.5);
			this.getgEng().addDuringFrame(ball);
			System.out.println("Hey, I went boom!");
		}

		
		frameLife = frameLife + 1;
	}
	
	@Override
	public void updateEvents() {
		if (gEvents == null) return;
		for (GameEvent ge : gEvents) {
			if (ge.getFlag() == GameEvent.GameEventFlag.BOOM) {
				/*
					this.getgEng().removeDuringFrame(this);
					Boom ball = new Boom(this.getPhysicsPos(), new GfxCircle(0.5), this.getgEng(), 0.5);
					this.getgEng().addDuringFrame(ball);
					System.out.println("Hey, I went boom!");
					*/
				}
			if(ge.getFlag() == GameEvent.GameEventFlag.TOUCH) {
				this.remove();
				this.getgEng().removeDuringFrame(this);
				Boom ball = new Boom(this.getPhysicsPos(), new GfxCircle(0.5), this.getgEng(), 0.5);
				this.getgEng().addDuringFrame(ball);
				System.out.println("Hey, I went boom!");
			}
				
		        // play using standard audio
				//Sound test = new Sound("ship_flip_effect.mp3");
				//test.play();
				System.err.println("I've been blown up");
			}
		gEvents.clear();
		}
		
	}


