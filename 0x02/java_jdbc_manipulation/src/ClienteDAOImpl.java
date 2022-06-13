import java.sql.*;

public class ClienteDAOImpl implements ClienteDAO{

    private static Connection connection = null;

    @Override
    public Connection connect(String urlConexao) {
        if(connection == null) {
            try {
                connection = DriverManager.getConnection(urlConexao);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return connection;
    }

    @Override
    public void createTable(String urlConexao) {
        if(connection == null) {
            connect(urlConexao);
        }
        Statement st = null;

        try {
            st = connection.createStatement();
            st.executeQuery("CREATE TABLE Cliente " +
                    "(id INTEGER NOT NULL, " +
                    "nome VARCHAR(255), " +
                    "idade INTEGER, " +
                    "cpf VARCHAR(255), " +
                    "rg VARCHAR(255), " +
                    "PRIMARY KEY ( id ))");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void insert(String url_conexao, Cliente cliente) {
        if(connection == null) {
            connect(url_conexao);
        }
        PreparedStatement st = null;

        try {
            st = connection.prepareStatement("INSERT INTO Cliente " +
                    "(nome, idade, cpf, rg) " +
                    "VALUES " +
                    "(?, ?, ?, ?)");
            st.setString(1, cliente.getNome());
            st.setInt(2, cliente.getIdade());
            st.setString(3, cliente.getCpf());
            st.setString(4, cliente.getRg());
            st.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if(st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    @Override
    public void selectAll(String urlConexao) {
        if(connection == null) {
            connect(urlConexao);
        }
        Statement st = null;
        ResultSet rs = null;

        try {
            st = connection.createStatement();

            rs = st.executeQuery("SELECT * FROM Cliente");

            while(rs.next()) {
                System.out.println(rs.getInt("id") + ", " +
                        rs.getString("nome") + ", " +
                        rs.getInt("idade") + ", " +
                        rs.getString("cpf") + ", " +
                        rs.getString("rg"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            if(st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    @Override
    public void update(String urlConexao, int id, String name, Integer idade) {
        if(connection == null) {
            connect(urlConexao);
        }
        PreparedStatement st = null;

        try {
            st = connection.prepareStatement("UPDATE Cliente SET nome = ?, idade = ?" +
                    " WHERE id = ?");

            st.setString(1, name);
            st.setInt(2, idade);
            st.setInt(3, id);
            st.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if(st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    @Override
    public void delete(String urlConexao, int id) {
        if(connection == null) {
            connect(urlConexao);
        }
        PreparedStatement st = null;

        try {
            st = connection.prepareStatement("DELETE FROM Cliente" +
                    " WHERE id = ?");

            st.setInt(1, id);
            st.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if(st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void closeConnection() {
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
