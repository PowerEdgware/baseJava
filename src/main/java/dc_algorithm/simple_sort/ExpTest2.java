package dc_algorithm.simple_sort;

public class ExpTest2 {

	public static void main(String[] args) {
		int size=100_000;
		BubbleSort bubbleSort=new BubbleSort(size);
		SelectSort selectSort=new SelectSort(size);
		InsertSort insertSort=new InsertSort(size);
		
		for(int j=size-1;j>0;j--){
			long n=(long) (Math.random()*(size-1));
			bubbleSort.insert(j);
			selectSort.insert(j);
			insertSort.insert(j);
		}
		System.out.println("start bubbleSort...");
		long start=System.currentTimeMillis();
		bubbleSort.bubble_sort();
		long end=System.currentTimeMillis();
		System.out.println("bubble_sort total data="+size+" cost="+(end-start)+" ms");
		
		System.out.println("start selectSort...");
		start=System.currentTimeMillis();
		selectSort.select_sort();
		end=System.currentTimeMillis();
		System.out.println("select_sort total data="+size+" cost="+(end-start)+" ms");
		
		System.out.println("start insertSort...");
		start=System.currentTimeMillis();
		insertSort.insert_sort();
		end=System.currentTimeMillis();
		System.out.println("insert_sort total data="+size+" cost="+(end-start)+" ms");
		//此时插入排序最慢，因为比较次数多
		/**
		 * start bubbleSort...
			bubble_sort total data=100000 cost=4751 ms
			start selectSort...
			swap count=49999
			select_sort total data=100000 cost=3861 ms
			start insertSort...
			insert_sort total data=100000 cost=7501 ms
		 */
	}
}
