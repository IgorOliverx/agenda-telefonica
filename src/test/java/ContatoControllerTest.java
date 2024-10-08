import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.controller.ContatoController;

public class ContatoControllerTest {

    private ContatoController contatoController;

    @BeforeEach
    public void setUp() {
        contatoController = new ContatoController();
    }

    @Test
    public void testValidarDadosComDadosValidos() {
        boolean resultado = contatoController.validarDados("Nome", "12345678900", "123456789", "email@exemplo.com", "01/01/2022");
        assertTrue(resultado);
    }

    @Test
    public void testValidarDadosComCamposVazios() {
        boolean resultado = contatoController.validarDados("", "12345678900", "123456789", "email@exemplo.com", "01/01/2022");
        assertFalse(resultado);
    }

    @Test
    public void testValidarDadosComEmailInvalido() {
        boolean resultado = contatoController.validarDados("Nome", "12345678900", "123456789", "email@exemplo", "01/01/2022");
        assertFalse(resultado);
    }

    @Test
    public void testValidarEmailComEmailValido() {
        boolean resultado = contatoController.validarEmail("email@exemplo.com");
        assertTrue(resultado);
    }

    @Test
    public void testValidarEmailComEmailInvalido() {
        boolean resultado = contatoController.validarEmail("email@exemplo");
        assertFalse(resultado);
    }
}