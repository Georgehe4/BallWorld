package Tower;


public class TeslaTower extends Tower{
	static int[] costs = {300, 400, 500, 600 };
	int counter = 0;
	int counterMax = 50;

	public TeslaTower(World w, float x, float y) {
		super(w, x, y);
		cost[0]=300;
		cost[1]=400;
		cost[2]=500;
		cost[3]=600;
		attack = true;
		damage = 90;
		fireRate = 4;
		level = 1;
		name = "Tes";
		range = 1000;
		color = w.getColor(255, 500, 500);
		world.subtractMoney(costs[0]);
	}

	public boolean upgrade() {
		if (level != 3 && world.subtractMoney(costs[level])) {
			counterMax -= 3;
			level++;
			range += 300;
			fireRate++;
			damage += 5;
			return true;
		}
		return false;
	}

	public void update() {
		counter++;
		if (counter >= counterMax
				&& world.getClosestInvader(this, range) != null
				&& world.getClosestInvaderDist(x, y) < 100) {
			world.addActors(new Zap(world, x, y, 10,100));
			counter = 0;
		}
	}

	public class Zap extends Projectile {

		public Zap(World w, float x, float y, int damage, int rad) {
			super(w, x, y, 10,null);
			radius = rad;
			color=w.getColor(700, 150, 700);
		}

		public void update() {
				world.damageInvaders(x, y, radius/2, damage);
				world.removeActors(this);
			}
		}
}

