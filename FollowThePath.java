package checkpoint4;
import lejos.robotics.mapping.LineMap;
import lejos.robotics.geometry.Line;
import lejos.robotics.geometry.Rectangle;

public class FollowThePath {

	public static LineMap makeMap() {
		
		Rectangle boundary = new Rectangle (0, 0, 1000, 1000);
		
		Line[] obstacles = new Line[] {
	            //laptop
	            new Line(148, 250, 592, 250),  // bottom 
	            new Line(590, 248, 590, 589),  // right 
	            new Line(592, 587, 148, 587),  // top 
	            new Line(150, 589, 150, 248),  // left 

	            //book (a5~)
	            new Line(548, 350, 800, 350),  // bottom
	            new Line(798, 348, 798, 662),  // right 
	            new Line(800, 660, 548, 660),  // top 
	            new Line(550, 662, 550, 348)   // left 
	         
		} ;
		
		return new LineMap(obstacles, boundary);

	}

}
