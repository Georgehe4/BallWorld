package Tower;

import Tower.Gunner.Bullet;

public class MissileTower extends Tower {
	static int[] costs = { 100, 200, 300, 400 };
	int counter = 0;
	int counterMax = 25;

	public MissileTower(World w, float x, float y) {
		super(w, x, y);
		cost[0]=100;
		cost[1]=200;
		cost[2]=300;
		cost[3]=400;
		attack = true;
		damage = 30;
		fireRate = 5;
		level = 1;
		name = "Mi";
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
		if (counter >= counterMax
				&& world.getClosestInvader(this, range) != null
				&& world.getClosestInvaderDist(x, y) < 100) {
			world.addActors(new Missile(world, x, y, 40, 10, world
					.getClosestInvader(this, range)));
			counter = 0;
		}
	}

	public class Missile extends Projectile {
		float deg;
		Invader target;
		float radius;
		float lastX;
		float lastY;
		float splash;
		public Missile(World w, float x, float y, float sp, int damage, Actor targets) {
			super(w, x, y, 10, targets);
			if (!w.getInvaders().isEmpty())
				target = (Invader) targets;
			splash=sp;
			radius = 20;
			speed = 5.0;
		}

		public void update() {
			if (world.containsInvader(target)
					&& world.distanceBetween(this, target) > 10) {
				deg = (float) Math.atan((y - target.y) / (x - target.x));
				if (x - target.x >= 0) {
					deg += Math.PI;
				}
				lastX = target.x;
				lastY = target.y;
				x += Math.cos(deg) * speed;
				y += Math.sin(deg) * speed;
			} else if (!world.containsInvader(target)) {
				x += Math.cos(deg) * speed;
				y += Math.sin(deg) * speed;
			}
			if (world.distanceBetween(x, y, lastX, lastY) < 10) {
				world.damageInvaders(x, y, splash, damage);
				world.removeActors(this);
			}
		}
		// world.getClosestInvader(this,2)!=null
	}
}
