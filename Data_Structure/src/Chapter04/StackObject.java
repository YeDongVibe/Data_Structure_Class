package Chapter04;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class ObjectStack {
	private List<Point> data; // 스택용 배열
	private int capacity; // 스택의 크기
	private int top; // 스택 포인터

	public class EmptyGenericStackException extends RuntimeException {
		public EmptyGenericStackException() {
		}
	}

	// --- 실행시 예외: 스택이 가득 참 ---//
	public class OverflowGenericStackException extends RuntimeException {
		public OverflowGenericStackException() {
		}
	}

	public ObjectStack(int maxlen) {
		top = 0;
		capacity = maxlen;
		try {
			data = new ArrayList<Point>(); // 스택 본체용 배열을 생성
		} catch (OutOfMemoryError e) { // 생성할 수 없음
			capacity = 0;
		}
	}

	public int push(Point x) throws OverflowGenericStackException { // 스택을 x에 푸시
		if (top >= capacity) // 스택이 가득찰 때
			throw new OverflowGenericStackException();
		{
			data.add(x);
			top++;
			return 1;
		}

	}

	public Point pop() throws EmptyGenericStackException { // 스택에서 데이터를 팝(상단에 있는 데이터를 꺼냄)
		if (top <= 0) // 스택이 비어있을 때
			throw new EmptyGenericStackException();
		{
			Point p = data.remove(top - 1);
			return p;
		}
	}

	public Point peek() throws EmptyGenericStackException { // 최상위의 값을 확인하기 위한 것.
		if (top <= 0) { // 스택이 비어있을 때
			throw new EmptyGenericStackException();
		}
		Point p = data.get(top - 1);
		return p;
	}

	public void clear() {
		top = 0;
	}

	public int indexOf(Point x) {
		for (int i = top - 1; i >= 0; i--) {
			if (data.get(i).equals(x)) { // x는
				return i;
			}
		}
		return -1;
	}

	public boolean isEmpty() {
		return top <= 0;
	}

	public void dump() {
		if (isEmpty()) { // 아래의 if (top <= 0)이랑 같은 내용.
			// if (top <= 0) {
			System.out.println("스택이 비어 있슴돠");
		} else {
			for (int i = 0; i < top; i++) {
				System.out.println(data.get(i) + " ");
				System.out.println();
			}

		}
	}
}

class Point {
	private int ix;
	private int iy;

	public Point(int x, int y) {
		this.ix = x;
		this.iy = y;
	}

	public String toString(){
		//return "ix = " + ix +"iy = " + iy;
		return "("+ix+", "+iy+")" ;
		
	}
	
	@Override
	public boolean equals(Object p) {
		Point px = (Point)p; //Object 클래스에서는 ix와 iy가 선언되지 않기에 equals를 사용하기 위해 재정의(오버라이드)하여 사용.
		if (this.ix == px.ix && this.iy == px.iy) {
			return true;
		} else {
			return false;
		}
	}

}

public class StackObject {

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		ObjectStack s = new ObjectStack(8); // 최대 8 개를 push할 수 있는 stack
		Random random = new Random();
		int rndx = 0, rndy = 0;
		Point p = null;
		while (true) {
			System.out.println(); // 메뉴 구분을 위한 빈 행 추가
			//System.out.printf("현재 데이터 개수: %d / %d\n", s.size(), s.getCapacity());
			System.out.print("(1)push　(2)pop　(3)peek　(4)dump　(0)종료: ");

			int menu = stdIn.nextInt();
			if (menu == 0)
				break;

			switch (menu) {
			case 1: // 푸시
				System.out.print("데이터: ");
				rndx = random.nextInt(30); //bound 값 : ==random.nextInt() %30;
				rndy = random.nextInt(20);
				p = new Point(rndx, rndy);
				try {
					s.push(p);
				} catch (ObjectStack.OverflowGenericStackException e) {
					System.out.println("stack이 가득찼있습니다.");
				}
				break;

			case 2: // 팝
				try {
					p = s.pop();
					System.out.println("pop한 데이터는 " + p + "입니다.");
				} catch (ObjectStack.EmptyGenericStackException e) {
					System.out.println("stack이 비어있습니다.");
				}
				break;

			case 3: // 피크
				try {
					p = s.peek();
					System.out.println("peek한 데이터는 " + p + "입니다.");
				} catch (ObjectStack.EmptyGenericStackException e) {
					System.out.println("stack이 비어있습니다.");
				}
				break;

			case 4: // 덤프
				s.dump();
				break;
			}
		}
	}
}