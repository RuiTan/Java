package com.tanrui;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<AnyType> implements Iterable<AnyType> {

    private static class Node<AnyType>{
        Node(AnyType data, Node<AnyType> prev, Node<AnyType> next){
            this.data = data;
            this.prev = prev;
            this.next = next;
            this.time = 1;
        }
        public void ariseOnce(){
            ++time;
        }
        public int getTime(){
            return time;
        }
        AnyType data;
        int time;
        Node<AnyType> prev;
        Node<AnyType> next;
    }
    private int theSize;
    private int deleted = 0;
    private int modCount = 0;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;

    public LinkedList(){
        clear();
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (Node<AnyType> temp = beginMarker.next; temp!=endMarker; temp = temp.next){
            if (temp.time != 0) {
                stringBuilder.append(temp.data + ":" + temp.time);
                if (temp.next != endMarker)
                    stringBuilder.append(',');
            }
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public void clear(){
        theSize = 0;
        ++modCount;
        beginMarker = new Node<>(null, null, null);
        endMarker = new Node<>(null, beginMarker, null);
        beginMarker.next = endMarker;
    }

    public int size(){
        return theSize;
    }

    public boolean isEmpty(){
        return size()==0;
    }

    public boolean add(AnyType x){
        add(size(), x);
        return true;
    }

    public void checkIndex(int index, int begin, int end){
        if (index < begin || index > end)
            throw new ArrayIndexOutOfBoundsException();
    }

    public void add(int index, AnyType x){
        checkIndex(index, 0, size());
        Node<AnyType> temp = getNodeByData(x);
        if (temp != null){
            temp.time++;
        }else if (size() == 0){
            theSize++;
            beginMarker.next = endMarker.prev = new Node<>(x, beginMarker, endMarker);
        } else{
            theSize++;
            temp = getNode(index-1);
            temp.next = temp.next.prev = new Node<>(x, temp, temp.next);
        }
        ++modCount;
    }

    public AnyType get(int index){
        checkIndex(index, 0, size());
        return getNode(index).data;
    }

    public AnyType set(int index, AnyType x){
        checkIndex(index, 0, size());
        Node<AnyType> node = getNode(index);
        AnyType oldData = node.data;
        node.data = x;
        return oldData;
    }

    public AnyType remove(int index){
        checkIndex(index, 0, size()-1);
        Node<AnyType> temp = getNode(index);
        remove(temp);
        return temp.data;
    }

    private <AnyType> void remove(Node<AnyType> node) {
        node.time--;
        if (deleted > size()/2)
            removeNodes();
        ++modCount;
    }

    public void removeNodes(){
        Node<AnyType> temp = beginMarker.next;
        for (int i = 0; i < size(); i++){
            if (temp.time == 0){
                --theSize;
                temp.next.prev = temp.prev;
                temp.prev.next = temp.next;
            }
        }
    }

    private Node<AnyType> getNode(int index){
        checkIndex(index, -1, size()+1);
        Node<AnyType> temp = beginMarker;
        for (int i = -1; i < index; i++){
            temp = temp.next;
        }
        return temp;
    }

    private Node<AnyType> getNodeByData(AnyType data){
        if (size() == 0)
            return null;
        Node<AnyType> temp = beginMarker.next;
        for (int i = 0; i < size(); i++){
            if (temp.data.equals(data)){
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    public LinkedListIterator<AnyType> iterator(){
        return new LinkedListIterator<>();
    }

    private class LinkedListIterator<AnyType> implements Iterator<AnyType>{
        private Node<AnyType> current = (Node<AnyType>) beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current != endMarker;
        }

        @Override
        public AnyType next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            if (expectedModCount != modCount){
                throw new ConcurrentModificationException();
            }
            AnyType data = current.data;
            current = current.next;
            okToRemove = true;
            return data;
        }

        @Override
        public void remove() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (!okToRemove)
                throw new IllegalStateException();
            LinkedList.this.remove(current.prev);
            okToRemove = false;
            expectedModCount++;
        }
    }
}
