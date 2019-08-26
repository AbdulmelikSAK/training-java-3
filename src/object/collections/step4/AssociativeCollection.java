package object.collections.step4;

public class AssociativeCollection {

	private class Pair {
		Object key, value;

		Pair(Object k, Object v) {
			key = k;
			value = v;
		}
	}

	private IList pairs;
	private boolean useEquals;

	public AssociativeCollection() {
		pairs = new List();
	}

	/**
	 * Construct an associative collection.
	 * 
	 * @param useEquals
	 *            tells the implementation to compare keys with
	 *            Object.equals(Object) or not.
	 */
	public AssociativeCollection(boolean useEquals) {
		this(); // invoke the default constructor above.
		this.useEquals = useEquals;
	}

	/**
	 * The put method adds or updates a pair to the associative collection. If the
	 * key is unknown, the pair is added, and the method returns null. If the key is
	 * known, the value is updated with the given value and the replaced value is
	 * returned.
	 */
	public Object put(Object key, Object value) {

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

	/**
	 * The get method performs a lookup, it finds if the given key is known. If it
	 * is, the associated value is returned, otherwise null is returned.
	 */
	public Object get(Object key) {
		IList.Iterator iter;
		iter = pairs.iterator();
		while (iter.hasNext()) {
			Pair pair = (Pair) iter.next();
			if (pair.key == key || pair.key.equals(key))
				return pair.value;
		}
		return null;
	}

	/**
	 * The method remove is fairly straightforward, it removes the pair identified
	 * by the given key from the collection. If the pair was unknown, null is
	 * returned. Otherwise, the value associated with the given key is returned.
	 */
	public Object remove(Object key) {
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

}
