package com.tanrui;

import java.util.Iterator;

public class ArrayList<AnyType> implements Iterable<AnyType>{
    private static final int DEFAULT_CAPACITY = 5;
    private int theSize;
    private AnyType [] theItems;

    public ArrayList(){
        clear();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        for (int i = 0; i < size(); i++){
            stringBuilder.append(theItems[i]);
            if (i != size()-1)
                stringBuilder.append(',');
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public void clear(){
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public int size(){
        return theSize;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public void trimToSize(){
        ensureCapacity(size());
    }

    public AnyType get(int index) throws ArrayIndexOutOfBoundsException {
        if (index < 0 || index >= size()) throw new ArrayIndexOutOfBoundsException();
        return theItems[index];
    }

    public void ensureCapacity(int newCapacity){
        if (newCapacity < size()){
            return;
        }
        AnyType[] items = theItems;
        theItems = (AnyType []) new Object[newCapacity];
        for (int i = 0; i < size(); i++){
            theItems[i] = items[i];
        }
    }

    public boolean add(AnyType x){
        add(size(), x);
        return true;
    }

    public void add(int index, AnyType x){
        if (theItems.length == size())
            ensureCapacity(size() * 2 + 1);
        for (int i = size(); i >= 0; i--){
            if (index == i) {
                theItems[index] = x;
                break;
            }
            theItems[i] = theItems[i-1];
        }
        theSize++;
    }

    public AnyType remove(int index){
        if (index < 0 || index >= size()) throw new ArrayIndexOutOfBoundsException();
        AnyType returnValue = theItems[index];
        for (int i = index; i < size()-1; i++){
            theItems[i] = theItems[i+1];
        }
        theSize--;
        return returnValue;
    }

    @Override
    public Iterator<AnyType> iterator() {
        return new ArrayListIterator<>();
    }

    private class ArrayListIterator<AnyType> implements Iterator<AnyType>{

        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public AnyType next() {
            if (!hasNext())
                throw new ArrayIndexOutOfBoundsException();
            return (AnyType) theItems[current++];
        }

        @Override
        public void remove() {
            ArrayList.this.remove(--current);
        }
    }
}
