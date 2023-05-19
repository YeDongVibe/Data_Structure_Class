package Chapter05;

public class Queen_repeat {
	static final int qCount = 8;

	public static void SolveQueen(int[][] d) {
		int count = 0, mode = 0;
		int icol = 0, irow = 0;
		ObjectStack st = new ObjectStack(10);
		Point p = new Point(icol, irow);
		d[icol][irow] = 1;
		count++;
		st.push(p);
		while (count < qCount) {
			icol++;
			int crow = 0;
			while (icol < d.length) {
				crow = NextMove(d, icol, crow);
				while (crow < d[0].length) {
					p = new Point(icol,crow);
					st.push(p);
					count++;
					break;

				}
				if (crow != d[0].length) {
					break;
				} else {
					p = st.pop();
					count--;

				}

			}

		}
	}

	public static boolean checkRow(int[][] data, int row) {

		return true;
	}

	public static boolean checkCol(int[][] data, int col) {

		return true;
	}

	public static boolean checkDiagSW(int[][] data, int row, int col) { // x++, y-- or x--, y++ where 0<= x,y <= 7 / 배열 data의 row,col의 sw대각선에 배치 가능한가?
		int r = row;
		int c = col;
		while(row >=0 && row < data[0].length && col >=0 && col <data[0].length) {
			row++;
			col--;
			if((c<0) || (r >=data.length)) {
				break;
			}
			if(data[r][c] == 1) {
				return false;
			}
		}
		
		while(row >=0 && row < data[0].length && col >=0 && col <data[0].length) {
			row--;
			col++;
		}
		return true;
	}

	public static boolean checkDiagSE(int[][] data, int row, int col) {// x++, y++ or x--, y--

		return true;
	}

	public static boolean CheckMove(int[][] data, int row, int col) {// (x,y)로 이동 가능한지를 check
		if(checkRow(data, row) && checkCol(data, col) &&checkDiagSE(data, row, col)&&checkDiagSW(data, row, col) == true) {
			return true;
			}
			else 
				return false;
		}

	public static int NextMove(int[][] data, int row, int Newcol) {// 다음 row에 대하여 이동할 col을 조사
		for(int col = 0; col <data.length; col++) {
			if(CheckMove(data, row, col)) {
				return col;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int row = qCount, col = qCount;
		int[][] data = new int[qCount][qCount];
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