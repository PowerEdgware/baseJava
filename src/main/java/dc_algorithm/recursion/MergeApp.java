package dc_algorithm.recursion;

/**
 *归并排序
 * @author yu
 *
 */
public class MergeApp {

	public static void main(String[] args) {
		// a,b 必须已经有序
		int[] a={2,5,12,80};
		int[] b={1,3,6,10,22,31};
		int[] c=new int[a.length+b.length];
		merge(a, b, c);
		display(c);
	}
	
	// A B is orderArr
	static void merge(int[] a,int[] b,int[] c){
		int sizeA=a.length,sizeB=b.length,
				aDex=0,bDex=0,cDex=0;
		while(aDex<sizeA && bDex<sizeB)
			if(a[aDex]<b[bDex])
				c[cDex++]=a[aDex++];
			else
				c[cDex++]=b[bDex++];
		
		//not empty A
		while(aDex<sizeA)
			c[cDex++]=a[aDex++];
		
		//not empty B
		while(bDex<sizeB)
			c[cDex++]=b[bDex++];
	}
	
	static void display(int arr[]){
		for(int k=0;k<arr.length;k++)
			System.out.print(arr[k]+" ");
		
		System.out.println();
	}
}
