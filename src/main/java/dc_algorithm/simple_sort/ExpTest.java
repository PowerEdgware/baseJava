package dc_algorithm.simple_sort;
//实验-附录
public class ExpTest {
	public static void main(String[] args) {
		int size=100_000;
		BubbleSort bubbleSort=new BubbleSort(size);
		SelectSort selectSort=new SelectSort(size);
		InsertSort insertSort=new InsertSort(size);
		
		for(int j=0;j<size;j++){
			long n=(long) (Math.random()*(size-1));
			bubbleSort.insert(n);
			selectSort.insert(n);
			insertSort.insert(n);
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
		/**
		 * 
		 * start bubbleSort...
			bubble_sort total data=100000 cost=14069 ms
			start selectSort...
			swap count=99988
			select_sort total data=100000 cost=2861 ms
			start insertSort...
			insert_sort total data=100000 cost=2892 ms
			
			start bubbleSort...
			bubble_sort total data=600000 cost=544413 ms
			start selectSort...
			swap count=599986
			select_sort total data=600000 cost=119062 ms
			start insertSort...
			insert_sort total data=600000 cost=115248 ms
		 * 
		 */
	}
}
