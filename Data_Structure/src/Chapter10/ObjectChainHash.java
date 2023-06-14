package Chapter10;

//23.3.8 수정 완료 - 객체들의 chain hash 구성
import java.util.Comparator;
//hash node가 student 객체일 때를 구현하는 과제
//체인법에 의한 해시
import java.util.Scanner;

//체인법에 의한 해시

class SimpleObject5 {
	static final int NO = 1;
	static final int NAME = 2;
	String no; // 회원번호
	String name; // 이름

	public SimpleObject5(String sno, String sname) {
		this.no = sno;
		this.name = sname;
	}

	public SimpleObject5() {
		this.no = null;
		this.name = null;
	}

	// --- 문자열 표현을 반환 ---//
	public String toString() {
		return "(" + no + ") " + name;
	}

	// --- 회원번호로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject5> NO_ORDER = new NoOrderComparator();

	private static class NoOrderComparator implements Comparator<SimpleObject5> {
		public int compare(SimpleObject5 d1, SimpleObject5 d2) {
			return d1.no.compareTo(d2.no);
		}
	}

	// --- 이름으로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject5> NAME_ORDER = new NameOrderComparator();

	private static class NameOrderComparator implements Comparator<SimpleObject5> {
		public int compare(SimpleObject5 d1, SimpleObject5 d2) {
			return d1.name.compareTo(d2.name);
		}
	}

	void scanData(String guide, int sw) {
		Scanner stdIn = new Scanner(System.in);
		System.out.println(guide + "할 데이터를 입력하세요: ");
		if ((sw & NO) == NO) {
			System.out.println("번호: ");
			no = stdIn.next();
		}
		if ((sw & NAME) == NAME) {
			System.out.println("이름: ");
			name = stdIn.next();
		}
	}
}

class ChainHash5 {
//--- 해시를 구성하는 노드 ---//
	class Node2 {
		private SimpleObject5 data; // 키값
		private Node2 next; // 뒤쪽 포인터(뒤쪽 노드에 대한 참조)
		// --- 생성자(constructor) ---//

		public Node2(SimpleObject5 s) {
			this.data = s;
			this.next = null;
		}

		Node2(SimpleObject5 s, Node2 p) {
			this.data = s;
			this.next = p;
		}

		Node2() {
			this.data = null;
			this.next = null;
		}
		// --- 키값을 반환 ---//
		/*
		 * Integer getKey() { return data.keyCode(); }
		 */

		// --- 키의 해시값을 반환 ---//
		public int hashCode() {
			int hash = this.data.hashCode();
			hash = 31 * hash;
			hash = hash * hash;
			return hash;
		}
	}

	private int size; // 해시 테이블의 크기
	private Node2[] table; // 해시 테이블

//--- 생성자(constructor) ---//
	public ChainHash5(int capacity) {
		try {
			table = new Node2[capacity];
			this.size = capacity;
		} catch (OutOfMemoryError e) { // 테이블을 생성할 수 없음
			this.size = 0;
		}
	}

//--- 키값이 key인 요소를 검색(데이터를 반환) ---//
	public int search(SimpleObject5 st, Comparator<? super SimpleObject5> c) {
		int hash = Integer.parseInt(st.no) % 13; // 검색할 데이터의 해시값

		Node2 p = table[hash]; // 선택 노드
		System.out.println("hash = " + hash);
		while (p != null) {
			if (c.compare(st, p.data) == 0)
				return 1; // 검색 성공
			p = p.next; // 다음 노드를 선택
		}
		return 0; // 검색 실패
	}

//--- 키값이 key인 데이터를 data의 요소로 추가 ---//
	public int add(SimpleObject5 st, Comparator<? super SimpleObject5> c) {
		System.out.println("st.no = " + st.no);
		int hash = Integer.parseInt(st.no) % 13; // 추가할 데이터의 해시값
		System.out.println("hash 값 = " + hash);
		Node2 p = table[hash]; // 선택 노드

		while (p != null) {
			if (c.compare(st, p.data) == 0) // 키값이 이미 등록됨
				return 1;
			p = p.next; // 다음 노드를 선택
		}
		Node2 temp = new Node2(st, table[hash]);
		table[hash] = temp; // 노드 삽입
		return 0;
	}

//--- 키값이 key인 요소를 삭제 ---//
	public int delete(SimpleObject5 st, Comparator<? super SimpleObject5> c) {
		int hash = Integer.parseInt(st.no) % 13; // 삭제할 데이터의 해시값
		Node2 p = table[hash]; // 선택 노드
		Node2 q = null; // 바로 앞의 선택 노드
		// 구현실습

		while (p != null) {
			if (c.compare(st, p.data) == 0) { // 찾으면
				if (q == null)
					table[hash] = p.next;
				else
					q.next = p.next;
				return 1;
			}
			q = p;
			p = p.next; // 다음 노드를 선택
		}
		return 0; // 찾는 키값이 없음
	}

//--- 해시 테이블을 덤프(dump) ---//
	public void dump() {
		for (int i = 0; i < size; i++) {
			Node2 p = table[i];
			System.out.printf("%02d  ", i);
			while (p != null) {
				System.out.printf("→ (%s : %s)", p.data.no, p.data.name);
				p = p.next;
			}
			System.out.println();
		}
	}
}

public class ObjectChainHash {
	enum Menu {
		Add("삽입"), Delete("삭제"), Search("검색"), Show("출력"), Exit("종료");

		private final String message; // 표시할 문자열

		static Menu MenuAt(int idx) { // 순서가 idx번째인 열거를 반환
			for (Menu m : Menu.values())
				if (m.ordinal() == idx)
					return m;
			return null;
		}

		Menu(String string) { // 생성자(constructor)
			message = string;
		}

		String getMessage() { // 표시할 문자열을 반환
			return message;
		}
	}
		// --- 메뉴 선택 ---//
		static Menu SelectMenu() {
			Scanner sc = new Scanner(System.in);
			int key;
			do {
				for (Menu m : Menu.values()) {
					System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
					if ((m.ordinal() % 3) == 2 && m.ordinal() != Menu.Exit.ordinal())
						System.out.println();
				}
				System.out.print(" : ");
				key = sc.nextInt();
			} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());
			return Menu.MenuAt(key);
		}
	

	public static void main(String[] args) {
		Menu menu;
		Scanner stdIn = new Scanner(System.in);
		ChainHash5 hash = new ChainHash5(15);
		SimpleObject5 data;
		int select = 0, result = 0;
		do {
			switch (menu = SelectMenu()) {
			case Add:
				data = new SimpleObject5();
				data.scanData("삽입", SimpleObject5.NO | SimpleObject5.NAME);
				result = hash.add(data, SimpleObject5.NO_ORDER);
				if (result == 1)
					System.out.println(" 중복 데이터가 존재한다");
				else
					System.out.println(" 입력 처리됨");
				break;
			case Delete:
				// Delete
				data = new SimpleObject5();
				data.scanData("삭제", SimpleObject5.NO);
				result = hash.delete(data, SimpleObject5.NO_ORDER);
				if (result == 1)
					System.out.println(" 삭제 처리");
				else
					System.out.println(" 삭제 데이터가 없음");
				break;
			case Search:
				data = new SimpleObject5();
				data.scanData("검색", SimpleObject5.NO);
				result = hash.search(data, SimpleObject5.NO_ORDER);
				if (result == 1)
					System.out.println(" 검색 데이터가 존재한다");
				else
					System.out.println(" 검색 데이터가 없음");
				break;
			case Show:
				hash.dump();
				break;
			}
		} while (menu != Menu.Exit);
	}
}
