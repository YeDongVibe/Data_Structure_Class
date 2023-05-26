package Chapter06;

import java.util.Random;


class BubbleSort3 {
    //--- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
    static void swap(int[] a, int idx1, int idx2) {
        int t = a[idx1]; a[idx1] = a[idx2]; a[idx2] = t;
    }

    //--- 버블 정렬 ---//
    static void bubbleSort(int[] a, int n) {
    	int count = 0; //비교 횟수 세기 위한 변수 선언
    	int k = 0; // a[k]보다 앞쪽은 정렬을 마친 상태
    	while(k < n-1) {
    		int last = n-1; //마지막으로 요소를 교환 한 위치

            for (int j = n - 1; j > k; j--) {
            	count++;
                if (a[j - 1] > a[j]) {
                    swap(a, j - 1, j);
                    last = j;
                }
            }
            k = last;

        System.out.println("비교 횟수(count) : " + count);
        System.out.println("비교 횟수(last) : " + last);

    }
    }

    public static void main(String[] args) {
        Random random = new Random();
        System.out.println("버블 정렬(버전 3)");

        int nx = 100;
        int[] x = new int[nx];

        for (int i = 0; i < nx; i++) {
            //x[i] = sc.nextInt();
            x[i] = random.nextInt(100); //랜덤하게 수를 배치하여 사용
        }

        bubbleSort(x, nx);  // 배열 x를 단순교환정렬

        System.out.println("오름차순으로 정렬했습니다.");
        for (int i = 0; i < nx; i++)
            System.out.print(" " + x[i]);

    }
}
