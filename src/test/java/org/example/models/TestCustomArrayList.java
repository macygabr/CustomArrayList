package org.example.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestCustomArrayList {
    private CustomArrayList<String> customList;
    private ArrayList<String> list;

    @Before
    public void testAdd() {
        customList = new CustomArrayList<>();
        list = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            String element = i + "_test";
            assertEquals(customList.add(element), list.add(element));
        }
    }

    @After
    public void testGet() {
        assertEquals(customList.size(), list.size());
        for (int i = 0; i < list.size(); i++) {
            assertEquals(customList.get(i), list.get(i));
        }
    }

    @Test
    public void testAddIndex() {
        customList.add(0, "test0");
        list.add(0, "test0");
    }

    @Test
    public void testAddAll() {
        List<String> newElements = Arrays.asList("t", "e", "s", "t");
        assertEquals(list.addAll(newElements), customList.addAll(newElements));
    }

    @Test
    public void testClear() {
        list.clear();
        customList.clear();
    }

    @Test
    public void testIsEmpty() {
        assertEquals(customList.isEmpty(), list.isEmpty());
        list.clear();
        customList.clear();
        assertEquals(customList.isEmpty(), list.isEmpty());
    }

    @Test
    public void testRemove() {
        assertEquals(list.remove(1), customList.remove(1));
        assertEquals(list.remove("test2"), customList.remove("test2"));
    }

    @Test
    public void testSort() {
        Comparator<String> strComparator = Comparator.reverseOrder();
        customList.sort(strComparator);
        list.sort(strComparator);
    }
}
