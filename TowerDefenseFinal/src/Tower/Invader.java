package Tower;

import java.util.ArrayList;

public class Invader extends Actor implements Animated{
	protected int money;
	protected int targetNum;
	protected pathPoint target;
	protected ArrayList<pathPoint> locs=new ArrayList<pathPoint>();
	protected float maxHealth;
	protected float health;
	protected float regen;
	protected float speed;
	protected double theta;
	public Invader(World w, int hp, int money) {
		super(w,w.pathPoints().get(0).x,w.pathPoints().get(0).y);
		locs=w.pathPoints();
		targetNum=0;
		target=locs.get(targetNum);
		regen=0;
		speed=1;
		this.money=money;
		maxHealth=hp;
		health=maxHealth;
	}
	public void lowerHealth(int a){
		health-=a;
	}
	public void update(){
		color= world.getColor(255, (int)health*2, 100);
		health+=regen;
		if(health>maxHealth)
			health=maxHealth;
		if((world).distanceBetween(x,y,target.x,target.y)<4){
			targetNum++;
			if(targetNum<locs.size()){
				target=locs.get(targetNum);
				theta=Math.atan((y-target.y)/(x-target.x));
				if(x-target.x>=0){
					theta+=Math.PI;
				}
			}
			else {world.lowerlives(1);
			world.removeActors(this);
			}
			
		}
		x+=Math.cos(theta)*speed;
		y+=Math.sin(theta)*speed;
		if(health<=0){
			world.removeActors(this);
			world.addMoney(money);
		}
	}
}
