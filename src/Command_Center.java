import java.awt.Color;

import edu.princeton.cs.algs4.StdDraw;

public class Command_Center extends GameObject {
	private Vec2d spawn_point;
	private int health;
	private int lasthealth;
	private int maxhealth;
	private int healthDeath;
	private int maxMissiles = 10;
	private double missileSpeed = 0.2;
	private GfxCommand commandBase;
	private boolean isTarget;
	GfxUI missileCount;
	
	public Command_Center(Vec2d loc,GameEngine g){
		super(new GfxCommand(0.5), new PhyBox(new Vec2d(0,0), new Vec2d(loc), 0.5, 100000000), g);
		commandBase = (GfxCommand)this.getGfxObj();
		spawn_point = loc;
		health = 50;
		healthDeath = 50;
		lasthealth = 50;
		maxhealth = health;
		missileCount = new GfxUI(new Vec2d(loc));
		updateGfxMissiles(maxMissiles);
		this.getgEng().addUI(missileCount);
		isTarget = true;
	}
	public boolean getTarget() {
		return isTarget;
	}
	private void updateGfxMissiles(int count) {
		commandBase.setAmmoCount(maxMissiles);
	}
	
	public void update(){
		updateGfxMissiles(maxMissiles);
		missileCount.setMsg(Integer.toString(maxMissiles));
		if (this.health < this.healthDeath) {
			System.out.println();
			System.out.println("I have been damaged: badly");
			this.getgEng().removeDuringFrame(this);
			super.setActive(false);
			this.getgEng().removeUI(missileCount);
		}
		if (lasthealth != health) {
			lasthealth = health;
			GfxObject graphics = this.getGfxObj();
			if (graphics instanceof GfxBox) {
				GfxBox box = (GfxBox) graphics;
				if (health > maxhealth / 2 && health < 4 * maxhealth / 5)
					box.setColor(Color.MAGENTA);	
				if (health < maxhealth / 2)
					box.setColor(Color.RED);	
			}
		}
		
	}
	
	public void updateEvents(){
		if (gEvents == null) return;
		for (GameEvent ge : gEvents) {
			if (ge.getFlag() == GameEvent.GameEventFlag.TOUCH) {
				System.out.println("Base has taken some damage!");
				this.health = 0;
				System.out.println("My base is locx : " + this.getPhysicsPos().getX());
				System.out.println("My health is now " + this.health);
			}
		}
		gEvents.clear();
	}
	
	public Vec2d getSpawnPoint(){
		return this.spawn_point;
	}
	public int getHealth(){
		return this.health;
	}

	public void fireMissile(Vec2d target) {
		if (!super.isActive()) return;
		if (maxMissiles > 0) {
			maxMissiles -= 1;
			Vec2d start = spawn_point;
			Vec2d dir = Vec2d.subtract(target, start);
			dir = Vec2d.getUnitVec(dir);
			dir = Vec2d.scaledVector(dir, missileSpeed);
			double mass = 10;
			double size = 0.2;

			PlayerMissle pM = new PlayerMissle(new GfxCircle(size),
					new PhyBox(dir, start, 0.0, mass), 
					this.getgEng(),
					target);
			pM.getpObj().setNonsolid(true);
			this.getgEng().addDuringFrame(pM);
			System.out.println("Added missle");

		}
		
	}
}
