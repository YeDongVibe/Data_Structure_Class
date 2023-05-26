package Chapter06;

import java.util.Random;

public class Sort {
	// --- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
	static void swap(int[] a, int idx1, int idx2) {
		int t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
	}
	

	// --- 버블 정렬 ---//
	static void bubbleSort(int[] a, int n) {
		int count = 0; // 비교 횟수 세기 위한 변수 선언
		for (int i = 0; i < n - 1; i++) {
			for (int j = n - 1; j > i; j--) {
				count++;
				if (a[j - 1] > a[j])
					swap(a, j - 1, j);
			}
		}
		System.out.println("비교 횟수(count) : " + count);
	}

	// --- 버블 정렬 ---//
	static void bubbleSort2(int[] a, int n) {
		int count = 0; // 비교 횟수 세기 위한 변수 선언
		for (int i = 0; i < n - 1; i++) {
			int exchg = 0;
			for (int j = n - 1; j > i; j--) {
				count++;
				if (a[j - 1] > a[j]) {
					swap(a, j - 1, j);
					exchg++;
				}
			}
			if (exchg == 0) {
				break;
			}
		}
		System.out.println("비교 횟수(count) : " + count);
	}

	static void bubbleSort3(int[] a, int n) {
		int count = 0; // 비교 횟수 세기 위한 변수 선언
		int k = 0; // a[k]보다 앞쪽은 정렬을 마친 상태
		while (k < n - 1) {
			int last = n - 1; // 마지막으로 요소를 교환 한 위치

			for (int j = n - 1; j > k; j--) {
				count++;
				if (a[j - 1] > a[j]) {
					swap(a, j - 1, j);
					last = j;
				}
			}
			k = last;
		}
		System.out.println("비교 횟수(count) : " + count);
	}

	// --- 단순 삽입 정렬 ---//
	static void insertionSort(int[] a, int n) {
		int count = 0;
		for (int i = 1; i < n; i++) {
			int j;
			int tmp = a[i];
			for (j = i; j > 0 && a[j - 1] > tmp; j--) {
				a[j] = a[j - 1];

			}
			count++;
			a[j] = tmp;
		}
		System.out.print("정렬 횟수(count) : " + count);
	}
	
	// --- 단순 선택 정렬 ---//
	static void selectionSort(int[] a, int n) {
		int count = 0;
		for (int i = 0; i < n - 1; i++) {
			int min = i; // 아직 정렬되지 않은 부분에서 가장 작은 요소의 인덱스를 저장
			for (int j = i + 1; j < n; j++) {
				count++;
				if (a[j] < a[min]) {
					min = j;
				}
			}
			swap(a, i, min); // 아직 정렬되지 않은 부분의 첫 요소와 가장 작은 요소를 교환
		}
		System.out.println("정렬 횟수(count) : " + count);
	}

	
	 //--- 셸 정렬 ---//
    static void shellSort(int[] a, int n) {
    	int count = 0;
        for (int h = n / 2; h > 0; h /= 2)
            for (int i = h; i < n; i++) {
                int j;
                int tmp = a[i];
                for (j = i - h; j >= 0 && a[j] > tmp; j -= h) {
                    a[j + h] = a[j];
                count++;
                }
                a[j + h] = tmp;
            }
        System.out.println("비교 횟수(count) : " + count);
    }
    
	public static void main(String[] args) {
		Random random = new Random();


		int nx = 100;
		int[] x = new int[nx];

		for (int i = 0; i < nx; i++) {
			x[i] = random.nextInt(100); // 랜덤하게 수를 배치하여 사용
		}
		
		System.out.println("오름차순으로 정렬했습니다.");
		for (int i = 0; i < nx; i++) {
			System.out.print(" " + x[i]);
		}

		System.out.println();
		

		System.out.println("버블 정렬1");
		bubbleSort(x, nx); // 배열 x를 단순교환정렬
		System.out.println();


		System.out.println("버블 정렬2");
		bubbleSort2(x, nx);
		System.out.println();

		System.out.println("버블 정렬3");
		bubbleSort3(x, nx);
		System.out.println();

		System.out.println("삽입 정렬");
		insertionSort(x, nx);
		System.out.println();

		System.out.println("선택 정렬");
		selectionSort(x, nx);
		System.out.println();

		System.out.println("쉘 정렬");
		shellSort(x, nx);
		System.out.println();

		
	}
}
