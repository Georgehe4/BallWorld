import java.util.ArrayList;

public interface World {
	public void registerNewBallType(String name);
	public void addBall(Ball b);
	public void removeBall(Ball b);
	public Ball getClosestBall(Ball b);
	public Ball getClosestBall(float x, float y);
	public float getMinX();
	public float getMaxX();
	public float getMinY();
	public float getMaxY();
	public int getColor(int a, int b, int c);
	public int checkNumBalls();
	public void addBallDuring(Ball b);
	public float distanceBetween(float x1, float y1, float x2, float y2);
	public float distanceBetween(Ball o, Ball b);
	public ArrayList<Ball> getAllBalls();
	public void addSpecialBall(Ball b);
	public ArrayList<Ball> getAllSpecialBalls();
	public Ball getClosestSpecialBall(Ball b);
	public int checkNumSpecialBalls();
	public void addSFXBall(Ball b);

}