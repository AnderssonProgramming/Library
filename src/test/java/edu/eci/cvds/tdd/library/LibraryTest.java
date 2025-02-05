package edu.eci.cvds.tdd.library;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;

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

    @Test
    public void testLoanABookShouldTrue() {
        // arrange
        Book book = new Book("The Prince", "Machiavelli", "1001");
        User user = new User();
        user.setName("Cris");
        user.setId("1001");
        Library library = new Library();
        library.addBook(book);
        library.addUser(user);  // Asegurarnos de agregar al usuario a la biblioteca

        // act
        Loan loan = library.loanABook(user.getId(), book.getIsbn());  // Usar userId e isbn como parámetros

        // assert
        assertTrue(loan != null && loan.getStatus() == LoanStatus.ACTIVE);
    }
    
    // @Test
    // public void testReturnLoanShouldPass(){

    //     Book book = new Book("They prince", "Maquiavelo","1001");
    //     User user = new User();
    //     user.setName("Cris");
    //     user.setId("1001");


    //     Loan loan = new Loan();

    // }
}
