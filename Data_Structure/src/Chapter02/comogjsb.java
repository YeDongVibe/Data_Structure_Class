package Chapter02;

class PhyscData implements Comparable<PhyscData> {
	   String name;
	   int height;
	   double vision;
	   
	   public PhyscData(String name, int height, double vision) {
		      this.name = name;
		      this.height = height;
		      this.vision = vision;
		   }

	   @Override
	   public int compareTo(PhyscData cp) {
	      if (this.name.compareTo(cp.name) == 0) {
	         if (this.height == cp.height) {
	            if (this.vision == cp.vision) {
	               return 0;
	            } else if (this.vision < cp.vision) {
	               return -1;
	            } else {
	               return 1;
	            }
	         }

	      } else if (this.name.compareTo(cp.name) < 0) {
	         return -1;
	      } else {
	         return 1;
	      }
	      return 0;
	   }
	   
	   @Override
	    public String toString() {
	        return name + " (" + height + "cm, " + vision + ")";
	    }

	 

	}



	public class comogjsb {

	   static void showData(PhyscData[] data) {

	      for (int i = 0; i < data.length; i++) {
	         System.out.print(data[i] + ", ");
	      }

	   }

	   static void sortData(PhyscData[] data) {

	      for (int i = 0; i < data.length; i++) {
	         for (int j = 0; j < data.length; j++) {
	            if (data[i].compareTo(data[j]) < 0) {
	               PhyscData temp = data[i];
	               data[i] = data[j];
	               data[j] = temp;
	            }

	         }
	      }

	   }

	   public static void main(String[] args) {
	      PhyscData[] data = { 
	            new PhyscData("홍길동", 162, 0.3), 
	            new PhyscData("홍동", 164, 1.3),
	            new PhyscData("홍길", 152, 0.7), 
	            new PhyscData("김홍길동", 172, 0.3), 
	            new PhyscData("길동", 182, 0.6),
	            new PhyscData("길동", 167, 0.5), 
	            new PhyscData("길동", 167, 0.2), 
	            };

	      showData(data);
	      sortData(data);
	      System.out.println("// ");
	      showData(data);
	      System.out.println("// ");
	   }

	}