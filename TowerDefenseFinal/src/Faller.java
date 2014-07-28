import java.util.Random;

public class Faller implements Ball, Animated{
	float x, y;
	int color;
	private float radius;
	World world;
	static Random rng=new Random();
	double change, acceleration=0.25;
	double deltaX;
	int remover=0;
	public Faller(World w, float x, float y) {
		this.world = w;
		this.color = w.getColor(255, 0, 100);
		this.radius = 12;
		this.x = x;
		this.y = y;
		deltaX=rng.nextDouble()*5-2;
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
		x+=deltaX;
		y+=change;
		change+=acceleration;
		if(y>world.getMaxY()){
			change=-change+rng.nextDouble()+1;
			remover++;
		}
		if(y<world.getMaxY()){
			remover=0;
		}
		if(x<0||x>world.getMaxX()){
			deltaX=-.9*deltaX;
			
		}
		if(remover>2){
			world.removeBall(this);
		}
	}
}


