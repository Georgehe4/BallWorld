package Tower;

public abstract class  Tower extends Actor implements Animated{
	protected int money;
	protected String name="Tower";
	protected boolean attack;
	protected float damage;
	protected float range;
	protected float fireRate;
	protected int counter=100;
	protected int current=0;
	protected int[] cost=new int[4];
	protected int level;
	public Tower(World w, float x, float y) {
		super(w, x, y);
		radius=30;
	}
	public String getName(){
		return name;
	}
	public int getCost(){
		int toReturn=0;
		for(int i=0;i<level;i++){
			toReturn+=cost[i];
		}
		return toReturn;
	}
	public int getLevel(){
		return level;
	}
	public float getFireRate(){
		return fireRate;
	}
	public float getRange() {
		return range;
	}
	public boolean getAttack() {
		return attack;
	}
	public void update() {
	}
	public boolean upgrade() {
		return false;
	}
	
}