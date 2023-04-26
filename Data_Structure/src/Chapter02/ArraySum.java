package Chapter02;

import java.util.Random;

public class ArraySum {

		static void getArr (int [][] A) {
			//난수 생성하여 배열에 입력
			Random rand = new Random(System.currentTimeMillis()); //초기 ceed값
			for(int i = 0; i <A.length; i++) {
				for(int j = 0; j < A.length; j++) {
					A [i][j] = rand.nextInt(10); //10이하 정수 출력
				}

			}

	}
		static void ShowMatrix (int [][] A, int [][] B, int [][] C, int [][] D) {
			for(int i = 0; i<2; i++) {
				for(int j = 0; j <3; j++) {
					System.out.println(A[i][j]);
				}
			}
		}
		
		static int[][] SumMatrix(int[][] A, int[][] A1) { 
			int[][] A2 = new int[2][3];
			for (int i = 0; i < 2; i++) {

				for (int j = 0; j < 3; j++) { 

					A2[i][j] = A[i][j] + A1[i][j];
				}
			}
			return A2;
		}
		
		public static void main(String[] args) {
			int [][] A = new int [2][3];
			int [][] B = new int [3][4];
			int [][] C = new int [2][4];
			int [][] D = new int [3][2];

			getArr(A);
			ShowMatrix(A)
			System.out.println(A);
}
}
