package Chapter06;
// 배열을 나눔

class Partition {

    //--- 배열 요소 a[idx1]과 a[idx2]의 값을 교환 ---//
    static void swap(int[] a, int idx1, int idx2) {
        int t = a[idx1];  a[idx1] = a[idx2];  a[idx2] = t;
    }

    //--- 배열을 나눔 ---//
    static void partition(int[] a, int n) {
        int pl = 0;        // 왼쪽 커서
        int pr = n - 1;    // 오른쪽 커서
        int x = a[n / 2];  // 피벗(가운데 요소)

        do {
            while (a[pl] < x) pl++; //pl이 x보다 작으면 pl 증가
            while (a[pr] > x) pr--; //pr이 x보다 크면  pr 감소
            if (pl <= pr)
                swap(a, pl++, pr--);
        } while (pl <= pr);

        System.out.println("피벗의 값은 " + x + "입니다.");

        System.out.println("피벗 이하 그룹 ");
        for (int i = 0; i <= pl - 1; i++) // a[0] ～ a[pl - 1]
            System.out.print(a[i] + " ");
        System.out.println();

        if (pl > pr + 1) {
            System.out.println("피벗과 일치하는 그룹 ");
            for (int i = pr + 1; i <= pl - 1; i++) // a[pr + 1] ～ a[pl - 1]
                System.out.print(a[i] + " ");
            System.out.println();
        }

        System.out.println("피벗 이상 그룹 ");
        for (int i = pr + 1; i < n; i++) // a[pr + 1] ～ a[n - 1]
            System.out.print(a[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {

        System.out.println("배열을 나눕니다.");
        int [] x = {5, 7, 1, 4, 6, 2, 3, 9, 8};
        int nx = 9;


        partition(x, nx); 
    }
}
