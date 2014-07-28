import java.util.Random;
public class Stalker implements Ball, Animated{
	float x, y;
	int color;
	private float radius;
	World world;
	double direction;
	static Random rng=new Random();
	float moveToX, moveToY;
	public Stalker(World w, float x, float y) {
		this.world = w;
		this.color = w.getColor(255, 255, 100);
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
		if(world.checkNumBalls()>1){
			moveToX=world.getClosestBall(this).getX();
			moveToY=world.getClosestBall(this).getY();
		}else{
			moveToX=x;
			moveToY=y;
		}
		x+=(moveToX-x)/30.0;
		y+=(moveToY-y)/30.0;
	}
}