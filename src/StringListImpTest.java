import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testng.Assert.*;

class StringListImpTest {
    private final String itemPositiv = "text";
    private final String itemNegativ = null;

    private StringList actual = new StringListImp(3);
    private StringList actual2 = new StringListImp(3);
    private List<String> expected = new ArrayList<String>();


    static Stream<Arguments> argumentsForTests() {
        return Stream.of(Arguments.of("item111", "item222", "item555", "item333"),
                Arguments.of("item222", "item222", "item666", "item333"),
                Arguments.of("item333", "item333", "item777", "item333"));
    }
    @ParameterizedTest
    @MethodSource("argumentsForTests")
    void addTest(String addItem1, String addItem2, String addItem3) {
        assertEquals(addItem1, actual.add(addItem1));
        assertEquals(addItem2, actual.add(addItem2));
        assertEquals(addItem3, actual.add(addItem3));
        expected.add(addItem1);
        expected.add(addItem2);
        expected.add(addItem3);
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
    @ParameterizedTest
    @MethodSource("argumentsForTests")
    void addNegativTest(String addItem1, String addItem2, String addItem3, String addItem4) {
        assertThrows(NullItemExeption.class, () -> actual.add(null));
        actual.add(addItem1);
        actual.add(addItem2);
        actual.add(addItem3);
        assertThrows(StorageIsFullExeption.class, () -> actual.add(addItem4));
    }
    @ParameterizedTest
    @MethodSource("argumentsForTests")
    void addIndexTest(String addItem1, String addItem2, String addItem3, String addItem4) {
        assertEquals(addItem1, actual.add(0, addItem1));
        assertEquals(addItem2, actual.add(1, addItem2));
        assertEquals(addItem3, actual.add(0, addItem3));
        expected.add(addItem3);
        expected.add(addItem1);
        expected.add(addItem2);
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
    @ParameterizedTest
    @MethodSource("argumentsForTests")
    void addIndexNegativTest(String addItem1, String addItem2, String addItem3, String addItem4) {
        assertThrows(NullItemExeption.class, () -> actual.add(null));
        actual.add(0, addItem1);
        actual.add(0, addItem2);
        assertThrows(InvalidIndexExeption.class, () -> actual.add(5, addItem3));
        assertThrows(InvalidIndexExeption.class, () -> actual.add(-1, addItem3));
        assertThrows(InvalidIndexExeption.class, () -> actual.add(actual.size() + 1, addItem3));
        actual.add(0, addItem3);
        assertThrows(StorageIsFullExeption.class, () -> actual.add(addItem4));
    }
    @ParameterizedTest
    @MethodSource("argumentsForTests")
    void setTest(String addItem1, String addItem2, String addItem3) {
        actual.add(addItem1);
        actual.add(addItem1);
        actual.add(addItem1);
        assertEquals(addItem3, actual.set(2, addItem3));
        assertEquals(addItem1, actual.set(0, addItem1));
        assertEquals(addItem2, actual.set(1, addItem2));
        expected.add(addItem1);
        expected.add(addItem2);
        expected.add(addItem3);
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
    @ParameterizedTest
    @MethodSource("argumentsForTests")
    void setNegativTest(String addItem1, String addItem2) {
        actual.add(addItem1);
        actual.add(addItem1);
        actual.add(addItem1);
        assertThrows(NullItemExeption.class, () -> actual.set(0, null));
        assertThrows(InvalidIndexExeption.class, () -> actual.set(-1, null));
        assertThrows(InvalidIndexExeption.class, () -> actual.set(actual.size(), null));
        assertThrows(InvalidIndexExeption.class, () -> actual.set(actual.size() + 1, null));
        assertThrows(InvalidIndexExeption.class, () -> actual.set(-1, addItem2));
        assertThrows(InvalidIndexExeption.class, () -> actual.set(actual.size(), addItem2));
        assertThrows(InvalidIndexExeption.class, () -> actual.set(actual.size() + 1, addItem2));
    }
    @ParameterizedTest
    @MethodSource("argumentsForTests")
    void removeItemTest(String addItem1, String addItem2, String addItem3) {
        actual.add(addItem1);
        actual.add(addItem2);
        actual.add(addItem3);
        assertEquals(addItem3, actual.remove(addItem3));
        expected.add(addItem1);
        expected.add(addItem2);
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
    @ParameterizedTest
    @MethodSource("argumentsForTests")
    void removeItemNegativTest(String addItem1, String addItem2, String addItem3) {
        actual.add(addItem1);
        actual.add(addItem1);
        actual.add(addItem1);
        assertThrows(ElementNotFoundExeption.class, () -> actual.remove(itemPositiv));
        assertThrows(NullItemExeption.class, () -> actual.remove(null));
    }
    @ParameterizedTest
    @MethodSource("argumentsForTests")
    void removeIndexTest(String addItem1, String addItem2, String addItem3) {
        actual.add(addItem1);
        actual.add(addItem2);
        actual.add(addItem3);
        assertEquals(addItem2, actual.remove(1));
        expected.add(addItem1);
        expected.add(addItem3);
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
        actual.add(addItem3);
        assertEquals(addItem3, actual.remove(2));
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
        assertEquals(addItem1, actual.remove(0));
        expected.remove(0);
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
    @ParameterizedTest
    @MethodSource("argumentsForTests")
    void removeIndexNegativTest(String addItem1, String addItem2, String addItem3) {
        actual.add(addItem1);
        actual.add(addItem2);
        actual.add(addItem3);
        assertThrows(InvalidIndexExeption.class, () -> actual.remove(actual.size()));
        assertThrows(InvalidIndexExeption.class, () -> actual.remove(-1));
        assertThrows(NullItemExeption.class, () -> actual.remove(null));
    }
    @ParameterizedTest
    @MethodSource("argumentsForTests")
    void containsTest(String addItem1, String addItem2, String addItem3) {
        actual.add(addItem1);
        actual.add(addItem2);
        actual.add(addItem3);
        assertEquals(0, actual.indexOf(addItem1));
        assertTrue(actual.contains(addItem1));
        assertTrue(actual.contains(addItem3));
        assertTrue(actual.contains(addItem2));
        assertFalse(actual.contains(itemPositiv));
        assertFalse(actual.contains(null));
    }
    @ParameterizedTest
    @MethodSource("argumentsForTests")
    void indexOfTest(String addItem1, String addItem2, String addItem3) {
        actual.add(addItem1);
        actual.add(addItem2);
        actual.add(itemPositiv);
        assertEquals(0, actual.indexOf(addItem1));
        assertEquals(2, actual.indexOf(itemPositiv));
        assertEquals(-1, actual.indexOf(null));
        assertEquals(-1, actual.indexOf(addItem3));
    }
    @ParameterizedTest
    @MethodSource("argumentsForTests")
    void lastindexOfTest(String addItem1, String addItem2, String addItem3) {
        actual.add(itemPositiv);
        actual.add(addItem2);
        actual.add(itemPositiv);
        assertEquals(1, actual.lastIndexOf(addItem2));
        assertEquals(2, actual.lastIndexOf(itemPositiv));
        assertEquals(-1, actual.lastIndexOf(null));
        assertEquals(-1, actual.lastIndexOf(addItem3));
    }
    @ParameterizedTest
    @MethodSource("argumentsForTests")
    void getTest(String addItem1, String addItem2, String addItem3) {
        actual.add(addItem1);
        actual.add(addItem2);
        actual.add(addItem3);
        assertEquals(addItem1, actual.get(0));
        assertEquals(addItem2, actual.get(1));
        assertEquals(addItem3, actual.get(2));
    }
    @ParameterizedTest
    @MethodSource("argumentsForTests")
    void getNegativTest(String addItem1, String addItem2, String addItem3) {
        actual.add(addItem1);
        actual.add(addItem2);
        actual.add(addItem3);
        assertThrows(InvalidIndexExeption.class, () -> actual.get(-1));
        assertThrows(InvalidIndexExeption.class, () -> actual.get(actual.size()));
    }
    @ParameterizedTest
    @MethodSource("argumentsForTests")
    void equalsTest(String addItem1, String addItem2, String addItem3) {
        actual.add(addItem1);
        actual.add(addItem2);
        actual.add(addItem3);
        actual2.add(addItem1);
        actual2.add(addItem2);
        actual2.add(addItem3);
        assertTrue(actual.equals(actual2));
        assertFalse(actual.equals(expected));
    }
    @ParameterizedTest
    @MethodSource("argumentsForTests")
    void equalsNegativTest(String addItem1, String addItem2, String addItem3) {
        actual.add(addItem1);
        actual.add(addItem2);
        actual.add(addItem3);
        assertThrows(NullItemExeption.class, () -> actual.equals(null));
    }
    @ParameterizedTest
    @MethodSource("argumentsForTests")
    void sizeTest(String addItem1, String addItem2, String addItem3) {
        assertEquals(0, actual.size());
        actual.add(addItem1);
        assertEquals(1, actual.size());
        actual.add(addItem2);
        assertEquals(2, actual.size());
        actual.add(addItem3);
        assertEquals(3, actual.size());
    }
    @Test
    void isEmptyTest() {
        assertTrue(actual.isEmpty());
        actual.add(itemPositiv);
        assertFalse(actual.isEmpty());
    }
    @Test
    void cleanTest() {
        actual.add(itemPositiv);
        actual.clear();
        assertTrue(actual.isEmpty());
    }
    @ParameterizedTest
    @MethodSource("argumentsForTests")
    void toArray(String addItem1, String addItem2, String addItem3) {
        actual.add(addItem1);
        actual.add(addItem2);
        actual.add(addItem3);
        expected.add(addItem1);
        expected.add(addItem2);
        expected.add(addItem3);
        assertEquals(actual.toArray(), expected.toArray());
    }
}