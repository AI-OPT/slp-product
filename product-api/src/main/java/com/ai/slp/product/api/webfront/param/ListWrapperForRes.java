package com.ai.slp.product.api.webfront.param;

import com.ai.opt.base.vo.BaseResponse;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * 服务返回list包装类型
 * Created by jackieliu on 16/6/2.
 */
public class ListWrapperForRes<T> extends BaseResponse implements List {
    private static final long serialVersionUID = 1L;
    List<T> objList;

    public void setObjList(List<T> objList) {
        this.objList = objList;
    }

    @Override
    public int size() {
        return objList.size();
    }

    @Override
    public boolean isEmpty() {
        return objList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return objList.contains(o);
    }

    @Override
    public Iterator iterator() {
        return objList.iterator();
    }

    @Override
    public Object[] toArray() {
        return objList.toArray();
    }

    @Override
    public boolean add(Object o) {
        return objList.add((T)o);
    }

    @Override
    public boolean remove(Object o) {
        return objList.remove(o);
    }

    @Override
    public boolean addAll(Collection c) {
        return objList.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return objList.addAll(index,c);
    }

    @Override
    public void clear() {
        objList.clear();
    }

    @Override
    public Object get(int index) {
        return objList.get(index);
    }

    @Override
    public Object set(int index, Object element) {
        return objList.set(index,(T)element);
    }

    @Override
    public void add(int index, Object element) {
        objList.add(index,(T)element);
    }

    @Override
    public Object remove(int index) {
        return objList.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return objList.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return objList.lastIndexOf(o);
    }

    @Override
    public ListIterator listIterator() {
        return objList.listIterator();
    }

    @Override
    public ListIterator listIterator(int index) {
        return objList.listIterator();
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return objList.subList(fromIndex,toIndex);
    }

    @Override
    public boolean retainAll(Collection c) {
        return objList.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection c) {
        return objList.removeAll(c);
    }

    @Override
    public boolean containsAll(Collection c) {
        return objList.containsAll(c);
    }

    @Override
    public Object[] toArray(Object[] a) {
        return objList.toArray(a);
    }
}
