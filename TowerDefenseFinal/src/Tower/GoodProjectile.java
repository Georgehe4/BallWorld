package Tower;

public class GoodProjectile extends Actor implements Animated{
	private float nX,nY;
	private double speed;
	private int damageValue;
	public GoodProjectile(World w, float x, float y, int d) {
		super(w,x,y);
		setRadius(13);
		setColor(200);
		speed=5.0;
		nX=w.getClosestTower(this).getX();
		nX=w.getClosestTower(this).getY();
		damageValue=d;
	}
	
	public void update(){
		setX((float) (getX()+nX-getX()/speed));
		setY((float) (getY()+nY-getY()/speed));
		if(Math.abs(Math.abs(getX()-nX)+Math.abs(getY()-nY))<1){
			getWorld().removeActors(this);
		}
	}
}