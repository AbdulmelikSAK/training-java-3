package object.collections.step4;

public class List implements IList{

	class Cell {
		Object elem;
		Cell next;

		Cell(Cell prev, Object elem) {
			next = prev.next;
			prev.next = this;
			this.elem = elem;
		}

		Cell(Object elem) {
			this.elem = elem;
		}
	}

	Cell head; // head of the list of cells
	int ncells; // number of cells in the list

	public class Iterator implements IList.Iterator{
		List l = List.this;
		Cell iter=head;
		public boolean hasNext() {
			if (iter!=null) {
				return true;
			}
			else { 
				return false;
			}
		}

		public Object next() {
			try {
			return iter.elem;
			}finally {
				iter = iter.next;
			}
		}
	}

	public Iterator iterator() {
		return new Iterator();
	}

	public List() {
		ncells = 0;
	}

	public List(Object[] array) {
		if (array.length==0) {head=null; return;}
		if (array.length!=0) {			
			for (int i = 0 ; i < ncells ; i++) {
				insertAt(i,array[i]);
			}
		}

	}

	public List(List l) {
		List L = new List();
		L = l;
	}

	private void check(int idx) {
		if (idx < 0 || idx >= ncells)
			throw new IndexOutOfBoundsException(Integer.toString(idx));
	}

	public int length() {
		return ncells;
	}

	public Object elementAt(int index) {
		check(index);
		Cell current = head;
		
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.elem;
	}

	public Object updateAt(int index, Object niu) {
		check(index);
		Cell current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		Object v = current.elem;
		current.elem = niu;
		return v;

	}

	public void insertAt(int index, Object elem) {
		Cell nouv;
		if(index==0) {
			if (ncells >0) {
				Cell pre = head;
				head = new Cell(elem);
				head.next=pre;
				ncells++;
			}
			else {
				head = new Cell(elem);
				ncells++;
			}
		}else if (index > 0 && index < ncells) {
			Cell current = head;
			for (int i = 1; i < index; i++) {
				current = current.next;
			}
			nouv = new Cell(current, elem);
			ncells++;
		} else if (index >= ncells) {
			Cell der = head;
			for (int i = 1; i < index; i++) {
				if ( der.next == null) {
					nouv = new Cell(der, null);
					ncells++;
					der = nouv;
				}
				else {
				der = der.next;
				}
			}
			new Cell(der, elem);
			ncells++;
		}
	}

	public Object removeAt(int index) {
		if (index > 0 && index < ncells) {
			
			Cell avant = head;
			for (int i = 1; i < index; i++) {
				avant = avant.next;
			}
			
			Cell removed = avant.next;
			avant.next=avant.next.next;
			ncells--;
			return removed.elem;
		}
		else if (index == 0) {
			Cell removed = head;
			if (head.next==null) {
				head=null;
				ncells--;
			}
			else {
			head = head.next;
			ncells--;
			}
			return removed.elem;
		}
		else {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
	}

	public boolean remove(Object elem) {
		Cell current = head;
		for (int i = 0; i <ncells; i++) {
			if (current.elem == elem) {
				removeAt(i);
				return true;
			}
			current = current.next;
		}
		return false;
	}

	public boolean contains(Object elem) {
		Cell current = head;
		for (int i = 0; i <ncells; i++) {
			if (current.elem == elem) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	@Override
	public void toArray(Object[] elems) {
		
		
	}


}
