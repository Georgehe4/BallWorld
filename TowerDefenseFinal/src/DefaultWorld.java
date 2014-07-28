import guicomponents.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import processing.core.PApplet;

public class DefaultWorld extends PApplet implements World {

	// List of all ball objects in the world
	private ArrayList<Ball> balls = new ArrayList<Ball>();			
	private ArrayList<Ball> specialBalls=new ArrayList<Ball>();
	private ArrayList<Ball> SFX = new ArrayList<Ball>();
	// List of ball objects to remove in next time step.  Gets set in removeBall();
	private ArrayList<Ball> ballsToRemove = new ArrayList<Ball>();
	private ArrayList<Ball> ballsToAdd = new ArrayList<Ball>();
	private ArrayList<Ball> specialBallsToAdd = new ArrayList<Ball>();

	private ArrayList<Ball> SFXBallsToAdd = new ArrayList<Ball>();


	// Keep balls that implement the Interactive interface separately
	// because we want to loop through them separately when the mouse is clicked
	private ArrayList<Ball> interactiveBalls = new ArrayList<Ball>();

	// Things for automatic ball creation
	ClassLoader cl = Thread.currentThread().getContextClassLoader();
	URLClassLoader uLoad = new URLClassLoader(new URL[] {}, cl);

	// Things for the GUI
	GOptionGroup ballSelector = new GOptionGroup();
	ArrayList<GOption> options = new ArrayList<GOption>();  // List of radio buttons
	private int options_y;									// y coord of next button
	private int options_w;									// width of button
	private int options_spacing;							// vert. spacing for b.
	private int options_x;									
	GTextField ballEntryField;								// textbox

	public void setup() {
		size(600, 550);

		// GUI Setup
		G4P.setColorScheme(this, GCScheme.GREEN_SCHEME);
		G4P.messagesEnabled(true);
		G4P.setMouseOverEnabled(true);
		GComponent.globalFont = GFont.getFont(this, "Arial", 13);
		options_y = 40;
		options_w = 135;
		options_spacing = 15;
		options_x = this.width - options_w;
		ballEntryField = new GTextField(this, "", width - 135, 10, 120, 16);
		ballEntryField.tag = "Type Ball Class Name Here -> ";

		registerNewBallType("None");
		registerNewBallType("Dud");
		registerNewBallType("Mover");
		registerNewBallType("Shooter");
		registerNewBallType("Bouncer");
		registerNewBallType("LotsOfBallsDestructable");
		registerNewBallType("LotsOfBallsSpecial");
		registerNewBallType("LotsOfSpecialBalls");
		
		registerNewBallType("AimedShooter");
		registerNewBallType("Shrinker");
		registerNewBallType("MovingShrinker");		
		registerNewBallType("Faller");
		registerNewBallType("Seeker");
		registerNewBallType("Stalker");
		registerNewBallType("Mine");		
		registerNewBallType("Exploder");
		registerNewBallType("Missile");
		registerNewBallType("Turret");
		registerNewBallType("SpecialTurret");
		
		registerNewBallType("Assassin");
		registerNewBallType("NormalsMustDie");
		registerNewBallType("SpecialsMustDie");
		registerNewBallType("EverythingMustDie");

	}

	public void draw() {
		background(200);
		fill(color(50, 50, 50, 50));
		rect(width-145, 0, 145, height);		// draw GUI panel
		for(Ball b:specialBalls){
			drawBall(b);
			if (b instanceof Animated)
				((Animated) b).update();

			if (b instanceof BallInteractive)
				((BallInteractive) b).update(balls);		

		}
		ellipse(this.mouseX, this.mouseY, 30, 30);
		for (Ball b:balls) {
			drawBall(b);
			if (b instanceof Animated)
				((Animated) b).update();

			if (b instanceof BallInteractive)
				((BallInteractive) b).update(balls);		
		}
		for (Ball b:SFX) {
			drawBall(b);
			if (b instanceof Animated)
				((Animated) b).update();

			if (b instanceof BallInteractive)
				((BallInteractive) b).update(balls);		
		}
		removeBalls();
		addBallsNow();
		addSpecialBallsNow();
		addSFXBallsNow();

	}

	private void removeBalls() {
		for (Ball b:ballsToRemove) {
			balls.remove(b);
			interactiveBalls.remove(b);
			specialBalls.remove(b);
			SFX.remove(b);
		}
		ballsToRemove.clear();
	}
	public ArrayList<Ball> getAllSpecialBalls(){
		return specialBalls;
	}
	private void addBallsNow(){
		for(Ball a:ballsToAdd){
			balls.add(a);
		}
		ballsToAdd.clear();
	}
	private void addSpecialBallsNow(){
		for(Ball a:specialBallsToAdd){
			specialBalls.add(a);
		}
		specialBallsToAdd.clear();
	}
	private void addSFXBallsNow(){
		for(Ball a:SFXBallsToAdd){
			SFX.add(a);
		}
		SFXBallsToAdd.clear();
	}
	public void addSFXBall(Ball b){
		SFXBallsToAdd.add(b);
	}
	private void drawBall(Ball b) {
		fill(b.getColor());
		ellipse(b.getX(), b.getY(), b.getRadius(), b.getRadius());
	}

	@Override
	public void registerNewBallType(String name) {
		GOption g = new GOption(this, name, options_x, options_y, options_w);
		ballSelector.addOption(g);
		options.add(g);
		ballSelector.setSelected(g);
		options_y += options_spacing;
	}

	public void handleOptionEvents(GOption selected, GOption deselected) {
		for (GOption o : options) {
			if (selected == o) {
				System.out.println(o.getText() + " selected");
			}
		}
	}
	public int checkNumBalls(){
		return balls.size();
	}
	public int checkNumSpecialBalls(){
		return specialBalls.size();
	}
	private void createBall(String class2Load) {
		// Try to load the class and find out what constructors exist
		Class studentClass = null;
		Constructor world = null, noArg = null;
		try {
			studentClass = Class.forName(class2Load, true, this.uLoad);
			Constructor[] studentConstructors = studentClass.getConstructors();
			for (int i = 0; i < studentConstructors.length; i++) {
				System.out.println(studentConstructors[i].toString());
				Class[] params = studentConstructors[i].getParameterTypes();
				for (int j = 0; j < params.length; j++) {
					String name = params[j].getName();
					if (name.equals("World")) {
						world = studentConstructors[i];
					}
				}
				if (params.length == 0) {
					noArg = studentConstructors[i];
				}
			}
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Warning student class not found");
		}

		Object[] args4selected;
		Constructor selected = null;
		if (world != null) { // use this one if we have it
			selected = world;
			args4selected = new Object[] { this, mouseX, mouseY };
		} else {
			if (noArg != null) { // otherwise pick no arg constructor
				selected = noArg;
				args4selected = new Object[] {};
			} else {
				System.out.println("Class must have a constructor that "
						+ "takes three arguments of type World, float, float");
				return;
			}
		}

		// This needs to be final so it can be used in an local class
		final Constructor construct = selected;
		final Object[] args = args4selected;
		System.out.println("Constructor selected = " + construct);
		Ball newBall = null;
		try {
			newBall = (Ball) construct.newInstance(args);
			balls.add(newBall);
			if (newBall instanceof UserInteractive) {
				interactiveBalls.add(newBall); 
			}
		} catch (InstantiationException ie) {
			System.out.println("Failed to instantiate the ball");
			System.out.println(ie);
			return;
		} catch (IllegalAccessException iae) {
			System.out.println("Illegal access instantiating ball");
			System.out.println(iae);
			return;
		} catch (InvocationTargetException ite) {
			System.out.println("Failed to instantiate the ball");
			System.out.println(ite);
			return;
		}
	}

	public void mouseClicked() {
		if (ballSelector.selectedOption().getText().equals("None")||ballSelector.selectedOption().getText().equals("Kamikaze")||ballSelector.selectedOption().getText().equals("AimedMover")) return;

		if (mouseX < this.getMaxX()) {
			createBall(ballSelector.selectedOption().getText());
		}
		
		
	}

	@Override
	public void addBall(Ball b) {
		balls.add(b);
	}
	public void addBallDuring(Ball b){
		ballsToAdd.add(b);
	}
	public void addSpecialBall(Ball b){
		specialBallsToAdd.add(b);
	}

	@Override
	public void removeBall(Ball b) {
		ballsToRemove.add(b);
	}

	@Override
	public Ball getClosestBall(Ball b) {
		if (balls.isEmpty()) return null;
		Ball closest = null;
		float d;
		if (b != balls.get(0)) {
			closest = balls.get(0);
			d = distanceBetween(balls.get(0), b);
		} else {
			closest = balls.get(1);
			d = distanceBetween(balls.get(1), b);
		}
		for (Ball o : balls) {
			if (b != o && distanceBetween(o, b) < d) {
				closest=o;
				d=distanceBetween(o,b);
			}
		}
		return closest;

	}
	public Ball getClosestSpecialBall(Ball b) {
		if (specialBalls.isEmpty()) return null;
		Ball closest = null;
		float d;
		if (b != specialBalls.get(0)) {
			closest = specialBalls.get(0);
			d = distanceBetween(specialBalls.get(0), b);
		} else {
			closest = specialBalls.get(1);
			d = distanceBetween(specialBalls.get(1), b);
		}
		for (Ball o : specialBalls) {
			if (b != o && distanceBetween(o, b) < d) {
				closest=o;
				d=distanceBetween(o,b);
			}
		}
		return closest;

	}
	public float distanceBetween(Ball o, Ball b) {
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
	public Ball getClosestBall(float x, float y) {
		return getClosestBall(new Dud(this, x, y));
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
		for (Ball b:interactiveBalls) {
			((UserInteractive)b).userDragged(mouseX, mouseY);
		}
	}
	public ArrayList<Ball> getAllBalls(){
		ArrayList<Ball> toReturn=new ArrayList<Ball>();
		toReturn.addAll(balls);
		toReturn.addAll(specialBalls);
		return toReturn;
	}
	
	public void handleTextFieldEvents(GTextField tfield){
		if (tfield.getEventType() == GTextField.ENTERED) {
			String t = this.ballEntryField.getText();
			if (t != null && !t.equals("")) {
				this.registerNewBallType(t);	
				ballEntryField.setText("");
			}
		}
	}

	public static void main(String[] args) {
		PApplet.main(new String[] { "DefaultWorld" });
	}

	
}