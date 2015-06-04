package cn.miao.code.sync;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Created by Miaosk
 * @date Created on 2015/5/19.
 */
public class DbFactory {

    private static DataSource ds = null;

    public static Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Connection getLocalConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void roallBack(Connection conn) {
        try {
            conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
        }
    }

    public static void free(ResultSet rs, Statement st, Connection conn) {
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null)
                    st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null)
                        conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public DataSource getDs() {
        return DbFactory.ds;
    }

    public void setDs(DataSource ds) {
        DbFactory.ds = ds;
    }


}
