package Chapter05;

//true : 여왕 놓을 수 있음
//false : 여왕 못놓음.

public class Queen {
	
	static final int qCount = 8;

	
	public static void SolveQueen(int[][] data) {
		int count = 0, mode = 0;
		// x : icol, y : irow 로 바꿈/직관적으로 보기 위해 이렇게 생각하기
		// icol : 가로축 , crow : 세로축
		int icol = 0, irow = 0;
		ObjectStack st = new ObjectStack(10);

		// 초기 (0.0)위치에 Queen 배치.
		Point p = new Point();
		// 기본생성자 만들기
		p.setRow(irow);
		p.setCol(icol);

		data[icol][irow] = 1;
		count++;
		st.push(p);
		System.out.println("push : " + p);

		while (count < qCount) {// queen을 8(qCount)개 놓을 때 까지 반복.
			icol++; // 가로로 하나 증가
			int crow = 0;

			while (icol < data[0].length) { // 체스판의 가로축 끝까지 반복

				while (crow < data.length) { // 체스판의 세로축 끝까지 반복
					
					if (CheckMove(data, crow, icol) == true) {
						p = new Point();// 현재 위치 생성
						p.setRow(crow); // 현위치 입력
						p.setCol(icol); // 현위치 입력

						st.push(p);
						System.out.println("push : " + p);
						count++;

						// 초기값 설정해주기 : (0.0)에 queen 배치
						data[crow][icol] = 1;
						break;
					}
					crow++; //만약 자리를 못찾으면 증가함.
				}
				
				if (crow < data[0].length) { //다음 col의 자리를 찾겠다.
					break;
				} else { //놓을 수 없는 자리가 없을 때
					p = st.pop(); //다른자리에 놓겠다.그래서 pop을 하여 꺼냄.
					System.out.println("pop  : " + p);
					count--;
					data[p.getRow()][p.getCol()] = 0; //꺼내온 queen의 위치를 0으로 초기화함.
					
					icol = p.getCol();
					crow = p.getRow() + 1; //pop한 위치의 row보다 한칸 아래(즉. +1한 위치에 지정)
				}
			}
		}
	}

	public static boolean checkRow(int[][] data, int row) {// 가로체크하기 / 배열 data에서 row에 Queen을 놓을 수 있는가?
		for (int c = 0; c < data[row].length; c++) {
			if (data[row][c] == 1) // row는 고정하고 col을 이동하면서 체크하기
				return false;
		}
		return true;
	}

	public static boolean checkCol(int[][] data, int col) {// 세로체크하기 / 배열 data에서 col에 배치할 수 있는가?가능하면 true로 리턴함.
		for (int r = 0; r < data[col].length; r++) { // row는 고정하고 col을 이동하면서 체크하기
			if (data[r][col] == 1) {
				return false;
			}
		}
		return true;
	}

	public static boolean checkDiagSW(int[][] data, int row, int col) { // row++, col-- or row--, col++ where 0<= x,y <= 7 //대각선 체크 (남서쪽)
		int r = row;
		int c = col;
		while (true) {
			r++;
			c--;
			if ((c < 0) || (r >= data.length)) {
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

	public static boolean CheckMove(int[][] data, int row, int col) {// (x,y)로 이동 가능한지를 check //가로(row), 세로(col), 대각선(dia) 각각의 queen이 있는지 확인
		if (checkRow(data, row) && checkCol(data, col) && checkDiagSE(data, row, col)
				&& checkDiagSW(data, row, col) == true && data[row][col] == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static int NextMove(int[][] data, int row, int col) {// 다음 row에 대하여 이동할 col을 조사
		while (col<data[0].length) {
			if(CheckMove(data, row, col)) 
				return col;
				col++;
		}
		return data[0].length;
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
