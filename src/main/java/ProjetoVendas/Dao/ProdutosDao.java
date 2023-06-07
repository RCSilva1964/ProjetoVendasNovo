package ProjetoVendas.Dao;

import ProjetoVendas.JDBC.ConnectionFactory;
import ProjetoVendas.Model.Clientes;
import ProjetoVendas.Model.Fornecedores;
import ProjetoVendas.Model.Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProdutosDao {

    private Connection con;

    public ProdutosDao() {

        this.con = new ConnectionFactory().getConnection();
    }

    public void cadastrarProduto(Produtos obj) {

        try {

            String sql = "INSERT INTO tb_produtos (descricao, preco, qtd_estoque, for_id) VALUES (?, ?, ?, ?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd_estoque());
            stmt.setInt(4, obj.getFornecedor().getId());
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto");
        }
    }

    public List<Produtos> listarProdutos() {

        try {

            List<Produtos> lista = new ArrayList<>();
            String sql = "SELECT p.id, p.descricao, p.preco, p.qtd_estoque, f.nome FROM tb_produtos as p "
                    + "INNER JOIN tb_fornecedores as f on (p.for_id = f.id)";

            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();

                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
                f.setNome(rs.getString(("f.nome")));

                obj.setFornecedor(f);

                lista.add(obj);
            }
            return lista;

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar clientes." + erro);
            return null;
        }
    }

    public void alterarProdutos(Produtos obj) {
        try {

            String sql = "UPDATE tb_produtos set descricao=?, preco=?, qtd_estoque=?, for_id=? WHERE id=?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd_estoque());
            stmt.setInt(4, obj.getFornecedor().getId());
            stmt.setInt(5, obj.getId());
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto alterado com sucesso.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar atualizar produto.");
        }
    }
    
    
    public void excluirProdutos(Produtos obj) {
        try {
            
            String sql = "DELETE from tb_produtos WHERE id=?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, obj.getId());
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Produto excluido com sucesso.");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir produto.");
        }
    }
    
    
    public List<Produtos> ListarProdutosPorNome(String nome) {
        try {
            
            List<Produtos> lista = new ArrayList<>();
            
            String sql = "SELECT p.id, p.descricao, p.preco, p.qtd_estoque, f.nome FROM tb_produtos as p "
                    + "INNER JOIN tb_fornecedores as f on (p.for_id = f.id) WHERE p.descricao like ?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            
             while (rs.next()) {
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
                
                f.setNome(rs.getString(("f.nome")));
                obj.setFornecedor(f);
                lista.add(obj);
             }
             
             return lista;
            
        } catch (Exception e) {
            return null;
        }       
        
    }
}
