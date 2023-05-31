package Chapter06;

//heap 자료구조 1.0.doc 파일을 먼저 공부할 필요 
import java.util.Scanner;

//class Element {
//	public int key;
//
//	public int getElement() {
//		return key;
//	}
//
//	public Element(int key) {
//		this.key = key;
//	}
//}
//
//interface MaxPQ {
//	public void Insert(Element x);
//
//	public Element DeleteMax(Element x);
//}
//
//class MaxHeap implements MaxPQ {
//	final int heapSize = 1000;
//
//	public MaxHeap(int sz) {
//		MaxSize = sz;
//		n = 0;
//		heap = new Element[MaxSize + 1]; // Don't want to use heap[0]
//	}
//
//	public void display() {
//		int i;
//		for (i = 1; i <= n; i++)
//			System.out.print("  (" + i + ", " + heap[i].key + ") ");
//		System.out.println();
//	}
//
//	public void Insert(Element x) {
//		int i;
//		if (n == MaxSize) {
//			HeapFull();
//			return;
//		}
//		n++;
//		for (i = n; i >= 1;) {
//			if (i == 1)
//				break; // at root
//			if (x.key <= heap[i / 2].key)
//				break;// 자바에서 generic array 사용 안됨
//			// move from parent to i					
//			heap[i] = heap[i / 2];
//			i /= 2;
//		}
//		heap[i] = x;
//
//	}
//
//	public Element DeleteMax(Element x) {
//
//		int i, j;
//		if (n == 0) {
//			HeapEmpty();
//			Element elm = new Element(0);
//			return elm;
//		}
//		x = heap[1];
//		Element k = heap[n];
//		n--;
//
//		for (i = 1, j = 2; j <= n;) {
//			if (j < n)
//				if (heap[j].key < heap[j + 1].key)
//					j++;
//			// j points to the larger child
//			if (k.key >= heap[j].key)
//				break;
//			heap[i] = heap[j];
//			i = j;
//			j *= 2;
//		}
//		heap[i] = k;
//		return x;
//	}
//
//	private Element[] heap;
//	private int n; // current size of MaxHeap
//	private int MaxSize; // Maximum allowable size of MaxHeap
//
//	private void HeapEmpty() {
//		System.out.println("Heap Empty");
//	}
//
//	private void HeapFull() {
//		System.out.println("Heap Full");
//	}
//}

public class HeapSortCPP {

	public static void main(String[] args) {
		int select = 0;
		Scanner stdIn = new Scanner(System.in);
		MaxHeap heap = new MaxHeap(20);
		Element ele = null;
		 final int count = 5;
	     int[] x = new int[count];


		Element deletedEle = null;

		do {
			System.out.println("Max Tree. Select: 1 insert, 2 display, 3 delete,  4 sort, 5 exit => ");
			select = stdIn.nextInt();
			switch (select) {
			case 1:
			     for (int i = 0; i < count; i++) {
			         x[i] = (int)(Math.random() * 30);
						heap.Insert(new Element(x[i]));
			     }
				break;
			case 2:
				heap.display();
				break;
			case 3:
				deletedEle = heap.DeleteMax(ele);
				if (deletedEle != null) {
					System.out.println("deleted element: " + deletedEle.key);
				}
				System.out.println("current max heap: ");
				heap.display();
				break;
			case 4:
				for (int j = 0; j < count; j++) {
					deletedEle = heap.DeleteMax(ele);
					x[j] = deletedEle.getElement();
				}
				for (int num: x)
					System.out.println(" " + num);
			case 5:
				return;

			}
		} while (select < 5);

		return;
	}
}
