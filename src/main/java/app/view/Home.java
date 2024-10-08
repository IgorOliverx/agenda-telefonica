
package app.view;

import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.tools.Tool;

import app.controller.LoginController;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;

/**
 *
 * @author igorf and Edu
 */
public class Home extends javax.swing.JFrame {

    
    private javax.swing.JButton btnEntrar;
    private javax.swing.JFormattedTextField inputEmail;
    private javax.swing.JPasswordField inputSenha;
    private javax.swing.JLabel jLabel1;

    /**
     * Creates new form TelaDeInicio
     */
    public Home() {
        super("Home");
        inicializar();
        setIcon();
       
    }

    private void setIcon(){
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("./resources/icon.png")));
    }

 

  
    private void inicializar() {


        inputSenha = new javax.swing.JPasswordField();
        inputEmail = new javax.swing.JFormattedTextField();
        btnEntrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
    
      
        setVisible(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(830, 600));
        setResizable(false);
        setSize(new java.awt.Dimension(830, 600));
        getContentPane().setLayout(null);
        getContentPane().add(inputSenha);
        inputSenha.setBounds(480, 310, 170, 30);
        getContentPane().add(inputEmail);
        inputEmail.setBounds(480, 240, 170, 30);

        btnEntrar.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 14)); // NOI18N
        btnEntrar.setText("Entrar");
        btnEntrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnEntrar);
        btnEntrar.setBounds(520, 360, 130, 27);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/View/resources/wp-login.png"))); 
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 830, 550);

        pack();

        btnEntrar.addActionListener(e -> {
            LoginController operacao = new LoginController();
            String usuario = inputEmail.getText();
            String senha = inputSenha.getText();
        
            boolean loginRealizado = operacao.verificaLogin(usuario, senha);
        
            if (loginRealizado) {
                this.dispose();
                new app.view.AgendaView();
            }
        });
}
}