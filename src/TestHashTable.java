import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

class TestHashTable {

	@Test
	void addEntry() {
		HashTable<String, Integer> table = new HashTable<String, Integer>();
		table.put("t", 1);
		assertEquals(1, table.size());
	}

	@Test
	HashTable<String, Integer> addEntries() {
		HashTable<String, Integer> table = new HashTable<String, Integer>();
		table.put("t", 1);
		table.put("k", 2);
		table.put("l", 1);
		table.put("a", 1);
		table.put("b", 1);
		table.put("c", 1);
		table.put("d", 2323);
		table.put("e", 1);
		table.put("f", 1);
		table.put("g", 1);
		assertEquals(10, table.size());

		return table;
	}

	@Test
	void deleteEntries() {
		HashTable<String, Integer> table = addEntries();
		table.delete("t");
		table.delete("k");
		table.delete("l");
		table.delete("a");
		table.delete("b");
		table.delete("c");
		table.delete("d");
		table.delete("e");
		table.delete("f");
		assertEquals(1, table.size());
	}

	@Test
	void testPerformance() {
		long time = currentTime();
		HashTable<String, Integer> table = new HashTable<String, Integer>();
		for (int i = 0; i < 10000; i++) {
			table.put("" + i, i);
		}
		System.out.println(timing(time));

		long time2 = currentTime();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < 10000; i++) {
			map.put("" + i, i);
		}
		System.out.println(timing(time2));

		assertEquals(1, time < time2);
	}

	private long currentTime() {
		return System.currentTimeMillis();
	}

	private long timing(long time) {
		return (currentTime() - time);
	}
}
