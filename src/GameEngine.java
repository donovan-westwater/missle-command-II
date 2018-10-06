import java.util.ArrayList;
import java.util.ListIterator;

import edu.princeton.cs.algs4.StdDraw;

public class GameEngine {
	private GfxUI test = new GfxUI(new Vec2d(0.0, 0.0));
	private GfxEngine gfxEng;
	private PhysicsEngine pEng;
	private Player player;
	private AlienInvader invader;
	private GameMaster game;
	private Vec2d gravity = new Vec2d(0,0.01);

	// the individual gfx object and phy objects are stored inside the gameobjects
	private ArrayList<GameObject> gObjs;
	private ArrayList<GameObject> frameObjs;
	private ArrayList<GameObject> deadFrameObjs;
	private int frame = 0;
	
	// Constructor
	public GameEngine(double minX, double minY, double maxX, double maxY) {
		this.gfxEng = new GfxEngine(minX, minY, maxX, maxY);
		this.pEng = new PhysicsEngine(minX, minY);
		gObjs = new ArrayList<GameObject>();
		frameObjs = new ArrayList<GameObject>();
		deadFrameObjs = new ArrayList<GameObject>();
	}
	public Vec2d getGravity() {
		return this.gravity;
	}
	public void setGravity(Vec2d newgrav) {
		gravity = newgrav;
	}
	public int getFrame() {
		return frame;
	}

	public void setFrame(int frame) {
		this.frame = frame;
	}

	// Public 
	public void add(GfxEngine gfxEngine, PhysicsEngine pEngine) {
		this.gfxEng = gfxEngine;
		this.pEng = pEngine;
	}
	
	public void killInvader() {
		invader = null;
	}
	public void killPlayer() {
		player = null;
	}
	
	public void update() {
		frame = frame + 1;
		this.updateInput();
		this.updateObjects();
		this.addInnerFrameObjects();
		this.removeInnerFrameObjects();
		this.updatePhysics();
		this.updateGraphicPositions();
		this.updateEvents();
		this.updateDraw();
	}
	
	public void addDuringFrame(GameObject gameobj) {
		frameObjs.add(gameobj);
	}
	private void addInnerFrameObjects() {
		if (!frameObjs.isEmpty()) {
			for (GameObject frameGO : frameObjs) {
				this.add(frameGO);
			}
		}
		frameObjs.clear();
	}
	public void removeDuringFrame(GameObject gameobj) {
		deadFrameObjs.add(gameobj);
	}
	private void removeInnerFrameObjects() {
		if (!deadFrameObjs.isEmpty()) {
			for (GameObject frameGO : deadFrameObjs) {
				this.remove(frameGO);
			}
		}
		deadFrameObjs.clear();
	}
	
	public void add(GameObject gameobj) {
		gObjs.add(gameobj);
		if (gameobj.getGfxObj() != null) gfxEng.add(gameobj.getGfxObj());
		if (gameobj.getpObj() != null) pEng.addPhyObject(gameobj.getpObj());
	}
	public void remove(GameObject gameobj) {
		gfxEng.remove(gameobj.getGfxObj());
		pEng.removePhyObject(gameobj.getpObj());
		gObjs.remove(gameobj);
	}
	public ArrayList<GameObject> getgObjs(){
		return gObjs;
	}
	public PhysicsEngine getpEng(){
		return this.pEng;
	}

	//Private
	private void updateInput() {
		if (game != null)game.update();
		if (player != null) player.update();
		if (invader != null) invader.update();
	}
	private void updateDraw() {
		gfxEng.draw();
	}
	private void updatePhysics() {
		pEng.update();
	}
	private void updateGraphicPositions() {
		for (GameObject g : gObjs) {
			if(g.isActive()) g.setGraphicPosition();
		}
	}
	private void updateObjects() {
		for (GameObject g: gObjs) {
			if (g.isActive()) g.update();
		}
	}
	private void updateEvents() {
		for (GameObject g : gObjs) {
			if (g.isActive()) g.updateEvents();
		}
	}

	public void lowerMissileCount() {
		player.setMissileCount(player.getMissileCount() - 1);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public void addUI(GfxUI ui) {
		this.gfxEng.addUI(ui);
	}
	public void removeUI(GfxUI ui) {
		this.gfxEng.removeUI(ui);
	}

	public AlienInvader getInvader() {
		return invader;
	}

	public void setInvader(AlienInvader invader) {
		this.invader = invader;
	}
	public void setGameMaster(GameMaster master){
		this.game = master;
	}
	public GameMaster getGameMaster(){
		return game;
	}


}
