package Chapter05;

public class Queen_repeat {
	static final int qCount = 4;

	public static void SolveQueen(int[][] data) {
		int count = 0;
		int icol = 0, irow = 0;
		ObjectStack st = new ObjectStack(10);
		Point p = new Point();
		p.setRow(irow);
		p.setCol(icol);

		data[icol][irow] = 1;
		count++;
		st.push(p);
		System.out.println("push : " + p);

		while (count < qCount) {
			icol++;
			int crow = 0;

			while (icol < data[0].length) {
				icol++;
				crow = NextMove(data, icol, crow);

				while (crow < data[0].length) {
					if (CheckMove(data, crow, icol) == true) {
						p = new Point();
						p.setRow(crow); // 현위치 입력
						p.setCol(icol); // 현위치 입력

						st.push(p);
						System.out.println("push : " + p);
						count++;
						data[crow][icol] = 1;
					}
					 crow++;
				}
				if (crow != data[0].length) {
					break;
				} else {
					p = st.pop();
					System.out.println("pop  : " + p);
					data[p.getRow()][p.getCol()] = 0;
					count--;

					icol = p.getCol();
					crow = p.getRow() + 1;
				}

			}

		}
	}

	public static boolean checkRow(int[][] data, int row) {
		for (int c = 0; c <= data[row].length; c++) {
			if (data[row][c] == 1) 
				return false;
		}

		return true;
	}

	public static boolean checkCol(int[][] data, int col) {
		for (int r = 0; r <= data[col].length; r++) {
			if (data[r][col] == 1) {
				return false;
			}
		}

		return true;
	}

	public static boolean checkDiagSW(int[][] data, int row, int col) { // x++, y-- or x--, y++ where 0<= x,y <= 7 / 배열
																		// data의 row,col의 sw대각선에 배치 가능한가?
		int r = row;
		int c = col;
		while (row >= 0 && row <= data[0].length && col >= 0 && col <= data[0].length) {
			row++;
			col--;
			if ((c < 0) || (r >= data.length)) {
				break;
			}
			if (data[r][c] == 1) {
				return false;
			}
		}

		while (row >= 0 && row <= data[0].length && col >= 0 && col <= data[0].length) {
			row--;
			col++;
			if ((c >= data[0].length) || (r < 0)) {
				break;
			}
			if (data[r][c] == 1) {
				return false;
			}
		}
		return true;
	}

	public static boolean checkDiagSE(int[][] data, int row, int col) {// x++, y++ or x--, y--
		int r = row;
		int c = col;
		while (true) {
			r--;
			c--;
			if ((c < 0) || (r < 0)) {
				break;
			}
			if (data[r][c] == 1) {
				return false;
			}
		}

		while (true) {
			r++;
			c++;
			if ((c >= data[0].length) || (r >= data.length)) {
				break;
			}
			if (data[r][c] == 1) {
				return false;
			}
		}
		return true;
	}

	public static boolean CheckMove(int[][] data, int row, int col) {// (x,y)로 이동 가능한지를 check
		if (checkRow(data, row) && checkCol(data, col) && checkDiagSE(data, row, col)
				&& checkDiagSW(data, row, col) == true && data[row][col] == 0) {
			return true;
		} else
			return false;
	}

	public static int NextMove(int[][] data, int row, int Newcol) {// 다음 row에 대하여 이동할 col을 조사
		for (int col = 0; col <= data[0].length; col++) {
			if (CheckMove(data, row, col)) {
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