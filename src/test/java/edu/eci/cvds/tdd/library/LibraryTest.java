package edu.eci.cvds.tdd.library;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import edu.eci.cvds.tdd.library.book.Book;

public class LibraryTest {
    @Test
    public void testAddBookShouldTrue(){
        // arrange
        Book book = new Book("They prince", "Maquiavelo","1001");
        Library library = new Library();

        // act
        boolean result = library.addBook(book);

        // assert
        assertTrue(result);
    }

    // @Test
    // public void testAddBookShouldFalse(){
    //     // arrange
    //     Book book = new Book("They prince", "Maquiavelo","1001");
    //     Library library = new Library();

    //     // act
    //     boolean result = library.addBook(book);

    //     // assert
    //     assertFalse(result);
    // }
     
}
