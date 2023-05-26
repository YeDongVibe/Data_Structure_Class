package Chapter06;
// 셸 정렬(버전 1)

import java.util.Random;

class ShellSort {
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
           System.out.println("쉘 정렬");

           int nx = 100;
           int[] x = new int[nx];

           for (int i = 0; i < nx; i++) {
               //x[i] = sc.nextInt();
               x[i] = random.nextInt(100); //랜덤하게 수를 배치하여 사용
           }

           shellSort(x, nx);  // 배열 x를 단순교환정렬

           System.out.println("오름차순으로 정렬했습니다.");
           for (int i = 0; i < nx; i++)
               System.out.print(" " + x[i]);

       }
   }
