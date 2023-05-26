package Chapter06;
// 단순 삽입 정렬

import java.util.Random;

class InsertionSort {
	// --- 단순 삽입 정렬 ---//
	static void insertionSort(int[] a, int n) {
		int count = 0;
		for (int i = 1; i < n; i++) {
			int j;
			int tmp = a[i];
			for (j = i; j > 0 && a[j - 1] > tmp; j--) {
				a[j] = a[j - 1];
				count++;
			}
			a[j] = tmp;
		}
		System.out.print("정렬 횟수(count) : " + count);
	}

	public static void main(String[] args) {
		Random random = new Random();
		System.out.println("단순 삽입 정렬");

		int nx = 100;
		int[] x = new int[nx];

		for (int i = 0; i < nx; i++) {
			x[i] = random.nextInt(100); // 랜덤하게 수를 배치하여 사용
			System.out.print(" " + x[i]);
		}
		System.out.println();
		
		insertionSort(x, nx); // 배열 x를 단순교환정렬
		
		System.out.println();
		System.out.println("오름차순으로 정렬했습니다.");
		for (int i = 0; i < nx; i++)
			System.out.print(" " + x[i]);

	}
}
