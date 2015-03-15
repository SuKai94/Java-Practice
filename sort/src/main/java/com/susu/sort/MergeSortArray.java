package com.susu.sort;

/**
 * 归并排序
 * 
 * @author sukai
 *
 */
public class MergeSortArray extends SortArray {

	public MergeSortArray(int maxElems) {
		super(maxElems);
	}

	@Override
	public void sort() {
		int[] workSpace = new int[nElems];
		recMergeSort(workSpace, 0, nElems - 1);
	}

	private void recMergeSort(int[] workSpace, int lowerBound, int upperBound) {
		if (lowerBound == upperBound) {
			return;
		} else {
			int mid = (lowerBound + upperBound) / 2;
			recMergeSort(workSpace, lowerBound, mid);
			recMergeSort(workSpace, mid + 1, upperBound);
			merge(workSpace, lowerBound, mid + 1, upperBound);
		}
	}

	private void merge(int[] workSpace, int lowPtr, int highPtr, int upperBound) {
		int j = 0; // workSpace index
		int lowerBound = lowPtr;
		int mid = highPtr - 1;
		int n = upperBound - lowerBound + 1;
		while (lowPtr <= mid && highPtr <= upperBound) {
			if (array[lowPtr] < array[highPtr]) {
				workSpace[j++] = array[lowPtr++];
			} else {
				workSpace[j++] = array[highPtr++];
			}
		}
		while (lowPtr <= mid) {
			workSpace[j++] = array[lowPtr++];
		}
		while (highPtr <= upperBound) {
			workSpace[j++] = array[highPtr++];
		}
		for (j = 0; j < n; j++) {
			array[lowerBound + j] = workSpace[j];
		}
	}

	public static void main(String[] args) {
		MergeSortArray arr = new MergeSortArray(50);
		arr.insert(77);
		arr.insert(99);
		arr.insert(44);
		arr.insert(55);
		arr.insert(22);
		arr.insert(88);
		arr.insert(11);
		arr.insert(0);
		arr.insert(66);
		arr.insert(33);
		arr.display();
		arr.sort();
		arr.display();
	}
}
