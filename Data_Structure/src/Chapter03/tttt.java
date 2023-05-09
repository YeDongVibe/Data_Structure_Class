package Chapter03;

import java.util.Arrays;
import java.util.Comparator;

public class tttt {

    public static void main(String[] args) {
        Fruit[] arr = {
            new Fruit("사과", 200, "2023-5-8"),
            new Fruit("키위", 500, "2023-6-8"),
            new Fruit("오렌지", 200, "2023-7-8"),
            new Fruit("바나나", 50, "2023-5-18"),
            new Fruit("수박", 880, "2023-5-28"),
            new Fruit("체리", 10, "2023-9-8")
        };
        System.out.println("정렬 전 객체 배열: ");
        showData(arr);

        Arrays.sort(arr, (a, b) -> a.getPrice() - b.getPrice()); // 람다식 우선 적용
        System.out.println("람다식 정렬(가격) 후 객체 배열: ");
        showData(arr);

        Arrays.sort(arr, new Comparator<Fruit>() {
            @Override
            public int compare(Fruit a1, Fruit a2) {
                return a1.getName().compareTo(a2.getName());
            }
        });
        System.out.println("comparator 정렬(이름) 후 객체 배열: ");
        showData(arr);

        Comparator<Fruit> cc_name = new Comparator<Fruit>() {
            @Override
            public int compare(Fruit a1, Fruit a2) {
                return a1.getName().compareTo(a2.getName());
            }
        };

        Comparator<Fruit> cc_price = new Comparator<Fruit>() {
            @Override
            public int compare(Fruit a1, Fruit a2) {
                return a1.getPrice() - a2.getPrice();
            }
        };

        Fruit newFruit = new Fruit("체리", 500, "2023-5-18");
        int result3 = Arrays.binarySearch(arr, newFruit, cc_name);
        System.out.println("\nArrays.binarySearch() 조회 결과: " + result3);
        result3 = binarySearch(arr, newFruit, cc_name);
        System.out.println("\nbinarySearch() 조회 결과: " + result3);

        sortData(arr, cc_price);
        System.out.println("comparator 정렬(가격) 후 객체 배열: ");
        showData(arr);
        result3 = Arrays.binarySearch(arr, newFruit, cc_price);
        System.out.println("\nArrays.binarySearch() 조회 결과: " + result3);
        result3 = binarySearch(arr, newFruit, cc_price);
        System.out.println("\nbinarySearch() 조회 결과: " + result3);
    }

    public static void showData(Fruit[] arr) {
        for (Fruit f : arr) {
            System.out.println(f);
        }
        System.out.println();
    }

    public static int binarySearch(Fruit[] arr, Fruit key, Comparator<Fruit> c) {
        int start = 0;
        int end = arr.length - 1;
        int mid;
        int compResult;

        while (end - start >= 0) {
            mid = (start + end) / 2;
            compResult = c;
        }
    }
}
