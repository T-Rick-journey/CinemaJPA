package main.java.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.java.POJO.Diretor;

public class DiretorDAO implements DAO<Diretor> {
    
    @Override
    public void Insert(Diretor diretor) {
        Connection conn;
        try {
            conn = ds.getConnection();
            String sql = "INSERT INTO DIRETOR"
                    + "(ID, NOME, VALOR_CACHE)"
                    + "VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, diretor.getId());
            stmt.setString(2, diretor.getNome());
            stmt.setDouble(3, diretor.getValor_cache());
            int contUp = stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e){
            System.out.println(e);
        }
    }

    @Override
    public void update(Diretor diretor) {
        Connection conn;
        try {
            conn = ds.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement stmt = conn.prepareStatement("UPDATE DIRETOR "
                    + " SET NOME = ?, VALOR_CACHE = ? "
                    + " WHERE ID = ?");
            stmt.setString(1, diretor.getNome());
            stmt.setDouble(2, diretor.getValor_cache());
            stmt.setInt(3, diretor.getId());
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
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM DIRETOR "
                                                            + "WHERE ID = ?");
            stmt.setInt(1, id);
            conn.commit();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Diretor findByID(int id) {
        Diretor diretor = new Diretor();
        Connection conn;
        try{
            conn = ds.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM DIRETOR WHERE ID = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                diretor.setId(rs.getInt("ID"));
                diretor.setNome(rs.getString("NOME"));
                diretor.setValor_cache(rs.getDouble("VALOR_CACHE"));
            }
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return diretor;
    }

    @Override
    public List<Diretor> findAll() {
        List<Diretor> listaDiretor = new ArrayList<Diretor>();
        Diretor diretor = null;
        Connection conn;
        try {
            conn = ds.getConnection();
            String sql = "SELECT * FROM DIRETOR";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
		diretor = new Diretor();
		diretor.setId(rs.getInt("ID"));
                diretor.setNome(rs.getString("NOME"));
                diretor.setValor_cache(rs.getDouble("VALOR_CACHE"));
                listaDiretor.add(diretor);
            }
            stmt.close();

        } catch (SQLException e){
            e.printStackTrace();
        }
        return listaDiretor;
    }
    
}
