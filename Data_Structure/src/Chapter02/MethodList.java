package Chapter02;

import java.util.Random;

public class MethodList {
	static void getData (int [] data) {
		//난수 생성하여 배열에 입력
		Random rand = new Random(System.currentTimeMillis()); //초기 ceed값
		for(int i = 0; i <data.length; i++) {
			data [i] = rand.nextInt(10); //10이하 정수 출력
		}
		
	}
	static void showData (int [] data) {
		//배열 출력
		for(int i = 0; i <data.length; i++) {
		System.out.print(data[i] + " ");
		}
	}
	
	static int findMax(int [] data){
		int max = 0;
		for (int j = 0; j < data.length; j++) {
			if (data[j] > max)
				max = data[j];
		}
		return max;
	}
	private static void sortData(int[] data, int k, int l) {
		for(k = 0; k <data.length; k++) {
			for(l = k +1; l <data.length; l++) {
				if(data[k] > data[l]) {
					int temp = data[k];
					data[k] = data[l];
					data[l] = temp;
				}
			}
		}
		
	}


	public static void main(String[] args) {
		int [] data = new int [10];
		getData (data);
		showData(data);
		int mvalue = findMax(data);
		System.out.println("\nmax = " + mvalue);
		sortData(data, mvalue, mvalue);
		System.out.println("\n정렬 후 결과");
		showData(data);
	}
	
	

}
