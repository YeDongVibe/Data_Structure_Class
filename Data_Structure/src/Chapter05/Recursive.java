package Chapter05;


//재귀에 대한 이해를 돕는 순수 재귀 메서드(재귀를 제거)

import java.util.Scanner;

public class Recursive {
	// --- 재귀를 제거한 recur ---//
	static void recur(int n) {
		IntStack s = new IntStack(n);

		while (true) {
			if (n > 0) {
				s.push(n); // n 값을 푸시
				n = n - 1;
				continue;
			}
			if (s.isEmpty() != true) { // 스택이 비어 있지 않으면
				n = s.pop(); // 저장하고 있던 값을 n에 팝
				System.out.println(n);
				n = n - 2;
				continue;
			}
			break;
		}
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.print("정수를 입력하세요 : ");
		int x = stdIn.nextInt();

		recur(x);
	}
}