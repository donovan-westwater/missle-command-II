import java.util.ArrayList;

public class Boom extends GameObject{
	private double intialr;
	private double changer;
	private int explodeCount = 0;

	public Boom(Vec2d start, GfxCircle gfx,GameEngine gx, double r) {
		super(new GfxCircle(r), new PhyBox(new Vec2d(0,0), start, 0.0, 100000000), gx);	
		intialr = r;
		changer = intialr;
	}
	
	public void update() {
		this.explosion();
	}
	
	public void explosion() {
		GfxCircle g = (GfxCircle) this.getGfxObj(); 
		PhyBox p =  (PhyBox) this.getpObj();
		GameEngine gE = this.getgEng();
		ArrayList<PhyObject> dead = new ArrayList<PhyObject>(0);
		dead = gE.getpEng().withinRadius(g.loc, changer/2);
		changer += 0.1;
		g.setSize(changer);
		if(changer > 2){
			gE.removeDuringFrame(this);
		}
		for(PhyObject x : dead){
			if(dead != null && x != this.getpObj() ){
				x.getgObj().addEvent(new GameEvent(GameEvent.GameEventFlag.TOUCH));
				if (!(x.getgObj() instanceof Boom ||
						x.getgObj() instanceof GameObjectSmokeTrail ||
						x.getgObj() instanceof Command_Center)) {
					if (x.getgObj() instanceof GameObjectAlienMRV && x.gObj.getlastHit() instanceof Boom)
						gE.getGameMaster().addScore(250);
					if (x.getgObj() instanceof GameObjectAlienRocket && x.gObj.getlastHit() instanceof Boom)
						gE.getGameMaster().addScore(50);
					gE.removeDuringFrame(x.getgObj());
					gE.addDuringFrame(new Boom(x.getLoc(), g, gE, intialr));
					explodeCount += 1;
					System.out.println("Chain reaction success!");
				}
			}
		}

	}
	public static void main(String[] args){
		double minX = -5.0;
		double minY = -5.0;
		double maxX = 5.0;
		double maxY = 5.0;
		GameEngine gEngine = new GameEngine(minX, minY, maxX, maxY);
		GfxCircle firstCirc = new GfxCircle(0.2);
		PhyBox pCirBox = new PhyBox(new Vec2d(0, 0), new Vec2d(0.0, 0.0), 0.2, 1000000);
		
		Boom x = new Boom(new Vec2d(0, 0),firstCirc,gEngine, 0.2);

		gEngine.add(x);
		while(true) {
			gEngine.update();

		}
	}
}
