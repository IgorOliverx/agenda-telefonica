package app.controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import app.connection.services.ContatoDAO;
import app.model.Contato;
import app.view.ContactView;

public class ContatoController {
    private List<Contato> contatos;
    private DefaultTableModel tableModel;
    private JTable table;
    Contato contato;

    //Construtor inicializando atributos
    public ContatoController(List<Contato> contatos, DefaultTableModel tableModel, JTable table){
        this.contatos = contatos;
        this.tableModel = tableModel;
        this.table = table;

    }
 
    public ContatoController() {}


    private void atualizarTabela() {
        tableModel.setRowCount(0); 
        contatos = new ContatoDAO().listarTodos();
     
        for (Contato contato : contatos) {
            tableModel.addRow(new Object[] { contato.getNome(), contato.getCpf(), contato.getTelefone(), contato.getEmail(), contato.getData()});
        }
    }

  
     public void cadastrar(String nome, String cpf, String telefone, String email, String data) {
        Contato novocontato = new Contato(nome, cpf, telefone, email, data);
        new ContatoDAO().cadastrar(novocontato);
        JOptionPane.showMessageDialog(null, "contato cadastrado com sucesso");
        atualizarTabela();
        
    }

    public void deletar(String cpf){
        Object[] opcoes = { "Sim", "Não" };

      int linhaSelecionada = table.getSelectedRow();

      try{
      if(linhaSelecionada >= 0){
        int resposta = JOptionPane.showOptionDialog(null,
        "Você tem certeza que quer excluir este contato? ",
        "Excluir contato", JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
        if(resposta == JOptionPane.YES_OPTION){
            new ContatoDAO().deletar(cpf);
            JOptionPane.showMessageDialog(null, "contato deletado com sucesso!");
            atualizarTabela();
        }
    }else{
         JOptionPane.showMessageDialog(null, "Por favor, selecione um contato", "Erro nos dados", JOptionPane.ERROR_MESSAGE);
    }
      }catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro nos dados", JOptionPane.ERROR_MESSAGE);
        }



    }

    public void atualizar(String nome, String cpf, String telefone, String email, String data){
        Contato contatoAtualizado = new Contato(nome, cpf, telefone, email, data);
        new ContatoDAO().atualizar(contatoAtualizado);
        JOptionPane.showMessageDialog(null, "contato editado com sucesso");

    }

    public boolean validarDados(String nome, String cpf, String telefone, String email, String data){
        boolean dadosValidos = true;

        try {
          
            if (nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty() || email.isEmpty() || data.isEmpty()) {
                throw new IllegalArgumentException("Por favor, preencha todos os campos.");
            }

            if(!validarEmail(email)){
                throw new IllegalArgumentException("Por favor, preencha corretamente o Email");
            }

        
        } catch (IllegalArgumentException e) {
            
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro nos dados", JOptionPane.ERROR_MESSAGE);
            dadosValidos = false;
        }


        return dadosValidos;
    }

    public boolean validarEmail(String email){
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        return email.matches(regex);
    }
}
