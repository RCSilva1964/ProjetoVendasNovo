package ProjetoVendas.Dao;

import ProjetoVendas.JDBC.ConnectionFactory;
import ProjetoVendas.Model.Clientes;
import ProjetoVendas.Model.Fornecedores;
import ProjetoVendas.Model.WebServiceCep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class FornecedoresDao {

    private Connection con;
    
    public FornecedoresDao() {
        this.con = new ConnectionFactory().getConnection();
    }
    
    
    public void cadastrarFornecedor(Fornecedores obj) {

        try {
            String sql = "INSERT INTO tb_fornecedores (nome, cnpj, email, telefone, celular, cep,"
                    + "endereco, numero, complemento, bairro, cidade, estado) VALUES (?,?,?,?,?,?,"
                    + "?,?,?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCnpj());            
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getCep());
            stmt.setString(7, obj.getEndereco());
            stmt.setInt(8, obj.getNumero());
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getUf());

            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso.");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar fornecedor." + erro);
        }
    }
    
    
     public void alterarCliente(Clientes obj) {

        try {
            String sql = "UPDATE tb_clientes SET nome = ?, rg = ?, cpf = ?, email = ?, telefone = ?, "
                    + "celular = ?, cep = ?, endereco = ?, numero = ?, complemento = ?, bairro = ?,"
                    + " cidade = ?, estado = ? WHERE id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getTelefone());
            stmt.setString(6, obj.getCelular());
            stmt.setString(7, obj.getCep());
            stmt.setString(8, obj.getEndereco());
            stmt.setInt(9, obj.getNumero());
            stmt.setString(10, obj.getComplemento());
            stmt.setString(11, obj.getBairro());
            stmt.setString(12, obj.getCidade());
            stmt.setString(13, obj.getUf());
            stmt.setInt(14, obj.getId());

            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso.");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar cliente." + erro);
        }
    }

    public void excluirCliente(Clientes obj) {

        try {
            String sql = "DELETE FROM tb_clientes WHERE id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());

            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso.");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir cliente." + erro);
        }
    }

    public List<Fornecedores> listarFornecedores() {

        try {

            List<Fornecedores> lista = new ArrayList<>();
            String sql = "SELECT * FROM tb_fornecedores";
            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Fornecedores obj = new Fornecedores();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));                
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));

                lista.add(obj);
            }
            return lista;

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar fornecedores." + erro);
            return null;
        }

    }
    

    public Fornecedores consultaPorNome(String nome) {

        try {

            String sql = "SELECT * FROM tb_fornecedores WHERE nome = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            Fornecedores obj = new Fornecedores();

            if (rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));                
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));
            }
            return obj;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar por nome.");
            return null;
        }
    }

    public List<Fornecedores> buscarFornecedorPorNome(String nome) {

        try {

            List<Fornecedores> lista = new ArrayList<>();
            String sql = "SELECT * FROM tb_fornecedores WHERE nome LIKE ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Fornecedores obj = new Fornecedores();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));                
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));

                lista.add(obj);
            }
            return lista;

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar fornecedores." + erro);
            return null;
        }
    }

    public Fornecedores buscaCep(String cep) {

        WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);

        Fornecedores obj = new Fornecedores();

        if (webServiceCep.wasSuccessful()) {

            try {
                obj.setEndereco(webServiceCep.getLogradouroFull());
                obj.setCidade(webServiceCep.getCidade());
                obj.setBairro(webServiceCep.getBairro());
                obj.setUf(webServiceCep.getUf());
            
             } catch (Exception e) {                
            }
        } else {
            JOptionPane.showMessageDialog(null, "Cep não encontrado.");            
        }                    
        return obj;   
    }  
}
