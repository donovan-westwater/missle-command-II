import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.lang.String; 
import edu.princeton.cs.algs4.StdDraw;


public class GfxUI extends GfxObject {
	private Vec2d center;
	private Font font;
	
	
	public Vec2d getCenter() {
		return center;
	}
	public void setCenter(Vec2d center) {
		this.center = center;
	}
	private Color color;
	private String msg = "This is a test";  
	
	
	public GfxUI(Vec2d loc) {
		this.center = loc;
	}
	@Override
	public void draw () {
		StdDraw.setPenColor(Color.WHITE);
		StdDraw.text(center.getX(),  center.getY(), msg);
	}
	@Override
	void setGraphicPosition(Vec2d loc) {
		this.center = loc;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
}
