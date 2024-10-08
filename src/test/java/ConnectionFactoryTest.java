import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import org.mockito.MockedStatic;

import app.connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryTest {

    private static final String url = "jdbc:mysql://localhost:3306/test";
    private static final String usuario = "root";
    private static final String senha = "password";

    @Test
    public void testGetConnectionSuccess() throws SQLException {
        Connection mockConnection = mock(Connection.class);
        try (MockedStatic<DriverManager> mockedDriverManager = mockStatic(DriverManager.class)) {
            mockedDriverManager.when(() -> DriverManager.getConnection(url, usuario, senha)).thenReturn(mockConnection);

            Connection connection = ConnectionFactory.getConnection();
            assertNotNull(connection);
            assertEquals(mockConnection, connection);
        }
    }

    @Test
    public void testGetConnectionFailure() throws SQLException {
        try (MockedStatic<DriverManager> mockedDriverManager = mockStatic(DriverManager.class)) {
            mockedDriverManager.when(() -> DriverManager.getConnection(url, usuario, senha)).thenThrow(new SQLException());

            assertThrows(RuntimeException.class, () -> ConnectionFactory.getConnection());
        }
    }
}