
/*This class represents a hash node that is used in a hash table (chained collission)*/
public class Node<K, V> {

	private K key;
	private V value;
	private Node<K, V> next;

	/* We encapsulate variables and only set key and value in the constructor */
	public Node(K key, V value) {
		this.key = key;
		this.value = value;
		this.next = null;

	}

	public K getKey() {
		return key;
	}

	@Override
	public String toString() {
		return "Node [key=" + key + ", value=" + value +"]";
	}

	public void setValue(V value) {
		this.value = value;
	}
	
	public V getValue() {
		return value;
	}

	/*
	 * We need to be able to set next after the node has been created in relation to
	 * a collission
	 */
	public void setNext(Node<K, V> next) {
		this.next = next;
	}

	public Node<K, V> getNext() {
		return next;
	}
}
