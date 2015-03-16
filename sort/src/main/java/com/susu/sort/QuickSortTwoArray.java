package com.susu.sort;

/**
 * 快速排序2 改进选择枢纽，三数据取中
 * 
 * @author sukai
 *
 */
public class QuickSortTwoArray extends SortArray {

	public QuickSortTwoArray(int maxElems) {
		super(maxElems);
	}

	@Override
	public void sort() {
		reQuickSort(0, nElems - 1);
	}

	private void reQuickSort(int left, int right) {
		int size = right - left + 1;
		if (size <= 3) {
			manualSort(left, right);
		} else {
			int median = medianOf3(left, right);
			int partition = partitionIt(left, right, median);
			reQuickSort(left, partition - 1);
			reQuickSort(partition + 1, right);
		}

	}

	private int medianOf3(int left, int right) {
		int center = (left + right) / 2;
		if (array[left] > array[center]) {
			swap(left, center);
		}
		if (array[left] > array[right]) {
			swap(left, right);
		}
		if (array[center] > array[right]) {
			swap(center, right);
		}
		swap(center, right - 1);
		return array[right - 1];
	}

	private void manualSort(int left, int right) {
		int size = right - left + 1;
		if (size <= 1) {
			return;
		}
		if (size == 2) {
			if (array[left] > array[right]) {
				swap(left, right);
			}
		} else {
			if (array[left] > array[right -1]) {
				swap(left, right -1);
			}
			if (array[left] > array[right]) {
				swap(left, right);
			}
			if (array[right - 1] > array[right]) {
				swap(right - 1, right);
			}
		}
	}

	private int partitionIt(int left, int right, int pivot) {
		int leftPtr = left - 1;
		int rightPtr = right;
		while (true) {
			while (array[++leftPtr] < pivot)
				;
			while (rightPtr > 0 && array[--rightPtr] > pivot)
				;
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
		QuickSortTwoArray arr = new QuickSortTwoArray(50);
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
