package dao;

import datasource.MariaDbConnection;
import entity.Currency;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CurrencyDao {

    public double getExchangeRate(String abbreviation) throws SQLException {
        String sql = "SELECT rate_to_usd FROM currency WHERE abbreviation = ?";

        try (Connection conn = MariaDbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, abbreviation);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("rate_to_usd");
                } else {
                    throw new SQLException("Currency not found: " + abbreviation);
                }
            }
        }
    }

    public List<Currency> getAllCurrencies() throws SQLException {
        List<Currency> currencies = new ArrayList<>();
        String sql = "SELECT id, abbreviation, name, rate_to_usd FROM currency";

        try (Connection conn = MariaDbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                currencies.add(new Currency(
                        rs.getInt("id"),
                        rs.getString("abbreviation"),
                        rs.getString("name"),
                        rs.getDouble("rate_to_usd")
                ));
            }
        }
        return currencies;
    }
}

