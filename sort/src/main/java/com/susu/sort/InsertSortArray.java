package com.susu.sort;

public class InsertSortArray extends SortArray {

	public InsertSortArray(int maxElems) {
		super(maxElems);
	}
	
	/*
	 * out标记未排序部分最左端的数据
	 * 内层循环中，in从out开始，向左移动，直到temp变量小于in所指的数据，就不能再往左移动了
	 * @see com.susu.sort.SortArray#sort()
	 */
	@Override
	public void sort() {
		for (int out = 1; out < nElems; out++) {
			int temp = array[out];
			int in = out;
			while(in > 0 && array[in-1] >= temp) {
				array[in] = array[in -1];
				in--;
			}
			array[in] = temp;
		}
	}
	
	public static void main(String[] args) {
		InsertSortArray arr = new InsertSortArray(50);
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
