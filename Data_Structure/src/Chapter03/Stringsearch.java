package Chapter03;

import java.util.Arrays;
import java.util.Random;

public class Stringsearch {

	static void showData(String[] data) {
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + ", ");
		}

	}

	static void sortData(String[] data) {
		for (int k = 0; k < data.length; k++) {
			for (int l = 0; l < data.length; l++) {
				if (data[k].compareTo(data[l]) < 0) {
					String temp = data[k];
					data[k] = data[l];
					data[l] = temp;
				}
			}
		}

	}

	private static int binarySearch(String[] data, String key) {
		int m;
		int l = 0;
		int r = data.length - 1;

		while (l <= r) {
			m = (l + r) / 2;

			if (data[m].compareTo(key) == 0)
				return m;
			else if (data[m].compareTo(key) < 0)
				l = m + 1;
			else
				r = m - 1;
		}

		return -1;

	}

	private static int linearSearch(String[] data, String key) {
		int i = 0;
		while (i < data.length) {
			if (data[i] == key)
				return i;
			i++;
		}
		return -1;
	}

	public static void main(String[] args) {
		String[] data = { "apple", "grape", "persimmon", "감", "배", "사과", "포도", "pear", "blueberry", "strawberry",
				"melon", "oriental melon" };

		System.out.println("정렬 전 결과");
		showData(data);
		sortData(data);
		System.out.println("\n정렬 후 결과");
		showData(data);

		String key = "감";
		int result = linearSearch(data, key);
		System.out.println("\nlinearSearch(): result = " + result);

		key = "배";
		result = binarySearch(data, key);
		System.out.println("\nbinarySearch(): result = " + result);
		int idx = Arrays.binarySearch(data, key);
		System.out.println("\nArrays.binarySearch(): result = " + result);

	}

}
