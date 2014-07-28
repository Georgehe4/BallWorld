package Tower;

import java.util.ArrayList;

public class Boss extends Invader{
	public Boss(World a, int hp, float sp, float regen, int money){
		super(a, hp, money);
		speed=sp;
		this.regen=regen;
	}
}
