package Chapter04;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//int형 고정 길이 큐의 사용 예
class Queue {
	private List<Integer> que;
	private int capacity; // 큐의 크기
	private int front; // 맨 처음 요소 커서
	private int rear; // 맨 끝 요소 커서
	private int num; // 현재 데이터 개수

	// --- 실행시 예외: 큐가 비어있음 ---//
	public class EmptyIntQueueException extends RuntimeException {
		public EmptyIntQueueException() {
		}
	}

	// --- 실행시 예외: 큐가 가득 찼음 ---//
	public class OverflowIntQueueException extends RuntimeException {
		public OverflowIntQueueException() {
		}
	}

	// --- 생성자(constructor) ---//
	public Queue(int maxlen) {
		num = front = rear = 0;
		capacity = maxlen;
		try {
			que = new ArrayList<Integer>();
			; // 큐 본체용 배열을 생성
		} catch (OutOfMemoryError e) { // 생성할 수 없음
			capacity = 0;
		}
	}

	// --- 큐에 데이터를 인큐 ---//
	public int enque(Integer x) throws OverflowIntQueueException {
		if (num >= capacity)
			throw new OverflowIntQueueException(); // 큐가 가득 찼음
		que.add(x);
		rear++;
		num++;
		if (rear == capacity)
			rear = 0;
		return x;
	}

	// --- 큐에서 데이터를 디큐 ---//
	public Integer deque() throws EmptyIntQueueException {
		if (num <= 0)
			throw new EmptyIntQueueException(); // 큐가 비어있음
		Integer i = que.get(front);
		front++;
		num--;
		if (front == capacity)
			front = 0;
		return i;
	}

	// --- 큐에서 데이터를 피크(프런트 데이터를 들여다봄) ---//
	public Integer peek() throws EmptyIntQueueException {
		if (num <= 0)
			throw new EmptyIntQueueException(); // 큐가 비어있음
		{
			Integer i = que.get(front);
			return i;
		}
	}

	// --- 큐를 비움 ---//
	public void clear() {
		num = front = rear = 0;
	}

	// --- 큐에서 x를 검색하여 인덱스(찾지 못하면 –1)를 반환 ---//
	public int indexOf(Integer x) {
		for (int i = 0; i < num; i++) {
			int idx = (i + front) % capacity;
			if (que.get(i).equals(x)) // 검색 성공
				return idx;
		}
		return -1; // 검색 실패
	}

	// --- 큐가 비어있는가? ---//
	public boolean isEmpty() {
		return num <= 0;
	}

	// --- 큐가 가득 찼는가? ---//
	public boolean isFull() {
		return num >= capacity;
	}

	// --- 큐 안의 모든 데이터를 프런트 → 리어 순으로 출력 ---//
	public void dump() {
		if (num <= 0)
			System.out.println("큐가 비어있습니다.");
		else {
			for (int i = 0; i < num; i++)
				System.out.print(que.get(((i + front) % capacity)) + " ");
			System.out.println();
		}
	}

}

//int형 고정 길이 큐의 사용 예
public class QueInt {

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		Queue s = new Queue(4); // 최대 64개를 인큐할 수 있는 큐

		while (true) {
			System.out.println(" "); // 메뉴 구분을 위한 빈 행 추가
			// System.out.printf("현재 데이터 개수: %d / %d\n", s.size(), s.getCapacity());
			System.out.print("(1)인큐　(2)디큐　(3)피크　(4)덤프　(0)종료: ");

			int menu = stdIn.nextInt();
			if (menu == 0)
				break;

			int x;
			switch (menu) {
			case 1: // 인큐
				System.out.print("데이터: ");
				x = stdIn.nextInt();
				try {
					s.enque(x);
				} catch (Queue.OverflowIntQueueException e) {
					System.out.println("큐가 가득 찼습니다.");
				}
				break;

			case 2: // 디큐
				try {
					x = s.deque();
					System.out.println("디큐한 데이터는 " + x + "입니다.");
				} catch (Queue.EmptyIntQueueException e) {
					System.out.println("큐가 비어 있습니다.");
				}
				break;

			case 3: // 피크
				try {
					x = s.peek();
					System.out.println("피크한 데이터는 " + x + "입니다.");
				} catch (Queue.EmptyIntQueueException e) {
					System.out.println("큐가 비어 있습니다.");
				}
				break;

			case 4: // 덤프
				s.dump();
				break;
			}
		}
		stdIn.close();
	}
}
