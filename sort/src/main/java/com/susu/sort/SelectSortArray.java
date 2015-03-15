package com.susu.sort;

/**
 * 选择排序
 * 
 * @author sukai
 *
 */
public class SelectSortArray extends SortArray {

	public SelectSortArray(int maxElems) {
		super(maxElems);
	}

	@Override
	public void sort() {
		int min;
		for (int out = 0; out < nElems - 1; out++) {
			min = out;
			for (int in = out + 1; in < nElems; in++) {
				if (array[in] < array[min]) {
					min = in;
				}
			}
			swap(out, min);
		}
	}
	
	public static void main(String[] args) {
		SelectSortArray arr = new SelectSortArray(50);
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
