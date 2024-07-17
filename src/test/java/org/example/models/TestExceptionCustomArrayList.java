package org.example.models;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.Assert.assertEquals;

public class TestExceptionCustomArrayList {
    private final CustomArrayList<String> customList = new CustomArrayList<>();
    private final ArrayList<String> list = new ArrayList<>();

    @Before
    public void testAdd() {
        assertEquals(customList.add("test1"), list.add("test1"));
        assertEquals(customList.add("test2"), list.add("test2"));
        assertEquals(customList.add("test3"), list.add("test3"));
    }

    @Test
    public void testAddIndex() {
        IndexOutOfBoundsException exceptionList = assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, "test0"));
        IndexOutOfBoundsException exceptionCustomList = assertThrows(IndexOutOfBoundsException.class, () -> customList.add(-1, "test0"));
        assertEquals("Index -1 out of bounds for length 3", exceptionCustomList.getMessage());
        assertEquals("Index: -1, Size: 3", exceptionList.getMessage());
    }

    @Test
    public void testRemove() {
        IndexOutOfBoundsException exceptionList = assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        IndexOutOfBoundsException exceptionCustomList = assertThrows(IndexOutOfBoundsException.class, () -> customList.remove(-1));
        assertEquals(exceptionList.getMessage(), exceptionCustomList.getMessage());
    }
}
