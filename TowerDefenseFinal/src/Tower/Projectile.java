package Tower;

public abstract class Projectile extends Actor implements Animated{
	double speed;
	int damage;
	public Projectile(World w, float x, float y, int i, Actor targets) {
		super(w,x,y);
		setRadius(13);
		setColor(200);
		damage=i;
		speed=5.0;
	}
	
	public void update(){
		}
	}
