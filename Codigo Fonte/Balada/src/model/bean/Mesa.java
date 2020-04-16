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
public class Mesa {
    
    
    private String nome , descricao , status;
    private int codigo, numPedidoAtual;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getNumPedidoAtual() {
        return numPedidoAtual;
    }

    public void setNumPedidoAtual(int numPedidoAtual) {
        this.numPedidoAtual = numPedidoAtual;
    }
    
    
    
    
}
