package dao;

import model.Item;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    public void add(Item i) throws SQLException {
        String sql = "INSERT INTO items(name, price, stock) VALUES (?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, i.getName());
            ps.setDouble(2, i.getPrice());
            ps.setInt(3, i.getStock());
            ps.executeUpdate();
        }
    }

    public void update(Item i) throws SQLException {
        String sql = "UPDATE items SET name=?, price=?, stock=? WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, i.getName());
            ps.setDouble(2, i.getPrice());
            ps.setInt(3, i.getStock());
            ps.setInt(4, i.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM items WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public Item get(int id) throws SQLException {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM items WHERE id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Item(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"), rs.getInt("stock"));
            }
            return null;
        }
    }

    public List<Item> all() throws SQLException {
        List<Item> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM items ORDER BY id DESC")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Item(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"), rs.getInt("stock")));
            }
        }
        return list;
    }
}
