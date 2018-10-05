public class PhysicsEvent {
	PhyObject collidingObjA;
	PhyObject collidingObjB;
	Vec2d newVA;
	Vec2d newVB;
	EventFlag flag;
	double starttime;
	double time;
	public enum EventFlag {
		PHASE,InRadius,MISS, TOUCH;
	}
	public PhysicsEvent(PhyObject a, Vec2d aPostEventVec, PhyObject b, Vec2d bPostEventVec, EventFlag flag) {
		this.collidingObjA = a;
		this.newVA = aPostEventVec;
		this.collidingObjB = b;
		this.newVB = bPostEventVec;
		this.flag = flag;
	}

	public PhysicsEvent(EventFlag miss) {
		this.collidingObjA = null;
		this.collidingObjB = null;
		this.flag = miss;
		this.newVA = null;
		this.newVB = null;
	}


	public PhysicsEvent(PhyBox a, Vec2d aPostEventVec, PhyBox b, Vec2d bPostEventVec, double t, EventFlag flag) {
		this.collidingObjA = a;
		this.newVA = aPostEventVec;
		this.collidingObjB = b;
		this.newVB = bPostEventVec;
		this.flag = flag;
		this.starttime = a.frametime;
		this.time = t; 
	}

	public PhyObject getCollidingObjA() {
		return collidingObjA;
	}

	public PhyObject getCollidingObjB() {
		return collidingObjB;
	}

	public Vec2d getNewVA() {
		return newVA;
	}

	public Vec2d getNewVB() {
		return newVB;
	}

	public EventFlag getFlag() {
		return flag;
	}

	public void setCollidingObjA(PhyObject collidingObjA) {
		this.collidingObjA = collidingObjA;
	}

	public void setCollidingObjB(PhyObject collidingObjB) {
		this.collidingObjB = collidingObjB;
	}

	public void setNewVA(Vec2d newVA) {
		this.newVA = newVA;
	}

	public void setNewVB(Vec2d newVB) {
		this.newVB = newVB;
	}

	public void setFlag(EventFlag flag) {
		this.flag = flag;
	}

}
