package app.model;


public class Contato {

    //Atributos do modelo
    String nome;
    String cpf;
    String telefone;
    String email;
    String data;
    
    
    /*
    * Construtor inicializando atributos
    */
    public Contato(String nome, String cpf, String telefone, String email, String data) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.data = data;
    }
    
    
    /*
    * Construtor vazio
    */
    public Contato() {}


   /*
    * Métodos modificadores de acesso
    */
    public String getNome() {
        return nome;
    }
    
    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }


    public String getData() {
        return data;
    }
    

   
    
    

 
    

    
}
