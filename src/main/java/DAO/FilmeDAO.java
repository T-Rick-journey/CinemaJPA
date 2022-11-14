package main.java.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.POJO.Filme;



public class FilmeDAO implements DAO<Filme> {

    @Override
    public void Insert(Filme filme) {
        Connection conn;
        try {
            conn = ds.getConnection();
            String sql = "INSERT INTO FILME"
                    + "(ID, TITULO, ID_DIRETOR, LANCAMENTO )"
                    + "VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, filme.getId());
            stmt.setString(2, filme.getTitulo());
            stmt.setDouble(3, filme.getId_Diretor());
            stmt.setDate(4, (Date) filme.getLancamento());
            int contUp = stmt.executeUpdate();
            stmt.close();

        } catch (SQLException e){
            System.out.println(e);
        }
    }

    @Override
    public void update(Filme filme) {
        Connection conn;
        try {
            conn = ds.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement stmt = conn.prepareStatement("UPDATE FILME "
                    + "SET ID = ? , TITULO = ? , ID_DIRETOR = ? , "
                    + " LANCAMENTO = ? "
                    + " WHERE ID = ? ");
            stmt.setInt(1, filme.getId());
            stmt.setString(2, filme.getTitulo());
            stmt.setInt(3, filme.getId_Diretor());
            stmt.setDate(4, (Date) filme.getLancamento());
            stmt.setInt(5, filme.getId());
            int contup = stmt.executeUpdate();
            conn.commit();
            stmt.close();
            
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        Connection conn;
        try {
            conn = ds.getConnection();
            conn.setAutoCommit(false);
            String sql = "DELETE FROM FILME WHERE ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int contUp = stmt.executeUpdate();
            conn.commit();
            stmt.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Filme findByID(int id) {
        Connection conn;
        Filme filme = new Filme();
        try{
            conn = ds.getConnection();
            String sql = "SELECT * FROM FILME WHERE ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                filme.setId(rs.getInt("ID"));
                filme.setId_Diretor(rs.getInt("ID_DIRETOR"));
                filme.setTitulo(rs.getString("TITULO"));
                filme.setLancamento(rs.getDate("LANCAMENTO"));
            }
            stmt.close();

        } catch (SQLException e){
            e.printStackTrace();
        }
        return filme;
    }

    public List<Filme> findByDiretorID(int id) {
        Connection conn;
        List<Filme> listaFilme = new ArrayList<Filme>();
        Filme filme = null;
        try {
            conn = ds.getConnection();
            String sql = "Select * from Filme where ID_DIRETOR = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                filme = new Filme();
                filme.setId(rs.getInt("ID"));
                filme.setId_Diretor(rs.getInt("ID_DIRETOR"));
                filme.setLancamento(rs.getDate("LANCAMENTO"));
                filme.setTitulo(rs.getString("TITULO"));
                listaFilme.add(filme);
            }
            stmt.close();
            
        } catch (SQLException e){
            
        }
        return listaFilme;
    }

    @Override
    public List<Filme> findAll() {
        List<Filme> listaAllFilme = new ArrayList<Filme>();
        Filme filme = null;
        Connection conn;
        try {
            conn = ds.getConnection();
            String sql = "SELECT * FROM FILME";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                filme = new Filme();
                filme.setId(rs.getInt("ID"));
                filme.setId_Diretor(rs.getInt("ID_DIRETOR"));
                filme.setTitulo(rs.getString("TITULO"));
                filme.setLancamento(rs.getDate("LANCAMENTO"));
                listaAllFilme.add(filme);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaAllFilme;
    }

    @Override
    public void close() {
        DAO.super.close();
    }

}
