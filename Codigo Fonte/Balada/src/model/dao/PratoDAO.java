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
import model.bean.Cliente;
import model.bean.Pratos;

/**
 *
 * @author PC Ryzen
 */
public class PratoDAO {
    
    
    public void cadastraPratosNormal(Pratos p){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        
        try {
            
            stmt = (PreparedStatement) con.prepareStatement("Insert into pratos ( codigo, nome, tipo , descricao , valor , tempoPreparo) values(?,?,?,?,?,?)");
                     
            
            stmt.setInt(1, p.getCodigo());
            stmt.setString(2, p.getNome());
            stmt.setString(3, p.getTipo());
            stmt.setString(4, p.getDescricao());
            stmt.setFloat(5, p.getValor());
            stmt.setInt(6, p.getTempoPreparo());
                    
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "Cadastro Salvo!");
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar prato: "+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        
    }
    
    public void cadastraPratosCliente(Pratos p , Cliente c){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        
        try {
            
            stmt = (PreparedStatement) con.prepareStatement("Insert into pratos ( codigo, nome, tipo  , valor , tempoPreparo , descricao) values(?,?,?,?,?,?)");
            
            stmt.setInt(1, p.getCodigo());
            stmt.setString(2, p.getNome());
            stmt.setString(3, p.getTipo());            
            stmt.setFloat(4, p.getValor());
            stmt.setInt(5, p.getTempoPreparo());
            stmt.setString(6, p.getDescricao());
                    
            stmt.execute();
            
// ----------------------------------------------------------------------------------------------------------------------------------------------------------
            

            stmt = (PreparedStatement) con.prepareStatement("update cliente set Pratos_codigo=? , dataEntradaPrato=? where cpf=?");
            
            stmt.setInt(1, p.getCodigo());
            stmt.setString(2, c.getDataPrato());
            stmt.setString(3, c.getCpf());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cadastro salvo!");
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar prato: "+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        
        
    }
    
    public void AlterarPratos(Pratos p){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        
        try {
                                  

            stmt = (PreparedStatement) con.prepareStatement("update pratos set nome=? ,tipo =? ,descricao =? ,valor=? ,tempoPreparo=? where codigo=?");
            
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getTipo());
            stmt.setString(3, p.getDescricao());
            stmt.setFloat(4, p.getValor());
            stmt.setInt(5, p.getTempoPreparo());
            stmt.setInt(6, p.getCodigo());
                                    
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cadastro Alterado!");
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar prato: "+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        
    }
    
    public void ExcluirPratos(Pratos p){
        
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        
        try {
                                  

            stmt = (PreparedStatement) con.prepareStatement("Delete from pratos where codigo=?");
            
            stmt.setInt(1, p.getCodigo());
                                    
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "Cadastro Excluido!");
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir cadastro: "+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        
    }
    
    
    public int getIdPrato(String nomePrato){
         
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        int ID = -1;
        
        try {
            
            stmt = (PreparedStatement) con.prepareStatement("SELECT codigo from pratos where nome=?;");
            stmt.setString(1, nomePrato);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                ID =  rs.getInt("codigo");
            }
            
            //JOptionPane.showMessageDialog(null, " Codigo Prato: "+ID);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar Dados!"+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
         return ID;
         
     }
    
}
