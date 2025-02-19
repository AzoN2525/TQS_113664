package tqs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.*;

public class TqsStackTest {
    
    private TqsStack stack;

    @BeforeEach
    public void setUp() {
        stack = new TqsStack();
    }

    @Test
    @DisplayName("A stack is empty on construction")
    public void newStackIsEmpty() {
        Assertions.assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("A stack has size 0 on construction")
    public void newStackHasSizeZero() {
        Assertions.assertEquals(0, stack.size());
    }

    @Test
    @DisplayName("After n pushes to an empty stack, n > 0, the stack is not empty and its size is n")
    public void stackIsNotEmptyAndHasSizeNAfterNPushes() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        Assertions.assertFalse(stack.isEmpty());
        Assertions.assertEquals(3, stack.size());
    }

    @Test
    @DisplayName("If one pushes x then pops, the value popped is x")
    public void pushXThenPopReturnsX() {
        stack.push(1);
        Assertions.assertEquals(1, stack.pop());
    }

    @Test
    @DisplayName("If one pushes x then peeks, the value returned is x, but the size stays the same")
    public void pushXThenPeekReturnsXAndStackSizeStaysTheSame() {
        stack.push(1);
        Assertions.assertEquals(1, stack.peek());
        Assertions.assertEquals(1, stack.size());
    }

    @Test
    @DisplayName("If the size is n, then after n pops, the stack is empty and has a size 0")
    public void afterNPopStackIsEmptyAndHasSizeZero() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.pop();
        stack.pop();
        stack.pop();
        Assertions.assertTrue(stack.isEmpty());
        Assertions.assertEquals(0, stack.size());
    }


    @Test
    @DisplayName("Popping from an empty stack throws a NoSuchElementException")
    public void popFromEmptyStackThrowsException() {
        Assertions.assertThrows(java.util.NoSuchElementException.class, () -> stack.pop());
    }

    @Test
    @DisplayName("Peeking into an empty stack throws a NoSuchElementException")
    public void peekIntoEmptyStackThrowsException() {
        Assertions.assertThrows(java.util.NoSuchElementException.class, () -> stack.peek());
    }

    @Test
    @DisplayName("For bounded stacks only, pushing onto a full stack throws an IllegalStateException")
    public void pushIntoFullStackThrowsException() {
        BoundedTqsStack stack = new BoundedTqsStack(1);
        stack.push(1);
        Assertions.assertThrows(IllegalStateException.class, () -> stack.push(2));
    }

    // ------------------------------------------------------------ popTopN tests ------------------------------------------------------------

    @Test
    @DisplayName("popTopN should return the correct nth element")
    void testPopTopN() {
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);

        assertEquals(30, stack.popTopN(2)); // Deve remover 40 e retornar 30
        assertEquals(2, stack.size()); // A pilha agora tem 2 elementos restantes
    }

    @Test
    @DisplayName("popTopN should throw exception if n is greater than stack size")
    void testPopTopNTooLarge() {
        stack.push(5);
        stack.push(10);

        assertThrows(NoSuchElementException.class, () -> stack.popTopN(3));
    }

    @Test
    @DisplayName("popTopN should throw exception when stack is empty")
    void testPopTopNEmptyStack() {
        assertThrows(NoSuchElementException.class, () -> stack.popTopN(1));
    }

    @Test
    @DisplayName("popTopN should throw exception if n is zero")
    void testPopTopNZero() {
        stack.push(5);
        assertThrows(NoSuchElementException.class, () -> stack.popTopN(0));
    }
}
