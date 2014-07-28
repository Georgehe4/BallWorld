package Tower;

import java.util.ArrayList;

public interface World {
	public void lowerlives(int a);
	public int getMoney();
	public boolean subtractMoney(int a);
	public void addMoney(int a);
	public void registerNewActorsType(String name);
	public void addActors(Actor b);
	public void removeActors(Actor b);
	public int checkNumInvaders();
	public float getMinX();
	public float getMaxX();
	public float getMinY();
	public float getMaxY();
	public float getClosestInvaderDist(float x, float y);
	public int getColor(int a, int b, int c);
	public void addActorsDuring(Actor b);
	public float distanceBetween(float x1, float y1, float x2, float y2);
	public float distanceBetween(Actor o, Actor b);
	public void addProjectilesNow();
	public Tower getClosestTower(Actor a);
	public Invader getClosestInvader(Actor a, float b);
	public float getClosestTowerDist(float x, float y);
	public Tower getClosestTower(float x, float y);
	public void addProjectile(Projectile projectile);
	public ArrayList<Tower> getTowers();
	public ArrayList<Invader> getInvaders();
	public ArrayList<pathPoint> pathPoints();
	public boolean containsInvader(Invader a);
	public void damageInvaders(float x,float y, float radius, int dmg);
	public boolean gameStarted();
	public void drawLine(float x1, float y1, float x2, float y2);
	//add methods to set the money inside
	
}