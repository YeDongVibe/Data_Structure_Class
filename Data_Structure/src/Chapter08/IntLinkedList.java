package Chapter08;
//단순한 linked list에서 insert, delete하는 알고리즘을 코딩: 1단계

import java.util.Random;
import java.util.Scanner;

class Node1 {
	int data;
	Node1 link;

	public Node1(int element) {
		data = element;
		link = null;
	}
}

class LinkedList1 {
	Node1 first;

	public LinkedList1() {
		first = null;
	}

	public int Delete(int element) {// delete the element 처음, 중간, 끝 순간에 삭제
		Node1 p = first;
		Node1 q = null;
		while(p != null) {
			if(element == p.data) {
			if(p == first) {
				first = p.link;
				return element;
			}
			else {
				q.link = p.link;
				return element;
			}
		}
			else { //ele != p.data
				q = p;
				p = p.link;
			}
			
		}
		return 0;
	}

	public void Show() { // 전체 리스트를 순서대로 출력한다.
		Node1 p = first;
		while (p != null) {
			System.out.print("show : " + p.data + " \n");
			p = p.link;
		}
	}

	public void Add(int element) {// 임의 값을 삽입할 때 리스트가 오름차순으로 정렬이 되도록 한다
		Node1 nd = new Node1(element);
		Node1 p = first;
		Node1 q = null;
		if (p == null) {
			first = nd;
			return;
		}
		while (p != null) {
			if (element < p.data) {
				if (p == first) { // p가 첫번째일때
					nd.link = p;
					first = nd;
					return;
				} else { // p가 첫번째가 아닐때
					nd.link = p;
					q.link = nd;
					return;
				}
			} else if (element > p.data) {
				q = p;
				p = p.link;
				if (p == null) {
					q.link = nd;
					return;
				}
			}
		}

	}

	public boolean Search(int data) { // 전체 리스트를 순서대로 출력한다.
		Node1 p = first;
		while (p != null) {
			if (data == (p.data)) {
				return true;
			}
			p = p.link;
		}
		return false;
	}
}

public class IntLinkedList {
	enum Menu {
		Add("삽입"), Delete("삭제"), Show("인쇄"), Search("검색"), Exit("종료");

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
		Menu menu; // 메뉴
		Random rand = new Random();
		System.out.println("Linked List");
		LinkedList1 l = new LinkedList1();
		Scanner sc = new Scanner(System.in);
		int data = 0;
		System.out.println("inserted");
		l.Show();
		do {
			switch (menu = SelectMenu()) {
			case Add: // 머리노드 삽입
				data = rand.nextInt(20);
				// double d = Math.random();
				// data = (int) (d * 50);
				l.Add(data);
				break;
			case Delete: // 머리 노드 삭제
				data = sc.nextInt();
				int num = l.Delete(data);
				System.out.println("삭제된 데이터는 " + num);
				break;
			case Show: // 꼬리 노드 삭제
				l.Show();
				break;
			case Search: // 회원 번호 검색
				int n = sc.nextInt();
				boolean result = l.Search(n);
				if (result == false)
					System.out.println("검색 값 = " + n + "데이터가 없습니다.");
				else
					System.out.println("검색 값 = " + n + "데이터가 존재합니다.");
				break;
			case Exit: // 꼬리 노드 삭제
				break;
			}
		} while (menu != Menu.Exit);
		sc.close();
	}
}
