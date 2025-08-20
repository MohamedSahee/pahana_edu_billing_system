package dao;

import model.User;
import util.DBConnection;
import java.sql.*;

public class UserDAO {
    public User findByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("username"),
                        rs.getString("password"), rs.getString("role"));
            }
            return null;
        }
    }

    public void add(User u) throws SQLException {
        String sql = "INSERT INTO users(username, password, role) VALUES (?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getRole());
            ps.executeUpdate();
        }
    }
}
