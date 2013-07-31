package com.michelin.sort;

/**
 * 一个排序学习的类.
 * 
 * @author Lin.xr
 */
public class SortTest {
	private int array[] = { 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5,
			4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51 };

	public SortTest() {
//		bubbleSort(array);
//		insertSort(array);
//		selectSort(array);
//		shellSort(array);
//		mergeSort(array);
		quickSort(array);
		for (int i = 0; i < array.length; i++)
			System.out.print(array[i] + ", ");
	}

	/**
	 * 冒泡排序, 记录某次遍历时最后发生数据交换的位置，这个位置之后的数据显然已经有序了。
	 * 因此通过记录最后发生数据交换的位置就可以确定下次循环的范围了
	 * 
	 * @param array
	 */
	void bubbleSort(int[] array) {
		int flag = array.length;
		int loopCount = 0;
		while (flag > 0) {
			loopCount = flag;
			flag = 0;
			for (int i = 1; i < loopCount; i++) {
				if (array[i] < array[i - 1]) {
					swap(array, i, i - 1);
					flag = i;
				}
			}
		}
	}

	/**
	 * 
	 * 直接插入排序, 设数组为a[0…n-1]
	 * 1. 初始时，a[0]自成1个有序区，无序区为a[1..n-1]。令i=1 
	 * 2. 将a[i]并入当前的有序区a[0…i-1]中形成a[0…i]的有序区间
	 * 3. i++并重复第二步直到i==n-1。排序完成。
	 * 
	 * @param array
	 */
	void insertSort(int[] array) {
		int n = array.length;
		for(int i = 1; i < n; i++)
			for(int j = i - 1; j >= 0 && array[j] >= array[j + 1]; j--)
				swap(array, j, j + 1);
	}
	
	/**
	 * 选择排序
	 * 设数组为a[0…n-1]。
	 * 1. 初始时，数组全为无序区为a[0..n-1]。令i=0
	 * 2. 在无序区a[i…n-1]中选取一个最小的元素，将其与a[i]交换。交换之后a[0…i]就形成了一个有序区
	 * 3. i++并重复第二步直到i==n-1。排序完成
	 * @param array
	 */
	void selectSort(int[] array) {
		int n = array.length;
		int minIndex;
		for(int i = 0; i < n; i++) {
			minIndex = i;
			for(int j = i + 1; j < n; j ++) {
				if(array[minIndex] > array[j]) {
					minIndex = j;
				}
			}
			swap(array, i, minIndex);
		}
	}
	
	/**
	 * 希尔排序
	 * 是对相隔若干距离的数据进行直接插入排序，因此可以形象的称希尔排序为“跳着插”
	 * 
	 * @param array
	 */
	void shellSort(int[] array) {
		int n = array.length;
		int i, j, gap;
		for(gap = n / 2; gap > 0; gap /= 2)
			for(i = gap; i < n; i++)
				for(j = i - gap; j >= 0 && array[j] >= array[j + 1]; j--)
					swap(array, j, j + 1);
	}
	
	/**
	 * 归并排序, 当一个数组左边有序，右边也有序，那合并这两个有序数组就完成了排序。
	 * 如何让左右两边有序了？用递归！这样递归下去，合并上来就是归并排序。
	 * 
	 * @param array
	 */
	void mergeSort(int[] array) {
		int n = array.length;
		int[] tmp = new int[n];
		mergeSort(array, 0, n - 1, tmp);
	}
	
	void mergeSort(int[] array, int first, int last, int[] tmp) {
		if(first < last) {
			int mid = (first + last) / 2;
			mergeSort(array, first, mid, tmp);
			mergeSort(array, mid + 1, last, tmp);
			mergeArray(array, first, mid, last, tmp);
		}
	}
	
	void mergeArray(int[] array, int first, int mid, int last, int[] tmp) {
		int i = first, j = mid + 1, k = 0;
		int n = mid, m = last;
		while(i <= n && j <= m) {
			if(array[i] <= array[j]) {
				tmp[k++] = array[i++];
			}else {
				tmp[k++] = array[j++];
			}
		}
		while(i <= n) {
			tmp[k++] = array[i++];
		}
		while(j <= m) {
			tmp[k++] = array[j++];
		}
		for(i = 0; i < k; i++) {
			array[first + i] = tmp[i];
		}
	}
	
	/**
	 * 快速排序 “挖坑填数+分治法”，首先令i =L; j = R;
	 * 将a[i]挖出形成第一个坑，称a[i]为基准数。然后j--由后向前找比基准数小的数，
	 * 找到后挖出此数填入前一个坑a[i]中，再i++由前向后找比基准数大的数
	 * ，找到后也挖出此数填到前一个坑a[j]中。重复进行这种“挖坑填数”直到i==j
	 * 。再将基准数填入a[i]中，这样i之前的数都比基准数小，i之后的数都比基准数大。
	 * 因此将数组分成二部分再分别重复上述步骤就完成了排序。
	 * 
	 * @param array
	 */
	void quickSort(int[] array) {
		int n = array.length;
		quickSort(array, 0, n - 1);
	}
	
	void quickSort(int[] array, int first, int last) {
		if(first < last) {
			int i = first, j = last;
			int tmp = array[i];
			while (i < j) {
				while (i< j && array[j] >= tmp )
					j--;
				if(i < j)
					array[i++] = array[j];
				while (i < j && array[i] < tmp )
					i++;
				if(i < j)
					array[j--] = array[i];
			}
			array[i] = tmp;
			quickSort(array, 0, i - 1);
			quickSort(array, i + 1, last);	
		}
	}
	
	void swap(int[] array, int i, int j) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

	public static void main(String[] args) {
		new SortTest();
	}
}
