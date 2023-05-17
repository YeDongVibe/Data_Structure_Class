package Chapter05;

import java.util.Scanner;

public class Factorial {
	static int factorial(int n) {
//		if (n >0) {
//			return n * factorial(n-1);
//		}
//		else {
//			return 1;
//		}
//		
		return (n > 0) ? n * factorial(n-1) : 1;
		//n = 5이면 0보다 크기때문에 n*factorial(n-1)를 실행.
		//이후 n으로 4가 들어가서 반복.
		
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("정수를 입력하시라우 : ");
		int x = sc.nextInt();
		
		System.out.println(x + "의 팩토리얼은 " + factorial(x) + " 입니다.");
		}
}
