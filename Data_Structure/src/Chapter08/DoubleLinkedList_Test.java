package Chapter08;

/*
 * 정수 리스트 > 객체 리스트 > 
 */
import java.util.Comparator;
import java.util.Scanner;

class SimpleObject2 {
	static final int NO = 1; // 번호를 읽어 들일까요?
	static final int NAME = 2; // 이름을 읽어 들일까요?
	String no; // 회원번호
	String name; // 이름

	public SimpleObject2(String sno, String sname) {
		this.no = sno;
		this.name = sname;
	}

	// --- 문자열 표현을 반환 ---//
	public String toString() {
		return "(" + no + ") " + name;
	}

	// --- 데이터를 읽어 들임 ---//
	void scanData(String guide, int sw) {
		Scanner sc = new Scanner(System.in);
		System.out.println(guide + "할 데이터를 입력하세요." + sw);

		if ((sw & NO) == NO) { // & 는 bit 연산자임
			System.out.print("번호: ");
			no = sc.next();
		}
		if ((sw & NAME) == NAME) {
			System.out.print("이름: ");
			name = sc.next();
		}
	}

	// --- 회원번호로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject2> NO_ORDER = new NoOrderComparator();

	private static class NoOrderComparator implements Comparator<SimpleObject2> {
		public int compare(SimpleObject2 d1, SimpleObject2 d2) {
			if (d2 == null) {
				return -1; // 두 번째 객체가 null이면 첫 번째 객체가 더 작다고 가정
			}
			return (d1.no.compareTo(d2.no) > 0) ? 1 : ((d1.no.compareTo(d2.no) < 0)) ? -1 : 0;
		}
	}

	// --- 이름으로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject2> NAME_ORDER = new NameOrderComparator();

	private static class NameOrderComparator implements Comparator<SimpleObject2> {
		public int compare(SimpleObject2 d1, SimpleObject2 d2) {
			return (d1.name.compareTo(d2.name) > 0) ? 1 : ((d1.name.compareTo(d2.name) < 0)) ? -1 : 0;
		}
	}
}

class Node4 {
	SimpleObject2 data; // 데이터
	Node4 llink; // 좌측포인터(앞쪽 노드에 대한 참조)
	Node4 rlink; // 우측포인터(뒤쪽 노드에 대한 참조)

	// --- 생성자(constructor) ---//
	Node4(SimpleObject2 so) {
		this.data = so;
		llink = rlink = this;
	}

	Node4() {
		this.data = null;
		llink = rlink = this;
	}

	Node4(String sno, String sname) {
		data = new SimpleObject2(sno, sname);
		llink = rlink = this;
	}

	public int compareNode(Node4 n2) {
		SimpleObject2 so1 = this.data;
		if (SimpleObject2.NO_ORDER.compare(so1, n2.data) < 0)
			return -1;
		else if (SimpleObject2.NO_ORDER.compare(so1, n2.data) > 0)
			return 1;
		else
			return 0;
	}
}

class DoubledLinkedList2 {
	private Node4 first; // 머리 포인터(참조하는 곳은 더미노드)

// --- 생성자(constructor) ---//
	public DoubledLinkedList2() {
		first = new Node4(); // dummy(first) 노드를 생성

	}

// --- 리스트가 비어있는가? ---//
	public boolean isEmpty() {
		return first.rlink == first;
	}

// --- 노드를 검색 ---//
	public boolean search(SimpleObject2 obj, Comparator<? super SimpleObject2> c) {
		Node4 p = first;
		while (p != null) {
			if (c.compare(obj, p.data) == 0) {
				System.out.println("검색 성공 = " + p.data.toString());
				return true;
			}
			p = p.rlink;
		}
		return false;
	}

// --- 전체 노드 표시 ---//
	public void show() {
		Node4 p = first.rlink;
		if (isEmpty()) { // 리스트가 비어있을 경우
			System.out.println("데이터가 없습니다.");
			return;
		}
		while (p != first) {
			System.out.println("show: " + p.data + "\n");
			p = p.rlink;
		}
	}

// --- 올림차순으로 정렬이 되도록 insert ---//
	public void add(SimpleObject2 obj, Comparator<? super SimpleObject2> c) {
		Node4 nd = new Node4(obj);
		Node4 p = first.rlink;
		Node4 q = null;
		if (p == first) { // 리스트가 비어있을 경우
			nd.rlink = first;
			first.rlink = nd;
			return;
		}
		while (p != first) {
			if (c.compare(obj, p.data) < 0) { // obj가 p.data보다 작을 경우
				nd.rlink = p;
				if (q == null) {
					first.rlink = nd;
				} else {
					q.rlink = nd;
				}
				return;
			}
			q = p;
			p = p.rlink;
		}
		q.rlink = nd; // 리스트의 마지막에 노드 추가
		nd.rlink = first;

	}

// --- list에 삭제할 데이터가 있으면 해당 노드를 삭제 ---//
	public void delete(SimpleObject2 obj, Comparator<? super SimpleObject2> c) {
		Node4 p = first.rlink;
		Node4 q = null;
		if (isEmpty()) { // 리스트가 비어있을 경우
			System.out.println("데이터가 없습니다.");
			return;
		}
		while (p != first) {
			if (c.compare(obj, p.data) == 0) { // obj와 p.data가 같을 경우
				if (q == null) { // 첫 번째 노드를 삭제할 경우
					first.rlink = p.rlink;
				} else {
					q.rlink = p.rlink;
				}
				return;
			}
			q = p;
			p = p.rlink;
		}
		System.out.println("데이터가 존재하지 않습니다.");
	}

	public DoubledLinkedList2 merge(DoubledLinkedList2 lst2) {
		
	}
}

public class DoubleLinkedList_Test {
	enum Menu {
		Add("삽입"), Delete("삭제"), Show("인쇄"), Search("검색"), Merge("병합"), Exit("종료");

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
		Scanner sc1 = new Scanner(System.in);
		int key;
		do {
			for (Menu m : Menu.values()) {
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
				if ((m.ordinal() % 3) == 2 && m.ordinal() != Menu.Exit.ordinal())
					System.out.println();
			}
			System.out.print(" : ");
			key = sc1.nextInt();
		} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());
		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Menu menu; // 메뉴
		Scanner sc2 = new Scanner(System.in);
		System.out.println("Linked List");
		DoubledLinkedList2 lst1 = new DoubledLinkedList2(), lst2 = new DoubledLinkedList2(),
				lst3 = new DoubledLinkedList2();
		String sno1 = null, sname1 = null;
		SimpleObject2 so;
		boolean result = false;
		do {
			switch (menu = SelectMenu()) {
			case Add: // 머리노드 삽입
				System.out.println(" 회원번호: ");
				sno1 = sc2.next();
				System.out.println(" 회원이름: ");
				sname1 = sc2.next();
				so = new SimpleObject2(sno1, sname1);
				lst1.add(so, SimpleObject2.NO_ORDER);
				break;
			case Delete: // 머리 노드 삭제
				System.out.println(" 삭제할 회원번호: ");
				sno1 = sc2.next();
				System.out.println(" 삭제할 회원이름: ");
				sname1 = sc2.next();
				so = new SimpleObject2(sno1, sname1);
				lst1.delete(so, SimpleObject2.NO_ORDER);
				break;
			case Show: // 꼬리 노드 삭제
				lst1.show();
				break;
			case Search: // 회원 번호 검색
				System.out.println(" 검색할 회원번호: ");
				sno1 = sc2.next();
				System.out.println(" 검색할 회원이름: ");
				sname1 = sc2.next();
				so = new SimpleObject2(sno1, sname1);
				result = lst1.search(so, SimpleObject2.NO_ORDER);
				if (result == false)
					System.out.println("검색 값 = " + so + "데이터가 없습니다.");
				else
					System.out.println("검색 값 = " + so + "데이터가 존재합니다.");
				break;
			case Merge:
				for (int i = 0; i < 5; i++) {
					System.out.println(" 두번째 리스트 회원번호: ");
					sno1 = sc2.next();
					System.out.println(" 두번째 리스트 회원이름: ");
					sname1 = sc2.next();
					so = new SimpleObject2(sno1, sname1);
					lst2.add(so, SimpleObject2.NO_ORDER);
					lst3 = lst1.merge(lst2);
					System.out.println("list1: ");
					lst1.show();
					System.out.println("list2: ");
					lst2.show();
					System.out.println("list3: ");
					lst3.show();
				}
				break;
			case Exit: // 꼬리 노드 삭제
				break;
			}
		} while (menu != Menu.Exit);
	}
}
