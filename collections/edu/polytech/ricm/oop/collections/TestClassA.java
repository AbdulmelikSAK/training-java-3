package edu.polytech.ricm.oop.collections;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestClassA {
	  @Test
	  public void test() {
	    HashTable ac;
	    ac = new HashTable();
	    Object key = new String("One");
	    Object value = "toto";
	    ac.put(key, value);
	    Object key2 = new String("One");
	    Object value2 = ac.get(key2);
	    assertTrue(value == value2);
	  }
	}
