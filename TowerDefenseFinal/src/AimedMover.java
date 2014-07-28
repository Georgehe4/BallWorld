import java.util.Random;


public class AimedMover implements Ball, Animated{
	float x, y;
	int color;
	private float radius;
	World world;
	double dx, dy;
	public AimedMover(World w, float x, float y, double dx, double dy) {
		this.world = w;
		this.color = w.getColor(255, 0, 100);
		this.radius = 12;
		this.x = x;
		this.y = y;
		this.dx=dx;
		this.dy=dy;
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
		x+=dx;
		y+=dy;
		if(x<-2||x>world.getMaxX()+2||y<-2||y>world.getMaxY()+2){
			world.removeBall(this);
		}
	}
}


