package de.thro.inf.prg3.a02;

import java.util.Iterator;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListImpl implements SimpleList, Iterable {
    
    private static class Element {
        private Object item;
        
        private Element next;
        
        public Element(Object item) {
            this.item = item;
            this.next = null;
        }
        
        public Element(Object item, Element next) {
            this.item = item;
            this.next = next;
        }
        
        public Element getNext() {
            return next;
        }
        
        public void setNext(Element next) {
            this.next = next;
        }
        
        public Object getItem() {
            return item;
        }
    }
    
    private class SimpleIteratorImpl implements Iterator {
        
        private Element next = SimpleListImpl.this.head;
        
        @Override
        public boolean hasNext() {
            return next != null;
        }
        
        @Override
        public Object next() {
            Object returnValue = next.getItem();
            next = next.getNext();
            return returnValue;
        }
    }
    
    private Element head = null;
    private int size = 0;
    
    @Override
    public Iterator iterator() {
        return new SimpleIteratorImpl();
    }
    
    @Override
    public void add(Object o) {
        Element newElement = new Element(o);
        if (size == 0) {
            head = newElement;
        } else {
            Element last = head;
            // iterate the rest of the list
            // find last element
            for (int i = 1; i < size; i++) {
                last = last.getNext();
            }
            last.setNext(newElement);
        }
        size++;
    }
    
    @Override
    public int size() {
        return this.size;
    }
    
    @Override
    public SimpleList filter(SimpleFilter filter) {
        SimpleList filteredList = new SimpleListImpl();
        for (Object element : this) {
            if (filter.include(element)) {
                filteredList.add(element);
            }
        }
        return filteredList;
    }
    
}
