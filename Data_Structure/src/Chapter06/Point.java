package Chapter06;

public class Point {
	private int x;
	private int y;

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getRow() {
		return y;
	}

	public int getCol() {
		return x;
	}

	public void setRow(int y) {
		this.y = y;
	}

	public void setCol(int x) {
		this.x = x;
	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point() {
		this.x = 0;
		this.y = 0;
	}

	@Override
	public boolean equals(Object obj) {
		Point p = (Point) obj;

		if ((this.x == p.x) && (this.y == p.y))
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		return "[" + this.x + ", " + this.y + "]";
	}

}