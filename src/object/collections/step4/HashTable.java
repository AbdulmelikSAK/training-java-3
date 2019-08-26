package object.collections.step4;

public class HashTable implements IMap {

	private class _Iterator implements IMap.Iterator {
		IList.Iterator iter;
		boolean keys;
		int indiceb=0;

		_Iterator(IList.Iterator iter, boolean keys) {
			this.iter = iter;
			this.keys = keys;
		}

		public boolean hasNext() {
			if (iter.hasNext()) {
				return true;
			}
			else if (!iter.hasNext() && indiceb<NBUCKETS) {
				while (!iter.hasNext() && indiceb<NBUCKETS) {
					indiceb++;
					if (indiceb != NBUCKETS) {
						iter=buckets[indiceb].iterator();
					}
				}
				if (indiceb==NBUCKETS) {
					return false;
				}
				else {
					return true;
				}
			}
			else {
				return false;
			}
		}

		public Object next() {
			Pair pair = (Pair) iter.next();
			if (keys)
				return pair.key;
			else
				return pair.value;

		}
	}

	static final int NBUCKETS = (1 << 8);

	private class Pair {
		Object key, value;

		Pair(Object k, Object v) {
			key = k;
			value = v;
		}
	}

	IList[] buckets; // Lists of pairs.

	public HashTable() {
		buckets = new IList[NBUCKETS];
		for (int i = 0; i<NBUCKETS; i++){
			buckets[i]=new List();
		}
	}

	public Iterator keys() {
		return new _Iterator(buckets[0].iterator(), true);
		/*return new Iterator() {
			int i = 0;
			boolean next;
			_Iterator iter = null;
			{
				getNext();
			}

			private void getNext() {
				while (i < buckets.length && buckets[i] != null) {
					i++;
				}
				if (i == buckets.length) {
					next = false;
				} else {
					iter = new _Iterator(buckets[i].iterator(), true);
					next = true;
				}
			}

			@Override
			public boolean hasNext() {
				return next;
			}

			@Override
			public Object next() {
				Object r = iter.next();
				if (!iter.hasNext()) {
					i++;
					getNext();
				}
				return r;
			}
		};*/
	}

	public Iterator values() {
		return new _Iterator(buckets[0].iterator(), false);
		/*return new Iterator() {
			int i = 0;
			boolean next;
			_Iterator iter = null;
			{
				getNext();
			}

			private void getNext() {
				while (i < buckets.length && buckets[i] != null) {
					i++;
				}
				if (i == buckets.length) {
					next = false;
				} else {
					iter = new _Iterator(buckets[i].iterator(), false);
					next = true;
				}
			}

			@Override
			public boolean hasNext() {
				return next;
			}

			@Override
			public Object next() {
				Object r = iter.next();
				if (!iter.hasNext()) {
					i++;
					getNext();
				}
				return r;
			}
		};*/
	}

	public int length() {
		int length=0;
		for (int i = 0 ; i < NBUCKETS; i++) {
			length += buckets[i].length();
		}
		return length;
	}

	public Object get(Object key) {
		int hash = Math.abs(key.hashCode() % buckets.length);
		IList.Iterator iter = buckets[hash].iterator();
		while (iter.hasNext()) {
			Pair elem = (Pair) iter.next();
			if (key.equals(elem.key)) {
				return elem.value;
			}
		}
		return null;
	}

	public Object put(Object key, Object value) {
		int hash = Math.abs(key.hashCode() % buckets.length);
		/*if (get(key) == null) {
			Pair elem = new Pair(key, value);
			buckets[hash].insertAt(buckets[hash].length(), elem);
			return null;
		} else {
			IList.Iterator iter = buckets[hash].iterator();
			Pair elem = null;
			while (iter.hasNext() && !elem.key.equals(key)) {
				elem = (Pair) iter.next();
			}
			Object removed = elem.value;
			elem.value = value;
			return removed;
		}*/
		List pairs = (List) buckets[hash];
		for (int i = 0; i < pairs.length(); i++) {
			Pair currentpair = (Pair) pairs.elementAt(i);
			if (currentpair.key == key) {
				Object replacedvalue = currentpair.value;
				currentpair.value = value;
				return replacedvalue;
			}
		}
		Pair addedpair = new Pair(key, value);
		pairs.insertAt(pairs.length(), addedpair);
		return null;
	}

	public Object remove(Object key) {
		/*if (get(key) == null) {
			return null;
		} else {
			int hash = Math.abs(key.hashCode() % buckets.length);
			IList.Iterator iter = buckets[hash].iterator();
			Pair elem = null;
			while (iter.hasNext() && !elem.key.equals(key)) {
				elem = (Pair) iter.next();
			}
			Object removed = elem.value;
			buckets[hash].remove(elem);
			return removed;
		}*/
		int hash = Math.abs(key.hashCode() % buckets.length);
		List pairs = (List) buckets[hash];
		for (int i = 0; i < pairs.length(); i++) {
			Pair currentpair = (Pair) pairs.elementAt(i);
			if (currentpair.key == key) {
				Object removedvalue = currentpair.value;
				pairs.removeAt(i);
				return removedvalue;
			}
		}
		return null;
	}

	public boolean contains(Object key) {
		int hash = Math.abs(key.hashCode() % buckets.length);
		IList.Iterator iter = buckets[hash].iterator();
		while (iter.hasNext()) {
			Pair elem = (Pair) iter.next();
			if (key.equals(elem.key)) {
				return true;
			}
		}
		return false;
	}
}
