import java.util.Random;



public class MovingShrinker implements Ball, Animated{
	float x, y;
	int color;
	private float radius;
	World world;

	double direction;
	static Random rng=new Random();
	public MovingShrinker(World w, float x, float y) {
		this.world = w;
		this.color = w.getColor(255, 0, 100);
		this.radius = 12;
		this.x = x;
		this.y = y;
		direction=rng.nextDouble()*2*Math.PI;
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
		radius-=.5;
		x+=Math.cos(direction);
		y+=Math.sin(direction);
		if(radius<=0){
			world.removeBall(this);
		}
	}
}


