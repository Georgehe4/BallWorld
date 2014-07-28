package Tower;

import guicomponents.*;

import java.awt.Font;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import processing.core.PApplet;

public class DefaultWorld extends PApplet implements World {

	// List of all actor objects in the world
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	// List of actor objects to remove in next time step. Gets set in
	// removeActors();
	// must use these
	public int cheats = 0;
	public int state=0;
	public boolean godMode = false;
	public boolean paused = false;
	public ArrayList<pathPoint> pathPoints = new ArrayList<pathPoint>();
	public ArrayList<Invader> invaders = new ArrayList<Invader>();
	public ArrayList<Tower> towers = new ArrayList<Tower>();
	public ArrayList<Actor> actorsToRemove = new ArrayList<Actor>();
	public ArrayList<Actor> actorsToAdd = new ArrayList<Actor>();
	public ArrayList<Projectile> projectilesToAdd = new ArrayList<Projectile>();
	// Keep actors that implement the Interactive interface separately
	// because we want to loop through them separately when the mouse is clicked
	public ArrayList<Actor> interactiveActors = new ArrayList<Actor>();
	public int lives = 20;
	public int counter = 0;
	public Level level;
	// Things for the GUI
	GOptionGroup actorSelector = new GOptionGroup();
	ArrayList<GOption> options = new ArrayList<GOption>(); // List of radio
	// buttons

	private int options_y; // y coord of next button
	private int options_w; // width of button
	private int options_spacing; // vert. spacing for b.
	private int options_x;
	public int money = 500000;
	public int levelCounter;
	public boolean gameStarted = false;
	private int mazeNum;
	public GButton selectMaze1;
	public GButton selectMaze2;
	public GButton selectMaze3;
	public void setup() {
		
		size(1000, 1000);
		// GUI Setup
		G4P.setColorScheme(this, GCScheme.GREEN_SCHEME);
		G4P.messagesEnabled(true);
		G4P.setMouseOverEnabled(true);
		GComponent.globalFont = GFont.getFont(this, "Arial", 13);

		selectMaze1=new GButton(this, "Maze1", 350, 500, 100, 100);
		selectMaze2=new GButton(this, "Maze2", 450, 500, 100, 100);
		selectMaze3=new GButton(this, "Maze3", 550, 500, 100, 100);
		
		options_y = 40;
		options_w = 135;
		options_spacing = 15;
		options_x = this.width - options_w;

	}
	public void drawLine(float x1, float y1, float x2, float y2){
		line(x1,y1,x2,y2);
	}
	public void draw() {
		//
		if(lives<=0){
			this.restartGame();
		}
		if(state==1){
			
		
		
		
		background(100);
		fill(color(250, 215, 0, 1000));
		text("Money: " + ((Integer) money).toString(), 500, 60);
		fill(color(450, 100, 50, 1000));
		text("Level: " + ((Integer) (level.currentLevel+1)).toString(), 300, 60);
		fill(color(600, 000, 000, 1000));
		text("Lives: " + ((Integer) lives).toString(), 100, 60);
		fill(0, 300, 300, 80);
		rect(width - 145, 0, 145, height); // draw GUI panel
		fill(color(250, 200, 20, 1000));
		for (int a = 0; a < pathPoints.size()-1; a += 4) {
			rect(pathPoints.get(a).x - 20, pathPoints.get(a).y - 20,
					pathPoints.get(a + 1).x - pathPoints.get(a).x + 40, 40);
		}
		for (int a = 3; a < pathPoints.size(); a += 4) {
			rect(pathPoints.get(a).x - 20, pathPoints.get(a).y - 20,
					pathPoints.get(a - 1).x - pathPoints.get(a).x + 40, 40);
		}
		for (int a = 2; a < pathPoints.size(); a += 2) {
			rect(pathPoints.get(a).x - 20, pathPoints.get(a).y - 20, 40,
					pathPoints.get(a - 1).y - pathPoints.get(a).y + 40);
		}
		fill(color(50, 50, 50, 7000));
		for (Actor b : invaders) {
			drawActors(b);
			if (b instanceof Animated && !paused)
				((Animated) b).update();
		}
		for (Actor b : towers) {
			drawActors(b);
			if (b instanceof Animated && !paused)
				((Animated) b).update();

		}
		for (Actor b : projectiles) {
			drawActors(b);
			if (b instanceof Animated && !paused)
				((Animated) b).update();
		}
		for (Tower a : towers) {
			fill(color(0, 0, 0, 500));
			text(a.getName() + ((Integer) a.getLevel()).toString(), a.x - 12,
					a.y + 5);
		}
		// test code
		// counter++;
		// if (counter == 20) {
		// this.addActors(new Invader(this, 50, 10));
		// counter = 0;
		// }
		// test code end
		if (!paused) {
			if (!actorSelector.selectedOption().getText().equals("Upgrade") || !actorSelector.selectedOption().getText().equals("Sell") ) {
				fill(1000, 1000, 1000);
				this.ellipse(mouseX, mouseY, 30, 30);
			}
			removeActors();
			addActorsNow();

			level.update();
			if (this.invaders.size() == 0 && gameStarted) {
				levelCounter++;
				if (levelCounter == 300) {
					level.nextLevel();
					levelCounter = 0;
				}
			}

		}
		}else if(state==2){
			
		}
	}

	private void removeActors() {
		for (Actor a : actorsToRemove) {
			if (a instanceof Tower)
				towers.remove((Tower) a);

			if (a instanceof Projectile)
				projectiles.remove((Projectile) a);

			if (a instanceof Invader)
				invaders.remove((Invader) a);
		}
		actorsToRemove.clear();
	}

	public boolean gameStarted() {
		return gameStarted;
	}
	public void restartGame(){
		for(Tower a:towers){
			removeActors(a);
		}
		for(Invader b:invaders){
			removeActors(b);
		}
		level=new Level(this);
		money=500;
		lives=20;
		gameStarted=false;
	}
	public boolean canPlaceTower(float x, float y) {
		if (getClosestTowerDist(x, y) < 30) {
			System.out.println(getClosestTowerDist(x, y));
			return false;
		}
		pathPoint a;
		pathPoint b;
		boolean canPlace = true;
		float xLo;
		float yLo;
		float xHi;
		float yHi;
		for (int i = 0; i < pathPoints.size() - 1; i++) {
			a = pathPoints.get(i);
			b = pathPoints.get(i + 1);
			xLo = Math.min(a.x, b.x);
			yLo = Math.min(a.y, b.y);
			xHi = Math.max(a.x, b.x);
			yHi = Math.max(a.y, b.y);
			if ((x > xLo - 35 && x < xHi + 35)
					&& (y > yLo - 35 && y < yHi + 35)) {
				canPlace = false;
			}
		}
		return canPlace;
	}

	private void addActorsNow() {
		for (Actor a : actorsToAdd) {
			if (a instanceof Tower)
				towers.add((Tower) a);

			if (a instanceof Projectile)
				projectiles.add((Projectile) a);

			if (a instanceof Invader)
				invaders.add((Invader) a);

		}
		actorsToAdd.clear();
	}

	public void addProjectilesNow() {
		for (Actor a : projectilesToAdd) {
			projectiles.add((Projectile) a);
		}
		projectilesToAdd.clear();
	}

	public void addProjectile(Projectile b) {
		projectilesToAdd.add(b);
	}

	public int checkNumInvaders() {
		return invaders.size();
	}

	private void drawActors(Actor b) {
		fill(b.getColor());
		ellipse(b.getX(), b.getY(), b.getRadius(), b.getRadius());
	}

	@Override
	public void registerNewActorsType(String name) {
		GOption g = new GOption(this, name, options_x, options_y, options_w);
		actorSelector.addOption(g);
		options.add(g);
		actorSelector.setSelected(g);
		options_y += options_spacing;
	}

	public void handleOptionEvents(GOption selected, GOption deselected) {
		for (GOption o : options) {
			if (selected == o) {
			}
		}
	}

	public void handleButtonEvents(GButton button) {
		if (button.getText().equals("Start Game")) {
			gameStarted = true;
			godMode = false;
		}
		if (button.getText().equals("Restart Game"))
			restartGame();
		if (button.getText().equals("Pause"))
			paused = !paused;
		if (button.getText().equals("Next Wave"))
			levelCounter=299;
		if(button.getText().equals("Maze1")){
			pathPoints = MazeCreater.getMaze(0);
			level = new Level(this);
			state=1;
			
			GButton start = new GButton(this, "Start Game", 875, 650, 100, 50);
			GButton pause = new GButton(this, "Pause", 875, 600, 100, 50);
			GButton nextWave = new GButton(this, "Next Wave", 875, 550, 100, 50);
			GButton restart = new GButton(this, "Restart Game", 875, 700, 100, 50);
			this.selectMaze1.setVisible(false);
			this.selectMaze2.setVisible(false);
			this.selectMaze3.setVisible(false);
			
			registerNewActorsType("Gunner");
			registerNewActorsType("MissileTower");
			registerNewActorsType("LaserTower");
			registerNewActorsType("TeslaTower");
			registerNewActorsType("Upgrade");
			registerNewActorsType("Sell");
			
		}
		if(button.getText().equals("Maze2")){
			pathPoints = MazeCreater.getMaze(1);
			level = new Level(this);
			state=1;
			GButton restart = new GButton(this, "Restart Game", 875, 700, 100, 50);
			GButton start = new GButton(this, "Start Game", 875, 650, 100, 50);
			GButton pause = new GButton(this, "Pause", 875, 600, 100, 50);
			GButton nextWave = new GButton(this, "Next Wave", 875, 550, 100, 50);
			this.selectMaze1.setVisible(false);
			this.selectMaze2.setVisible(false);
			this.selectMaze3.setVisible(false);
			registerNewActorsType("Gunner");
			registerNewActorsType("MissileTower");
			registerNewActorsType("LaserTower");
			registerNewActorsType("TeslaTower");
			registerNewActorsType("Upgrade");
			registerNewActorsType("Sell");
			
		}
		if(button.getText().equals("Maze3")){
			pathPoints = MazeCreater.getMaze(2);
			level = new Level(this);
			state=1;
			GButton restart = new GButton(this, "Restart Game", 875, 700, 100, 50);
			GButton start = new GButton(this, "Start Game", 875, 650, 100, 50);
			GButton pause = new GButton(this, "Pause", 875, 600, 100, 50);
			GButton nextWave = new GButton(this, "Next Wave", 875, 550, 100, 50);
			this.selectMaze1.setVisible(false);
			this.selectMaze2.setVisible(false);
			this.selectMaze3.setVisible(false);
			registerNewActorsType("Gunner");
			registerNewActorsType("MissileTower");
			registerNewActorsType("LaserTower");
			registerNewActorsType("TeslaTower");
			registerNewActorsType("Upgrade");
			registerNewActorsType("Sell");	
		}
			
	}

	public void mouseClicked() {
			System.out.println(mouseX+", "+mouseY);
		if (godMode) {
			damageInvaders(mouseX, mouseY, 9001, 9001);
		} else if (mouseX < width - 195) {
			if (actorSelector.selectedOption().getText().equals("MissileTower")) {
				if (cheats == 0 || cheats==1) {
					cheats=1;
				} else {
					cheats = 0;
				}
				if (canPlaceTower(mouseX, mouseY)) {
					if (money - MissileTower.costs[0] >= 0) {
						MissileTower t = new MissileTower(this, mouseX, mouseY);
						towers.add(t);
						if (t instanceof UserInteractive) {
							interactiveActors.add(t);
						}
					}
				}
			} else if (actorSelector.selectedOption().getText()
					.equals("Gunner")) {
				if (cheats == 2) {
					cheats++;
				} else {
					cheats = 0;
				}
				if (canPlaceTower(mouseX, mouseY)) {
					if (money - Gunner.costs[0] >= 0) {
						Gunner t = new Gunner(this, mouseX, mouseY);
						towers.add(t);
						if (t instanceof UserInteractive) {
							interactiveActors.add(t);
						}
					}
				}
			} else if (actorSelector.selectedOption().getText()
					.equals("LaserTower")) {
				if (cheats == 4) {
					godMode = true;
				} else {
					cheats = 0;
				}
				if (canPlaceTower(mouseX, mouseY)) {
					if (money - LaserTower.costs[0] >= 0) {
						LaserTower t = new LaserTower(this, mouseX, mouseY);
						towers.add(t);
						if (t instanceof UserInteractive) {
							interactiveActors.add(t);
						}
					}
				}
			} else if (actorSelector.selectedOption().getText()
					.equals("TeslaTower")) {
				if (cheats == 1) {
					cheats++;
				} else {
					cheats = 0;
				}
				if (canPlaceTower(mouseX, mouseY)) {
					if (money - TeslaTower.costs[0] >= 0) {
						TeslaTower t = new TeslaTower(this, mouseX, mouseY);
						towers.add(t);
						if (t instanceof UserInteractive) {
							interactiveActors.add(t);
						}
					}
				}
			} 
			 else if (actorSelector.selectedOption().getText()
						.equals("Sell")) {
					if (cheats == 3) {
						cheats++;
					} else {
						cheats = 0;
					}
					Tower closest;
					if (getClosestTowerDist(mouseX, mouseY) < 30) {
						closest = getClosestTower(mouseX, mouseY);
						money+=closest.getCost()*0.8;
						removeActors(closest);
					}
			 }
			else if (actorSelector.selectedOption().getText()
					.equals("Upgrade")) {
				if (cheats == 3) {
					cheats++;
				} else {
					cheats = 0;
				}
				Tower closest;
				if (getClosestTowerDist(mouseX, mouseY) < 30) {
					closest = getClosestTower(mouseX, mouseY);
					closest.upgrade();
				}
			}
		}

		// you can add more if-statements here to add other types.
	}

	@Override
	public void addActors(Actor b) {
		actorsToAdd.add(b);
	}

	public void addActorsDuring(Actor b) {
		actorsToAdd.add(b);
	}

	@Override
	public void removeActors(Actor b) {
		actorsToRemove.add(b);
	}

	public ArrayList<Tower> getTowers() {
		return towers;
	}

	public ArrayList<Invader> getInvaders() {
		return invaders;
	}

	public Tower getClosestTower(Actor b) {
		if (towers.isEmpty())
			return null;
		Tower closest = null;
		float d = 2000;
		for (Tower e : towers)
			if (distanceBetween(e, b) < d) {
				closest = e;
				d = distanceBetween(e, b);
			}
		return closest;
	}

	public Tower getClosestTower(float x, float y) {
		if (towers.isEmpty())
			return null;
		Tower closest = null;
		float d = 2000;
		for (Tower e : towers)
			if (distanceBetween(e.getX(), e.getY(), x, y) < d) {
				closest = e;
				d = distanceBetween(e.getX(), e.getY(), x, y);
			}
		return closest;
	}

	public float getClosestInvaderDist(float x, float y) {
		if (towers.isEmpty())
			return 2000;
		float d = 2000;
		for (Invader e : invaders)
			if (distanceBetween(e.getX(), e.getY(), x, y) < d) {
				d = distanceBetween(e.getX(), e.getY(), x, y);
			}
		return d;
	}

	public float getClosestTowerDist(float x, float y) {
		if (towers.isEmpty())
			return 2000;
		float d = 2000;
		for (Tower e : towers)
			if (distanceBetween(e.getX(), e.getY(), x, y) < d) {
				d = distanceBetween(e.getX(), e.getY(), x, y);
			}
		return d;
	}

	public float distanceBetween(Actor o, Actor b) {
		float dx = o.getX() - b.getX();
		float dy = o.getY() - b.getY();
		return (float) Math.sqrt(dx * dx + dy * dy);
	}

	public float distanceBetween(float x1, float y1, float x2, float y2) {
		float dx = x1 - x2;
		float dy = y1 - y2;
		return (float) Math.sqrt(dx * dx + dy * dy);
	}

	@Override
	public float getMinX() {
		return 0;
	}

	@Override
	public float getMaxX() {
		return this.width - 135;
	}

	@Override
	public float getMinY() {
		return 0;
	}

	@Override
	public float getMaxY() {
		return this.height;
	}

	public int getColor(int a, int b, int c) {
		return color(a, b, c);
	}

	public void mouseDragged() {
		for (Actor b : interactiveActors) {
			((UserInteractive) b).userDragged(mouseX, mouseY);
		}
	}

	public int getMoney() {
		return money;
	}

	public boolean subtractMoney(int a) {
		if (money - a >= 0) {
			money -= a;
			return true;
		}
		return false;
	}

	public void addMoney(int a) {
		money += a;
	}

	public boolean containsInvader(Invader a) {
		if (invaders.contains(a))
			return true;
		return false;
	}

	public void damageInvaders(float x, float y, float radius, int dmg) {
		for (Invader a : invadersWithinRange(x, y, radius)) {
			a.lowerHealth(dmg);
		}
	}

	public ArrayList<Invader> invadersWithinRange(float x, float y, float radius) {
		ArrayList<Invader> toReturn = new ArrayList<Invader>();
		if (invaders.isEmpty())
			return toReturn;
		for (Invader e : invaders) {
			if (distanceBetween(x, y, e.x, e.y) < radius) {
				toReturn.add(e);
			}
		}
		return toReturn;
	}

	public Invader getClosestInvader(Actor b, float a) {
		if (invaders.isEmpty())
			return null;
		Invader closest = null;
		float d = 900;
		for (Invader e : invaders) {
			if (distanceBetween(e, b) < d) {
				closest = e;
				d = distanceBetween(e, b);
			}
		}
		if (d < a) {

			return closest;
		} else {
			return null;
		}
	}

	public static void main(String[] args) {
		PApplet.main(new String[] { "Tower.DefaultWorld" });
	}

	public ArrayList<pathPoint> pathPoints() {
		return pathPoints;
	}

	@Override
	public void lowerlives(int a) {
		lives -= a;
	}

}