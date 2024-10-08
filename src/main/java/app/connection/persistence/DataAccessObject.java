package app.connection.persistence;

import java.sql.Connection;
import java.util.List;



public abstract class DataAccessObject<Modelo> {

     private Connection connection;


    public DataAccessObject() {}

    public DataAccessObject(Connection connection) {
        this.connection = connection;
    }

    public abstract void criarTabela();
    
    public abstract Modelo procurarPorId(String id);

    public abstract List<Modelo> listarTodos();

    public abstract void cadastrar(Modelo entidade);

    public abstract void atualizar(Modelo entidade);

    public abstract void deletar (String id);

}
