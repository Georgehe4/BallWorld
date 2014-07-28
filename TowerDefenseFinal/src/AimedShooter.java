import java.util.Random;


public class AimedShooter implements Ball, Animated {
	float x, y;
	int color;
	private float radius;
	World world;
	double direction;
	static Random rng=new Random();
	int counter;
	Ball closestBall=null;
	boolean a=true, b=true;
	public AimedShooter(World w, float x, float y) {
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
		if(counter==20){
			if(world.checkNumBalls()>1){
				closestBall=world.getClosestBall(this);
				world.addSpecialBall(new AimedMover(world, x, y, (closestBall.getX()-x)/world.distanceBetween((Ball)this, closestBall)*7,(world.getClosestBall(this).getY()-y)/world.distanceBetween((Ball)this, closestBall)*7));
			}else{
				world.addSpecialBall(new Mover(world, x, y));
			}
			counter=0;
		}
	}
}


