
public abstract class GfxObject {
	protected Vec2d loc;
	protected boolean visible = true;
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	abstract void draw();
	abstract void setGraphicPosition(Vec2d loc2);
}
