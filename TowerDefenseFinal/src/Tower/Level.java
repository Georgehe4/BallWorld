package Tower;

import java.util.ArrayList;

public class Level{
	int numInvaders;
	int interval;
	int currentLevel=0;
	int counter;
	int currentMonster;
	private ArrayList<ArrayList<Invader>> levels=new ArrayList<ArrayList<Invader>>();
	World w;
	private int[] intervals={30, 30, 30, 27, 80, 30, 27, 50, 15, 150, 50, 27, 30, 10, 150, 30, 30, 30, 15, 120, 6, 6};
	public Level(World w){
		this.w=w;

		for(int i=0; i<22; i++){
			levels.add(new ArrayList<Invader>());
		}
		for(int i=0; i<10; i++){
			levels.get(0).add(new Invader(w, 100, 5));
		}
		for(int i=0; i<10; i++){
			levels.get(1).add(new Invader(w, 110, 5));
		}
		for(int i=0; i<10; i++){
			levels.get(2).add(new Invader(w, 120, 5));
		}
		for(int i=0; i<10; i++){
			levels.get(3).add(new SpeedyInvader(w, 80, (float)1.5, 5));
		}
		for(int i=0; i<2; i++){
			levels.get(4).add(new Boss(w, 500, (float).5, 0, 25));
		}
		for(int i=0; i<15; i++){
			levels.get(5).add(new Invader(w, 150, 7));
		}
		for(int i=0; i<15; i++){
			levels.get(6).add(new SpeedyInvader(w, 120, (float)2, 7));
		}
		for(int i=0; i<15; i++){
			levels.get(7).add(new RegenInvader(w, 150, (float).05, 7));
		}
		for(int i=0; i<45; i++){
			levels.get(8).add(new Invader(w, 150, 2));
		}
		for(int i=0; i<2; i++){
			levels.get(9).add(new Boss(w, 1000, (float).5, (float).05 ,45));
		}
		for(int i=0; i<20; i++){
			levels.get(10).add(new Invader(w, 250, 5));
		}
		for(int i=0; i<20; i++){
			levels.get(11).add(new SpeedyInvader(w, 150, (float)2.5, 10));
		}
		for(int i=0; i<20; i++){
			levels.get(12).add(new RegenInvader(w, 200, (float).05, 10));
		}
		for(int i=0; i<60; i++){
			levels.get(13).add(new Invader(w, 200, 3));
		}
		for(int i=0; i<2; i++){
			levels.get(14).add(new Boss(w, 1500, (float).75,(float).05, 60));
		}
		for(int i=0; i<20; i++){
			levels.get(15).add(new Invader(w, 350, 10));
		}
		for(int i=0; i<20; i++){
			levels.get(16).add(new SpeedyInvader(w, 250, (float)3, 10));
		}
		for(int i=0; i<20; i++){
			levels.get(17).add(new RegenInvader(w, 300, (float).05, 10));
		}
		for(int i=0; i<60; i++){
			levels.get(18).add(new Invader(w, 200, 3));
		}
		for(int i=0; i<3; i++){
			levels.get(19).add(new Boss(w, 100, (float)1, (float)0.1 , 9001));
		}
		for(int i=0;i<2000;i++){
			levels.get(20).add(new Invader(w,100,20));
		}
		for(int i=0;i<2000;i++){
			levels.get(21).add(new RegenInvader(w, 300, (float).4, 10));
		}
	}
	public void update(){
		if(w.gameStarted()){
			counter++;
			if(counter==intervals[currentLevel] ){
				counter=0;
				if(currentMonster<levels.get(currentLevel).size()){
					w.addActors(levels.get(currentLevel).get(currentMonster));
					currentMonster++;
				}
			}
		}
	}
	public void nextLevel(){
		if(currentLevel==21){
			return;
		}
		currentLevel++;
		counter=0;
		currentMonster=0;
	}
}
