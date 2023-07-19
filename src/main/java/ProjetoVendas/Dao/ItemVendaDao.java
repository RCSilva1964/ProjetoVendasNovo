package ProjetoVendas.Dao;

import ProjetoVendas.JDBC.ConnectionFactory;
import ProjetoVendas.Model.ItemVenda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;


public class ItemVendaDao {

    private Connection con;
    
    public ItemVendaDao() {
        this.con = new ConnectionFactory().getConnection();
    } 
    
    // Método para cadastrar ítens:
    public void cadastrarItem(ItemVenda obj) {
        
        try {
            
            String sql = "INSERT INTO tb_itensvendas(venda_id, produto_id, qtd, subtotal) VALUES (?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, obj.getVenda().getId());
            stmt.setInt(2, obj.getProduto().getId());
            stmt.setDouble(3, obj.getQtd());
            stmt.setDouble(4, obj.getSubtotal());

            stmt.execute();
            stmt.close();

            // JOptionPane.showMessageDialog(null, "Item registrado com sucesso!");

        }catch (Exception erro) {
                        
            JOptionPane.showMessageDialog(null, "Erro ao registrar o ítem: " + erro);
        }
    }
}
