package Chapter03;

import java.util.Arrays;

class PhyscData implements Comparable<PhyscData> {
	String name;
	int height;
	double vision;

	public PhyscData(String name, int height, double vision) {
		this.name = name;
		this.height = height;
		this.vision = vision;
	}

	public int compareTo(PhyscData cp) {
		if (this.name.compareTo(cp.name) == 0) {
			if (this.height == cp.height) {
				if (this.vision == cp.vision) {
					return 0;
				} else if (this.vision < cp.vision) {
					return -1;
				} else {
					return 1;
				}
			}

		} else if (this.name.compareTo(cp.name) < 0) {
			return -1;
		} else {
			return 1;
		}
		return 0;
	}

	@Override
	public String toString() {
		return name + " (" + height + "cm, " + vision + ")";
	}

}

public class gildongsearch {

	static void sortData(PhyscData[] data) {

		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data.length; j++) {
				if (data[i].compareTo(data[j]) < 0) {
					PhyscData temp = data[i];
					data[i] = data[j];
					data[j] = temp;
				}

			}
		}

	}

	private static int linearSearch(PhyscData[] data, PhyscData key) {
		int i = 0;
		while (i < data.length) {
			if (data[i] == key)
				return i;
			i++;
		}
		return -1;
	}

	private static int binarySearch(PhyscData[] data, PhyscData key) {
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

	public static void main(String[] args) {
		PhyscData[] data = {
				new PhyscData("홍길동", 162, 0.3),
				new PhyscData("홍동", 164, 1.3),
				new PhyscData("홍길", 152, 0.7),
				new PhyscData("김홍길동", 172, 0.3),
				new PhyscData("길동", 182, 0.6),
				new PhyscData("길동", 167, 0.2),
				new PhyscData("길동", 167, 0.5),
		};
		
		System.out.println("정렬 전 결과");
		showData(data);
		sortData(data);
		System.out.println("\n정렬 후 결과");
		showData(data);
		PhyscData key = new PhyscData("길동", 167, 0.2);
		int result = linearSearch(data, key);
		System.out.println("\nlinearSearch(): result = " + result);
		key = new PhyscData("길동", 167, 0.5);
		
		result = binarySearch(data, key);
		System.out.println("\nbinarySearch(): result = " + result);
		int idx = Arrays.binarySearch(data, key);
		System.out.println("\nArrays.binarySearch(): result = " + result);
	}
	static void showData(PhyscData[]arr) {
		System.out.println();
		for (PhyscData fruit: arr) {
			System.out.print(fruit+" ");
		}
		System.out.println();
	}

	
}

