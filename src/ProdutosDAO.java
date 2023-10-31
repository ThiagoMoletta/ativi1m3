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
        JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso.");
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

    ArrayList<ProdutosDTO> listarProdutos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
    
        
}

