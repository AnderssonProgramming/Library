package edu.eci.cvds.tdd.library;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;

@DisplayName("Library Unit Tests")
public class LibraryTest {

    private Library library;
    private Book book;
    private User user;

    @BeforeEach
    void setUp() {
        library = new Library();
        book = new Book("The Prince", "Machiavelli", "1001");
        user = new User();
        user.setName("Cris");
        user.setId("1001");
    }

    @Nested
    @DisplayName("Tests for addBook method")
    class AddBookTests {

        @Test
        @DisplayName("Should add a new book with quantity 1")
        void testAddNewBook() throws Exception {
            // Arrange: (setUp already creates a book)
            // Act
            boolean added = library.addBook(book);
            // Assert
            assertTrue(added, "addBook should return true when adding a new book");
            assertEquals(1, getBookCount(book), "Book count should be 1 after adding a new book");
        }

        @Test
        @DisplayName("Should increment quantity when adding an existing book")
        void testAddExistingBookIncrementsQuantity() throws Exception {
            // Arrange: add the book once
            library.addBook(book);
            // Act: add the same book again
            library.addBook(book);
            // Assert
            assertEquals(2, getBookCount(book), "Book count should be 2 after adding the same book twice");
        }

        /**
         * Helper method using reflection to get the book count from the private field.
         */
        private int getBookCount(Book book) throws Exception {
            Field booksField = Library.class.getDeclaredField("books");
            booksField.setAccessible(true);
            @SuppressWarnings("unchecked")
            Map<Book, Integer> booksMap = (Map<Book, Integer>) booksField.get(library);
            return booksMap.get(book);
        }
    }

    @Nested
    @DisplayName("Tests for addUser method")
    class AddUserTests {

        @Test
        @DisplayName("Should add a new user successfully")
        void testAddUser() throws Exception {
            // Act
            boolean added = library.addUser(user);
            // Assert
            assertTrue(added, "addUser should return true for a new user");
            assertTrue(userExists(user), "User should exist in the library after being added");
        }

        /**
         * Helper method using reflection to check if the user exists in the library.
         */
        private boolean userExists(User user) throws Exception {
            Field usersField = Library.class.getDeclaredField("users");
            usersField.setAccessible(true);
            @SuppressWarnings("unchecked")
            java.util.List<User> usersList = (java.util.List<User>) usersField.get(library);
            return usersList.contains(user);
        }
    }

    @Nested
    @DisplayName("Tests for loanABook method")
    class LoanABookTests {

        @BeforeEach
        void setupLoan() {
            // Ensure the library has a book and a user
            library.addBook(book);
            library.addUser(user);
        }

        @Test
        @DisplayName("Should loan a book successfully when available and user exists")
        void testLoanABookSuccessfully() {
            // Act
            Loan loan = library.loanABook(user.getId(), book.getIsbn());
            // Assert
            assertNotNull(loan, "Loan should not be null for valid inputs");
            assertEquals(LoanStatus.ACTIVE, loan.getStatus(), "Loan status should be ACTIVE");
            assertEquals(user, loan.getUser(), "Loan should have the correct user");
            assertEquals(book, loan.getBook(), "Loan should have the correct book");
            assertNotNull(loan.getLoanDate(), "Loan date should be set to current date");
        }

        @Test
        @DisplayName("Should return null when the book is not available")
        void testLoanABookWhenNotAvailable() {
            // Arrange: Loan the only available copy with one user
            Loan firstLoan = library.loanABook(user.getId(), book.getIsbn());
            assertNotNull(firstLoan, "First loan should succeed");
            
            // Create a second user to try to loan the same book
            User secondUser = new User();
            secondUser.setId("2002");
            secondUser.setName("Alice");
            library.addUser(secondUser);
            
            // Act: Try loaning again; there are no more copies available.
            Loan secondLoan = library.loanABook(secondUser.getId(), book.getIsbn());
            // Assert
            assertNull(secondLoan, "Loan should be null if the book is not available");
        }

        @Test
        @DisplayName("Should not allow the same user to have two active loans for the same book")
        void testLoanABookWhenAlreadyActiveLoanExists() {
            // Act: Loan the book for the first time
            Loan firstLoan = library.loanABook(user.getId(), book.getIsbn());
            // Act: Try to loan the same book again for the same user
            Loan secondLoan = library.loanABook(user.getId(), book.getIsbn());
            // Assert
            assertNotNull(firstLoan, "First loan should be successful");
            assertNull(secondLoan, "Second loan for the same user and book should be null");
        }

        @Test
        @DisplayName("Should return null when user does not exist")
        void testLoanABookWithNonexistentUser() {
            // Arrange: Ensure the book exists
            library.addBook(book);
            // Act
            Loan loan = library.loanABook("nonexistentUser", book.getIsbn());
            // Assert
            assertNull(loan, "Loan should be null if user does not exist");
        }

        @Test
        @DisplayName("Should return null when book does not exist")
        void testLoanABookWithNonexistentBook() {
            // Arrange: Ensure the user exists
            library.addUser(user);
            // Act
            Loan loan = library.loanABook(user.getId(), "nonexistentIsbn");
            // Assert
            assertNull(loan, "Loan should be null if book does not exist");
        }
    }

    @Nested
    @DisplayName("Tests for returnLoan method")
    class ReturnLoanTests {

        private Loan loan;

        @BeforeEach
        void setupReturnLoan() {
            library.addBook(book);
            library.addUser(user);
            loan = library.loanABook(user.getId(), book.getIsbn());
        }

        @Test
        @DisplayName("Should return a loan successfully and update the book count")
        void testReturnLoanSuccessfully() throws Exception {
            // Act
            Loan returnedLoan = library.returnLoan(loan);
            // Assert
            assertNotNull(returnedLoan, "Returned loan should not be null");
            assertEquals(LoanStatus.RETURNED, returnedLoan.getStatus(), "Loan status should be RETURNED");
            assertNotNull(returnedLoan.getReturnDate(), "Return date should be set");

            // Verify that the book count has been incremented back.
            assertEquals(1, getBookCount(book), "Book count should be 1 after returning the loan");
        }

        @Test
        @DisplayName("Should return null if the loan does not exist in the system")
        void testReturnNonexistentLoan() {
            // Arrange: Create a loan that was never added to the library
            Loan fakeLoan = new Loan();
            fakeLoan.setBook(book);
            fakeLoan.setUser(user);
            fakeLoan.setLoanDate(LocalDateTime.now());
            fakeLoan.setStatus(LoanStatus.ACTIVE);
            // Act
            Loan result = library.returnLoan(fakeLoan);
            // Assert
            assertNull(result, "Returning a non-existent loan should return null");
        }

        @Test
        @DisplayName("Should return null if the loan is already returned")
        void testReturnAlreadyReturnedLoan() {
            // Arrange: Return the loan first
            Loan returnedLoan = library.returnLoan(loan);
            assertNotNull(returnedLoan, "Initial return should be successful");
            // Act: Attempt to return it again
            Loan result = library.returnLoan(loan);
            // Assert
            assertNull(result, "Returning an already returned loan should return null");
        }

        /**
         * Helper method using reflection to get the book count from the private field.
         */
        private int getBookCount(Book book) throws Exception {
            Field booksField = Library.class.getDeclaredField("books");
            booksField.setAccessible(true);
            @SuppressWarnings("unchecked")
            Map<Book, Integer> booksMap = (Map<Book, Integer>) booksField.get(library);
            return booksMap.get(book);
        }
    }
}
