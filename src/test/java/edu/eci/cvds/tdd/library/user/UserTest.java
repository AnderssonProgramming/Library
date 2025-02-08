package edu.eci.cvds.tdd.library.user;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Pruebas Unitarias para la clase User")
class UserTest {

    @Test
    @DisplayName("Debería obtener el nombre después de establecerlo")
    void testGetName() {
        // Arrange
        User user = new User();
        String expectedName = "John Doe";
        user.setName(expectedName);
        
        // Act
        String actualName = user.getName();
        
        // Assert
        assertEquals(expectedName, actualName, "El nombre obtenido debe coincidir con el configurado");
    }

    @Test
    @DisplayName("Debería establecer el nombre correctamente")
    void testSetName() {
        // Arrange
        User user = new User();
        String expectedName = "Jane Doe";
        
        // Act
        user.setName(expectedName);
        
        // Assert
        assertEquals(expectedName, user.getName(), "El nombre configurado debe ser el esperado");
    }

    @Test
    @DisplayName("Debería obtener el ID después de establecerlo")
    void testGetId() {
        // Arrange
        User user = new User();
        String expectedId = "12345";
        user.setId(expectedId);
        
        // Act
        String actualId = user.getId();
        
        // Assert
        assertEquals(expectedId, actualId, "El ID obtenido debe coincidir con el configurado");
    }

    @Test
    @DisplayName("Debería establecer el ID correctamente")
    void testSetId() {
        // Arrange
        User user = new User();
        String expectedId = "67890";
        
        // Act
        user.setId(expectedId);
        
        // Assert
        assertEquals(expectedId, user.getId(), "El ID configurado debe ser el esperado");
    }
}