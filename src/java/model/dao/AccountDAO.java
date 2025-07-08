package model.dao;

import jakarta.servlet.ServletContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import utilities.ConnectDB;

/**
 *
 * @author Thai Duong
 */
public class AccountDAO implements Accessible<Account> {

    private ServletContext sc;
    private Connection conn;

    public AccountDAO() {
        try {
            ConnectDB db = new ConnectDB();
            this.conn = db.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AccountDAO(ServletContext sc) throws SQLException, ClassNotFoundException {
        this.sc = sc;
        ConnectDB db = new ConnectDB(sc);
        this.conn = db.getConnection();
    }

    private Connection getConnect(ServletContext sc) throws SQLException, ClassNotFoundException {
        ConnectDB db = (sc == null) ? new ConnectDB() : new ConnectDB(sc);
        return db.getConnection();
    }

    @Override
    public int insertRec(Account obj) {
        String sql = "INSERT INTO accounts (account, pass, lastName, firstName, birthday, gender, phone, isUse, roleInSystem) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, obj.getAccount());
            ps.setString(2, obj.getPass());
            ps.setString(3, obj.getLastName());
            ps.setString(4, obj.getFirstName());
            ps.setDate(5, obj.getBirthday());
            ps.setBoolean(6, obj.isGender());
            ps.setString(7, obj.getPhone());
            ps.setBoolean(8, obj.getIsUse());
            ps.setInt(9, obj.getRoleInSystem());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateRec(Account obj) {
        String sql = "UPDATE accounts SET pass = ?, lastName = ?, firstName = ?, birthday = ?, gender = ?, phone = ?, isUse = ?, roleInSystem = ? WHERE account = ?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, obj.getPass());
            ps.setString(2, obj.getLastName());
            ps.setString(3, obj.getFirstName());
            ps.setDate(4, obj.getBirthday());
            ps.setBoolean(5, obj.isGender());
            ps.setString(6, obj.getPhone());
            ps.setBoolean(7, obj.getIsUse());
            ps.setInt(8, obj.getRoleInSystem());
            ps.setString(9, obj.getAccount());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int deleteRec(Account obj) {
        String sql = "DELETE FROM accounts WHERE account = ?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, obj.getAccount());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Account getObjectById(String id) {
        String sql = "SELECT * FROM accounts WHERE account = ?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Account(rs.getString("account"), rs.getString("pass"),
                        rs.getString("lastName"), rs.getString("firstName"),
                        rs.getDate("birthday"), rs.getBoolean("gender"),
                        rs.getString("phone"), rs.getBoolean("isUse"),
                        rs.getInt("roleInSystem"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Account> listAll() {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT * FROM accounts";
        try ( PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Account a = new Account(rs.getString("account"), rs.getString("pass"),
                        rs.getString("lastName"), rs.getString("firstName"),
                        rs.getDate("birthday"), rs.getBoolean("gender"),
                        rs.getString("phone"), rs.getBoolean("isUse"),
                        rs.getInt("roleInSystem"));
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
