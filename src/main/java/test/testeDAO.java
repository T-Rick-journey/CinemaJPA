package main.java.test;

import java.sql.Date;
import main.java.POJO.Diretor;
import main.java.POJO.Filme;
import main.java.DAO.FilmeDAO;
import java.util.List;
import main.java.DAO.DiretorDAO;

public class testeDAO {

    public static void main(String[] args) {
        // Testes com diretores
        DiretorDAO Diretores = new DiretorDAO();
        
        System.out.println("______________direto by id_________________");
        Diretor diretor1 = Diretores.findByID(1);
        System.out.println(diretor1.getNome()+"\n"
                +diretor1.getValor_cache()+"\n\n");
        
        System.out.println("___________Todos os Diretores____________________");
        List<Diretor> listaDiretores = Diretores.findAll();
        for (Diretor diretor2: listaDiretores){
            System.out.println(diretor2.getId()+"\n"
                +diretor2.getNome()+"\n"
                +diretor2.getValor_cache()+"\n");
        }
        System.out.println("___________Inserir Diretor____________________");
        Diretor diretor3 = new Diretor();
        diretor3.setId(6);
        diretor3.setNome("GEORGE ORWELL");
        diretor3.setValor_cache(493521.82);
        Diretores.Insert(diretor3);
        
        System.out.println("___________Update diretor____________________");
        Diretor diretorUpdate = new Diretor();
        diretorUpdate.setId(1);
        diretorUpdate.setNome("HIROYA OKU");
        diretorUpdate.setValor_cache(23475.00);
        Diretores.update(diretor3);
        // Diretor após o update
        Diretor diretorAposUpdate = Diretores.findByID(1);
        System.out.println(diretorAposUpdate.getNome()+"\n"
                +diretorAposUpdate.getValor_cache()+"\n\n");
        
        System.out.println("______________Delete diretor_________________");
        Diretores.delete(6);
        
        // Testes com filmes
        FilmeDAO Filmes = new FilmeDAO();
        System.out.println("_______________Filme By ID________________");
        Filme filme1 = Filmes.findByID(4);
        System.out.println(filme1.getId()+"\n"+filme1.getTitulo()+"\n"+
                filme1.getId_Diretor()+"\n"+filme1.getLancamento());
        System.out.println("_____________Todos os filmes de 1 diretor__________________");
        List<Filme> listaFilmes = Filmes.findByDiretorID(2);
        for (Filme filme2: listaFilmes){
            System.out.println(filme2.getId()+"\n"+filme2.getTitulo()+"\n"+
                filme2.getId_Diretor()+"\n"+filme2.getLancamento());
        }
        System.out.println("_____________Todos os filmes__________________");
        List<Filme> todoFilme = Filmes.findAll();
        for (Filme filme3: todoFilme){
            System.out.println(filme3.getId()+"\n"+filme3.getTitulo()+"\n"+
                filme3.getId_Diretor()+"\n"+filme3.getLancamento()+"\n");
        }
        
        System.out.println("___________Inserir Filme____________________");
        Filme filmeInsert = new Filme();
        long millis = System.currentTimeMillis();  
        Date data = new Date(millis); 

        filmeInsert.setId(13);
        filmeInsert.setId_Diretor(3);
        filmeInsert.setLancamento(data);
        filmeInsert.setTitulo("TITULO QUALQUER");
        Filmes.Insert(filmeInsert);
        
        System.out.println("___________Update Filme____________________");
        Filme filmeUpdate = new Filme();
        filmeUpdate.setId(13);
        filmeUpdate.setTitulo("O HOME NO CASTELO ALTO");
        filmeUpdate.setId_Diretor(3);
        
        Date data2 = new Date(millis); 
        filmeUpdate.setLancamento(data2);
        
        Filmes.update(filmeUpdate);
        
        Filme filme2 = Filmes.findByID(13);
        System.out.println(filme2.getId()+"\n"+filme2.getTitulo()+"\n"+
                filme2.getId_Diretor()+"\n"+filme2.getLancamento());
        System.out.println("___________Delete Filme____________________");
        Filmes.delete(13);

    }
}
