import java.util.ArrayList;

/*An implementation of chained hashing. The arraylist that stores the 
 * objects has a restriction of a maximal size of Integer.MAX_VALUE,
 * and storing more than a certain amount of objects will therefore result in longer entry chains*/
public class HashTable<K, V> {

	private ArrayList<Node<K, V>> table;
	private int size;
	private int capacity;
	final static float upperLoadFactor = 0.75f;
	final static float lowerLoadFactor = 0.25f;

	public HashTable() {
		capacity = 8;
		size = 0;
		table = new ArrayList<>(capacity);
		for (int i = 0; i < capacity; i++)
			table.add(null);

	}

	public HashTable(int capacity) {
		if (capacity <= 0)
			capacity = 8;
		table = new ArrayList<>(capacity);
	}

	public void put(K key, V value) {

		int code = hashValue(key);
		Node<K, V> current = table.get(code);

		while (current != null) {
			/* if the key already exist we overwrite the value */
			if (current.getKey().equals(key)) {
				current.setValue(value);
				return;
			}
			current = current.getNext();
		}

		/*
		 * if we reached this point the key did not exist and we add a new node to the
		 * beginning of the list!
		 */
		Node<K, V> newNode = new Node<K, V>(key, value);
		newNode.setNext(table.get(code));
		table.set(code, newNode);
		size++;

		/* Lastly we check if we need to re-hash the table */
		if (size / (float) capacity >= upperLoadFactor)
			reHash(capacity < Integer.MAX_VALUE / 2 ? 2 * capacity : Integer.MAX_VALUE);
	}

	public Node<K, V> get(K key) {
		int code = hashValue(key);
		Node<K, V> temp = table.get(code);

		while (temp != null) {
			if (temp.getKey().equals(key))
				return temp;
			temp = temp.getNext();
		}
		return null;
	}

	public void delete(K key) {
		int code = hashValue(key);
		Node<K, V> temp = table.get(code);
		Node<K, V> previous = null;

		while (temp != null) {
			if (temp.getKey().equals(key)) {
				/* if previous is null it is the the first node in the collission list */
				if (previous == null) {
					table.set(code, null);
				} else
					/*
					 * else the next node of the previous is mapped to the next node of the current
					 * the current is set to null (we expect the garbage collector to clean up)
					 */
					previous.setNext(temp.getNext());

				size--;
				break;
			}
			previous = temp;
			temp = temp.getNext();
		}

		/* Lastly we check if we need to re-hash the table */
		if (size / (float) capacity <= lowerLoadFactor) {
			reHash(capacity / 2);
		}
		return;
	}

	public int size() {
		return size;
	}

	private int hashValue(K key) {
		int code = key.hashCode() % capacity;
		if (code < 0)
			code *= -1;
		return code;
	}

	private void reHash(int capacity) {
		/* Double the table size and re-hash every item */
		this.capacity = capacity;

		ArrayList<Node<K, V>> temp = table;
		table = new ArrayList<>();
		size = 0;
		for (int i = 0; i < capacity; i++) {
			table.add(null);
		}

		for (Node<K, V> current : temp) {
			while (current != null) {
				/* re-hash and insert into new table */
				put(current.getKey(), current.getValue());
				current = current.getNext();
			}

		}

	}

	@Override
	public String toString() {
		String tv = "";
		for (int i = 0; i < table.size(); i++) {

			Node<K, V> current = table.get(i);
			if (current == null)
				continue;

			while (current != null) {

				tv += current.toString() + " ";

				current = current.getNext();
			}
			tv += "\n";
		}

		return "HashTable [table=" + tv + "\nsize=" + size + "\ncapacity=" + capacity + "]";
	}

}
