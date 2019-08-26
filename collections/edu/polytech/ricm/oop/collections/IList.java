package edu.polytech.ricm.oop.collections;

public interface IList {

  public interface Iterator {

    public boolean hasNext();

    public Object next();
  }

  Iterator iterator();

  int length();

  Object elementAt(int index);

  Object updateAt(int index, Object niu);

  void insertAt(int index, Object elem);

  Object removeAt(int index);

  boolean remove(Object elem);

  boolean contains(Object elem);

  void toArray(Object elems[]);

}