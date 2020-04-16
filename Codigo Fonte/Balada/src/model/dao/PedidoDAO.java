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
import model.bean.Pedido;

/**
 *
 * @author PC Ryzen
 */
public class PedidoDAO {
    
    
    public void cadastrarPedidoPrimeiroClienteComCadastroBebida(Pedido p){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        
        try {
            
            stmt = (PreparedStatement) con.prepareStatement("INSERT INTO pedido (cpf_cliente , cpf_func , id_mesa , codigo , data , quantidade , Bebidas_codigo) VALUES (?,?,?,?,?,?,?);");
            
            stmt.setString  (1, p.getCpf_Cliente());  
            stmt.setString  (2, p.getCpf_Func());
            stmt.setInt     (3, p.getId_mesa());
            stmt.setInt     (4, p.getCodigoPedido());
            stmt.setString  (5, p.getData());
            stmt.setInt     (6, p.getQuantidade());            
            stmt.setInt     (7, p.getCodBebida());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cadastro Realizado!");
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar pedido!"+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        
    }
    
    public void cadastrarPedidoPrimeiroClienteComCadastroPrato(Pedido p){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        
        try {
            
            stmt = (PreparedStatement) con.prepareStatement("INSERT INTO pedido (cpf_cliente , cpf_func , id_mesa , codigo , data , quantidade , Pratos_codigo) VALUES (?,?,?,?,?,?,?);");
            
            stmt.setString  (1, p.getCpf_Cliente());  
            stmt.setString  (2, p.getCpf_Func());
            stmt.setInt     (3, p.getId_mesa());
            stmt.setInt     (4, p.getCodigoPedido());
            stmt.setString  (5, p.getData());
            stmt.setInt     (6, p.getQuantidade());
            stmt.setInt     (7, p.getCodPrato());
            
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cadastro Realizado!");
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar pedido!"+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        
    }
    
    
    public boolean pagarConta(int numPedido){
        
        boolean t = false;
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        float Conta = 0;
        
        try {
            
            stmt = (PreparedStatement) con.prepareStatement(" select pedido.quantidade as qtd , pratos.valor as p from pedido , pratos where pedido.Pratos_codigo=pratos.codigo and pedido.codigo=? ");
            
            stmt.setInt (1, numPedido);
                        
            rs = stmt.executeQuery();
            
            while(rs.next()){                              
                
                Conta += rs.getInt("qtd")*rs.getFloat("p");
                
            }
            
            stmt = (PreparedStatement) con.prepareStatement(" select pedido.quantidade as qtd , bebidas.preco as p from pedido , bebidas where pedido.Bebidas_codigo=bebidas.codigo and pedido.codigo=? ");
            
            stmt.setInt (1, numPedido);
                        
            rs = stmt.executeQuery();
            
            while(rs.next()){                              
                
                Conta += rs.getInt("qtd")*rs.getFloat("p");
                
            }
            
            JOptionPane.showMessageDialog(null, "Total a PAGAR: "+Conta);
                        
            t= true;
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro Pesquisar conta pagamento!"+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt , rs);
        }
        
        return t;
        
    }
    
    
    
    public void cadastrarPedidoPrimeiroClienteSEMCadastroBebida(Pedido p){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        
        try {
            
            stmt = (PreparedStatement) con.prepareStatement("INSERT INTO pedido (  cpf_func , id_mesa , codigo , data , quantidade , Bebidas_codigo) VALUES (?,?,?,?,?,?);");
            
            
            stmt.setString  (1, p.getCpf_Func());
            stmt.setInt     (2, p.getId_mesa());
            stmt.setInt     (3, p.getCodigoPedido());
            stmt.setString  (4, p.getData());
            stmt.setInt     (5, p.getQuantidade());            
            stmt.setInt     (6, p.getCodBebida());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cadastro Realizado!");
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar pedido!"+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        
    }
    
    public void cadastrarPedidoPrimeiroClienteSEMCadastroPrato(Pedido p){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        
        try {
            
            stmt = (PreparedStatement) con.prepareStatement("INSERT INTO pedido ( cpf_func , id_mesa , codigo , data , quantidade , Pratos_codigo) VALUES (?,?,?,?,?,?);");
            
             
            stmt.setString  (1, p.getCpf_Func());
            stmt.setInt     (2, p.getId_mesa());
            stmt.setInt     (3, p.getCodigoPedido());
            stmt.setString  (4, p.getData());
            stmt.setInt     (5, p.getQuantidade());
            stmt.setInt     (6, p.getCodPrato());
            
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cadastro Realizado!");
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar pedido!"+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        
    }
    
    
}
