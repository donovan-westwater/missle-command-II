import java.util.ArrayList;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;
import java.awt.Font;

public class GameMaster {
	private Vec2d[] targets = new Vec2d[9];
	private GfxUI screen;
	private GfxUI replaytext;
	private GfxUI scoretext;
	private GameEngine eng;
	private int score;
	public enum Phase {
		PREGAME, GAME, POSTGAME;
	}
	private int phase = 1;
	private int press_amount = 0;
	private ArrayList<GameObject> phase1;
	private ArrayList<GameObject> phase2;
	private ArrayList<GameObject> phase3;
	private ArrayList<GameObject> cities;
	
	//Trying to get phy location, so moving towns up here
	private City town1;
	private City town2;
	private City town3;
	private City town5;
	private City town6;
	private City town7;
	
	public GameMaster(GameEngine tim){
		screen = new GfxUI(new Vec2d(0,0));
		replaytext = new GfxUI(new Vec2d(0, -1.0));
		scoretext = new GfxUI(new Vec2d(-2.5, 4.5));
		eng = tim;
		eng.setGameMaster(this);
		this.startPhase1();
		replaytext.setMsg("");
		scoretext.setMsg("");
	}
	public void update(){
		if(StdDraw.isKeyPressed(27) && press_amount == 0){
			changePhase(2);
			press_amount += 1;
			this.startPhase2();
		}
		boolean anyCitiesLeft = false;
		for(GameObject x : eng.getgObjs()){
			if(x instanceof City && phase == 2) {
				anyCitiesLeft = true;
				break;
			}
		}
		if (phase == 1) {
			screen.setMsg("MISSLE COMMAND 2!: PRESS 'ESCAPE' TO START!");
		}
		if (phase == 2) {
			StdDraw.setFont();
			scoretext.setMsg("SCORE: " + score);
		/*	
			targets[1] = this.eng.getgObjs().get(eng.getgObjs().indexOf(town1)).getPhysicsPos();
			targets[2] = this.eng.getgObjs().get(eng.getgObjs().indexOf(town2)).getPhysicsPos();
			targets[3] = this.eng.getgObjs().get(eng.getgObjs().indexOf(town3)).getPhysicsPos();
			targets[5] = this.eng.getgObjs().get(eng.getgObjs().indexOf(town5)).getPhysicsPos();
			targets[6] = this.eng.getgObjs().get(eng.getgObjs().indexOf(town6)).getPhysicsPos();
			targets[7] = this.eng.getgObjs().get(eng.getgObjs().indexOf(town7)).getPhysicsPos();
			*/	
			
		}
		if (!anyCitiesLeft && phase == 2) {
			this.startPhase3();
			this.changePhase(3);
		}
		if (phase == 3) {
			if(StdDraw.isKeyPressed(88)) {
				replaytext.setMsg("");
				startPhase2();
			}
			
		}
	}
	private void startPhase1(){
		screen.setCenter(new Vec2d(0,0));
		eng.addUI(screen);
		eng.addUI(replaytext);
		eng.addUI(scoretext);
	}
	private void startPhase2(){
		this.changePhase(2);
		double minX = -5.0;
		double minY = -5.0;
		double maxX = 5.0;
		double maxY = 5.0;
		screen.setMsg("");
		scoretext.setMsg("SCORE: " + score);

		Player hansolo = new Player(eng);
		eng.setPlayer(hansolo);

		
		AlienInvader invader = new AlienInvader(targets, eng);
		eng.setInvader(invader);
		double gutter = 1.0;
		double maxWidth = Math.abs(maxX - minX);
		double targetWidth = maxWidth - gutter;
		double defaultY = -4.0;
		System.out.println("Gutter: " + gutter + " and targetWidth " + targetWidth );
		for (int i = 0; i < targets.length; i++) {
			targets[i] = new Vec2d(minX + gutter/2 + targetWidth * i / (targets.length-1), defaultY);
		}
/*
		for (int i = 0; i < targets.length; i++) {
			targets[i] = new Vec2d(minX + gutter/2 + targetWidth * i / (targets.length-1), defaultY);
		}
		*/
		targets[4] = new Vec2d(-0.75,4);
		
		//Ground ground = new Ground(new Vec2d(0.0, minY), eng, maxWidth, 4.0);
		// first object into system draws first
		//eng.add(ground);


		PhyBox b1 = new PhyBox(new Vec2d(0.0, 0.0), targets[0], 0.5, 10000000.0);
		PhyBox b2 = new PhyBox(new Vec2d(0.0, 0.0), targets[4], 0.5, 20000000.0);
		PhyBox b3 = new PhyBox(new Vec2d(0.0, 0.0), targets[8], 0.5, 1000000000.0);
		Command_Center base1 = new Command_Center(targets[0], eng);
		Command_Center base2 = new Command_Center(targets[4], eng);
		Command_Center base3 = new Command_Center(targets[8], eng);
		eng.add(base1);
		eng.add(base2);
		eng.add(base3);

		hansolo.setCCLeft(base1);
		hansolo.setCCCenter(base2);
		hansolo.setCCRight(base3);

		City town1 = new City(1,targets[1], eng);
		City town2 = new City(2,targets[2], eng);
		City town3 = new City(3,targets[3], eng);
		City town5 = new City(5,targets[5], eng);
		City town6 = new City(6,targets[6], eng);
		City town7 = new City(7,targets[7], eng);
	

		eng.add(town1);
		eng.add(town2);
		eng.add(town3);
		eng.add(town5);
		eng.add(town6);
		eng.add(town7);


	}
	private void startPhase3(){
		screen.setMsg("GAME OVER");
		replaytext.setMsg("");
		eng.killInvader();
		eng.killPlayer();

	}
	private void changePhase(int num){
		phase = num;
	}
	public int getPhase(){
		return phase;
	}
	public void addScore(int pts) {
		this.score = this.score + pts;
	}
}
