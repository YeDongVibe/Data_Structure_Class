package Chapter10;
import java.util.Scanner;

//체인법에 의한 해시
//--- 해시를 구성하는 노드 ---//
class Node {
    int key;                 // 키값(정수)
    Node next;        // 뒤쪽 포인터(뒤쪽 노드에 대한 참조)

    //--- 생성자(constructor) ---//
    Node(int key) {
        this.key  = key;
        this.next = null;
    }
    Node(int key, Node p) {
        this.key  = key;
        this.next = p;
    }
    //--- 키값을 반환 ---//
    int getKey() {
        return key;
    }

    //--- 키의 해시값을 반환 ---//
    /**key의 hash값을 반환**/
    public int hashCode() {
    	int hash = 11;
    	hash = 31* hash * key;
    	hash = hash * hash;
        return hash;
    }
}
class SimpleChainHash {
 private int    size;              // 해시 테이블의 크기
 private Node[] table;        // 해시 테이블

 //--- 생성자(constructor) ---//
 public SimpleChainHash(int capacity) {
     try {
         table = new Node[capacity];
         this.size = capacity;
     } catch (OutOfMemoryError e) {        // 테이블을 생성할 수 없음
         this.size = 0;
     }
 }

 //--- 해시값을 구함 ---//
 public int hashValue(int key) {
 	int hash = 11;
 	hash = 31* hash * key;
 	hash = hash * hash;
     return hash;
 

 }

 //--- 키값이 key인 요소를 검색(데이터를 반환) ---//
 public int search(int key) {
     int hash = hashValue(key) % 10;            // 검색할 데이터의 해시값
 	System.out.println("hashvalue = " + hash);
     Node p = table[hash];            // 선택 노드

     while (p != null) {
         if (p.getKey() == key)
             return 1;                // 검색 성공
         p = p.next;                             // 다음 노드를 선택
     }
     return 0;                                // 검색 실패
 }

 //--- 키값이 key인 데이터를 data의 요소로 추가 ---//
 public int add(int key) {
     int hash = hashValue(key) % 10;            // 추가할 데이터의 해시값
 	System.out.println("hashvalue = " + hash);
     Node p = table[hash];            // 선택 노드

     while (p != null) {
         if (p.getKey() == key)       // 키값이 이미 등록됨
             return 0;
         p = p.next;                       // 다음 노드를 선택
     }
     Node temp = new Node(key, table[hash]);
     table[hash] = temp;                   // 노드 삽입
     return 1;
 }

 //--- 키값이 key인 요소를 삭제 ---//
 public int delete(int key) {
     int hash = hashValue(key) % 10;            // 삭제할 데이터의 해시값
     Node p = table[hash];            // 선택 노드
     Node pp = null;                  // 바로 앞의 선택 노드

     while (p != null) {
         if (p.getKey()==key) {    //찾으면
             if (pp == null)
                 table[hash] = p.next;
             else
                 pp.next = p.next;
             return 1;
         }
         pp = p;
         p = p.next;                       // 다음 노드를 선택
     }
     return 0;                             // 찾는 키값이 없음
 }

 //--- 해시 테이블을 덤프(dump) ---//
 public void dump() {
     for (int i = 0; i < size; i++) {
         Node p = table[i];
         System.out.printf("%02d  ", i);
         while (p != null) {
             System.out.printf("→ %s ", p.getKey());
             p = p.next;
         }
         System.out.println();
     }
 }
}
public class IntChainHash {
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
	

//체인법에 의한 해시 사용 예
 public static void main(String[] args) {
	 	Menu menu;
		SimpleChainHash hash = new SimpleChainHash(11);
		Scanner stdIn = new Scanner(System.in);
		int select = 0, result = 0, val = 0;
		final int count = 15;
		do {
			switch (menu = SelectMenu()) {
			case Add:

				int[] input = new int[count];
				for (int ix = 0; ix < count; ix++) {
					double d = Math.random();
					input[ix] = (int) (d * 20);
					System.out.print(" " + input[ix]);
				}
				for (int i = 0; i < count; i++) {
					if ((hash.add(input[i])) == 0)
						System.out.println("Insert Duplicated data");
				}
				break;
			case Delete:		
				// Delete
				System.out.println("Search Value:: ");
				val = stdIn.nextInt();
				result = hash.delete(val);
				if (result == 1)
					System.out.println(" 검색 데이터가 존재한다");
				else
					System.out.println(" 검색 데이터가 없음");
				System.out.println();
				break;
			case Search:	
				System.out.println("Search Value:: ");
				val = stdIn.nextInt();
				result = hash.search(val);
				if (result == 1)
					System.out.println(" 검색 데이터가 존재한다");
				else
					System.out.println(" 검색 데이터가 없음");
				System.out.println();
				break;
		
			case Show:
				hash.dump();
			break;
		}
	} while (menu != Menu.Exit);
		
	}
}
