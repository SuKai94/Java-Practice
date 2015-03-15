package com.susu.sort;

/**
 * 希尔排序 希尔排序基于插入排序，n-增量排序
 * 
 * @author sukai
 *
 */
public class ShellSortArray extends SortArray {

	public ShellSortArray(int maxElems) {
		super(maxElems);
	}

	@Override
	public void sort() {
		int h = 1;
		while (h <= nElems / 3) {
			h = h * 3 + 1; // (1, 4, 13 , 40, 121, ...)
		}

		while (h > 0) {
			for (int outer = h; outer < nElems; outer++) {
				int temp = array[outer];
				int inner = outer;
				while (inner > h - 1 && array[inner - h] >= temp) {
					array[inner] = array[inner - h];
					inner -= h;
				}
				array[inner] = temp;
			}
			h = (h - 1) / 3;
		}
	}

	/*
	 * 首先排第一组的前两个数据，然后排第二组的前两个数据，照此下去 当所有组的前两个数据排完，再对三个数据项进行排序
	 * 实际上的序列是：（0，4），（1，5），（2，6），（3，7），（0，4，8），（1，5，9）
	 */
	public static void main(String[] args) {
		ShellSortArray arr = new ShellSortArray(50);
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
