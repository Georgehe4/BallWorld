package Tower;

public class RegenInvader extends Invader{
	public RegenInvader(World w, int hp, float regen, int money){
		super(w, hp, money);
		this.regen=regen;
		this.color = w.getColor(500, 0, 100);
	}
}
