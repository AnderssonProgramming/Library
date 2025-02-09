package edu.eci.cvds.tdd.library.loan;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.user.User;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Pruebas Unitarias para la clase Loan")
class LoanTest {

    @Test
    @DisplayName("Debería obtener y establecer el libro correctamente")
    void testGetAndSetBook() {
        // Arrange
        Loan loan = new Loan();
        Book expectedBook = new Book("Hola", "Pedro", "4781248");
        
        // Act
        loan.setBook(expectedBook);
        
        // Assert
        assertEquals(expectedBook, loan.getBook(), "El libro obtenido debe coincidir con el establecido");
    }

    @Test
    @DisplayName("Debería obtener y establecer el usuario correctamente")
    void testGetAndSetUser() {
        // Arrange
        Loan loan = new Loan();
        User expectedUser = new User();
        
        // Act
        loan.setUser(expectedUser);
        
        // Assert
        assertEquals(expectedUser, loan.getUser(), "El usuario obtenido debe coincidir con el establecido");
    }

    @Test
    @DisplayName("Debería obtener y establecer la fecha de préstamo correctamente")
    void testGetAndSetLoanDate() {
        // Arrange
        Loan loan = new Loan();
        LocalDateTime expectedLoanDate = LocalDateTime.now();
        
        // Act
        loan.setLoanDate(expectedLoanDate);
        
        // Assert
        assertEquals(expectedLoanDate, loan.getLoanDate(), "La fecha de préstamo debe coincidir con la establecida");
    }

    @Test
    @DisplayName("Debería obtener y establecer el estado del préstamo correctamente")
    void testGetAndSetStatus() {
        // Arrange
        Loan loan = new Loan();
        LoanStatus expectedStatus = LoanStatus.ACTIVE;
        
        // Act
        loan.setStatus(expectedStatus);
        
        // Assert
        assertEquals(expectedStatus, loan.getStatus(), "El estado del préstamo debe coincidir con el establecido");
    }

    @Test
    @DisplayName("Debería obtener y establecer la fecha de devolución correctamente")
    void testGetAndSetReturnDate() {
        // Arrange
        Loan loan = new Loan();
        LocalDateTime expectedReturnDate = LocalDateTime.now().plusDays(7);
        
        // Act
        loan.setReturnDate(expectedReturnDate);
        
        // Assert
        assertEquals(expectedReturnDate, loan.getReturnDate(), "La fecha de devolución debe coincidir con la establecida");
    }
}