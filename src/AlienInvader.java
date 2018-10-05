
public class AlienInvader {

	private GameEngine gEng;
	private Vec2d[] targets;
	// private AlienWave waves; 
	private int alienframe = 0;
	private int[] waveTriggers = {30, 300, 600};
	private AlienWave waves = new AlienWave();
	
	public AlienInvader (Vec2d[] ts, GameEngine gE) {
		this.targets = ts;
		this.gEng = gE;
	}
	
	public void update() {
		alienframe += 1;
		for (int i = 0; i < 3; i++) {
			if (alienframe == waveTriggers[i]) {
				spawnWave(waves.getWave(i));
			}
		}
		if (alienframe > waveTriggers[2]) {
			if (Math.random() < .02) spawnWave(1);
		}
	}
	
	private void spawnWave(int totalMRVs) {
		for (int i = 0; i < totalMRVs; i++) {
			int targetNum = (int)(Math.random() * targets.length);
			Vec2d tar = targets[targetNum];
			GameObjectAlienMRV mrv = new GameObjectAlienMRV(tar, gEng, targets);
			this.gEng.addDuringFrame(mrv);
		}
	}
	private class AlienWave {
		int[] waves = {1, 2, 3};
		
		public AlienWave() {
			int i = 1;
		}
		
		public int getWave(int i) {
			return waves[i];
		}
	}
}
