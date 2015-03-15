package com.susu.sort;

/**
 * 冒泡排序
 * 
 * @author sukai
 *
 */

public class BubbleSortArray extends SortArray {

	public BubbleSortArray(int maxElems) {
		super(maxElems);
	}

	@Override
	public void sort() {
		for (int i = 0; i < nElems - 1; i++) {
			for (int j = 0; j < nElems - i - 1; j++) {
				if (array[j] > array[j + 1]) {
					swap(j, j + 1);
				}
			}
		}
	}

	public static void main(String[] args) {
		BubbleSortArray arr = new BubbleSortArray(50);
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
