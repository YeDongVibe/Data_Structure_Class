package Chapter05;

import java.util.ArrayList;

//https://www.geeksforgeeks.org/n-queen-problem-backtracking-3/?ref=lbp
//N Queen problem / backtracking

public class Queen {

	public static void SolveQueen(int[][] d) {
		int count = 0, mode = 0;
		int ix = 0, iy = 0;
		Stack3 st = new Stack3(10);
		Point p = new Point(ix, iy);
		d[ix][iy] = 1;
		count++;
		st.push(p);
		while (count < 8) {
			ix++;
			int cy = 0;
			while (ix < d.length) {

				while (cy < d[0].length) {

					st.push(px);
					count++;
					break;

				}
				if (cy != d[0].length) {
					break;
				} else {
					p = st.pop();
					count--;

				}

			}

		}
	}

	public static boolean checkRow(int[][] d, int crow) {// 가로

		return true;
	}

	public static boolean checkCol(int[][] d, int ccol) {// 세로

		return true;
	}

	public static boolean checkDiagSW(int[][] d, int cx, int cy) { // x++, y-- or x--, y++ where 0<= x,y <= 7 //대각선 체크

		return true;
	}

	public static boolean checkDiagSE(int[][] d, int cx, int cy) {// x++, y++ or x--, y-- //대각선 체크

		return true;
	}

	public static boolean CheckMove(int[][] d, int x, int y) {// (x,y)로 이동 가능한지를 check

	}

	public static boolean NextMove(int[][] d, int row, int col) {// 다음 row에 대하여 이동할 col을 조사

		return true;
	}

	public static void main(String[] args) {
		int row = 8, col = 8;
		int[][] data = new int[8][8];
		for (int i = 0; i < data.length; i++)
			for (int j = 0; j < data[0].length; j++)
				data[i][j] = 0;

		SolveQueen(data);

		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				System.out.print(" " + data[i][j]);
			}
			System.out.println();
		}
	}
}
