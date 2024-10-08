package app.controller;

import javax.swing.JOptionPane;

import app.connection.services.Authenticate;
import app.view.AgendaView;
import app.view.Home;

public class LoginController {
    private Home loginView;


    public LoginController(Home loginView) {
        this.loginView = loginView;
    }

    public LoginController() {
        super();
    }
    
public boolean verificaLogin(String usuario, String senha) {
    Authenticate login = new Authenticate();
    if (usuario == null || "".equals(usuario) || senha == null || "".equals(senha)) {
        JOptionPane.showMessageDialog(null, "Preencha todos os dados corretamente!", "",
                JOptionPane.WARNING_MESSAGE);
        return false;
    } else {
        boolean loginValido = login.verificarLogin(usuario, senha);

        if (loginValido) {
            new AgendaView().setVisible(true);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!", "", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }
}

    public void inicializacao(){
        Authenticate init = new Authenticate();
        init.criaTabela();
        init.inicializarBanco();
        Home loginView = new Home();
        LoginController loginController = new LoginController(loginView);
    
    }
}
