package org.example.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class TestCustomArrayList {
    private CustomArrayList<String> customList;
    private ArrayList<String> list;

    @Before
    public void testAdd() {
        customList = new CustomArrayList<>();
        customList.add("test1");
        customList.add("test2");
        customList.add("test3");

        list = new ArrayList<>();
        list.add("test1");
        list.add("test2");
        list.add("test3");
    }

    @After
    public void testGet() {
        assertEquals(customList.size(), list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(customList.get(i) + " = " + list.get(i));
            assertEquals(customList.get(i), list.get(i));
        }
    }

    @Test
    public void testClear() {
    }

    @Test
    public void testIsEmpty() {
    }

    @Test
    public void testRemove() {
//        list.remove(1);
//        customList.remove(1);
    }

    @Test
    public void testSort() {
//        list.remove(1);
//        customList.remove(1);
    }
}
