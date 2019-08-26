package object.collections.step4;

public class Line {
	private Point start;
	private Point end;

	public Line(Point s, Point e) {
		start = s;
		end = e;
	}

	public Line(Line l) {
		start = l.start;
		end = l.end;
	}

	/*
	 * Translating a line is just translating both points.
	 */
	public void translate(int dx, int dy) {
		this.start.translate(dx, dy);
		this.end.translate(dx, dy);
	}

	/*
	 * Rotating a line is just translating the end point.
	 */
	void rotate(int dx, int dy) {
		this.end.translate(dx, dy);
	}

	public Point getStartPoint() {
		return start;
	}

	public Point getEndPoint() {
		return end;
	}

	public boolean equals(Object o) {
		Line ol = (Line) o;
		if ( (this.start.equals(ol.start) && this.end.equals(ol.end)) || (this.start.equals(ol.end) && this.end.equals(ol.start))) {
			return true;
		}
		else {
			return false;
		}
	}
}
