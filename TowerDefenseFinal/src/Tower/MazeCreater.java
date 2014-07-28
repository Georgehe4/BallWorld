package Tower;


import java.util.ArrayList;

public class MazeCreater {
public static ArrayList<pathPoint> getMaze(int a){
	ArrayList<pathPoint> toReturn=new ArrayList<pathPoint>();
	if(a==0){
		toReturn.add(new pathPoint(150, 150));
		toReturn.add(new pathPoint(600, 150));
		toReturn.add(new pathPoint(600, 300));
		toReturn.add(new pathPoint(150, 300));
		toReturn.add(new pathPoint(150, 450));
		toReturn.add(new pathPoint(600, 450));
		toReturn.add(new pathPoint(600, 600));
		toReturn.add(new pathPoint(150, 600));
		toReturn.add(new pathPoint(150, 750));
		toReturn.add(new pathPoint(600, 750));
		return toReturn;
	}
	if(a==1){
		toReturn.add(new pathPoint(150, 150));
		toReturn.add(new pathPoint(675, 150));
		toReturn.add(new pathPoint(675, 700));
		toReturn.add(new pathPoint(150, 700));
		toReturn.add(new pathPoint(150, 225));
		toReturn.add(new pathPoint(600, 225));
		toReturn.add(new pathPoint(600, 625));
		toReturn.add(new pathPoint(225, 625));
		toReturn.add(new pathPoint(225, 300));
		toReturn.add(new pathPoint(525, 300));
		toReturn.add(new pathPoint(525, 550));
		toReturn.add(new pathPoint(300, 550));
		toReturn.add(new pathPoint(300, 375));
		toReturn.add(new pathPoint(450, 375));
		toReturn.add(new pathPoint(450, 475));
		toReturn.add(new pathPoint(375, 475));
		toReturn.add(new pathPoint(375, 450));
		return toReturn;
	}
	if(a==2){
		toReturn.add(new pathPoint(150, 150));
		toReturn.add(new pathPoint(750, 150));
		toReturn.add(new pathPoint(750, 450));
		toReturn.add(new pathPoint(150, 450));
		toReturn.add(new pathPoint(150, 750));
		toReturn.add(new pathPoint(750, 750));
		toReturn.add(new pathPoint(750, 150));
		toReturn.add(new pathPoint(450, 150));
		toReturn.add(new pathPoint(450, 750));
		toReturn.add(new pathPoint(150, 750));
		toReturn.add(new pathPoint(150, 200));
	}
	return toReturn;
}
}
