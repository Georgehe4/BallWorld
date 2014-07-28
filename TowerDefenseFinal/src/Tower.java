

import java.util.ArrayList;

public class Tower extends Dud implements Animated{
	protected String name;
	protected boolean attack;
	protected int damage;
	protected int range;
	protected int fireRate;
	protected int counter=10;
	protected int current=0;
	protected int level;
	
	public Tower(World w, float x, float y) {
		super(w, x, y);
	}
	
	public String getName(){
		return name;
	}
	public int getFireRate(){
		return fireRate;
	}
	public int getRange() {
		return range;
	}
	public boolean getAttack() {
		return attack;
	}
	public void update() {

		}
	public boolean canPlace(float x,float y){
		boolean flag=true;
		return flag;
	}
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}


	public int getColor() {
		return color;
	}
}