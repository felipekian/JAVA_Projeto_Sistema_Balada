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
public class Bebidas {
    
    private int codigo , quantidade , tempConsumo;
    private String nome, quenteFrio , fornecedor , validade;
    private float preco;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getTempConsumo() {
        return tempConsumo;
    }

    public void setTempConsumo(int tempConsumo) {
        this.tempConsumo = tempConsumo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getQuenteFrio() {
        return quenteFrio;
    }

    public void setQuenteFrio(String quenteFrio) {
        this.quenteFrio = quenteFrio;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
    
    
}
