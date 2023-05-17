package Chapter04;

import java.util.Scanner;

//int형 고정 길이 스택
 class IntStack {
	private int[] stk; // 스택용 배열
	private int capacity; // 스택의 크기
	private int ptr; // 스택 포인터 //처음 pointer는 0/ 하나 들어가면 1, 두개들어가면 2 계속 증가.//다음에 들어갈 곳을 가리킴.

	// --- 실행시 예외: 스택이 비어있음 ---//
	public class EmptyIntStackException extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public EmptyIntStackException() { //생성자만 만들어서 사용할 예정이기에 굳이 시리얼 아이디 추가할 필요는 없다
		}
	}

	// --- 실행시 예외: 스택이 가득 참 ---//
	public class OverflowIntStackException extends RuntimeException {
		public OverflowIntStackException() {
		}
	}

	public IntStack(int maxlen) {
		ptr = 0;
		capacity = maxlen;
		try {
			stk = new int[capacity]; // 스택 본체용 배열을 생성
		} catch (OutOfMemoryError e) { // 생성할 수 없음
			capacity = 0;
		}
	}

	public int push(int x) throws OverflowIntStackException { // 스택을 x에 푸시
		if (ptr >= capacity) { // 스택이 가득찰 때
			throw new OverflowIntStackException();
		}
		return stk[ptr++] = x;
	}

	public int pop() throws EmptyIntStackException { // 스택에서 데이터를 팝(상단에 있는 데이터를 꺼냄)
		if (ptr <= 0) { // 스택이 비어있을 때
			throw new EmptyIntStackException();
		}
		return stk[--ptr];
	}
	
	public int peek() throws EmptyIntStackException {
		if (ptr <= 0) { // 스택이 비어있을 때
			throw new EmptyIntStackException();
		}
		return stk[ptr -1];
	}
	
	public void clear() {
		ptr = 0;
	}
	
	public int indexOf(int x) {
		for(int i = ptr-1; i >=0; i--) {
			if(stk[i] == x) {
				return i;
			}
		}
		return -1;
	}
	
	public boolean isEmpty() {
		return ptr <=0;
	}
	
	public void dump() {
		if(isEmpty()) { //아래의 if (ptr <= 0)이랑 같은 내용.
		//if (ptr <= 0) {
			System.out.println("스택이 비어 있슴돠");
		}
		else {
			for(int i = 0; i < ptr; i++) {
				System.out.println(stk[i] + " ");
				System.out.println();
			}

		}
	}
 }

	public class StackInt {
		public static void main(String[] args) {
			Scanner stdIn = new Scanner(System.in);
			IntStack s = new IntStack(4); // 최대 64 개를 푸시할 수 있는 스택

			while (true) {
				System.out.println(); // 메뉴 구분을 위한 빈 행 추가
				//System.out.printf("현재 데이터 개수: %d / %d\n", s.size(), s.getCapacity());
				System.out.print("(1)push　(2)pop　(3)peek　(4)dump　(0)종료: ");

				int menu = stdIn.nextInt();
				if (menu == 0)
					break;

				int x;
				switch (menu) {

				case 1: // 푸시
					System.out.print("데이터: ");
					x = stdIn.nextInt();
					try {
						s.push(x);
					} catch (IntStack.OverflowIntStackException e) {
						System.out.println("스택이 가득 찼습니다.");
					}
					break;

				case 2: // 팝
					try {
						x = s.pop();
						System.out.println("팝한 데이터는 " + x + "입니다.");
					} catch (IntStack.EmptyIntStackException e) {
						System.out.println("스택이 비어있습니다.");
					}
					break;

				case 3: // 피크
					try {
						x = s.peek();
						System.out.println("피크한 데이터는 " + x + "입니다.");
					} catch (IntStack.EmptyIntStackException e) {
						System.out.println("스택이 비어있습니다.");
					}
					break;

				case 4: // 덤프
					s.dump();
					break;
				}
			}
		}
	}
