
package main.java.JPA;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import main.java.POJO.Filme;

public class Teste {

    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("Cinema").createEntityManager();
	System.out.println("Ok Conexao ----- Cinema-JPA");
                
        Query query = em.createQuery("select f from Filme f", Filme.class);
		List<Filme> Filmes = query.getResultList();
		System.out.println("Qtde de clientes - "+ Filmes.size());
		for (Filme filme : Filmes) {
			System.out.println(filme.getId()+"\n"+filme.getTitulo()+"\n"+filme.getLancamento());
		}
    }
    
}
