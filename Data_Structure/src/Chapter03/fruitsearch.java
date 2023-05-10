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

	//Static의 의미 :  객체가 여러개 만들어져도 오직 하나의 값만 ? : 공유 변수
	private static int binarySearch(Fruit[] arr, Fruit newFruit, Comparator<Fruit> cc_name) { // 타입 
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

		Comparator<Fruit> cc_name = new Comparator<Fruit>() {// 익명클래스 사용 //이름을 이용해서 비교하는 Comparator

			@Override
			public int compare(Fruit f1, Fruit f2) {
				// TODO Auto-generated method stub
				return (f1.name.compareTo(f2.name));
			}

		};
		
		Comparator<Fruit> cc_price = new Comparator<Fruit>() { // 익명클래스에서 생선된 객체를 cc_pirce가 가지고있고, 이로 구현된 것이 compare임.

			@Override
			public int compare(Fruit f1, Fruit f2) {
				return f1.getPrice() - f2.getPrice();
			}// 익명클래스 사용

		};
		
		Comparator<Fruit> cc_expire = new Comparator<Fruit>() {
			
			@Override
			public int compare(Fruit f1, Fruit f2) {
				// TODO Auto-generated method stub
				return (f1.expire.compareTo(f2.expire));
			}
		};
		
		Fruit newFruit = new Fruit("체리", 500, "2023-5-18");
		int result3 = Arrays.binarySearch(arr, newFruit, cc_name); //자바에서 제공하는 binarySearch 사용 //main내에서 만들어 사용
		System.out.println("\nArrays.binarySearch() 조회결과::" + result3);
		result3 = binarySearch(arr, newFruit, cc_name);//내가 만드는 binarySearch 사용
		System.out.println("\nbinarySearch() 조회결과::" + result3);

		sortData(arr, cc_price);
		System.out.println("\ncomparator 정렬(가격)후 객체 배열: ");
		showData(arr);
		result3 = Arrays.binarySearch(arr, newFruit, cc_price);
		System.out.println("\nArrays.binarySearch() 조회결과::" + result3);
		result3 = binarySearch(arr, newFruit, cc_price);
		System.out.println("\nbinarySearch() 조회결과::" + result3);
	}
	
//	int binarySearch(Fruit [] arr, Fruit newFruit, Comparator<? super Fruit> cc) {
//		//이진 탐색 코드 가져와서 Fruit 버전으로 변경
//		//if 문에서 비교할 때
//		Fruit d1 = arr[0];
//		Fruit d2 = arr[1];
//		if(cc.compare(d1, d2) > 0) {
//			
//		}
//	}

}
