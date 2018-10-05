import edu.princeton.cs.algs4.StdDraw;

public class GfxPlane {
	private double[] xCords = {0,.5,2,2,2.5,2.5};
	private double[] yCords = {0,0.5,0.5,1,1,0};
	private Vec2d shift;
	public GfxPlane(Vec2d mid){
		shift = mid;
		xCords[0] = shift.getX() - .2;
		xCords[1] = shift.getX() - .15;
		xCords[2] = shift.getX() + .15;
		xCords[3] = shift.getX() + .15;
		xCords[4] =	shift.getX() + .2;				
		xCords[5] = shift.getX() + .2;
		
		yCords[0] = shift.getY() - .025;
		yCords[1] = shift.getY() + .025;
		yCords[2] = shift.getY() + .025;
		yCords[3] = shift.getY() + .075;
		yCords[4] =	shift.getY() + .075;				
		yCords[5] = shift.getY() - .025;
		
	}
	public void draw(){
		
		
		StdDraw.line(xCords[0], yCords[0], xCords[1], yCords[1]);
		StdDraw.line(xCords[1], yCords[1], xCords[2], yCords[2]);
		StdDraw.line(xCords[2], yCords[2], xCords[3], yCords[3]);
		StdDraw.line(xCords[3], yCords[3], xCords[4], yCords[4]);
		StdDraw.line(xCords[4], yCords[4], xCords[5], yCords[5]);
		StdDraw.line(xCords[5], yCords[5], xCords[0], yCords[0]);
		
		
	}
	public void scalePlane(double x){
		for(int n = 0; n < xCords.length-1; n++){
			xCords[n] *= x;
			System.out.println(xCords[n]);
		}
		for(int y = 0; y < yCords.length-1; y++){
			yCords[y] *= x;
			System.out.println(yCords[y]);
		}
	}
	public static void main(String[] args){
		// TODO Auto-generated method stub
		double minX = -5.0;
		double minY = -5.0;
		double maxX = 5.0;
		double maxY = 5.0;
		
		StdDraw.setCanvasSize(800, 800);
		StdDraw.setXscale(minX, maxX);
		StdDraw.setYscale(minY, maxY);
		
		GfxPlane test = new GfxPlane(new Vec2d(0,0));
		test.scalePlane(10);
		test.draw();

	}
	

}
