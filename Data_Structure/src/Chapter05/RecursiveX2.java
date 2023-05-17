package Chapter05;

import java.util.Scanner;

//stack과 같은 원리로 가장 최상단의 정보를 밖으로 내보냄.
public class RecursiveX2 {
	static void recur(int n) {
		if (n > 0) {
			System.out.println("recur1(" + n + " - 1) : ");
			recur (n-1);

			System.out.println("n : " + n);
			
			System.out.println("recur2(" + n + " - 2) : ");
			recur(n-2);

			System.out.println();
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("정수를 입력하세요구르트 : ");
		int x = sc.nextInt();
		
		recur(x);

	}

}
