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
import model.bean.Mesa;

/**
 *
 * @author PC Ryzen
 */
public class MesaDAO {
    
    public void excluirMesa(Mesa m){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            
            stmt = (PreparedStatement) con.prepareStatement("DELETE FROM mesas where codigo = ?;");
                                   
            stmt.setInt(1, m.getCodigo());
        
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Mesa Excluida!");
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir mesa!"+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
    
    public void AlterarMesa(Mesa m){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        
        try {
            
            stmt = (PreparedStatement) con.prepareStatement("UPDATE mesas SET nome=? where codigo = ?;");
                        
            stmt.setString(1, m.getNome());
            stmt.setInt(2, m.getCodigo());
                    
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cadastro Atualizado!");
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar Dados!"+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
    
    public void CadastrarMesa(Mesa m){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        
        try {
            
            stmt = (PreparedStatement) con.prepareStatement("Insert into mesas ( codigo, nome, status) values(?,?,?)");
            
            String n = "livre";
            
            stmt.setInt(1, m.getCodigo());
            stmt.setString(2, m.getNome());
            stmt.setString(3, n);
                    
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cadastro Salvo!");
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar mesa!"+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
     
    public void atualizarStatusMesa(Mesa m){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        
        try {
            
            stmt = (PreparedStatement) con.prepareStatement("UPDATE mesas SET descricao=? , status=? , n_pedidoAtual=? where codigo=? ");
            
            stmt.setString(1 , m.getDescricao());
            stmt.setString(2, m.getStatus());
            stmt.setInt(3, m.getNumPedidoAtual());
            stmt.setInt(4, m.getCodigo());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado Status!");
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar Status mesa!"+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        
    }
 
    
    public boolean getStatusMesa(int m){
        
        boolean t = false;
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            
            stmt = (PreparedStatement) con.prepareStatement(" select status from mesas where codigo=? ");
            
            stmt.setInt(1, m);
            
            rs = stmt.executeQuery();
            
            String s = null;
            
            while(rs.next()){
                
                s = rs.getString("status");
                
                if("livre".equals(s)){
                    t = true;
                }
                
            }
            
            
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar Status mesa!"+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt ,rs);
        }
        
        return t;
    } 
    
    
    
    public int getCodPedidoMesaOcupada(int idmesa){
        int p = 0;
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            
            stmt = (PreparedStatement) con.prepareStatement(" select n_pedidoAtual from mesas where codigo=? ");
            
            stmt.setInt(1, idmesa);
            
            rs = stmt.executeQuery();
            
            String s = null;
            
            while(rs.next()){
                
                p = rs.getInt("n_pedidoAtual");
                
            }
            
            
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar Status mesa!"+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt ,rs);
        }
        
        return p;
    }
    
    
    public void atualizaStatusLiberaMesa(Mesa m){
                
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        
        try {
            
            stmt = (PreparedStatement) con.prepareStatement(" UPDATE mesas SET descricao=? , status=? ,  n_pedidoAtual=? where codigo=? ");
            stmt.setString(1, m.getDescricao());
            stmt.setString(2, m.getStatus());
            stmt.setInt(3, m.getNumPedidoAtual());
            stmt.setInt(4, m.getCodigo());
            
            stmt.executeUpdate();
            
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar Status mesa!"+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt );
        }
    }
    
}
    

