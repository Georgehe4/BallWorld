import java.util.Random;
public class Seeker implements Ball, Animated{
	float x, y;
	int color;
	private float radius;
	World world;
	double direction;
	static Random rng=new Random();
	float moveToX, moveToY;
	public Seeker(World w, float x, float y) {
		this.world = w;
		this.color = w.getColor(255, 0, 100);
		this.radius = 12;
		this.x = x;
		this.y = y;
		moveToX=rng.nextFloat()*world.getMaxX();
		moveToY=rng.nextFloat()*world.getMaxY();
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
		x+=(moveToX-x)/50.0;
		y+=(moveToY-y)/50.0;
	}
}