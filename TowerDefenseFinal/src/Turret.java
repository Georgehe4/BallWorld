
public class Turret implements Ball, Animated{
	float x, y;
	int color;
	private float radius;
	World world;
	int counter;
	boolean a;
	public Turret(World w, float x, float y) {
		this.world = w;
		this.color = w.getColor(255, 0, 100);
		this.radius = 20;
		this.x = x;
		this.y = y;
		a=false;
	}
	private Turret(World w, float x, float y,boolean a) {
		this.world = w;
		this.color = w.getColor(255, 0, 100);
		this.radius = 20;
		this.x = x;
		this.y = y;
		this.a=true;
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
		if(!a){
			world.addSpecialBall(new Turret(world, x, y, a));
			world.removeBall(this);
		}
		counter++;
		if(world.checkNumBalls()>0&&a){
			if(world.distanceBetween(world.getClosestBall(this),this)<100){
				if(counter>=5){
					world.addSpecialBall(new Missile(world, x, y));
					counter=0;
				}
			}
		}
	}
}


