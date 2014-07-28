import java.util.Random;
public class Assassin implements Ball, Animated{
	float x, y;
	int color;
	private float radius;
	World world;
	double direction;
	static Random rng=new Random();
	Ball hitThis;
	public Assassin(World w, float x, float y, Ball targetBall) {
		this.world = w;
		this.color = w.getColor(0, 0, 255);
		this.radius = 12;
		this.x = x;
		this.y = y;
		hitThis=targetBall;
	}
	public Assassin(World w, float x, float y) {
		this.world = w;
		this.color = w.getColor(0, 0, 255);
		this.radius = 12;
		this.x = x;
		this.y = y;
		if(world.checkNumBalls()>1)
			hitThis=world.getClosestBall(this);
		else hitThis=null;
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
		if(hitThis==null){
			if(world.checkNumBalls()>1)
				hitThis=world.getClosestBall(this);
		}else{
			x+=(hitThis.getX()-x)/5.0;
			y+=(hitThis.getY()-y)/5.0;
		}
		if(world.checkNumBalls()>1){
			if(world.distanceBetween(hitThis, this)<24){
				world.addSFXBall(new MovingShrinker(world, x, y));
				world.addSFXBall(new MovingShrinker(world, x, y));
				world.addSFXBall(new MovingShrinker(world, x, y));
				world.addSFXBall(new MovingShrinker(world, x, y));
				world.addSFXBall(new MovingShrinker(world, x, y));
				world.addSFXBall(new MovingShrinker(world, x, y));
				world.removeBall(hitThis);
				hitThis=null;
			}
		}

	}
}