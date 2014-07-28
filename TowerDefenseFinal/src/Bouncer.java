import java.util.Random;


public class Bouncer implements Ball, Animated{
	float x, y;
	int color;
	private float radius;
	World world;
	double direction;
	static Random rng=new Random();
	boolean a=true, b=true;
	public Bouncer(World w, float x, float y) {
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
		if(a)
			x+=Math.cos(direction);
		else
			x-=Math.cos(direction);
		if(b)
			y+=Math.sin(direction);
		else
			y-=Math.sin(direction);
		if(x>world.getMaxX()|| x<world.getMinX())
			a=!a;
		if(y>world.getMaxY()|| y<world.getMinY())
			b=!b;
	}
}


