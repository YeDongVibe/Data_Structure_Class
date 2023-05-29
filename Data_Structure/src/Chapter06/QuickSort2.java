package Chapter06;
// 퀵 정렬(비재귀 버전)

import java.util.Random;


class QuickSort2 {
	static int count;
    //--- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
    static void swap(int[] a, int idx1, int idx2) {
        int t = a[idx1];  a[idx1] = a[idx2];  a[idx2] = t;
    }

    //--- 퀵 정렬(비재귀 버전)---//
    static void quickSort(int[] a, int left, int right) {
        IntStack st = new IntStack(right - left + 1);
        Point p = new Point (left, right);
        st.push(p);

        while (!st.isEmpty()) {
        		p = st.pop();
                int pl = left  = p.getX();       // 왼쪽 커서
                int pr = right = p.getY();        // 오른쪽 커서
                int x = a[(left + right) / 2];        // 피벗은 가운데 요소

                do {
                    while (a[pl] < x) 
                    	pl++;
                    count++;
                    while (a[pr] > x) 
                    	pr--;
                    count++;
                    if (pl <= pr)
                        swap(a, pl++, pr--);
                } while (pl <= pr);

                if (left < pr) {
                	p = new Point (left, pr);
                    st.push(p);
                }
                if (pl < right) {
                	p = new Point (pl, right);
                    st.push(p);
                }

        }
    }

    public static void main(String[] args) {

    	Random random = new Random();
		System.out.println("퀵 정렬(버전 1)");
		int nx = 100;
		int[] x = new int[nx];
		for(int i = 0; i < nx; i++) {
			x[i] = random.nextInt(100);
		}


		quickSort(x, 0, nx - 1); // 배열 x를 퀵정렬
		System.out.println("정렬 횟수 : " + count);

		System.out.println("오름차순으로 정렬했습니다.");
		for (int i = 0; i < nx; i++)
			System.out.println("x[" + i + "]=" + x[i]);
	}
}
