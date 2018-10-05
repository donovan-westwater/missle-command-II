import edu.princeton.cs.algs4.StdDraw;

public class Vec2d {
	private double x, y;
	
	public Vec2d(double x, double y) {
		this.setX(x);
		this.setY(y);
	}
	public Vec2d(Vec2d copyme) {
		this.x = copyme.getX();
		this.y = copyme.getY();
	}
	public static Vec2d reflectAonB(Vec2d incoming, Vec2d wall) {
		Vec2d normal = Vec2d.normalToRight(wall);
		if (Vec2d.isObtuse(wall, incoming)) wall = Vec2d.flip(wall);
		Vec2d projnorm = Vec2d.projectAontoB(incoming, normal);
		projnorm = Vec2d.flip(projnorm);
		Vec2d projwall = Vec2d.projectAontoB(incoming, wall);
		return Vec2d.add(projnorm, projwall);
	}
	public void draw() {
		StdDraw.line(0.0, 0.0, this.getX(), this.getY());
	}
		
	public static boolean isAcute(Vec2d that, Vec2d tother) {
		if (Vec2d.dotAB(that, tother) > 0) return true;
		return false;
	}	
	public static boolean isObtuse(Vec2d that, Vec2d tother) {
		if (Vec2d.dotAB(that, tother) < 0) return true;
		return false;
	}
	public static Vec2d flip(Vec2d that) {
		return new Vec2d(-that.getX(), -that.getY());
	}
	public static Vec2d normalToRight(Vec2d that) {
		return new Vec2d (-that.getY(), that.getX());
	}
	public static Vec2d normalToLeft(Vec2d that) {
		return new Vec2d (that.getY(), -that.getX());
	}
	public static Vec2d add(Vec2d a, Vec2d b) {
		return new Vec2d(a.getX() + b.getX(), a.getY() + b.getY());
	}
	public static Vec2d subtract(Vec2d a, Vec2d minusb) {
		return new Vec2d(a.getX() - minusb.getX(), a.getY() - minusb.getY());
	}
	public static Vec2d getUnitVec(Vec2d that) {
		double length = that.length();
		double nx = that.getX() / length;
		double ny = that.getY() / length;
		double lengthsq = nx*nx + ny*ny;
		if (!(0.99999 < lengthsq && lengthsq < 1.00001)) { 
			System.out.println("Error, not a unit vector");
			System.out.println("Error is lengthsq of : " + lengthsq);
			System.out.println();
		}
		return new Vec2d(nx, ny);
	}
	public static Vec2d projectAontoB(Vec2d a, Vec2d b) {
		double scalar = Vec2d.dotAB(a, b) / b.length();
		System.out.println("b length: " + b.length());
		System.out.println("dotAB: " + Vec2d.dotAB(a,b));
		
		Vec2d unitB = Vec2d.getUnitVec(b);
		unitB.draw();
		Vec2d scaledVec = Vec2d.scaledVector(unitB, scalar);
		return scaledVec;
	}
	public static Vec2d scaledVector(Vec2d vec, double scale) {
		return new Vec2d(vec.getX() * scale, vec.getY() * scale);
	}
	public static double dotAB(Vec2d a, Vec2d b) {
		return a.getX()*b.getX() + a.getY()*b.getY();
	}

	public Vec2d getXVec() {
		return new Vec2d(this.getX(), 0.0);
	}
	public Vec2d getYVec() {
		return new Vec2d(0.0, this.getY());
	}
	
	public double length() {
		return Math.sqrt(this.x * this.x + this.y * this.y);
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void rotate(double theta) {
		double oldx = this.getX();
		double oldy = this.getY();
		this.setX(Math.cos(theta)*oldx - Math.sin(theta)*oldy);
		this.setY(Math.sin(theta)*oldx + Math.cos(theta)*oldy);
	}
	public static void main(String[] args) {
		for (int i = 1; i<100000; i++) {
			Vec2d test = new Vec2d((double)1/i, 0.0);
			if (Math.abs(test.getX() - test.length())> 0.001) {
				System.out.println("Error at " + test.getX());
			}
		}
		System.out.println("Tests complete");
	}

}
