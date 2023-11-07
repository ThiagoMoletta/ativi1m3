/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
        public ProdutosDAO() {
        conn = new conectaDAO().connectDB(); // Inicialize a conexão no construtor
    }
    
public void cadastrarProduto(ProdutosDTO produto) throws SQLException {
    conn = new conectaDAO().connectDB();
    String sql = "INSERT INTO tb_leilao (nome, valor, status) VALUES (?, ?, ?)";
    PreparedStatement preparedStatement = null;

    try {
        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, produto.getNome());
        preparedStatement.setInt(2, produto.getValor());
        preparedStatement.setString(3, produto.getStatus());

        preparedStatement.executeUpdate();
        JOptionPane.showMessageDialog(null, "Produto cadastrado");
    } finally {
        // Certifique-se de fechar o PreparedStatement e a conexão em um bloco finally.
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
}
public void venderProduto(int idProduto) throws SQLException {
    conn = new conectaDAO().connectDB();
    String sql = "UPDATE tb_leilao SET status = ? WHERE id = ?";
    PreparedStatement preparedStatement = null;

    try {
        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, "Vendido");
        preparedStatement.setInt(2, idProduto);

        int rowsUpdated = preparedStatement.executeUpdate();

        if (rowsUpdated > 0) {
            JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Produto não encontrado ou não pôde ser vendido.");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + e.getMessage());
    } finally {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
}

   public ArrayList<ProdutosDTO> listarProdutos() {
        ArrayList<ProdutosDTO> listaProdutos = new ArrayList<>();

          try {
            String sql = "SELECT id, nome, valor, status FROM tb_leilao";
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();

            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                listaProdutos.add(produto);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e.getMessage());
        } finally {
            closeConnection();
        }

        return listaProdutos;
    }

    private void closeConnection() {
        try {
            if (resultset != null) resultset.close();
            if (prep != null) prep.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + e.getMessage());
        }
    }
}

    
    
    
        


