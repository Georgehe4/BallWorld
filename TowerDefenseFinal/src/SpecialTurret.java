
public class SpecialTurret implements Ball, Animated{
	float x, y;
	int color;
	private float radius;
	World world;
	int counter;
	boolean a;
	public SpecialTurret(World w, float x, float y) {
		this.world = w;
		this.color = w.getColor(255, 0, 100);
		this.radius = 20;
		this.x = x;
		this.y = y;
		this.a=false;
	}
	private SpecialTurret(World w, float x, float y,boolean a) {
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
			world.addSpecialBall(new SpecialTurret(world, x, y, a));
			world.removeBall(this);
		}
		counter++;
		if(world.checkNumSpecialBalls()>1&&a){
			if(world.distanceBetween(world.getClosestSpecialBall(this),this)<100 && counter>=5 &&!(world.getClosestSpecialBall(this) instanceof Kamikaze)){

				world.addSpecialBall(new SpecialKamikaze(world, x, y, world.getClosestSpecialBall(this)));
				counter=0;

			}
		}
	}
}


