package edu.eci.cvds.tdd.library;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;

public class LibraryTest {
    private Library library;
    private Book book;
    private User user;
    private Loan loan;

    @BeforeEach
    public void setUp() {
        library = new Library();
        
        user = new User();
        user.setName("Cris");        
        user.setId("1001");

        book = new Book("They prince", "Maquiavelo","1001");        

        library.addBook(book);
        library.addUser(user);  

        loan = library.loanABook(user.getId(), book.getIsbn());  
    }

    @Test
    public void testAddBookShouldTrue(){
        // arrange

        // act
        boolean result = library.addBook(book);

        // assert
        assertTrue(result);
    }

    @Test
    public void testLoanABookShouldTrue() {

    
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
    

    @Test
    public void testReturnLoanSuccessfully() {
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
