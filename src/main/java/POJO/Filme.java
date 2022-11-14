
package main.java.POJO;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
public class Filme {
    public Filme (){}
    @Id
    private int id;
    private String titulo;
    @OneToMany
    private int id_Diretor;
    @Temporal(TemporalType.DATE)
    private Date lancamento;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getId_Diretor() {
        return id_Diretor;
    }

    public void setId_Diretor(int id_Diretor) {
        this.id_Diretor = id_Diretor;
    }

    public Date getLancamento() {
        return lancamento;
    }

    public void setLancamento(Date lancamento) {
        this.lancamento = lancamento;
    }
    
}
