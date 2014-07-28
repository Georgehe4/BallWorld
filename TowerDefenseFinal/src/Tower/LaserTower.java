package Tower;

public class LaserTower extends Tower {

	protected static int[] costs = { 300, 400, 500, 600 };
	int counter = 0;
	int counterMax = 40;
	float lastX;
	float lastY;

	public LaserTower(World w, float x, float y) {
		super(w, x, y);
		cost[0]=300;
		cost[1]=400;
		cost[2]=500;
		cost[3]=600;
		attack = true;
		damage = 30;
		fireRate = 5;
		level = 1;
		name = "Las";
		range = 1000;
		color = w.getColor(255, 500, 100);
		world.subtractMoney(costs[0]);
	}

	public boolean upgrade() {
		if (level != 3 && world.subtractMoney(costs[level])) {
			counterMax -= 3;
			level++;
			range += 20;
			fireRate++;
			damage += 5;
			return true;
		}
		return false;
	}

	public void update() {
		counter++;
		if (counter == counterMax - 1
				&& world.getClosestInvader(this, range) != null
				&& world.getClosestInvaderDist(x, y) < 100) {
			world.addActors(new Laser(world, x, y, 10, world.getClosestInvader(
					this, range)));
		}
		if (counter >= counterMax
				&& world.getClosestInvader(this, range) != null
				&& world.getClosestInvaderDist(x, y) < 100) {
			world.addActors(new Laser(world, x, y, 10, world.getClosestInvader(
					this, range)));
			counter = 0;
		}
	}

	public class Laser extends Projectile {
		float deg;
		Invader target;
		float radius;

		public Laser(World w, float x, float y, int damage, Actor targets) {
			super(w, x, y, 10, targets);
			color=w.getColor(90, 140, 1290);
			if (!w.getInvaders().isEmpty())
				target = (Invader) targets;
			deg = (float) Math.atan((y - target.y) / (x - target.x));
			if (x - target.x >= 0) {
				deg += Math.PI;
			}
			radius = 5;
			speed = 16.0;
		}

		public void update() {
			x += Math.cos(deg) * speed;
			y += Math.sin(deg) * speed;
			if (x > 1000 || x < 0 || y > 1000 || y < 0)
				world.removeActors(this);
			if (world.getClosestInvader(this, 9) != null) {
				world.getClosestInvader(this, 9).lowerHealth(damage);
			}
			// world.getClosestInvader(this,2)!=null
		}
	}

}
