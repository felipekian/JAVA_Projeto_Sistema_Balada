/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author PC Ryzen
 */
public class Pedido {
    
    private String cpf_Cliente , cpf_Func , data , descricao ;
    private int quantidade , id_mesa , codigoPedido  , codBebida , codPrato;

    public int getCodBebida() {
        return codBebida;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
        
    public void setCodBebida(int codBebida) {
        this.codBebida = codBebida;
    }

    public int getCodPrato() {
        return codPrato;
    }

    public void setCodPrato(int codPrato) {
        this.codPrato = codPrato;
    }
                
    public String getCpf_Cliente() {
        return cpf_Cliente;
    }

    public void setCpf_Cliente(String cpf_Cliente) {
        this.cpf_Cliente = cpf_Cliente;
    }

    public String getCpf_Func() {
        return cpf_Func;
    }

    public void setCpf_Func(String cpf_Func) {
        this.cpf_Func = cpf_Func;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }

    public int getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(int codigoPedido) {
        this.codigoPedido = codigoPedido;
    }
  
    
    
    
}
