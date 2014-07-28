import java.util.Random;


public class Exploder implements Ball, Animated{
	float x, y;
	int color;
	private float radius;
	World world;
	double direction;
	static Random rng=new Random();

	public Exploder(World w, float x, float y) {
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
		x+=Math.cos(direction)*2;
		y+=Math.sin(direction)*2;
		if(world.checkNumBalls()>1){
			if(world.distanceBetween(world.getClosestBall(this),this)<24){
				world.removeBall(world.getClosestBall(this));
				world.removeBall(this);
				world.addSFXBall(new MovingShrinker(world, x, y));
				world.addSFXBall(new MovingShrinker(world, x, y));
				world.addSFXBall(new MovingShrinker(world, x, y));
				world.addSFXBall(new MovingShrinker(world, x, y));
				world.addSFXBall(new MovingShrinker(world, x, y));
				world.addSFXBall(new MovingShrinker(world, x, y));
			}
		}
		if(x<-2||x>world.getMaxX()+2||y<-2||y>world.getMaxY()+2){
			world.removeBall(this);
		}
	}
}


