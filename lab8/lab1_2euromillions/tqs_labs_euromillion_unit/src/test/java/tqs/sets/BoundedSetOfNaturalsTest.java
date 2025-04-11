package tqs.sets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

/**
 * Unit tests for BoundedSetOfNaturals class.
 */
class BoundedSetOfNaturalsTest {
    private BoundedSetOfNaturals setA;
    private BoundedSetOfNaturals setB;
    private BoundedSetOfNaturals setC;

    @BeforeEach
    public void setUp() {
        setA = new BoundedSetOfNaturals(1);
        setB = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});
        setC = BoundedSetOfNaturals.fromArray(new int[]{50, 60});
    }

    @AfterEach
    public void tearDown() {
        setA = setB = setC = null;
    }

    @Test
    @DisplayName("Add valid numbers")
    void testAddValidNumbers() {
        BoundedSetOfNaturals set = new BoundedSetOfNaturals(5);
        assertTrue(set.add(1));
        assertTrue(set.add(2));
        assertTrue(set.contains(1));
        assertEquals(2, set.size());
    }

    @Test
    @DisplayName("Fail when adding numbers beyond capacity")
    void testAddBeyondCapacity() {
        BoundedSetOfNaturals set = new BoundedSetOfNaturals(2);
        set.add(1);
        set.add(2);
        assertThrows(IllegalStateException.class, () -> set.add(3));
    }

    @Test
    @DisplayName("Fail when adding negative numbers")
    void testAddNegativeNumber() {
        BoundedSetOfNaturals set = new BoundedSetOfNaturals(5);
        assertThrows(IllegalArgumentException.class, () -> set.add(-1));
    }

    @Test
    @DisplayName("Check if an element exists in the set")
    void testContainsElement() {
        BoundedSetOfNaturals set = new BoundedSetOfNaturals(5);
        set.add(4);
        assertTrue(set.contains(4));
        assertFalse(set.contains(5));
    }

    @Test
    @DisplayName("Fail when adding duplicate numbers")
    void testAddDuplicateNumber() {
        BoundedSetOfNaturals set = new BoundedSetOfNaturals(5);
        set.add(4);
        assertThrows(IllegalArgumentException.class, () -> set.add(4));
    }

    @Test
    @Disabled
    @DisplayName("Intersection: sets with common elements should intersect")
    void testIntersectsTrue() {
        BoundedSetOfNaturals set1 = BoundedSetOfNaturals.fromArray(new int[]{1, 2, 3, 4});
        BoundedSetOfNaturals set2 = BoundedSetOfNaturals.fromArray(new int[]{3, 4, 5, 6});
        
        assertTrue(set1.intersects(set2), "Sets have common elements and should intersect.");
    }

    @Test
    @DisplayName("Intersection: completely different sets should not intersect")
    void testIntersectsFalse() {
        BoundedSetOfNaturals set1 = BoundedSetOfNaturals.fromArray(new int[]{1, 2, 3});
        BoundedSetOfNaturals set2 = BoundedSetOfNaturals.fromArray(new int[]{4, 5, 6});
        
        assertFalse(set1.intersects(set2), "Distinct sets should not intersect.");
    }

    @Test
    @DisplayName("Intersection: a set should not intersect with an empty set")
    void testIntersectsWithEmptySet() {
        BoundedSetOfNaturals set1 = BoundedSetOfNaturals.fromArray(new int[]{1, 2, 3});
        BoundedSetOfNaturals emptySet = new BoundedSetOfNaturals(5);
        
        assertFalse(set1.intersects(emptySet), "A set should not intersect with an empty set.");
    }

    @Test
    @Disabled
    @DisplayName("Intersection: a set should always intersect with itself")
    void testIntersectsWithItself() {
        BoundedSetOfNaturals set = BoundedSetOfNaturals.fromArray(new int[]{1, 2, 3});
        
        assertTrue(set.intersects(set), "A set should always intersect with itself.");
    }

    @Test
    @DisplayName("Equality: sets with the same elements should be equal")
    void testEqualsTrue() {
        BoundedSetOfNaturals set1 = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30});
        BoundedSetOfNaturals set2 = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30});
        
        assertEquals(set1, set2, "Sets with the same elements should be equal.");
    }

    @Test
    @DisplayName("Equality: different sets should not be equal")
    void testEqualsFalse() {
        BoundedSetOfNaturals set1 = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30});
        BoundedSetOfNaturals set2 = BoundedSetOfNaturals.fromArray(new int[]{40, 50, 60});
        
        assertNotEquals(set1, set2, "Sets with different elements should not be equal.");
    }

    @Test
    @DisplayName("Equality: a set should not be equal to a different type")
    void testEqualsDifferentObject() {
        BoundedSetOfNaturals set = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30});
        
        assertNotEquals(set, "string", "A set should never be equal to an object of a different type.");
    }

    @Test
    @DisplayName("Equality: a set should not be equal to null")
    void testEqualsNull() {
        BoundedSetOfNaturals set = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30});
        
        assertNotEquals(set, null, "A set should never be equal to null.");
    }

    @Test
    @DisplayName("HashCode: sets with the same elements should have the same hashcode")
    void testHashCode() {
        BoundedSetOfNaturals set1 = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30});
        BoundedSetOfNaturals set2 = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30});

        assertEquals(set1.hashCode(), set2.hashCode(), "Sets with the same elements should have the same hashcode.");
    }

    @Test
    @Disabled
    @DisplayName("Removing an element should update the size")
    void testRemoveElement() {
        BoundedSetOfNaturals set = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30});
        set.remove(20);
        assertFalse(set.contains(20), "Element should be removed from the set.");
        assertEquals(2, set.size(), "Size should be reduced after removal.");
    }

    @Test
    @DisplayName("Removing a non-existent element should not affect the set")
    void testRemoveNonExistentElement() {
        BoundedSetOfNaturals set = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30});
        set.remove(40);
        assertEquals(3, set.size(), "Size should remain the same if removing a non-existent element.");
    }
}
