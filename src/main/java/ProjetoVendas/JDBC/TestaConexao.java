package ProjetoVendas.JDBC;

import javax.swing.JOptionPane;


public class TestaConexao {
    
    public static void main(String[] args) {
        
        try {
            
            new ConnectionFactory().getConnection();
            JOptionPane.showMessageDialog(null, "Conectado com sucesso!");
                
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o db" + erro);
        }
    }

}