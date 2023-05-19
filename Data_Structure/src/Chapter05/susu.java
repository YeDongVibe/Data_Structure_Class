package Chapter05;

public class susu {


   static final int qCount = 8;

   public static void SolveQueen(int[][] data) {
      int count = 0, mode = 0;
      int icol = 0, irow = 4;
      ObjectStack st = new ObjectStack(10);

      // 초기 (0,0) 위치에 퀸 배치
      Point p = new Point();
      p.setRow(irow);
      p.setCol(icol);
      data[irow][icol] = 1;
      count++;
      st.push(p);
      System.out.println("push(" + p + ")");

      // Queen을 qCount개 다 놓을때까지 반복
      while (count < qCount) {
         icol++;
         int crow = 0;
         // 체스판의 가로축 끝까지 반복
         while (icol < data[0].length) {
            // 체스판의 세로축 끝까지 반복
            while (crow < data.length) {

               // 퀸을 놓을 수 있는지 확인 하는 함수
               if (CheckMove(data, crow, icol)) {
                  p = new Point();
                  p.setRow(crow);
                  p.setCol(icol);

                  st.push(p);
                  System.out.println("push(" + p + ")");
                  count++;
                  data[crow][icol] = 1;
                  break;
               }
               crow++;

            }
            // 퀸자리를 찾았으니 나가가지고 다음 col를 옮겨서 찾을꼬얌
            if (crow < data.length) {
               // 가로축의 반복문을 break
               break;
            }
            // 퀸 자리를 못 찾았으니 전에 있던 포인트를 지우고 새로 찾기 시작할거야
            else {
               // 전에 있던 포인트를 없애고 다시 찾겠다.
//               if(!st.isEmpty()) {
               p = st.pop();
               System.out.println("pop(" + p + ")");
               count--;
               data[p.getRow()][p.getCol()] = 0;

               // row값을 한칸 옮겨가지구 다음 값을 정해가지구 초기화해줌
               icol = p.getCol();
               crow = p.getRow() + 1;
//               } else {
//                  break;
//               }
            }
         }
      }
   }

//   public static boolean checkRow(int[][] data, int row) {
//      // 지정된 row값에 col을 옮겨가면서 자리를 체크
//      for (int c = 0; c < data[row].length; c++) {
//         if (data[row][c] == 1) {
//            return false;
//         }
//      }
//      return true;
//   }

   public static boolean checkRow(int[][] data, int row, int col) {
      int c = col;

      while (true) {
         c++;

         if (c >= data.length) {
            break;
         }
         // 현재 위치에 퀸이 있으면 false 값 출력
         if (data[row][c] == 1) {
            return false;
         }
      }

      c = col;

      while (true) {
         c--;

         if (c < 0) {
            break;
         }
         if (data[row][c] == 1) {
            return false;
         }
      }
      return true;
   }

//   public static boolean checkCol(int[][] data, int col) {
//
//      for (int r = 0; r < data.length; r++) {
//         if (data[r][col] == 1) {
//            return false;
//         }
//      }
//      return true;
//   }

   public static boolean checkCol(int[][] data, int row, int col) {
      int r = row;
   
      while (true) {
         r++;

         if (r >= data.length) {
            break;
         }
         // 현재 위치에 퀸이 있으면 false 값 출력
         if (data[r][col] == 1) {
            return false;
         }
      }
      r = row;   

      while (true) {
         r--;

         if (r < 0) {
            break;
         }
         if (data[r][col] == 1) {
            return false;
         }
      }
      return true;
   }

   public static boolean checkDiagSW(int[][] data, int row, int col) { // x++, y-- or x--, y++ where 0<= x,y <= 7
                                                      // //row++, col++
      // row++ & col--
      int r = row;
      int c = col;

      while (true) {
         r++;
         c--;

         if (c < 0 || r >= data.length) {
            break;
         }
         // 현재 위치에 퀸이 있으면 false 값 출력
         if (data[r][c] == 1) {
            return false;
         }

      }

      // row-- & col++
      r = row;
      c = col;

      while (true) {
         r--;
         c++;

         if (r < 0 || c >= data[0].length) {
            break;
         }
         if (data[r][c] == 1) {
            return false;
         }
      }

      return true;
   }

   public static boolean checkDiagSE(int[][] data, int row, int col) {// x++, y++ or x--, y--

      // row++ & col++
      int r = row;
      int c = col;

      while (true) {
         r++;
         c++;

         if (c >= data[0].length || r >= data.length) {
            break;
         }
         // 현재 위치에 퀸이 있으면 false 값 출력
         if (data[r][c] == 1) {
            return false;
         }

      }

      // row-- & col--
      r = row;
      c = col;

      while (true) {
         r--;
         c--;

         if (r < 0 || c < 0) {
            break;
         }
         if (data[r][c] == 1) {
            return false;
         }
      }
      return true;
   }

   public static boolean CheckMove(int[][] data, int row, int col) {// (x,y)로 이동 가능한지를 check

      // 행 방향에 퀸이 있는지 확인 //열 방향에 퀸이 있는지 확인 //대각선 방향에 퀸이 있는지 확인 //대각선 방향에 퀸이 있는지 확인
      // //현재 위치에도 있는지 없는지 확인 없어야댐 0
      if (checkRow(data, row, col)  && checkCol(data, row, col) && checkDiagSE(data, row, col)
            && checkDiagSW(data, row, col) && data[row][col] == 0) {
         return true;
      }

      return false;

   }

   public static int NextMove(int[][] data, int row, int col) {// 다음 row에 대하여 이동할 col을 조사
      while (col < data[0].length) {
         if (CheckMove(data, row, col))
            return col;
         col++;
      }
      return data[0].length;
   }

   public static void main(String[] args) {
      int row = qCount, col = qCount;
      int[][] data = new int[qCount][qCount];
//      for (int i = 0; i < data.length; i++)
//         for (int j = 0; j < data[0].length; j++)
//            data[i][j] = 0;

      SolveQueen(data);

      System.out.println();
      for (int i = 0; i < data.length; i++) {
         for (int j = 0; j < data[0].length; j++) {
            System.out.print(" " + data[i][j]);
         }
         System.out.println();
      }
   }
}