import java.util.ArrayList;

import edu.princeton.cs.algs4.StdDraw;

public class PhysicsEngine {
	private double minX;
	private double maxX;
	private double minY;
	private double maxY;
	private int frame; 

	private ArrayList<PhyObject> pObjs;
	
	public PhysicsEngine(Double x, Double y) {
		
		this.minX = -Math.abs(x); 
		this.maxX = Math.abs(x);
		this.minY = -Math.abs(y);
		this.maxY = Math.abs(y);
		pObjs = new ArrayList<PhyObject>();
		frame = 0;
	}
	public void addPhyObject(PhyObject that) {
		pObjs.add(that);
		frame = 0;
	}
	public void removePhyObject(PhyObject that) {
		pObjs.remove(that);
	}
	// ** Proper method needs to handle collisons in time order
	// This one pass per frame will fail often but gets a simple framework in place
	public void update() {
		frame = frame + 1;
		for (PhyObject obj : pObjs) { 
			if (obj == null) break;
			obj.clean = true;
		}
		
		for (PhyObject obj : pObjs) {
			if (obj == null) break;
			for (PhyObject otherObj : pObjs) {
				if(obj == null) break;
				if (obj != otherObj) {
					// System.out.println("Checking for collison.");
					PhysicsEvent collison = PhyObject.collide(obj, otherObj);
					if (collison != null && collison.getFlag() == PhysicsEvent.EventFlag.TOUCH) {
						obj.getgObj().setLast(otherObj.getgObj());
						otherObj.gObj.setLast(obj.getgObj());
						processCollison(collison);	
					}
				}
			}
			if (obj.clean) {
				// move obj
				// System.out.println("Moving box cause it hit nothing.");
				// System.out.println("Frame is " + frame);
				if (!(obj instanceof PhyBox)) {
					System.out.println("But we only have phyboxes?");
				}
				PhyBox box = (PhyBox) obj;
				this.reflectOffBounds(box);
				box.setLoc(Vec2d.add(box.getLoc(), box.getDir()));
				obj.setFrame(frame);
			}
		}
	}
	private void reflectOffBounds(PhyObject obj) {
		// System.out.println(obj.getClass());
		// System.out.println("In reflect code.");
		
		if (obj instanceof PhyBox) {
			PhyBox boxA = (PhyBox) obj;
			Vec2d newVecA = boxA.getDir();
			// System.out.println(boxA.getLoc().getX() + boxA.getDir().getX());
			if (boxA.getLoc().getX() + boxA.getDir().getX() < minX
					|| boxA.getLoc().getX() + boxA.getDir().getX() > maxX) {
				// System.out.println("Beyond x bound of min " + minX + " and max " + maxX);
				newVecA.setX(-newVecA.getX());
				boxA.setDir(newVecA);
			}
			if(boxA.getLoc().getY() + newVecA.getY() < minY ||
					boxA.getLoc().getY() + newVecA.getY() > maxY) {
				newVecA.setY(-newVecA.getY());
				boxA.setDir(newVecA);
			}			
		}
	}

	private void processCollison(PhysicsEvent ePhy) {

		//System.out.println("Processing collison.");
		if (ePhy != null) {
			// System.out.println("Valid collison event.");
				
			if (ePhy.getFlag() == PhysicsEvent.EventFlag.TOUCH) {
				// System.out.println("Touching event.");

				PhyBox boxA = (PhyBox) ePhy.getCollidingObjA();
				PhyBox boxB = (PhyBox) ePhy.getCollidingObjB();
				double timeOfCollision = ePhy.time;
				// System.out.println();
				// System.out.println("Time to collision is : " + timeOfCollision);
				Vec2d newVecA = ePhy.getNewVA();
				Vec2d newVecB = ePhy.getNewVB();
				
				// move by fraction of time
				
				boxA.clean = false;
				boxB.clean = false;
				Vec2d aAtCollision = new Vec2d(Vec2d.add(boxA.getLoc(), Vec2d.scaledVector(boxA.getDir(), timeOfCollision)));
				Vec2d bAtCollision = new Vec2d(Vec2d.add(boxB.getLoc(), Vec2d.scaledVector(boxB.getDir(), timeOfCollision)));

				/*
				StdDraw.square(aAtCollision.getX(), aAtCollision.getY(), boxA.size/2);
				StdDraw.square(bAtCollision.getX(), bAtCollision.getY(), boxB.size/2);
				StdDraw.show();
				StdDraw.pause(20);
				*/
				boxA.setDir(newVecA);
				boxB.setDir(newVecB);

				// System.out.println("Before reflect code.");
				this.reflectOffBounds(boxA);
				this.reflectOffBounds(boxB);
				
				double remainingTime = 1.0 - timeOfCollision;
				
				Vec2d aEndFrame = Vec2d.add(aAtCollision, Vec2d.scaledVector(boxA.getDir(), remainingTime));
				Vec2d bEndFrame = Vec2d.add(bAtCollision, Vec2d.scaledVector(boxB.getDir(), remainingTime));
				boxA.setLoc(aEndFrame);
				boxB.setLoc(bEndFrame);
				
				boxA.getgObj().addEvent(new GameEvent(GameEvent.GameEventFlag.TOUCH));
				boxB.getgObj().addEvent(new GameEvent(GameEvent.GameEventFlag.TOUCH));

				// boxA.setLoc(Vec2d.add(boxA.getLoc(), boxA.getDir()));
				// boxB.setLoc(Vec2d.add(boxB.getLoc(), boxB.getDir()));
			}
		}
		// 
	}
//2
public ArrayList<PhyObject> withinRadius(Vec2d loc, double radius){
	ArrayList<PhyObject> result = new ArrayList<PhyObject>();
	if (pObjs == null) return result;
	if (pObjs.size() == 0) return result;
	if (loc == null) return result;
	for(PhyObject p : pObjs){
		double dist = Math.pow(loc.getX()-p.getLoc().getX(),2) + Math.pow(loc.getY()-p.getLoc().getY(), 2) - radius*radius;
		if(dist<= 0) {
			result.add(p);
		}
	}
	return result;
	
}

}

