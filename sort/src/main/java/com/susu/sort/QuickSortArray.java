package com.susu.sort;

/**
 * 快速排序
 * @author sukai
 *
 */
public class QuickSortArray extends SortArray{

	public QuickSortArray(int maxElems) {
		super(maxElems);
	}

	@Override
	public void sort() {
		reQuickSort(0, nElems - 1);
	}
	
	private void reQuickSort(int left, int right) {
		if (right - left <= 0) {
			return;
		} else {
			int pivot = array[right];
			int partition = partitionIt(left, right, pivot);
			reQuickSort(left, partition - 1);
			reQuickSort(partition + 1,right);
		}
	}
	
	private int partitionIt(int left, int right, int pivot) {
		int leftPtr = left - 1;
		int rightPtr = right;
		while (true) {
			while (array[++leftPtr] < pivot);
			while (rightPtr > 0 && array[--rightPtr] > pivot);
			if (leftPtr >= rightPtr) {
				break;
			} else {
				swap(leftPtr, rightPtr);
			}
		}
		swap(leftPtr, right);
		return leftPtr;
	}
	
	public static void main(String[] args) {
		QuickSortArray arr = new QuickSortArray(50);
		arr.insert(77);
		arr.insert(99);
		arr.insert(44);
		arr.insert(55);
		arr.insert(22);
		arr.insert(88);
		arr.insert(11);
		arr.insert(00);
		arr.insert(66);
		arr.insert(33);
		arr.display();
		arr.sort();
		arr.display();
	}

}
