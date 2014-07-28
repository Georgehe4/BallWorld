package Tower;

public class SpeedyInvader extends Invader{
	public SpeedyInvader(World w,int hp, float speed, int money){
		super(w, hp, money);
		this.speed=speed;
		this.color = w.getColor(400, 0, 100);
	}
}
