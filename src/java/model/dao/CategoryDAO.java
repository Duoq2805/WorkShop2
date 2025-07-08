package model.dao;

import model.Category;
import utilities.ConnectDB;

import jakarta.servlet.ServletContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements Accessible<Category> {

    private ServletContext sc;
    private Connection conn;

    public CategoryDAO() {
        try {
            ConnectDB db = new ConnectDB();
            this.conn = db.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CategoryDAO(ServletContext sc) throws SQLException, ClassNotFoundException {
        this.sc = sc;
        ConnectDB db = new ConnectDB(sc);
        this.conn = db.getConnection();
    }

    @Override
    public int insertRec(Category o) {
        String sql = "INSERT INTO categories (categoryName, memo) VALUES (?, ?)";
        try ( PreparedStatement ps = conn.prepareStatement(sql, new String[]{"typeId"})) {
            ps.setString(1, o.getCategoryName());
            ps.setString(2, o.getMemo());
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    o.setTypeId(rs.getInt(1)); // Lấy typeId tự tăng
                }
            }
            return affectedRows;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateRec(Category o) {
        String sql = "UPDATE categories SET categoryName = ?, memo = ? WHERE typeId = ?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, o.getCategoryName());
            ps.setString(2, o.getMemo());
            ps.setInt(3, o.getTypeId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int deleteRec(Category o) {
        String sql = "DELETE FROM categories WHERE typeId = ?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, o.getTypeId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Category> listAll() {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM categories";
        try ( PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Category c = new Category(rs.getInt("typeId"), rs.getString("categoryName"), rs.getString("memo"));
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Category getObjectById(String id) {
        String sql = "SELECT * FROM categories WHERE typeId = ?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Category(rs.getInt("typeId"), rs.getString("categoryName"), rs.getString("memo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
