package god.hu.model;

import java.util.*;
import java.util.function.Consumer;

/*
实现的一个简单动态数组,参考jdk Arraylist源码,仅仅实现了本程序需要的方法
By Gentleman.Hu
Home: https://crushing.xyz
Date: 2020-9-23 09:56:09
Email: justfeelingme@gmail.com
参考: https://www.softwaretestinghelp.com/java-generic-array/
 */

public class OwnList<T> {
    private int index = 0;
    private final int DEFAULT_SIZE = 10;
    private final double DEFAULT_LOAD = 0.75;
    private T[] array;
    private int size;

    public OwnList() {
        array = (T[]) new Object[DEFAULT_SIZE];
    }

    public void add(T elem) {
        if (index >= array.length * DEFAULT_LOAD) {
            array = Arrays.copyOf(array, array.length + 10);
        }
        array[index++] = elem;
        size++;
    }
    public int size(){
        return size;
    }
    public Object remove(int index) {
        if (index < 0 || index >= size)
            return null;
        Object obj = array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1]; //把后一个值赋给前一个值
        }
        size--;
        array[size] = null; //把最后一个数据赋值为null1
        return obj;
    }

    public   T get( int   index ) {
        if ( index <0 ||   index   >=   size ) return   null ;

        return   array [ index ];
    }

    static <T> T elementAt(Object[] es, int index) {
        return (T) es[index];
    }

    public void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        final Object[] es = array;
        final int size = this.size;
        for (int i = 0;  i < size; i++)
            action.accept(elementAt(es, i));
    }

    public boolean remove(Object o) {
        final Object[] es = array;
        final int size = this.size;
        int i = 0;
        found: {
            if (o == null) {
                for (; i < size; i++)
                    if (es[i] == null)
                        break found;
            } else {
                for (; i < size; i++)
                    if (o.equals(es[i]))
                        break found;
            }
            return false;
        }
        fastRemove(es, i);
        return true;
    }
    private void fastRemove(Object[] es,int i){
        final int newSize;
        if ((newSize = size - 1) > i)
            System.arraycopy(es, i + 1, es, i, newSize - i);
        es[size = newSize] = null;
    }
}
