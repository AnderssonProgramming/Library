package edu.eci.cvds.tdd.library.loan;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.user.User;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class LoanTest {

    @Test
    public void testGetAndSetBook() {
        Loan loan = new Loan();
        Book book = new Book("Hola", "Pedro", "4781248");
        loan.setBook(book);
        assertEquals(book, loan.getBook());
    }

    @Test
    public void testGetAndSetUser() {
        Loan loan = new Loan();
        User user = new User();
        loan.setUser(user);
        assertEquals(user, loan.getUser());
    }

    @Test
    public void testGetAndSetLoanDate() {
        Loan loan = new Loan();
        LocalDateTime loanDate = LocalDateTime.now();
        loan.setLoanDate(loanDate);
        assertEquals(loanDate, loan.getLoanDate());
    }

    @Test
    public void testGetAndSetStatus() {
        Loan loan = new Loan();
        LoanStatus status = LoanStatus.ACTIVE;
        loan.setStatus(status);
        assertEquals(status, loan.getStatus());
    }

    @Test
    public void testGetAndSetReturnDate() {
        Loan loan = new Loan();
        LocalDateTime returnDate = LocalDateTime.now().plusDays(7);
        loan.setReturnDate(returnDate);
        assertEquals(returnDate, loan.getReturnDate());
    }
}
