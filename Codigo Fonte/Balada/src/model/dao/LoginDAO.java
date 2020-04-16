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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Login;



/**
 *
 * @author PC Ryzen
 */
public class LoginDAO {
    
    public void createCadastro(Login l){
               
                
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
               
               
        try {
            stmt = (PreparedStatement) con.prepareStatement("INSERT INTO login(usuario, senha, cpf)VALUES(?,?,?);");
            
            stmt.setString(1, l.getLogin());
            stmt.setString(2, l.getSenha());
            stmt.setString(3, l.getCpfFunc());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cadastro Salvo!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar Cadastro de Login: "+ex);
        } finally{            
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    
    public boolean verificarLogin(String user , String senha){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        boolean check = false;
        
        try {
            stmt = (PreparedStatement) con.prepareStatement("select * from login where usuario=? and senha=? ");
            stmt.setString(1, user);
            stmt.setString(2, senha);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                check = true;
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return check;
    }

    
    
}
