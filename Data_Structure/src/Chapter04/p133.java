package Chapter04;

public class p133 {
	public class IntStack {
		private int[] stk; // 스택용 배열
		private int capacity; // 스택의 크기
		private int ptr; // 스택 포인터 //처음 pointer는 0/ 하나 들어가면 1, 두개들어가면 2 계속 증가.//다음에 들어갈 곳을 가리킴.

		// --- 실행시 예외: 스택이 비어있음 ---//
		public class EmptyIntStackException extends RuntimeException {
			public EmptyIntStackException() {
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
		
		public int push(int x) throws OverflowIntStackException {
			if (ptr >= capacity) {
				throw new OverflowIntStackException();
			}
			return stk[ptr++] = x;
		}
		
		public int pop() throws EmptyIntStackException {
			if(ptr <=0) {
				throw new EmptyIntStackException();
			}
			return stk[ptr--];
		}

	}
}