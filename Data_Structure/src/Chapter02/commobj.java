package Chapter02;

class PhyscData implements Comparable<PhyscData> {
	String name;
	int height;
	double vision;

	PhyscData(String name, int height, double vision) {
		this.name = name;
		this.height = height;
		this.vision = vision;
	}

	public int compareTo(PhyscData pd) {
		
		if(this.name == pd.name || this.height == pd.height || this.vision == pd.vision) {
			return 0;
		} else if (this.name < pd.name || this.height < pd.height || this.vision < pd.vision) {
			return -1;
		} else {
			return 1;
		}
	}
		
//		if (this.name.compareTo(pd.name) == 0) {
//			if (this.height == pd.height) {
//				if (this.vision == pd.vision) {
//				}
//			}
//			return 0;
//		}
//		if (this.name.compareTo(pd.name) < 0) {
//			if (this.height == pd.height) {
//				if (this.vision == pd.vision) {
//				}
//				return -1;
//			}
//		} else {
//			return 1;
//		}
//		return height;
//	}

	private static void showData(PhyscData[] dt) {
		for (int i = 0; i < dt.length; i++) {
			System.out.print(dt[i] + ", ");
		}
	}
	
	private static void sortData(PhyscData[] dt) {
		for (int k = 0; k < dt.length; k++) {
			for (int l = 0; l < dt.length; l++) {
				if (dt[k].compareTo(dt[l]) < 0) {
					PhyscData temp = dt[k];
					dt[k] = dt[l];
					dt[l] = temp;
				}
			}
		}

	}

	public class objectcompare {

		public static void main(String[] args) {
			PhyscData[] dt = {new PhyscData("홍길동", 162, 0.3), 
								new PhyscData("홍동", 164, 1.3),
								new PhyscData("홍길", 152, 0.7), 
								new PhyscData("김홍길동", 172, 0.3), 
								new PhyscData("이길동", 182, 0.6),
								new PhyscData("박길동", 167, 0.2), 
								new PhyscData("최길동", 169, 0.5) };

			showData(dt);
			sortData(dt);
			System.out.println(" ");
			showData(dt);
			System.out.println("ddd");
		}

	}

}