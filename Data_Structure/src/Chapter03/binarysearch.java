package Chapter03;

//이진 검색

import java.util.Arrays;
import java.util.Random;

public class binarysearch {

	private static void inputData(int[] data) {
		// 난수 생성하여 배열에 입력
		Random rand = new Random(System.currentTimeMillis()); // 초기 ceed값
		for (int i = 0; i < data.length; i++) {
			data[i] = rand.nextInt(40); // 10이하 정수 출력
			for(int j = 0; j < i; j++) {
				if(data[i] == data[j]) {
					i--;
					break;
				}
			}
		}

	}

	private static void showData(int[] data) {
		// 배열 출력
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + " ");
		}
	}

	private static void sortData(int[] data) {
		for (int k = 0; k < data.length; k++) {
			for (int l = k + 1; l < data.length; l++) {
				if (data[k] > data[l]) {
					int temp = data[k];
					data[k] = data[l];
					data[l] = temp;
				}
			}
		}

	}

	private static int linearSearch(int[] data, int key) {
		int i = 0;
		while (i < data.length) {
			if (data[i] == key)
				return i;
			i++;
		}
		return -1;
	}

	private static int binarySearch(int[] data, int key) {
		int m = 0;
		int l = 0;
		int r = data.length -1;
		while (l <= r) {
			m = (l + r ) /2;
			if (data[m] <key) l = m + 1;
			else if (data[m] > key) r = m - 1;
			else return l;
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] data = new int[10];
		inputData(data);
		System.out.println("정렬 전 결과");
		showData(data);
		System.out.print("\n정렬 후 결과");
		sortData(data);
		System.out.println();
		for (int num : data) {
			System.out.print(num + " ");
		}
		int key = 33;
		int result = linearSearch(data, key);
		System.out.println("\nlinearSearch(): result = " + result);

		key = 39;
		result = binarySearch(data, key);
		System.out.println("\nbinarySearch(): result = " + result);
		int idx = Arrays.binarySearch(data, key);
		System.out.println("\nArrays.binarySearch(): result = " + result);

	}

}
