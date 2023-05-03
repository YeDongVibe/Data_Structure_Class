package Chapter02;

public class Stringssss {
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

	public static void main(String[] args) {
		String[] data = { "apple", "grape", "banana", "melon" };

		showData(data);
		sortData(data);
		System.out.println(" ");
		showData(data);

	}

}
