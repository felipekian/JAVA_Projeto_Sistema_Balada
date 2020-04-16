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
import model.bean.Funcionario;

/**
 *
 * @author PC Ryzen
 */
public class FuncionarioDAO {
    
    public void excluirFuncionario(Funcionario f){
       
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        
        try {
            
            stmt = (PreparedStatement) con.prepareStatement("DELETE FROM funcionario where cpf = ?;");
                                   
            stmt.setString(1, f.getCpf());
        
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cadastro Excluido!");
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir Dados!");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void AtualizarCadastro(Funcionario f){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        
        try {
            
            stmt = (PreparedStatement) con.prepareStatement("UPDATE funcionario SET  endereco=? , telefone=?, nome=?,dataEntrada=?,cargo=?, salario=? where cpf = ?;");
            
            
            stmt.setString(1, f.getEndereco());
            stmt.setString(2, f.getTelefone());
            stmt.setString(3, f.getNome());
            stmt.setString(4, f.getDataEntrada());
            stmt.setString(5, f.getCargo());            
            stmt.setFloat(6, f.getSalario()); 
            stmt.setString(7, f.getCpf()); 
        
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cadastro Atualizado!");
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar Dados!"+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }

        
    }
    
    public void CadastrarFuncionario(Funcionario f){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        SimpleDateFormat formatoBrasileiro = new SimpleDateFormat("dd/MM/yyyy");
        Date d = new Date();        
        String data = formatoBrasileiro.format(d);
        
        try {
            
            boolean ok = true;
            
            stmt = (PreparedStatement) con.prepareStatement("Select * from funcionario where cpf=?");
            stmt.setString(1, f.getCpf());
            
            rs=stmt.executeQuery();
            
            while(rs.next()){
                ok = false;
            }
            
            if(ok){
                stmt = (PreparedStatement) con.prepareStatement("INSERT INTO funcionario (cpf ,nome , telefone, endereco , salario, dataEntrada,cargo)VALUES(?,?,?,?,?,?,?);");

                stmt.setString(1, f.getCpf());
                stmt.setString(2, f.getNome());
                stmt.setString(3, f.getTelefone());
                stmt.setString(4, f.getEndereco());
                stmt.setFloat(5, f.getSalario());
                stmt.setString(6, f.getDataEntrada());
                stmt.setString(7, f.getCargo());
                
                stmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Cadastro Salvo!");
            }else{
                JOptionPane.showMessageDialog(null, "Cadastro ja existe!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: "+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    
    public String getCPF (String nomeFunc){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String CPF = null;
        
        try {
            
            stmt = (PreparedStatement) con.prepareStatement("SELECT cpf from funcionario where nome=?");
            
            stmt.setString(1, nomeFunc);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                CPF = rs.getString("cpf");
            }
            
            //JOptionPane.showMessageDialog(null, "CPF Funcionario: "+CPF);
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar Dados!"+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt , rs);
        }
        return CPF;
    }
    
}
