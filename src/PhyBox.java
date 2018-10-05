public class PhyBox extends PhyObject{
	
	private double mass;
	public double size;

	public PhyBox(Vec2d direction, Vec2d location, double size, double mass) {
		this.setDir(direction);
		this.setLoc(location);
		this.size = size;
		this.mass = mass;
		this.frametime = 0;
		this.collisionCount = 0;
		this.setImmovable(false);
	}
	
	public double getSize() {
		return this.size;
	}
	public double getMass() {
		return mass;
	}
	public static boolean intersects(PhyBox a, Vec2d locA, PhyBox b, Vec2d locB) {
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
}

