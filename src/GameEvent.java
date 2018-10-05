
public class GameEvent {
	private GameEventFlag flag;
	public GameEventFlag getFlag() {
		return flag;
	}
	public void setFlag(GameEventFlag flag) {
		this.flag = flag;
	}

	public enum GameEventFlag {
		BOOM, MISS, TOUCH;
	}
	
	public GameEvent(GameEventFlag flag) {
		this.flag = flag;
	}
}