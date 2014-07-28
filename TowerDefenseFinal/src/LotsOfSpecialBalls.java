import java.util.Random;


public class LotsOfSpecialBalls implements Ball, Animated{
	float x, y;
	int color;
	private float radius;
	World world;
	double direction;
	static Random rng=new Random();
	int counter;
	Ball Mover=null;
	boolean a=true,b=true;
	public LotsOfSpecialBalls(World w, float x, float y) {
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
if(x<-2||x>world.getMaxX()+2||y<-2||y>world.getMaxY()+2){
			world.removeBall(this);
		}
		counter++;
		if(counter==5){
			world.addSpecialBall(new Bouncer(world, x, y));
			counter=0;
		}
		if(world.checkNumSpecialBalls()>100){
			world.removeBall(this);
		}
	}
}


