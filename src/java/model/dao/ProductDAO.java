package model.dao;

import model.Product;
import utilities.ConnectDB;

import jakarta.servlet.ServletContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements Accessible<Product> {

    private ServletContext sc;
    private Connection conn;

    public ProductDAO() {
        try {
            ConnectDB db = new ConnectDB();
            this.conn = db.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ProductDAO(ServletContext sc) throws SQLException, ClassNotFoundException {
        this.sc = sc;
        ConnectDB db = new ConnectDB(sc);
        this.conn = db.getConnection();
    }

    @Override
    public int insertRec(Product o) {
        String sql = "INSERT INTO products (productId, productName, productImage, brief, postedDate, typeId, account, unit, price, discount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, o.getProductId());
            ps.setString(2, o.getProductName());
            ps.setString(3, o.getProductImage());
            ps.setString(4, o.getBrief());
            ps.setDate(5, o.getPostedDate());
            ps.setInt(6, o.getTypeId());
            ps.setString(7, o.getAccount());
            ps.setString(8, o.getUnit());
            ps.setInt(9, o.getPrice());
            ps.setInt(10, o.getDiscount());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateRec(Product o) {
        String sql = "UPDATE products SET productName = ?, productImage = ?, brief = ?, postedDate = ?, typeId = ?, account = ?, unit = ?, price = ?, discount = ? WHERE productId = ?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, o.getProductName());
            ps.setString(2, o.getProductImage());
            ps.setString(3, o.getBrief());
            ps.setDate(4, o.getPostedDate());
            ps.setInt(5, o.getTypeId());
            ps.setString(6, o.getAccount());
            ps.setString(7, o.getUnit());
            ps.setInt(8, o.getPrice());
            ps.setInt(9, o.getDiscount());
            ps.setString(10, o.getProductId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int deleteRec(Product o) {
        String sql = "DELETE FROM products WHERE productId = ?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, o.getProductId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Product> listAll() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try ( PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Product p = new Product(rs.getString("productId"), rs.getString("productName"),
                        rs.getString("productImage"), rs.getString("brief"), rs.getDate("postedDate"),
                        rs.getInt("typeId"), rs.getString("account"), rs.getString("unit"),
                        rs.getInt("price"), rs.getInt("discount"));
                System.out.println("Debug: productId=" + p.getProductId() + ", brief=" + p.getBrief());
                list.add(p);
            }
            System.out.println("Số sản phẩm: " + list.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Product getObjectById(String id) {
        String sql = "SELECT * FROM products WHERE productId = ?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Product(rs.getString("productId"), rs.getString("productName"),
                        rs.getString("productImage"), rs.getString("brief"),
                        rs.getDate("postedDate"), rs.getInt("typeId"),
                        rs.getString("account"), rs.getString("unit"),
                        rs.getInt("price"), rs.getInt("discount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> listByCategory(String typeId) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE typeId = ?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(typeId));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product(
                        rs.getString("productId"),
                        rs.getString("productName"),
                        rs.getString("productImage"),
                        rs.getString("brief"),
                        rs.getDate("postedDate"),
                        rs.getInt("typeId"),
                        rs.getString("account"),
                        rs.getString("unit"),
                        rs.getInt("price"),
                        rs.getInt("discount")
                );
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> getFeaturedProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT TOP 6 * FROM products ORDER BY postedDate DESC";
        try ( PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Product p = new Product(
                        rs.getString("productId"),
                        rs.getString("productName"),
                        rs.getString("productImage"),
                        rs.getString("brief"),
                        rs.getDate("postedDate"),
                        rs.getInt("typeId"),
                        rs.getString("account"),
                        rs.getString("unit"),
                        rs.getInt("price"),
                        rs.getInt("discount")
                );
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> getFilteredProducts(Double min, Double max, boolean saleOnly, String sort) {
        List<Product> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM products WHERE 1=1");

        if (min != null) {
            sql.append(" AND price >= ").append(min);
        }
        if (max != null) {
            sql.append(" AND price <= ").append(max);
        }
        if (saleOnly) {
            sql.append(" AND discount > 0");
        }
        if ("asc".equals(sort)) {
            sql.append(" ORDER BY price ASC");
        } else if ("desc".equals(sort)) {
            sql.append(" ORDER BY price DESC");
        }

        try ( PreparedStatement ps = conn.prepareStatement(sql.toString());  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Product p = new Product(
                        rs.getString("productId"),
                        rs.getString("productName"),
                        rs.getString("productImage"),
                        rs.getString("brief"),
                        rs.getDate("postedDate"),
                        rs.getInt("typeId"),
                        rs.getString("account"),
                        rs.getString("unit"),
                        rs.getInt("price"),
                        rs.getInt("discount")
                );
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Product> searchByName(String keyword) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE productName LIKE ?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product(
                        rs.getString("productId"),
                        rs.getString("productName"),
                        rs.getString("productImage"),
                        rs.getString("brief"),
                        rs.getDate("postedDate"),
                        rs.getInt("typeId"),
                        rs.getString("account"),
                        rs.getString("unit"),
                        rs.getInt("price"),
                        rs.getInt("discount")
                );
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
