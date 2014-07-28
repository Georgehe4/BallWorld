package Tower;

public class Gunner extends Tower {
	static int[] costs = { 100, 150, 200, 250 };
	int counter=0;
	int counterMax=20;
	public Gunner(World w, float x, float y) {
		super(w, x, y);
		cost[0]=100;
		cost[1]=150;
		cost[2]=200;
		cost[3]=250;
		attack = true;
		damage = 5;
		level = 1;
		name = "Gu";
		range = 1000;
		color=w.getColor(255, 200, 100);
		world.subtractMoney(costs[0]);
	}

	public boolean upgrade() {
		if (level != 3 && world.subtractMoney(costs[level]) ) {
			counterMax-=2;
			level++;
			range += 20;
			damage += 5;
			return true;
		}
		return false;
	}

	public void update() {
		counter++;
		if(counter>=counterMax && world.getClosestInvader(this, range)!=null && world.getClosestInvaderDist(x,y)<100){
				world.addActors(new Bullet(world,x,y,10, world.getClosestInvader(this,range)));
				counter=0;
		}
	}
	public class Bullet extends Projectile {
		Invader target;
		public Bullet(World w, float x, float y, int damage, Actor targets) {
			super(w, x, y, 10,targets);
			if(!w.getInvaders().isEmpty())
				target=(Invader) targets;
		}
		public void update(){
			if(world.getInvaders().isEmpty()){
				world.removeActors(this);
				
			}
			else{
				target.lowerHealth(damage);
				world.removeActors(this);
				world.drawLine(this.getX(), this.getY(), target.getX(),target.getY());
			}
		}
	}
}
