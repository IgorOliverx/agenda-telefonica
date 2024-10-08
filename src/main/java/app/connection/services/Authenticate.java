package app.connection.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.connection.ConnectionFactory;

public class Authenticate {
    
     private Connection connection;
    
  
     public Authenticate(){
         this.connection = ConnectionFactory.getConnection();
     }
 
  
     private boolean tabelaExiste() {
         boolean tabelaExiste = false;
         String verificaTabela = "SELECT 1 FROM login LIMIT 1";
     
         try (Statement stmt = connection.createStatement()) {
             ResultSet rs = stmt.executeQuery(verificaTabela);
             tabelaExiste = true;
         } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
         }
     
         return tabelaExiste;
     }

   
     public void criaTabela(){
         String sql = "CREATE TABLE IF NOT EXISTS login (SENHA VARCHAR(30), USUARIO VARCHAR(25) PRIMARY KEY)";
         try (Statement stmt = this.connection.createStatement()) {
             stmt.execute(sql);
         } catch (SQLException e) {
             throw new RuntimeException("Erro ao criar a tabela: " + e.getMessage(), e);
         } 
     }

     public void inicializarBanco() {
         try{
             if (!tabelaExiste()) {
                 criaTabela();
                 preencherTabela("adm", "adm");
             }
         } catch(Exception e){
            throw new RuntimeException(e.getMessage());
         }
     }
 
    
 
    
 
     // Método para preencher a tabela com credenciais padrão
     public void preencherTabela(String usuario, String senha) {
         PreparedStatement stmt = null;
         String sql = "INSERT INTO login (usuario, senha) VALUES (?, ?)";
         try {
             stmt = connection.prepareStatement(sql);
             stmt.setString(1, usuario);
             stmt.setString(2, senha);
             stmt.executeUpdate();
         } catch (SQLException e) {
             throw new RuntimeException("Erro ao criar credenciais." + e.getMessage());
         } 
     }
 
     public boolean verificarLogin(String usuario, String senha){
        PreparedStatement stmt = null;
        boolean loginValido = false;
        ResultSet resultado = null;
        String sql = "SELECT * FROM login WHERE usuario = ? AND senha = ?";
    
        try {

            stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            resultado = stmt.executeQuery();
    
            if(resultado.next()){
                loginValido = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar credenciais." + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
        return loginValido;
    }
    
}