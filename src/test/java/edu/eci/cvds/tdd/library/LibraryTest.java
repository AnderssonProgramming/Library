package edu.eci.cvds.tdd.library;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
        
        Book book = new Book("The Prince", "Machiavelli", "1001");
        User user = new User();
        user.setName("Cris");
        user.setId("1001");
        Library library = new Library();
        library.addBook(book);
        library.addUser(user);  

        
        Loan loan = library.loanABook(user.getId(), book.getIsbn());  

        
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
    

    // private Library library;
    // private Book book;
    // private User user;
    // private Loan loan;

    // @Before
    // public void setUp() {
    //     library = new Library();
    //     book = new Book("Effective Java", "Joshua Bloch", "1234567890");
    //     user = new User("001", "John Doe");

    //     library.addBook(book);
    //     library.addUser(user);
    //     loan = library.loanABook(user.getId(), book.getIsbn());
    // }

    @Test
    public void testReturnLoanSuccessfully() {
        Library library = new Library();
        Book book = new Book("Effective Java", "Joshua Bloch", "1234567890");
        User user = new User();
        user.setName("Nick");
        user.setId("01");

        library.addBook(book);
        library.addUser(user);
        Loan loan = library.loanABook(user.getId(), book.getIsbn());

        Loan returnedLoan = library.returnLoan(loan);

        assertNotNull(returnedLoan);
        assertEquals(LoanStatus.RETURNED, returnedLoan.getStatus());
        assertNotNull(returnedLoan.getReturnDate());
        //assertEquals(Integer.valueOf(1), library.getBooks().get(book));
    }

    // @Test
    // public void testReturnLoanThatDoesNotExist() {
    //     Loan fakeLoan = new Loan();
    //     fakeLoan.setBook(new Book("Fake Book", "Fake Author", "0000000000"));
    //     fakeLoan.setUser(new User("002", "Jane Doe"));
    //     fakeLoan.setLoanDate(LocalDateTime.now());
    //     fakeLoan.setStatus(LoanStatus.ACTIVE);

    //     Loan returnedLoan = library.returnLoan(fakeLoan);
    //     assertNull(returnedLoan);
    // }

    // @Test
    // public void testReturnLoanAlreadyReturned() {
    //     library.returnLoan(loan); // Primer retorno exitoso
    //     Loan secondReturn = library.returnLoan(loan); // Segundo intento de retorno

    //     assertNull(secondReturn);
    // }
}
