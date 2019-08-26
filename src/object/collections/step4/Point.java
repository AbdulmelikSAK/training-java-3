package object.collections.step4;

/**
 * A class that represents the concept of a point, positioned at the coordinates
 * (x,y) on a plane.
 */
public class Point {
	public double x, y;
	public static double epsilon = 1e-3;

	public Point() {
	}

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Point(Point p) {
		this.x = p.x;
		this.y = p.y;
	}

	public String toString() {
		return "(" + x + "," + y + ")";
	}

	public void translate(double dx, double dy) {
		this.x += dx;
		this.y += dy;
	}

	static double floor(double d) {
		long v = (long) (d / epsilon);
		return ((double) v) * epsilon;
	}

	public boolean equals(Object o) {
		if (this == o)
		      return true;
		    if (o instanceof Point) {
		      Point p = (Point) o;
		      return (floor(x) == floor(p.x) && floor(y) == floor(p.y));
		    }
		    return false;
	}

}
