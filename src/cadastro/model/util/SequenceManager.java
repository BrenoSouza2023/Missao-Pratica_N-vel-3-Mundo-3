package cadastro.model.util;

import java.sql.*;

public class SequenceManager {
    public static int getValue(String sequenceName) throws SQLException {
        int value = -1;
        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement pstmt = ConectorBD.getPrepared(conn, "SELECT NEXT VALUE FOR " + sequenceName);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                value = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Failed to get next value for sequence: " + sequenceName, e);
        }
        return value;
    }

    public SequenceManager(ConectorBD conectorBD) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}