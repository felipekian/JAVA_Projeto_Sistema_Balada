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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Cliente;

/**
 *
 * @author PC Ryzen
 */
public class ClienteDAO {
   
    
    public void addPorcentagemCliente(String nomePrato , int qtd){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String cpfCliente = null;
        float valor = 0 , saldo = 0;
        boolean teve = false;
        
        //JOptionPane.showMessageDialog(null, "Nome: "+nomePrato+" quantidade: "+qtd);
        
        try {
            
            stmt = (PreparedStatement) con.prepareStatement("select cliente.cpf as cpfCliente , pratos.valor as valor , cliente.saldo as saldo from cliente , pratos where cliente.Pratos_codigo=pratos.codigo and pratos.nome=?");
            
            stmt.setString(1, nomePrato);
        
            rs = stmt.executeQuery();
            
            
            
            while(rs.next()){
                
                cpfCliente = rs.getString("cpfCliente");
                valor = rs.getFloat("valor");
                saldo = rs.getFloat("saldo");    
                
                //JOptionPane.showMessageDialog(null, "CPF: "+cpfCliente+" valor: "+valor+" saldo: "+saldo);
                
                teve = true;
            }
                        
            
            if(teve = true){
                
                float porcentagem = (float) ((float) qtd*(valor*0.015));
                
                saldo = porcentagem + saldo;
                
                stmt = (PreparedStatement) con.prepareStatement("UPDATE cliente set cliente.saldo=? where cliente.cpf=?");

                stmt.setFloat(1, saldo);
                stmt.setString(2, cpfCliente);

                stmt.executeUpdate();
                       
                JOptionPane.showMessageDialog(null, "O cliente com CPF "+cpfCliente+" recebeu uma porcentagem de "+porcentagem+" e o saldo total eh "+saldo+".");
            }
                        
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar Dados!"+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt,rs);
        }
        
        
    }
    
    public void cadastrarCliente(Cliente c){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        SimpleDateFormat formatoBrasileiro = new SimpleDateFormat("dd/MM/yyyy");
        Date d = new Date();        
        String data = formatoBrasileiro.format(d);
        
        try {
            
            boolean ok = true;
            
            stmt = (PreparedStatement) con.prepareStatement("Select * from cliente where cpf=?");
            stmt.setString(1, c.getCpf());
            
            rs=stmt.executeQuery();
            
            while(rs.next()){
                ok = false;
            }
            
            if(ok){
                stmt = (PreparedStatement) con.prepareStatement("INSERT INTO cliente (cpf , endereco , telefone, nome, email,clube,dataEntrada,dataAniversario)VALUES(?,?,?,?,?,?,?,?);");

                stmt.setString(1, c.getCpf());
                stmt.setString(2, c.getEndereco());
                stmt.setString(3, c.getTelefone());
                stmt.setString(4, c.getNome());
                stmt.setString(5, c.getEmail());
                stmt.setString(6, c.getClube());
                stmt.setString(7, data);
                stmt.setString(8, c.getDataAniversario());

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
    
    public void AtualizaCadastro(Cliente c){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        
        try {
            
            stmt = (PreparedStatement) con.prepareStatement("UPDATE cliente SET  endereco=? , telefone=?, nome=?, email=?,clube=?,dataAniversario=? where cpf = ?;");
            
            
            stmt.setString(1, c.getEndereco());
            stmt.setString(2, c.getTelefone());
            stmt.setString(3, c.getNome());
            stmt.setString(4, c.getEmail());
            stmt.setString(5, c.getClube());            
            stmt.setString(6, c.getDataAniversario());
            stmt.setString(7, c.getCpf());
        
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cadastro Atualizado!");
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar Dados!"+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        
    }
    
    public void ExcluirCliente(Cliente c){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        
        try {
            
            stmt = (PreparedStatement) con.prepareStatement("DELETE FROM cliente where cpf = ?;");
                                   
            stmt.setString(1, c.getCpf());
        
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cadastro Excluido!");
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir Dados!"+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        
        
    }
    
    
}
