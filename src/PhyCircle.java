
public class PhyCircle extends PhyObject {
	private Vec2d dir;
	private Vec2d loc;
	private double mass;
	public double size;
	public double radius;
	private boolean immovable;
	
	public PhyCircle(Vec2d direction, Vec2d location, double mass,double rad) {
		this.dir = direction;
		this.loc = location;
		this.mass = mass;
		this.radius = rad;
		this.frametime = 0;
		this.collisionCount = 0;
		this.immovable = false;
	}
	public static double distnaceFromCenter(PhyCircle a, PhyCircle b){//rename to Distance between centers
		double xPow = Math.pow(a.loc.getX()-b.loc.getX(), 2.0);
		double yPow = Math.pow(a.loc.getY() - b.loc.getY(), 2.0);
		double root = Math.sqrt(xPow + yPow);
		return root;
	}
	public static boolean intersects(PhyCircle a, Vec2d locA, PhyCircle b, Vec2d locB){
		if(a.radius+b.radius < PhyCircle.distnaceFromCenter(a, b)){
			return false;
		}else{
			return true;
		}
	}

public Vec2d getDir() {
	return new Vec2d(dir);
}
public void setDir(Vec2d dir) {
	this.dir = new Vec2d(dir);
}
public Vec2d getLoc() {
	return new Vec2d(loc);
}
public void setLoc(Vec2d loc) {
	this.loc = new Vec2d(loc);
}
public double getMass() {
	return mass;

}
public double getRadius(){
	return radius;
}
public boolean isImmovable() {
	return immovable;
}

public void setImmovable(boolean immovable) {
	this.immovable = immovable;
}
}
