package com.susu.sort;

public class HeapSortApp {

    public static void main(String[] args) {
        int size = 10;
        Heap theHeap = new Heap(size);

        for (int j = 0; j < size; j++) {
            int random = (int) (java.lang.Math.random() * 100);
            Node newNode = new Node(random);
            theHeap.insertAt(j, newNode);
            theHeap.incrementSize();
        }
        System.out.print("Random: ");
        theHeap.displayArray();

        for (int j = size / 2 - 1; j >= 0; j--) {
            theHeap.trickleDown(j);
        }
        System.out.print("Heap: ");
        theHeap.displayArray();
        
        for (int j = size - 1; j >= 0 ;j--) {
            Node biggestNode = theHeap.remove();
            theHeap.insertAt(j, biggestNode);
        }
        System.out.print("Sorted: ");
        theHeap.displayArray();
    }

}
