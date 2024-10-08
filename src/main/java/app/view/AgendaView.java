package app.view;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;


public class AgendaView extends JFrame{
    private JTabbedPane jTPane;
    public  AgendaView() {
        super("Agenda Telefônica");
         setResizable(false);
         setIcon();

        jTPane = new JTabbedPane();
        add(jTPane);
        ContactView tab1 = new ContactView();
        jTPane.add("Contatos", tab1);
        setBounds(200, 200, 832, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
    }



    private void setIcon(){
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.png")));
    }


    
}