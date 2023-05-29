package Chapter06;
// int형 고정 길이 스택

import java.util.ArrayList;
import java.util.List;


public class IntStack {
	private List<Point> data; // 스택용 배열
	private int capacity; // 스택의 크기
	private int ptr; // 스택 포인터

	public class EmptyGenericStackException extends RuntimeException {
		public EmptyGenericStackException() {
		}
	}

	// --- 실행시 예외: 스택이 가득 참 ---//
	public class OverflowGenericStackException extends RuntimeException {
		public OverflowGenericStackException() {
		}
	}

	public IntStack(int maxlen) {
		ptr = 0;
		capacity = maxlen;
		try {
			data = new ArrayList<Point>(); // 스택 본체용 배열을 생성
		} catch (OutOfMemoryError e) { // 생성할 수 없음
			capacity = 0;
		}
	}

	public int push(Point x) throws OverflowGenericStackException { // 스택을 x에 푸시
		if (ptr >= capacity) // 스택이 가득찰 때
			throw new OverflowGenericStackException();
		{
			data.add(x);
			ptr++;
			return 1;
		}

	}

	public Point pop() throws EmptyGenericStackException { // 스택에서 데이터를 팝(상단에 있는 데이터를 꺼냄)
		if (ptr <= 0) // 스택이 비어있을 때
			throw new EmptyGenericStackException();
		{
			Point p = data.remove(ptr - 1);
			ptr--;
			return p;
		}
	}

	public Point peek() throws EmptyGenericStackException { // 최상위의 값을 확인하기 위한 것.
		if (ptr <= 0) { // 스택이 비어있을 때
			throw new EmptyGenericStackException();
		}
		Point p = data.get(ptr - 1);
		return p;
	}

	public void clear() {
		ptr = 0;
	}

	public int indexOf(Point x) {
		for (int i = ptr - 1; i >= 0; i--) {
			if (data.get(i).equals(x)) { // x는
				return i;
			}
		}
		return -1;
	}


    //--- 스택의 크기를 반환 ---//
    public int getCapacity() {
        return capacity;
    }

    //--- 스택에 쌓여있는 데이터 갯수를 반환 ---//
    public int size() {
        return ptr;
    }

    //--- 스택이 비어있는가? ---//
    public boolean isEmpty() {
        return ptr <= 0;
    }

    //--- 스택이 가득 찼는가? ---//
    public boolean isFull() {
        return ptr >= capacity;
    }

    //--- 스택 안의 모든 데이터를 바닥 → 정상 순서로 표시 ---//
    public void dump() {
        if (ptr <= 0)
            System.out.println("스택이 비어있습니다.");
        else {
            for (int i = 0; i < ptr; i++)
                System.out.print(data.get(i)+ " ");
            System.out.println();
        }
    }
}
