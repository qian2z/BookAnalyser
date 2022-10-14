import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
/**
 * <b>WordListTest</b>
 * @author <qian2z>
 * @version 1.0
 * final submission 26 Nov 2021
 */
public class WordListTest {

	private WordList wl;
	
	@Before
	public void setup() {
		wl = new WordList();
	}

	@Test
	public void testCount() {
		wl.add("hi");
		assertEquals(1, wl.count());
		wl.add("hello");
		assertEquals(2, wl.count());
	}
	
	@Test
	public void testGet() {
		wl.add("hi");
		wl.add("hello");
		wl.add("bye");
		assertEquals("hi", wl.get(0));
		assertEquals("hello", wl.get(1));
		assertEquals("bye", wl.get(2));
	}
	
	@Test
	public void testSort1() {
		wl.add("c");
		wl.add("b");
		wl.add("a");
		wl.sort();
		assertEquals("a", wl.get(0));
	}
	
	@Test
	public void testSort2() {
		wl.add("ac");
		wl.add("ab");
		wl.add("a");
		wl.add("aa");
		wl.sort();
		assertEquals("a", wl.get(0));
		assertEquals("aa", wl.get(1));
	}
	
	@Test
	public void testFind() {
		wl.add("hi");
		wl.add("hello");
		wl.add("bye");
		assertTrue(wl.find("hi"));
		assertTrue(wl.find("hello"));
		assertTrue(wl.find("bye"));
		assertFalse(wl.find("HI"));
		assertFalse(wl.find("Hi"));
	}
	
	@Test
	public void testTTR() {
		wl.add("A");
		wl.add("B");
		wl.add("A");
		wl.add("C");
		wl.add("B");
		wl.calculateOccurrenceOfEachWord();
		wl.findUnique();
		assertEquals(20, wl.ttr(), 0.001);
		wl.add("D");
		wl.calculateOccurrenceOfEachWord();
		wl.findUnique();
		assertEquals(33.333, wl.ttr(), 0.001);
	}

}
