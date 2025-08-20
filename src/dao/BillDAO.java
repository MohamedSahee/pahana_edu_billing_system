package dao;

import util.DBConnection;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {
    public int createBill(int customerId, double total) throws SQLException {
        String sql = "INSERT INTO bills(customer_id, created_at, total) VALUES (?, NOW(), ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, customerId);
            ps.setDouble(2, total);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
            return -1;
        }
    }

    public void addBillItem(int billId, int itemId, int qty, double price) throws SQLException {
        String sql = "INSERT INTO bill_items(bill_id, item_id, qty, price) VALUES (?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, billId);
            ps.setInt(2, itemId);
            ps.setInt(3, qty);
            ps.setDouble(4, price);
            ps.executeUpdate();
        }
    }

    public List<String> byCustomer(int customerId) throws SQLException {
        // returns a simple list of bill summaries
        String sql = "SELECT id, created_at, total FROM bills WHERE customer_id=? ORDER BY id DESC";
        List<String> res = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.add("Bill #" + rs.getInt("id") + " | " + rs.getTimestamp("created_at") + " | Total: " + rs.getDouble("total"));
            }
        }
        return res;
    }
}
