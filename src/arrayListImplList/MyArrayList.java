package arrayListImplList;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyArrayList implements List {

    Object [] array = new Object [10];
    int size = 0;
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {
        size++;
        Object [] array1 = new Object[array.length+2];
        if(array.length == size){
            for (int i = 0; i < array.length; i++) {
                array1[i] = array[i];
            }
            array = array1;
        }
        for (int i = 0; i < array.length; i++) {
            if(array[i] == null){
                array[i] = o;

                return true;
            }

        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if(o == array[i]){
                remove(i);
                return true;
            }

        }
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Object get(int index) {
    if(index> size){
    throw new ArrayIndexOutOfBoundsException("This index is not allowed");
}
        return array[index];
    }

    @Override
    public Object set(int index, Object element) {
        return null;
    }

    @Override
    public void add(int index, Object element) {
        get(index);
       array[index] = element;
       size++;
    }

    @Override
    public Object remove(int index) {
        get(index);
        Object temp =array[index];
        Object[]array2=new Object [array.length-1];
        for (int i = 0; i < array2.length ; i++) {
            if(i >= index){
                array2[i] = array[i+1];

            }else if(i != index){
                    array2[i] = array[i];
                }
            }
        array=array2;
        size--;
        remove(temp);
        return index;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {

        return new Object[0];
    }
}
