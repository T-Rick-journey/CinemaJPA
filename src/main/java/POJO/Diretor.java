package main.java.POJO;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Diretor {
    public Diretor(){}
    
    @Id
    private int id;
    private String nome;
    private double valor_cache;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor_cache() {
        return valor_cache;
    }

    public void setValor_cache(double valor_cache) {
        this.valor_cache = valor_cache;
    }
     
}
