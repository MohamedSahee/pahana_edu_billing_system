package service;

import dao.BillDAO;
import dao.ItemDAO;
import model.Item;
import java.sql.SQLException;
import java.util.Map;

public class BillingService {
    private final BillDAO billDAO = new BillDAO();
    private final ItemDAO itemDAO = new ItemDAO();

    public int createBill(int customerId, Map<Integer, Integer> itemQty) throws SQLException {
        double total = 0.0;
        for (Map.Entry<Integer,Integer> e : itemQty.entrySet()) {
            Item i = itemDAO.get(e.getKey());
            if (i != null) {
                total += i.getPrice() * e.getValue();
            }
        }
        int billId = billDAO.createBill(customerId, total);
        if (billId > 0) {
            for (Map.Entry<Integer,Integer> e : itemQty.entrySet()) {
                Item i = itemDAO.get(e.getKey());
                if (i != null) {
                    billDAO.addBillItem(billId, i.getId(), e.getValue(), i.getPrice());
                }
            }
        }
        return billId;
    }
}
