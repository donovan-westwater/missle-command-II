import edu.princeton.cs.algs4.StdDraw;

public class Box {
	private Vec2d dir;
	private Vec2d loc;
	private double mass;
	public double size;

	public Box(Vec2d direction, Vec2d location, double size, double mass) {
		this.setDir(direction);
		this.setLoc(location);
		this.size = size;
		this.mass = mass;
	}
	public static boolean intersects(Box a, Vec2d locA, Box b, Vec2d locB) {
		double minAX = locA.getX()-a.size/2;
		double maxAX = locA.getX()+a.size/2;
		double minAY = locA.getY()-a.size/2;
		double maxAY = locA.getY()+a.size/2;
		double minBX = locB.getX()-b.size/2;
		double maxBX = locB.getX()+b.size/2;
		double minBY = locB.getY()-b.size/2;
		double maxBY = locB.getY()+b.size/2;
		
		if (maxAX < minBX || minAX > maxBX) return false;
		if (maxAY < minBY || minAY > maxBY) return false;
		return true;
	}
	public void draw() {
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.filledSquare(loc.getX(), loc.getY(), size/2);
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

}

