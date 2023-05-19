package Chapter05;

public class tt {
    static final int qCount = 8;

    public static void SolveQueen(int[][] data) {
        int count = 0;
        int icol = 0, irow = 4;
        ObjectStack st = new ObjectStack(10);

        // 초기 (0.0)위치에 Queen 배치.
        Point p = new Point();
        p.setRow(irow);
        p.setCol(icol);

        data[irow][icol] = 1;
        count++;
        st.push(p);
        System.out.println("push : " + p);

        while (count < qCount) {
            icol++; // 가로로 하나 증가
            int crow = 0;

            while (icol < data[0].length) {
                while (crow < data.length) {
                    if (CheckMove(data, crow, icol)) {
                        p = new Point();
                        p.setRow(crow);
                        p.setCol(icol);

                        st.push(p);
                        System.out.println("push : " + p);
                        count++;

                        data[crow][icol] = 1;
                        break;
                    }
                    crow++;
                }

                if (crow < data.length) {
                    break;
                } else {
//                    if (!st.isEmpty()) {
                        p = st.pop();
                        System.out.println("pop  : " + p);
                        count--;
                        data[p.getRow()][p.getCol()] = 0;

                        icol = p.getCol();
                        crow = p.getRow() + 1;
                }
//                    } else {
//                        break;
//                    }
                }
            }
        }
//    }

    public static boolean checkRow(int[][] data, int row) {
        for (int c = 0; c < data[row].length; c++) {
            if (data[row][c] == 1)
                return false;
        }
        return true;
    }

    public static boolean checkCol(int[][] data, int col) {
        for (int r = 0; r < data.length; r++) {
            if (data[r][col] == 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkDiagSW(int[][] data, int row, int col) {
        int r = row;
        int c = col;
        while (true) {
            r++;
            c--;
            if (c < 0 || r >= data.length) {
                break;
            }
            if (data[r][c] == 1) {
                return false;
            }
        }

        r = row;
        c = col;
        while (true) {
            r--;
            c++;
            if ((c >= data[0].length) || (r < 0)) {
                break;
            }
            if (data[r][c] == 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkDiagSE(int[][] data, int row, int col) {// row++, col++ or row--, col-- //대각선 체크(남동쪽)
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
		r = row;
		c = col;
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
    
    public static boolean CheckMove(int[][] data, int row, int col) {
        if (checkRow(data, row) && checkCol(data, col) && checkDiagSE(data, row, col) && checkDiagSW(data, row, col)
                && data[row][col] == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static int NextMove(int[][] data, int row, int col) {
        while (col < data[0].length) {
            if (CheckMove(data, row, col)) {
                return col;
            }
            col += 1;
        }
        return data[0].length;
    }

    public static void main(String[] args) {
        int[][] data = new int[qCount][qCount];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = 0;
            }
        }

        SolveQueen(data);

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                System.out.print(" " + data[i][j]);
            }
            System.out.println();
        }
    }
}

