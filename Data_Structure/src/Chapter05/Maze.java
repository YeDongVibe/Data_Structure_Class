package Chapter05;

import java.util.ArrayList;
import java.util.List;

enum Directions {
	N, NE, E, SE, S, SW, W, NW
}

class StackList {
	public List <Items> data;
	public int top;

	public StackList(int i) {
		top = 0;
		data = new ArrayList<>();
	}

	public void push(Items temp) {
		data.add(temp);
		top++;
	}

	public Items pop() {
		if (top > 0) {
			top--;
			return data.remove(top);
		}
		return null;
	
	}
	public boolean isEmpty() {
		return top <= 0;
	}
	public List<Items> getAll() {
		return data;
		
	}
	

	}

class Items {// 현재 위치
	int x;
	int y;
	int dir; // direction

	public Items(int x, int y, int dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}

}

class Offsets {
	int a;
	int b;

	public Offsets(int a, int b) {
		this.a = a;
		this.b = b;
	}

}

public class Maze {

	static Offsets[] moves = new Offsets[8];// static을 선언하는 이유를 알아야 한다
	static StackList st = new StackList(50);
	public static void path(int[][] maze, int[][] mark, int [][] dire, int ix, int iy) {
	
		mark[1][1] = 1;

		Items temp = new Items(0, 0, 0);//N :: 0
		temp.x = 1;
		temp.y = 1;
		temp.dir = 2;//E:: 2
		mark[temp.x][temp.y] = 2; //미로 찾기 궤적은 2로 표시
		st.push(temp);

		while (!st.isEmpty()) // stack not empty
		{
			Items tmp = st.pop(); // unstack
			int i = tmp.x;
			int j = tmp.y;
			int d = tmp.dir;
			mark[i][j] = 1;//backtracking 궤적은 1로 표시
			
			while (d < 8) // moves forward
			{
				int g = i + moves[d].a;
				int h = j + moves[d].b;
				if ((g == ix) && (h == iy)) { // reached exit
					System.out.println(st);	// output path
					System.out.println("the term near the exit: " + i +" " + j);
					System.out.println("exit: " + ix + " " + iy);
					mark[g][h] = 2;
					mark[i][j] = 2;
					while(!st.isEmpty()) {
						tmp = st.pop();
						mark[tmp.x][tmp.y] = 2;
						dire[g][h] = 2;
					}
					return;
				}
				if ((maze[g][h] == 0) && (mark[g][h] == 0)) { // new position
					temp.x = i;  
					temp.y = j; 
					temp.dir = d + 1;
					Items push = new Items(i, j, d+1);
					st.push(push);
					mark[temp.x][temp.y] = 1;
					i = g;
					j = h; 
					d = 0;
					dire[g][h] = 2;

				} else {
					d++;
				}

			}
		}
		System.out.println("no path in maze ");
		
	}

	public static void main(String[] args) {
		int[][] maze = new int[14][17];
		int[][] mark = new int[14][17];
		int[][] dire = new int[14][17]; //최단경로 표시 

		int input[][] = { // 12 x 15
				{ 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1 }, { 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1 },
				{ 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1 }, { 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0 },
				{ 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1 }, { 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1 },
				{ 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1 }, { 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1 }, { 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0 },
				{ 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 }, { 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0 } };
		for (int ia = 0; ia < 8; ia++)
			moves[ia] = new Offsets(0, 0);// 배열에 offsets 객체를 치환해야 한다.
		moves[0].a = -1;
		moves[0].b = 0;
		moves[1].a = -1;
		moves[1].b = 1;
		moves[2].a = 0;
		moves[2].b = 1;
		moves[3].a = 1;
		moves[3].b = 1;
		moves[4].a = 1;
		moves[4].b = 0;
		moves[5].a = 1;
		moves[5].b = -1;
		moves[6].a = 0;
		moves[6].b = -1;
		moves[7].a = -1;
		moves[7].b = -1;
		// Directions d;
		// d = Directions.N;
		// d = d + 1;//java는 지원안됨
		for (int i = 0; i < 14; i++) {
			for (int j = 0; j < 17; j++) {
				if ((i == 0) || (j == 0) || (i == 13) || (j == 16))
					maze[i][j] = 1;
				else {
					maze[i][j] = input[i - 1][j - 1];
				}
				mark[i][j] = 0;
				dire[i][j] = 0;

			}
		}
		
		System.out.println("maze[12,15]::");
		for (int i = 0; i <= 13; i++) {
			for (int j = 0; j <= 16; j++) {
				System.out.print(maze[i][j] + " ");

			}
			System.out.println();
		}
		
		System.out.println("처음 mark::");
		for (int i = 0; i <= 13; i++) {
			for (int j = 0; j <= 16; j++) {
				System.out.print(mark[i][j] + " ");

			}
			System.out.println();
		}
		
		path(maze, mark, dire, 12, 15);
		
		System.out.println("지나간 mark::");
		for (int i = 1; i <= 12; i++) {
			for (int j = 1; j <= 15; j++) {
				System.out.print(mark[i][j] + " ");

			}
			System.out.println();
		}
		
		List <Items> data = st.getAll();
		for(Items item : data) {
			dire[item.x][item.y] = 2;
		}
		System.out.println("최단거리 dire::");
		for (int i = 0; i <= 13; i++) {
			for (int j = 0; j <= 16; j++) {
				System.out.print(dire[i][j] + " ");

			}
			System.out.println();
		}

	}
}
