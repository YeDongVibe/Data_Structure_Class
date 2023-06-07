package Chapter09;

/*
 * 23.6.7 3회차 실습 코드
 */
import java.util.Random;
import java.util.Scanner;

//정수를 저정하는 이진트리 만들기 실습
class TreeNode {
	TreeNode LeftChild;
	int data;
	TreeNode RightChild;

	public TreeNode(int x) {
		LeftChild = RightChild = null;
		data = x;
	}
}

class Tree {
	TreeNode root; // treeNode가 루트로 잡고 진행.

	Tree() {
		root = null; // root를 null로 지정(기본값)
	}

	TreeNode inorderSucc(TreeNode current) {
		TreeNode temp = current.RightChild;// right의 head
		if (current.RightChild != null)
			while (temp.LeftChild != null)// temp의 자식이 null이 될때까지
				temp = temp.LeftChild;// temp의 자식이 null일 경우 해당 temp가 successor
		return temp;
	}

	boolean isLeafNode(TreeNode current) {
		if (current.LeftChild == null && current.RightChild == null)
			return true;
		else
			return false;
	}

	void inorder() {// inorder overloading
		inorder(root);
	}

	void preorder() {
		preorder(root);
	}

	void postorder() {
		postorder(root);
	}

	// inorder, preorder, postorder : recursive로 구현
	void inorder(TreeNode CurrentNode) { // currentNode : main node(head node)
		if (CurrentNode != null) {
			inorder(CurrentNode.LeftChild); // 왼쪽
			System.out.print(" " + CurrentNode.data); // 메인 노드
			inorder(CurrentNode.RightChild); // 오른쪽
		}
	}

	void preorder(TreeNode CurrentNode) {
		if (CurrentNode != null) {
			System.out.print(CurrentNode.data + " ");// 메인 노드
			preorder(CurrentNode.LeftChild);// 왼쪽
			preorder(CurrentNode.RightChild);// 오른쪽
		}
	}

	void postorder(TreeNode CurrentNode) {
		if (CurrentNode != null) {
			postorder(CurrentNode.LeftChild);// 왼쪽
			postorder(CurrentNode.RightChild);// 오른쪽
			System.out.print(CurrentNode.data + " ");// 메인노드
		}
	}

	boolean insert(int x) {// binary search tree를 만드는 입력 => A + B * C을 tree로 만드는 방법: 입력 해결하는 알고리즘 작성 방법을
							// 설계하여 구현
		TreeNode p = root;
		TreeNode temp = new TreeNode(x);

		if (p == null) { // root가 null인 경우
			root = temp;
			return true;
		}
		while (p != null) { // root에 data가 있을 때
			if (x == p.data) {
				return false;
			} else if (x < p.data) { // p.data보다 x가 작으면 왼쪽으로 삽입
				if (p.LeftChild == null) {
					p.LeftChild = temp;
					return true;
				}
				p = p.LeftChild;
			} else { // p.data보다 x가 크면 오른쪽으로 삽입
				if (p.RightChild == null) {
					p.RightChild = temp;
					return true;
				}
				p = p.RightChild;
			}
		}
		return false;

	}

	boolean delete(int num) {// inorderSucc가 필요
		// TreeNode tempP = inorderSucc(root); // 부모 노드
		TreeNode p = root;
		TreeNode q = null;

		if (p == null) {
			return false;
		}

		while (p != null) {
			if (p.data == num) {// 삭제하는 작업
				if (isLeafNode(p)) { // leaf Node(자식이 없는 경우) 삭제
					if (q == null) {
						root = null;
						return true;
					} else if (p == q.LeftChild) {
						q.LeftChild = null;
						return true;
					} else {
						q.RightChild = null;
						return true;
					}
				} else { // 삭제할 노드가 리프 노드가 아닌 경우 / inorder이용
					TreeNode su = inorderSucc(p);
					if (su == null) {
						return false;
					}
					delete(su.data); // 후속자 노드를 재귀적으로 삭제
					p.data = su.data; // 삭제할 노드의 데이터를 후속자 노드의 데이터로 대체
					return true;
				}
			} else if (num < p.data) {// 트리를 따라가기
				q = p;
				p = p.LeftChild;
			} else {
				q = p;
				p = p.RightChild;
			}
		}
		return false; // 삭제할 노드가 없는 경우
	}

	boolean search(int num) { // num : 찾는 값
		TreeNode p = root;
		while (p != null) {
			if (num == p.data) {
				return true;
			} else if (num < p.data) { // 찾는 값이 p.data보다 작으면
				p = p.LeftChild;
			} else { // 찾는 값이 p.data보다 크면
				p = p.RightChild;
			}
		}
		return false;

	}
}

public class BinaryTreeInt {
	enum Menu {
		Add("삽입"), Delete("삭제"), Search("검색"), InorderPrint("순차출력"), Exit("종료");

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
		Scanner stdIn = new Scanner(System.in);
		int key;
		do {
			for (Menu m : Menu.values())
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
			System.out.print(" : ");
			key = stdIn.nextInt();
		} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());

		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Random rand = new Random();
		Scanner stdIn = new Scanner(System.in);
		Tree t = new Tree(); // t는 root라는 변수가 있는데 root의 값은 null
		Menu menu; // 메뉴
		int count = 0;
		int num;
		boolean result;
		do {
			switch (menu = SelectMenu()) {
			case Add: // 노드 삽입
				System.out.println("The number of items = ");
				count = stdIn.nextInt();
				int[] input = new int[10];
				for (int ix = 0; ix < count; ix++) {
					input[ix] = rand.nextInt(20);
				}
				for (int i = 0; i < count; i++) {
					if (t.insert(input[i]) == false)
						System.out.println("Insert Duplicated data");
				}
				break;

			case Delete: // 노드 삭제
				System.out.println("삭제할 데이터:: ");
				num = stdIn.nextInt();
				if (t.delete(num) == true)
					System.out.println("삭제 데이터 = " + num + " 성공");
				else
					System.out.println("삭제 실패");
				;
				break;

			case Search: // 노드 검색
				System.out.println("검색할 데이터:: ");

				num = stdIn.nextInt();
				result = t.search(num);
				if (result == true)
					System.out.println(" 데이터 = " + num + " 이(가) 존재합니다.");
				else
					System.out.println("해당 데이터가 없습니다.");
				break;

			case InorderPrint: // 전체 노드를 키값의 오름차순으로 표시
				t.inorder(); // t.inorder(root)를 부름
				System.out.println();
				break;
			}
		} while (menu != Menu.Exit);
	}
}
