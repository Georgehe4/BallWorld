
public class LotsOfBallsSpecial implements Ball, Animated{
	float x, y;
	int color;
	private float radius;
	World world;
	double direction;
	int counter;
	Ball Mover=null;
	public LotsOfBallsSpecial(World w, float x, float y) {
		this.world = w;
		this.color = w.getColor(255, 0, 100);
		this.radius = 12;
		this.x = x;
		this.y = y;

	}
	

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getRadius() {
		return radius;
	}

	public int getColor() {
		return color;
	}
	public void update(){
		world.addSpecialBall(new LotsOfBallsDestructable(world,x,y));
		world.removeBall(this);
	}
}


