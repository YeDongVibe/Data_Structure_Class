package Chapter03;

//3장 객체 배열 정렬 - binary search
/*
* Comparator를 사용하는 방법
* MyComparator implements Comparator<>
* MyComparator myComparator = new MyComparator();
* Arrays.sort(array, myComparator);
* Collections.sort(list, myComparator);
*/

import java.util.Arrays;
import java.util.Comparator;

class Fruit {

	String name;
	int price;
	String expire;

	public Fruit(String name, int price, String expire) {
		this.name = name;
		this.price = price;
		this.expire = expire;
	}

	public String toString() {
		return name + " (" + price + "원, " + expire + ")";
	}

	public int getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

}

public class fruitsearch {

	private static void sortData(Fruit[] arr, Comparator<Fruit> cc_price) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (cc_price.compare(arr[i], arr[j]) > 0) {
					Fruit temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}


	static void showData(Fruit[] arr) {

		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + ", ");
		}

	}

	private static int binarySearch(Fruit[] arr, Fruit newFruit, Comparator<Fruit> cc_name) {
		int m;
		int l = 0;
		int r = arr.length - 1;

		while (l <= r) {
			m = (l + r) / 2;

			if (cc_name.compare(arr[m], newFruit) == 0)
				return m;
			else if (cc_name.compare(arr[m], newFruit) < 0)
				l = m + 1;
			else
				r = m - 1;
		}

		return -1;

	}

	public static void main(String[] args) {

		Fruit[] arr = {new Fruit("사과", 200, "2023-5-8"), 
				new Fruit("키위", 500, "2023-6-8"),
				new Fruit("오렌지", 200, "2023-7-8"), 
				new Fruit("바나나", 50, "2023-5-18"), 
				new Fruit("수박", 880, "2023-5-28"),
				new Fruit("체리", 10, "2023-9-8") };
		System.out.println("\n정렬전 객체 배열: ");
		showData(arr);
		Arrays.sort(arr, (a, b) -> a.getPrice() - b.getPrice()); // Fruit에 compareTo()가 있어도 람다식 우선 적용
		System.out.println("\n람다식 정렬(가격)후 객체 배열: ");
		showData(arr);

		Arrays.sort(arr, new Comparator<Fruit>() {
			@Override
			public int compare(Fruit a1, Fruit a2) {
				return a1.getName().compareTo(a2.getName());
			}
		});
		System.out.println("\ncomparator 정렬(이름)후 객체 배열: ");
		showData(arr);

		Comparator<Fruit> cc_name = new Comparator<Fruit>() {// 익명클래스 사용

			@Override
			public int compare(Fruit f1, Fruit f2) {
				// TODO Auto-generated method stub
				return (f1.name.compareTo(f2.name));
			}

		};
		Comparator<Fruit> cc_price = new Comparator<Fruit>() {

			@Override
			public int compare(Fruit f1, Fruit f2) {
				return f1.getPrice() - f2.getPrice();
			}// 익명클래스 사용

		};

		Fruit newFruit = new Fruit("체리", 500, "2023-5-18");
		int result3 = Arrays.binarySearch(arr, newFruit, cc_name);
		System.out.println("\nArrays.binarySearch() 조회결과::" + result3);
		result3 = binarySearch(arr, newFruit, cc_name);
		System.out.println("\nbinarySearch() 조회결과::" + result3);

		sortData(arr, cc_price);
		System.out.println("\ncomparator 정렬(가격)후 객체 배열: ");
		showData(arr);
		result3 = Arrays.binarySearch(arr, newFruit, cc_price);
		System.out.println("\nArrays.binarySearch() 조회결과::" + result3);
		result3 = binarySearch(arr, newFruit, cc_price);
		System.out.println("\nbinarySearch() 조회결과::" + result3);
	}

}
