package com.susu.sort;

public abstract class SortArray {

	protected int[] array;
	protected int nElems;

	public SortArray(int maxElems) {
		array = new int[maxElems];
		nElems = 0;
	}

	public void insert(int value) {
		array[nElems] = value;
		nElems++;
	}

	protected void swap(int indexOne, int indexTwo) {
		int temp = array[indexOne];
		array[indexOne] = array[indexTwo];
		array[indexTwo] = temp;
	}

	public void display() {
		for (int i = 0; i < nElems; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	public abstract void sort();
}
