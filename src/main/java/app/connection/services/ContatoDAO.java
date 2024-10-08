package app.connection.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.connection.ConnectionFactory;
import app.connection.persistence.DataAccessObject;
import app.model.Contato;

public class ContatoDAO extends DataAccessObject<Contato> {

    // Atributos de classe
    private Connection connection;
    private List<Contato> contatos;
    

    

    public ContatoDAO(){
        this.connection = ConnectionFactory.getConnection();
    }

   

    @Override
    public void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS contatos (NOME VARCHAR (255), CPF VARCHAR(20) PRIMARY KEY, TELEFONE VARCHAR(255), EMAIL VARCHAR(255), DATA VARCHAR(255))";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar tabela " + e.getMessage() + e.getSQLState());
        } finally {
            app.connection.ConnectionFactory.closeConnection(this.connection);
        }

    }

  
    @Override
    public Contato procurarPorId(String cpf) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Contato contato = null; 
    
        try {
            stmt = connection.prepareStatement("SELECT * FROM contatos WHERE cpf = ?");
            stmt.setString(1, cpf);
            rs = stmt.executeQuery();
    
            if (rs.next()) { 
                contato = new Contato(
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("data")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("contato não encontrado" + e.getMessage() + e.getSQLState());
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
        }
    
        return contato; 
    }
    

    @Override
    public List<Contato> listarTodos() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        contatos = new ArrayList<>();

        try {
            stmt = connection.prepareStatement("SELECT * FROM contatos");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Contato contato = new Contato(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("data"));
                contatos.add(contato);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar todos os contatos "+ e.getMessage() + e.getSQLState());
        } finally {
            app.connection.ConnectionFactory.closeConnection(connection, stmt, rs);
        }
        return contatos;
    }


    @Override
    public void cadastrar(Contato contato) {
        PreparedStatement stmt = null;
        String sql = "INSERT INTO contatos (nome, cpf, telefone, email, data) VALUES (?, ?, ?, ?, ?)";

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getCpf());
            stmt.setString(3, contato.getTelefone());
            stmt.setString(4, contato.getEmail());
            stmt.setString(5, contato.getData());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir dados " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }


    @Override
    public void atualizar(Contato entidade) {
        PreparedStatement stmt = null;

        String sql = "UPDATE contatos SET nome = ?, telefone = ?, email = ?,  data = ? WHERE cpf = ?";

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, entidade.getNome());
            stmt.setString(2, entidade.getTelefone());
            stmt.setString(3, entidade.getEmail());
            stmt.setString(4, entidade.getData());
            stmt.setString(5, entidade.getCpf());
            stmt.executeUpdate();
       
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar contato " + e.getMessage() + e.getSQLState());
        } finally {
            app.connection.ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    @Override
    public void deletar(String cpf) {
        PreparedStatement stmt = null;

        String sql = "DELETE FROM contatos WHERE cpf = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, cpf);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro na exclusão do contato: " + e.getMessage() + e.getSQLState());
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }



}
