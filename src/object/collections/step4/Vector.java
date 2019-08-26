package object.collections.step4;



public class Vector implements IList{

  Object elems[];
  int nelems;

  public class Iterator  implements IList.Iterator{
	int taille=0;
    public boolean hasNext() {
      if (taille<nelems) {
    	  return true;
      }
      else {
    	  return false;
      }
    }
    public Object  next() {
      try {
      return elems[taille];
      }finally {
    	  taille++;
      }
      
    }
  }
  
  public Iterator iterator(){
    return new Iterator();
  }
  
  public Vector() {
    elems = new Object[0];
    nelems=elems.length;
  }

  public Vector(Object array[]) {
    System.arraycopy(array, 0, elems, 0, array.length);
  }

  public Vector(Vector v) {
    System.arraycopy(v, 0, elems, 0, v.nelems);
  }

  public int length() {
    return nelems;
  }

  public Object elementAt(int index) {
    if (index >= 0 && index <elems.length) {
    	return elems[index];
    }
    else {
    	throw new ArrayIndexOutOfBoundsException();
    }
    
  }

  public Object updateAt(int index, Object niu) {
	if (index >= 0 && index < nelems) {
		Object elementremplacer = elems[index];   	
    	elems[index] = niu;
    	return elementremplacer;
	}
	else {
		throw new ArrayIndexOutOfBoundsException(); 
	}
  }

  public void insertAt(int index, Object elem) {
	  
	if(index==0) {
		Object[] tableau = new Object[nelems + 1];
        System.arraycopy(elems, 0, tableau, 1, nelems);
        tableau[0] = elem;
        elems = tableau;
        nelems++;
	}
	  
	else if (index > 0 && index < nelems) {
		Object[] tableau = new Object[nelems+1];
		System.arraycopy(elems, 0, tableau, 0, index-1);
		tableau[index] = elem;
		System.arraycopy(elems, index, tableau, index+1, nelems-index+1);
		elems = tableau;
		nelems++;
		}
	else {
		Object[] tableau = new Object[index+1];
		System.arraycopy(elems, 0, tableau, 0, nelems);
		tableau[index]= elem;
		elems = tableau;
		nelems = index+1;
	}
	
  }

  public Object removeAt(int index) {
	if (index >= 0 && index < nelems) {
	  
		Object elementremove = elems[index];
		Object[] tableau = new Object[nelems-1];
		System.arraycopy(elems, 0, tableau, 0, index);
		System.arraycopy(elems, index+1, tableau, index, nelems-index-1);
		elems = tableau;
		nelems--;
		return elementremove;
	}
	else {
		throw new ArrayIndexOutOfBoundsException();
	}
  }

  public boolean remove(Object elem) {
	  for(int i =0; i<nelems; i++) {
	    	if (elems[i] == elem) {
	    		removeAt(i);
	    		return true;
	    	}
	    }
	  return false;
  }

  public boolean contains(Object elem) {
    for(int i =0; i<nelems; i++) {
    	if (elems[i] == elem) {
    		return true;
    	}
    }
    return false;
  }

@Override
public void toArray(Object[] elems) {
	
	
}

}
