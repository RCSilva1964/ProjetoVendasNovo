package Dao;

import ProjetoVendas.JDBC.ConnectionFactory;
import ProjetoVendas.Model.Vendas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.cj.protocol.Resultset;

public class VendasDao {

    private Connection con;

    public VendasDao() {
        this.con = new ConnectionFactory().getConnection();
    }

    public void CadastrarVenda(Vendas obj) {

        try {
            
            String sql = "INSERT INTO tb_vendas(cliente_id, data_venda, total_venda, observacoes) VALUES (?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, obj.getCliente().getId());
            stmt.setString(2, obj.getData_venda());
            stmt.setDouble(3, obj.getTotal_venda());
            stmt.setString(4, obj.getObs());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Venda registrada com sucesso!");

        }catch (Exception erro) {
                        
            JOptionPane.showMessageDialog(null, "Erro ao registrar a venda. " + erro);
        }

    }
    
    public int retornaUltimaVenda() {

        try {
            int idvenda = 0;

            String sql = "SELECT max(id) id from tb_vendas.";
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                Vendas p = new Vendas();
                p.setId(rs.getInt("id"));
                idvenda = p.getId();
            }

            return idvenda;

        }catch (SQLException e) {
            throw new RuntimeException();
        }

    }
}
