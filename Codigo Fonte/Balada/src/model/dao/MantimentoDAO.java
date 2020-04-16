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
import javax.swing.JOptionPane;
import model.bean.Mantimentos;

/**
 *
 * @author PC Ryzen
 */
public class MantimentoDAO {
    
    
    public void cadastrarMantimentos(Mantimentos m){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        
        try {
                        
                stmt = (PreparedStatement) con.prepareStatement("INSERT INTO mantimentos (id , nome , fornecedor, validade, metrica,quantidade,valor)VALUES(?,?,?,?,?,?,?);");

                stmt.setInt(1, m.getCodigo());  
                stmt.setString(2, m.getNome());
                stmt.setString(3, m.getFornecedor());
                stmt.setString(4, m.getValidade());
                stmt.setString(5, m.getMetrica());
                stmt.setInt(6, m.getQuantidade());
                stmt.setFloat(7, m.getValor());
               
                stmt.execute();

                JOptionPane.showMessageDialog(null, "Cadastro Salvo!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: "+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        
    }
    
    public void AlterarMantimentos(Mantimentos m){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        
        try {
            
            stmt = (PreparedStatement) con.prepareStatement("UPDATE bebidas SET  nome=? , fornecedor=?, validade=?, quantidade=?,metrica=?,valor=? where id = ?;");
            
            stmt.setString(1, m.getNome());
            stmt.setString(2, m.getFornecedor());
            stmt.setString(3, m.getValidade());
            stmt.setInt(4, m.getQuantidade());
            stmt.setString(5, m.getMetrica());
            stmt.setFloat(6, m.getValor());
            stmt.setFloat(7, m.getCodigo());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cadastro Atualizado!");
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar Dados!"+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }

        
    }
    
    public void excluirMantimentos(Mantimentos m){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        
        try {
            
            stmt = (PreparedStatement) con.prepareStatement("DELETE FROM mantimentos where id = ?;");
                                   
            stmt.setInt(1, m.getCodigo());
        
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Mantimento Excluido!");
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir Dados!"+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
    public void retiraEstoque(Mantimentos m){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        int total = 0;
        
        try {
            
            stmt = (PreparedStatement) con.prepareStatement("SELECT mantimentos.quantidade as qtd from mantimentos where mantimentos.nome=?;");
            stmt.setString(1, m.getNome());
            rs = stmt.executeQuery();
            
            while(rs.next()){
                total = rs.getInt("qtd");
            }
            
            if(total < m.getQuantidade()){
                
                JOptionPane.showMessageDialog(null, "Total disponivel: "+total+".");
            }else{
                
                total -= m.getQuantidade();
                
                stmt = (PreparedStatement) con.prepareStatement("UPDATE mantimentos set quantidade=? where nome=?;");
               
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
     
    
    public void addEstoque(Mantimentos m){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        int total = 0;
        
        try {
            
            stmt = (PreparedStatement) con.prepareStatement("SELECT mantimentos.quantidade as qtd from mantimentos where mantimentos.nome=?;");
            stmt.setString(1, m.getNome());
            rs = stmt.executeQuery();
            
            while(rs.next()){
                total = rs.getInt("qtd");
            }
            
            
                
                total += m.getQuantidade();
                
                stmt = (PreparedStatement) con.prepareStatement("UPDATE mantimentos set quantidade=? where nome=?;");
                
                stmt.setInt(1, total);
                stmt.setString(2, m.getNome());
                
                stmt.executeUpdate();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar Dados!"+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
}
