package edu.eci.cvds.tdd.library.book;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Pruebas Unitarias para la clase Book")
class BookTest {

    @Test
    @DisplayName("Constructor y getters deben inicializar correctamente los atributos")
    void testConstructorAndGetters() {
        // Arrange
        String expectedTittle = "El Quijote";
        String expectedAuthor = "Cervantes";
        String expectedIsbn = "1234567890";
        
        // Act
        Book book = new Book(expectedTittle, expectedAuthor, expectedIsbn);
        
        // Assert
        assertEquals(expectedTittle, book.getTittle(), "El título debe coincidir con el esperado");
        assertEquals(expectedAuthor, book.getAuthor(), "El autor debe coincidir con el esperado");
        assertEquals(expectedIsbn, book.getIsbn(), "El ISBN debe coincidir con el esperado");
    }

    @Test
    @DisplayName("Dos libros con el mismo ISBN deben ser considerados iguales")
    void testEqualsSameIsbn() {
        // Arrange
        String isbnComun = "1234567890";
        Book book1 = new Book("Libro Uno", "Autor Uno", isbnComun);
        Book book2 = new Book("Libro Diferente", "Otro Autor", isbnComun);
        
        // Act & Assert
        assertEquals(book1,book2, "Los libros con el mismo ISBN deben ser iguales");
    }

    @Test
    @DisplayName("Dos libros con ISBN diferentes no deben ser considerados iguales")
    void testEqualsDifferentIsbn() {
        // Arrange
        Book book1 = new Book("Libro Uno", "Autor Uno", "1234567890");
        Book book2 = new Book("Libro Uno", "Autor Uno", "0987654321");
        
        // Act & Assert
        assertNotEquals(book1,book2, "Los libros con ISBN diferentes no deben ser iguales");
    }
}
