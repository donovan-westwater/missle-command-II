public class PhyObject {
	private Vec2d loc;
	public Vec2d getLoc() {
		return loc;
	}
	public void setLoc(Vec2d loc) {
		this.loc =loc;
	}
	
	private Vec2d dir;
	public Vec2d getDir() {
		return dir;
	}
	public void setDir(Vec2d vec) {
		dir = vec;
	}	
	
	boolean phase = false;
	boolean nonsolid = false;
	private boolean immovable;
	public boolean isImmovable() {
		return immovable;
	}
	public void setImmovable(boolean immovable) {
		this.immovable = immovable;
	}

	public boolean isNonsolid() {
		return nonsolid;
	}
	public void setNonsolid(boolean nonsolid) {
		this.nonsolid = nonsolid;
	}

	GameObject gObj;
	public GameObject getgObj() {
		return gObj;
	}
	public void setgObj(GameObject gObj) {
		this.gObj = gObj;
	}

	double frametime;
	boolean clean;
	int collisionCount;
	
	public double getFrameTime() {
		return frametime;
	}

	public void setFrame(double frametime) {
		this.frametime = frametime;
	}

	public static PhysicsEvent collide(PhyObject obj, PhyObject otherObj) {
		if (obj.isNonsolid() || otherObj.isNonsolid()) return null;
		if (obj instanceof PhyBox && otherObj instanceof PhyBox) {
			return collideBox(obj, otherObj);
		}
		return null;
	}

	public static boolean intersects(PhyObject obj, PhyObject otherObj) {
		PhyBox a = (PhyBox )obj;
		PhyBox b = (PhyBox) otherObj;
		double minAX = a.getLoc().getX() - a.size/2;
		double maxAX = a.getLoc().getX() + a.size/2;
		double minAY = a.getLoc().getY() - a.size/2;
		double maxAY = a.getLoc().getY() + a.size/2;
		double minBX = b.getLoc().getX() - b.size/2;
		double maxBX = b.getLoc().getX() + b.size/2;
		double minBY = b.getLoc().getY() - b.size/2;
		double maxBY = b.getLoc().getY() + b.size/2;
		
		if (maxAX < minBX || minAX > maxBX) return false;
		if (maxAY < minBY || minAY > maxBY) return false;
		// System.out.println("We intersected");
		// System.out.println("A box minx " + minAX + " maxX " + maxAX);
		// System.out.println("B box minx " + minBX + " maxX " + maxBX);
		
		// System.out.println(minAX);
		// System.out.println(minAX);
		
		return true;
	}
	
	private static PhysicsEvent collideBox(PhyObject boxA, PhyObject boxB) {
		
		PhyBox b1 = (PhyBox)boxA;
		PhyBox b2 = (PhyBox)boxB;
		
		// move boxes
		Vec2d b1_dir = b1.getDir();
		Vec2d b2_dir = b2.getDir();
		
		double xComboVel = b1.getDir().getX() - b2.getDir().getX();
		double yComboVel = b1.getDir().getY() - b2.getDir().getY();

		double b1_minX = b1.getLoc().getX() - b1.size / 2;
		double b1_maxX = b1.getLoc().getX() + b1.size / 2;
		double b1_minY = b1.getLoc().getY() - b1.size / 2;
		double b1_maxY = b1.getLoc().getY() + b1.size / 2;
		
		double b2_minX = b2.getLoc().getX() - b2.size / 2;
		double b2_maxX = b2.getLoc().getX() + b2.size / 2;
		double b2_minY = b2.getLoc().getY() - b2.size / 2;
		double b2_maxY = b2.getLoc().getY() + b2.size / 2;

		boolean skipY = false;
		boolean skipX = false;
		if (Math.abs(yComboVel) < 0.0001) {
			if (!isOverlapping(b1_minY, b1_maxY, b2_minY, b2_maxY)) {
				// no y overlap or y motion
				return new PhysicsEvent(PhysicsEvent.EventFlag.MISS);
			}
			skipY = true;
		}
		if (Math.abs(xComboVel) < 0.0001) {
			if (!isOverlapping(b1_minX, b1_maxX, b2_minX, b2_maxX)) {
				// no y overlap or y motion
				return new PhysicsEvent(PhysicsEvent.EventFlag.MISS);
			}
			skipX = true;
		}

		double t1 = -1;
		double t2 = -1;
		double t;
		double xt1, xt2, yt1, yt2;
		if (skipY) {
			t1 = (b2_minX - b1_maxX) / xComboVel;
			t2 = (b2_maxX - b1_minX) / xComboVel;
		}
		if (skipX) {
			t1 = (b2_minY - b1_maxY) / yComboVel;
			t2 = (b2_maxY - b1_minY) / yComboVel;
		}
		if (!skipY && !skipX) {
			xt1 = (b2_minX - b1_maxX) / xComboVel;
			xt2 = (b2_maxX - b1_minX) / xComboVel;
			yt1 = (b2_minY - b1_maxY) / yComboVel;
			yt2 = (b2_maxY - b1_minY) / yComboVel;
			
			// t1 = xt1;
			// t2 = xt2;
			
			t1 = minPosIntervalOverlap(xt1, xt2, yt1, yt2);
			t2 = -1.0;
		}
		if (t1 < 0 && t2 < 0) return new PhysicsEvent(PhysicsEvent.EventFlag.MISS);
		if (t1 > 0 && t2 > 0) { t = Math.min(t1, t2); }
		else { t = Math.max(t1, t2); }
		/*
		System.out.println();
		System.out.println("Times are t1: " + t1 + " and t2 " +t2);
		System.out.println("Time to collision is " + t);
		System.out.println("b1 min " + b1_minX + " b1max " + b1_maxX);
		System.out.println();
		*/ 
		if (t > 1) {
			// miss this frame
			return new PhysicsEvent(PhysicsEvent.EventFlag.MISS);
		}
		
		// This is the old code that didn't know the time of collision 
		Vec2d b1NewLoc = Vec2d.add(b1_dir, b1.getLoc());
		Vec2d b2NewLoc = Vec2d.add(b2_dir, b2.getLoc());
		PhyBox b1new = new PhyBox(b1_dir, b1NewLoc, b1.size, b1.getMass());
		PhyBox b2new = new PhyBox(b2_dir, b2NewLoc, b2.size, b2.getMass());

		if (intersects(b1new, b2new)) {
			// System.out.println();
			// System.out.println("Find reflection for colliding boxes");
			double x1C, y1C, x2C, y2C;
			if (t == 0) {
				x1C = b1.getLoc().getX();
				y1C = b1.getLoc().getY();
				x2C = b2.getLoc().getX();
				y2C = b2.getLoc().getY();
			} else {
				x1C = t * b1.getLoc().getX();
				y1C = t * b1.getLoc().getY();
				x2C = t * b2.getLoc().getX();
				y2C = t * b2.getLoc().getY();
			}
			Vec2d centerVec = new Vec2d(x1C-x2C, y1C-y2C);
			
			centerVec = Vec2d.getUnitVec(centerVec);
			boolean xaxis = true;
			// System.out.println();
			// System.out.println("The cos of collision is : " + centerVec.getX());
			
			if (Math.abs(centerVec.getX()) < Math.cos(Math.PI/4)) {
				xaxis = false;
				// System.out.println("Y axis");
			}
			double m1 = b1.getMass();
			double m2 = b2.getMass();
			Vec2d v1start = b1.getDir();
			Vec2d v2start = b2.getDir();
			double momentumS, kineticS;
			if (xaxis) {
				momentumS = m1*v1start.getX() + m2*v2start.getX();
				kineticS = 1.0/2.0*m1*v1start.getX()*v1start.getX() + 1.0/2.0*m2*v2start.getX()*v2start.getX();
			} else {
				momentumS = m1*v1start.getY() + m2*v2start.getY();
				kineticS = 1.0/2.0*m1*v1start.getY()*v1start.getY() + 1.0/2.0*m2*v2start.getY()*v2start.getY();
			}
			
			/* System.out.println("v1start length :" + v1start.length());
			System.out.println("v1start x :" + v1start.getX());
			System.out.println("v2start length :" + v2start.length());
			System.out.println("v2start x :" + v2start.getX());
			System.out.println();
			System.out.println("momentum at start is :" + momentumS); 
			System.out.println("kinetics at start is :" + kineticS); 
			*/
			Vec2d v1final_x, v1final_y, v2final_x, v2final_y;
			if (xaxis) {
				Vec2d vf1fromv1_x = Vec2d.scaledVector(v1start.getXVec(), (m1-m2)/(m1+m2)); 
				Vec2d vf1romv2_x = Vec2d.scaledVector(v2start.getXVec(), (2*m2)/(m1+m2));
				v1final_x = Vec2d.add(vf1fromv1_x, vf1romv2_x);

				Vec2d vf2fromv1_x = Vec2d.scaledVector(v1start.getXVec(), (2*m1)/(m1+m2)); 
				Vec2d vf2romv2_x = Vec2d.scaledVector(v2start.getXVec(), -(m1-m2)/(m1+m2));
				v2final_x = Vec2d.add(vf2fromv1_x, vf2romv2_x);

				v1final_y = v1start.getYVec();
				v2final_y = v2start.getYVec();
			} else {
				Vec2d vf1fromv1_y = Vec2d.scaledVector(v1start.getYVec(), (m1-m2)/(m1+m2)); 
				Vec2d vf1romv2_y = Vec2d.scaledVector(v2start.getYVec(), (2*m2)/(m1+m2));
				v1final_y = Vec2d.add(vf1fromv1_y, vf1romv2_y);

				Vec2d vf2fromv1_y = Vec2d.scaledVector(v1start.getYVec(), (2*m1)/(m1+m2)); 
				Vec2d vf2romv2_y = Vec2d.scaledVector(v2start.getYVec(), -(m1-m2)/(m1+m2));
				v2final_y = Vec2d.add(vf2fromv1_y, vf2romv2_y);

				v1final_x = v1start.getXVec();
				v2final_x = v2start.getXVec();
			}
			Vec2d v1final = Vec2d.add(v1final_x, v1final_y);
			Vec2d v2final = Vec2d.add(v2final_x, v2final_y);

			/*Vec2d vf1fromv1 = Vec2d.scaledVector(v1start, (m1-m2)/(m1+m2)); 
			Vec2d vf1romv2 = Vec2d.scaledVector(v2start, (2*m2)/(m1+m2));
			Vec2d v1final = Vec2d.add(vf1fromv1, vf1romv2);

			Vec2d vf2fromv1 = Vec2d.scaledVector(v1start, (2*m1)/(m1+m2)); 
			Vec2d vf2romv2 = Vec2d.scaledVector(v2start, -(m1-m2)/(m1+m2));
			Vec2d v2final = Vec2d.add(vf2fromv1, vf2romv2);*/
			/*
			if (xaxis) { 
				double momentumE = m1*v1start.getX() + m2*v2start.getX();
				double kineticE = 1.0/2.0*m1*v1final.getX()*v1final.getX() + 1.0/2.0 * m2*  v2final.getX() * v2final.getX();
				System.out.println("momentum at end is :" + momentumE); 
				System.out.println("kinetic at end is :" + kineticE); 
				System.out.println("v1start " + v1start.getX()); 
				System.out.println("v2final " + v2final.getX()); 
				System.out.println("v2start " + v2start.getX()); 
				System.out.println("v1final " + v1final.getX()); 
			} else {
				double momentumE = m1*v1start.getY() + m2*v2start.getY();
				double kineticE = 1.0/2.0*m1*v1final.getY()*v1final.getY() + 1.0/2.0 * m2*  v2final.getY() * v2final.getY();
				System.out.println("momentum at end is :" + momentumE); 
				System.out.println("kinetic at end is :" + kineticE); 
				System.out.println("v1start " + v1start.getY()); 
				System.out.println("v2final " + v2final.getY()); 
				System.out.println("v2start " + v2start.getY()); 
				System.out.println("v1final " + v1final.getY()); 
			} */
			
			// b1.setDir(v1final);
			// b2.setDir(v2final);
			
			
			return new PhysicsEvent(b1, v1final, b2, v2final, t, PhysicsEvent.EventFlag.TOUCH);
		} 
		return new PhysicsEvent(PhysicsEvent.EventFlag.MISS); 
	}

	private static boolean isOverlapping(double b1_min, double b1_max, double b2_min, double b2_max) {
		if (b1_max <= b2_min || b1_min >= b2_max) return false;
		return true;
	}

	private static double minPosIntervalOverlap(double xt1, double xt2, double yt1, double yt2) {
		double minX = Math.min(xt1, xt2);
		double maxX = Math.max(xt1, xt2);
		double minY = Math.min(yt1, yt2);
		double maxY = Math.max(yt1, yt2);

		// is there any overlap
		if (maxX < minY || maxY < minX) return -1;
		// is the overlap entirely negative
		if (maxX < 0 || maxY < 0) return -1;

		// there is overlap and some could be positive
		if (minX > 0 && minY > 0) return Math.max(minX, minY);
		if (minX < 0 && minY > 0) return minY;
		if (minY < 0 && minX > 0) return minX;
		return 0;
	}
}
	