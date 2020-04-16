/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import model.bean.Bebidas;

/**
 *
 * @author PC Ryzen
 */
public class BebidaDAO {
    
    
    
    
    public void excluirBebida(Bebidas b){
       
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        
        try {
            
            stmt = (PreparedStatement) con.prepareStatement("DELETE FROM bebidas where codigo = ?;");
                                   
            stmt.setInt(1, b.getCodigo());
        
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cadastro Excluido!");
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir Dados!"+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
    public void cadastrarBebida(Bebidas b){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        
        try {
                        
                stmt = (PreparedStatement) con.prepareStatement("INSERT INTO bebidas (codigo , nome , temperaturaConsumo, quenteFrio, quantidade,fornecedor,validade,preco)VALUES(?,?,?,?,?,?,?,?);");

                stmt.setInt(1, b.getCodigo());  
                stmt.setString(2, b.getNome());  
                stmt.setInt(3, b.getTempConsumo());  
                stmt.setString(4, b.getQuenteFrio());  
                stmt.setInt(5, b.getQuantidade());  
                stmt.setString(6, b.getFornecedor());  
                stmt.setString(7, b.getValidade());  
                stmt.setFloat(8, b.getPreco());  
               
                stmt.execute();

                JOptionPane.showMessageDialog(null, "Cadastro Salvo!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: "+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
    public void alterarBebida(Bebidas b){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        
        try {
            
            stmt = (PreparedStatement) con.prepareStatement("UPDATE bebidas SET  nome=? , temperaturaConsumo=?, quenteFrio=?, quantidade=?,fornecedor=?,validade=?,preco=? where codigo = ?;");
            
            stmt.setString(1, b.getNome());
            stmt.setInt(2, b.getTempConsumo());
            stmt.setString(3, b.getQuenteFrio());
            stmt.setInt(4, b.getQuantidade());
            stmt.setString(5, b.getFornecedor());
            stmt.setString(6, b.getValidade());
            stmt.setFloat(7, b.getPreco());
            stmt.setInt(8, b.getCodigo());
            
        
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cadastro Atualizado!");
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar Dados!"+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        
    }
        
    
    public void retiraEstoque(Bebidas m){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        int total = 0;
        
        try {
            
            stmt = (PreparedStatement) con.prepareStatement("SELECT bebidas.quantidade as qtd from bebidas where bebidas.nome=?;");
            stmt.setString(1, m.getNome());
            rs = stmt.executeQuery();
            
            while(rs.next()){
                total = rs.getInt("qtd");
            }
            
            if(total < m.getQuantidade()){
                
                JOptionPane.showMessageDialog(null, "Total disponivel: "+total+".");
            }else{
                
                total -= m.getQuantidade();
                
                stmt = (PreparedStatement) con.prepareStatement("UPDATE bebidas set quantidade=? where nome=?;");
                
                stmt.setInt(1, total);
                stmt.setString(2, m.getNome());
                
                stmt.executeUpdate();
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar Dados!"+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
    public void addEstoque(Bebidas m){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        int total = 0;
        
        try {
            
            stmt = (PreparedStatement) con.prepareStatement("SELECT bebidas.quantidade as qtd from bebidas where bebidas.nome=?;");
            stmt.setString(1, m.getNome());
            rs = stmt.executeQuery();
            
            while(rs.next()){
                total = rs.getInt("qtd");
            }
            
                total += m.getQuantidade();
                
                stmt = (PreparedStatement) con.prepareStatement("UPDATE bebidas set quantidade=? where nome=?;");
                
                stmt.setInt(1, total);
                stmt.setString(2, m.getNome());
                
                stmt.executeUpdate();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar Dados!"+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
     public int getIdBebida(String nomebebida){
         
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        int ID = -1;
        
        try {
            
            stmt = (PreparedStatement) con.prepareStatement("SELECT codigo from bebidas where nome=?;");
            stmt.setString(1, nomebebida);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                ID =  rs.getInt("codigo");
            }
            
            //JOptionPane.showMessageDialog(null, " Codigo bebida: "+ID);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar Dados!"+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
         return ID;
         
     }
    
}
