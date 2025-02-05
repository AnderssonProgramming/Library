package edu.eci.cvds.tdd.library;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;

/**
 * Library responsible for manage the loans and the users.
 */
public class Library {

    private final List<User> users;
    private final Map<Book, Integer> books;
    private final List<Loan> loans;

    public Library() {
        users = new ArrayList<>();
        books = new HashMap<>();
        loans = new ArrayList<>();
    }

    /**
     * Adds a new {@link edu.eci.cvds.tdd.library.book.Book} into the system, the book is store in a Map that contains
     * the {@link edu.eci.cvds.tdd.library.book.Book} and the amount of books available, if the book already exist the
     * amount should increase by 1 and if the book is new the amount should be 1, this method returns true if the
     * operation is successful false otherwise.
     *
     * @param book The book to store in the map.
     *
     * @return true if the book was stored false otherwise.
     */
    // public boolean addBook(Book book) {
    //     //TODO Implement the logic to add a new book into the map.
    //     for (Book b : books.keySet()){
    //         String currentBookName = b.getTittle();
    //         if (book.getTittle().equals(currentBookName)){
    //             int amount = books.get(currentBookName);
    //             books.put(book, amount+1);
    //             return true;
    //         }else {
    //             books.put(book, 1);
    //             return true;
    //         }
    //     }
    //     return false;
    // }

    public boolean addBook(Book book) {

        if (book instanceof Book){
                        
            if (books.containsKey(book)) {  // Verificar si el libro ya existe
                int amount = books.get(book);
                books.put(book, amount + 1);  // Incrementar la cantidad
            } else {
                books.put(book, 1);  // Agregar nuevo libro con cantidad 1
            }
            return true;

        }
        return false;
    }
    /**
     * This method creates a new loan with for the User identify by the userId and the book identify by the isbn,
     * the loan should be store in the list of loans, to successfully create a loan is required to validate that the
     * book is available, that the user exist and the same user could not have a loan for the same book
     * {@link edu.eci.cvds.tdd.library.loan.LoanStatus#ACTIVE}, once these requirements are meet the amount of books is
     * decreased and the loan should be created with {@link edu.eci.cvds.tdd.library.loan.LoanStatus#ACTIVE} status and
     * the loan date should be the current date.
     *
     * @param userId id of the user.
     * @param isbn book identification.
     *
     * @return The new created loan.
     */
    public Loan loanABook(String userId, String isbn) {
        // Verificar si el libro está disponible
        Book bookToLoan = null;
        for (Book book : books.keySet()) {
            if (book.getIsbn().equals(isbn)) {
                bookToLoan = book;
                break;
            }
        }

        if (bookToLoan == null || books.get(bookToLoan) <= 0) {
            // Si el libro no existe o no está disponible
            return null;
        }

        // Verificar si el usuario existe
        User userToLoan = null;
        for (User user : users) {
            if (user.getId().equals(userId)) {
                userToLoan = user;
                break;
            }
        }

        if (userToLoan == null) {
            // Si el usuario no existe
            return null;
        }

        // Verificar si el usuario ya tiene un préstamo activo para el mismo libro
        for (Loan existingLoan : loans) {
            if (existingLoan.getUser().equals(userToLoan) && existingLoan.getBook().equals(bookToLoan) 
                && existingLoan.getStatus() == LoanStatus.ACTIVE) {
                // Si el usuario ya tiene un préstamo activo para el libro
                return null;
            }
        }

        // Crear un nuevo préstamo
        Loan newLoan = new Loan();
        newLoan.setBook(bookToLoan);
        newLoan.setUser(userToLoan);
        newLoan.setLoanDate(LocalDateTime.now());
        newLoan.setStatus(LoanStatus.ACTIVE);

        // Disminuir la cantidad de libros disponibles
        books.put(bookToLoan, books.get(bookToLoan) - 1);

        // Guardar el nuevo préstamo
        loans.add(newLoan);

        return newLoan;  // Devolver el préstamo creado
    }

    /**
     * This method return a loan, meaning that the amount of books should be increased by 1, the status of the Loan
     * in the loan list should be {@link edu.eci.cvds.tdd.library.loan.LoanStatus#RETURNED} and the loan return
     * date should be the current date, validate that the loan exist.
     *
     * @param loan loan to return.
     *
     * @return the loan with the RETURNED status.
     */
    public Loan returnLoan(Loan loan) {
        //TODO Implement the login of loan a book to a user based on the UserId and the isbn.
        return null;
    }

    public boolean addUser(User user) {
        return users.add(user);
    }

}