package Tower;


public class Actor {
	protected float x;
	protected float y;
	protected int color;
	protected float radius;
	protected World world;
	
	public Actor(World w, float x, float y) {
		this.world = w;
		this.color = w.getColor(255, 0, 100);
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
	public void setX(float a){
		x=a;
	}
	public void setY(float a){
		y=a;
	}
	public void setColor(int a){
		color=a;
	}
	public void setRadius(int a){
		radius=a;
	}
	public float getRadius() {
		return radius;
	}

	public int getColor() {
		return color;
	}
	public World getWorld(){
		return world;
	}
	public void update(){
		
	}
	
	}

