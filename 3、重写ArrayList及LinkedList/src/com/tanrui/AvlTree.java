package com.tanrui;

import com.sun.xml.internal.ws.server.ServerRtException;
import org.omg.CORBA.Any;
import sun.org.mozilla.javascript.internal.Function;

import java.nio.BufferUnderflowException;
import java.nio.file.AtomicMoveNotSupportedException;

public class AvlTree<Anytype extends Comparable<? super Anytype>> {

    private static class AvlNode<Anytype extends Comparable<? super Anytype>>{

        AvlNode (Anytype theElement){
            this(theElement, null, null);
        }

        AvlNode (Anytype theElement, AvlNode<Anytype> theLeft, AvlNode<Anytype> theRight){
            element = theElement;
            left = theLeft;
            right = theRight;
            height = 0;
            times = 1;
        }

        @Override
        public String toString() {
            return element + "(" + times + ")";
        }

        Anytype element;
        AvlNode<Anytype> left;
        AvlNode<Anytype> right;
        int height;
        int times;
    }

    private int height(AvlNode<Anytype> node){
        return node == null ? -1 : node.height;
    }

    private AvlNode<Anytype> root;

    public AvlTree(){
        root = null;
    }

    public void makeEmpty(){
        root = null;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public boolean contains(Anytype x){
        return contains(x, root);
    }

    public Anytype findMin(){
        if (isEmpty())  throw new BufferUnderflowException();
        return findMin(root).element;
    }

    public Anytype findMax(){
        if (isEmpty())  throw new BufferUnderflowException();
        return findMax(root).element;
    }

    public void insert(Anytype x){
        root = insert(x, root);
    }

    public void remove(Anytype x){
        root = remove(x, root);
    }

    @Override
    public String toString(){
        return toString(root);
    }

    private String toString(AvlNode<Anytype> t){
        if (t == null)
            return null;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(t.toString());
        if (t.left != null || t.right != null){
            stringBuilder.append("[");
            if (t.left != null){
                stringBuilder.append(toString(t.left));
            }
            if (t.left != null || t.right != null)
                stringBuilder.append((","));
            if (t.right != null)
                stringBuilder.append(toString(t.right));
            stringBuilder.append("]");
        }
        return stringBuilder.toString();
    }

    private boolean contains(Anytype x, AvlNode<Anytype> t){
        if (t == null)
            return false;
        else {
            int result = x.compareTo(t.element);
            if (result < 0)
                return contains(x, t.left);
            else if (result > 0)
                return contains(x, t.right);
            else
                return true;
        }
    }

    private AvlNode<Anytype> findMin(AvlNode<Anytype> t){
        if (t == null)
            throw new BufferUnderflowException();
        if (t.left == null)
            return t;
        return findMin(t.left);
    }

    private AvlNode<Anytype> findMax(AvlNode<Anytype> t){
        if (t == null)
            throw new BufferUnderflowException();
        if (t.right == null)
            return t;
        return findMax(t.right);
    }

    private AvlNode<Anytype> rotateWithLeftChild(AvlNode<Anytype> t){
        AvlNode<Anytype> t2 = t.left;
        t.left = t2.right;
        t2.right = t;
        return t2;
    }

    private AvlNode<Anytype> rotateWithRightChild(AvlNode<Anytype> t){
        AvlNode<Anytype> t2 = t.right;
        t.right = t2.left;
        t2.left = t;
        t.height = Math.max(height(t.left), height(t.right)) + 1;
        t2.height = Math.max(height(t), height(t2.right)) + 1;
        return t2;
    }

    private AvlNode<Anytype> doubleRotateWithLeftChild(AvlNode<Anytype> t){
        rotateWithRightChild(t.left);
        return rotateWithLeftChild(t);
    }

    private AvlNode<Anytype> doubleRotateWithRightChild(AvlNode<Anytype> t){
        rotateWithLeftChild(t.right);
        return rotateWithRightChild(t);
    }

    private void insert(ArrayList<Anytype> arrayList){
        for (int i = 0; i < arrayList.size(); i++){
            insert(arrayList.get(i));
        }
    }

    private AvlNode<Anytype> insert(Anytype x, AvlNode<Anytype> t){
        if (t == null)
            return new AvlNode<Anytype>(x, null, null);

        int result = x.compareTo(t.element);

        if (result < 0){
            t.left = insert(x, t.left);
            if (height(t.left) - height(t.right) == 2) {
                if (x.compareTo(t.left.element) < 0)
                    t = rotateWithLeftChild(t);
                else
                    t = doubleRotateWithLeftChild(t);
            }
        }
        else if (result > 0){
            t.right = insert(x, t.right);
            if (height(t.right) - height(t.left) == 2){
                if (x.compareTo(t.right.element) > 0)
                    t = rotateWithRightChild(t);
                else
                    t = doubleRotateWithRightChild(t);
            }
        }
        else{
            t.times++;
            return t;
        }
        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }

    private AvlNode<Anytype> remove(Anytype x, AvlNode<Anytype> t){
        return null;
    }

    private void preOrder(AvlNode<Anytype> t){

    }

}
