package database.shopsDb;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    MysqlDataSource dataSource = new MysqlDataSource();

    public Controller() throws SQLException {

        setDataSource();
    }

    public void setDataSource() throws SQLException {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dataSource.setServerName(DbParameters.serverName);
        dataSource.setPort(DbParameters.port);
        dataSource.setDatabaseName(DbParameters.dbName);
        dataSource.setUser(DbParameters.username);
        dataSource.setPassword(DbParameters.password);

    }


    public boolean createNewChain(Chain newChain) throws SQLException {
        int res = 0;

        try (Connection connection = dataSource.getConnection()) {
            String insertChain = "insert into chain (name,subchain) values(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertChain, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, newChain.getChainName());
            preparedStatement.setString(2, newChain.getSubChain());
            res = preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    newChain.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating chain failed, no ID obtained.");
                }
            }
        }
        return res == 1;
    }

    public boolean createNewMall(Mall newMall) throws SQLException {
        int res = 0;

        try (Connection connection = dataSource.getConnection()) {
            String insertChain = "insert into mall (name,address,mall_group) values(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertChain, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, newMall.getName());
            preparedStatement.setString(2, newMall.getAddress());
            preparedStatement.setString(3, newMall.getMallGroup());
            res = preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    newMall.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating chain failed, no ID obtained.");
                }
            }
        }
        return res == 1;
    }


    public boolean addStore(Store newStore) throws SQLException {
        int res = 0;
        try (Connection connection = dataSource.getConnection()) {
            String insertStore = "insert into store (id_chain_fk,address,mall_id,store_num) values(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertStore, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, newStore.getChainId());
            preparedStatement.setString(2, newStore.getAddress());
            preparedStatement.setObject(3, newStore.getMallId());
            preparedStatement.setString(4, newStore.getStoreNum());
            res = preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    newStore.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating store failed, no ID obtained.");
                }
            }

        }
        return res == 1;

    }

    public boolean addEmployee(Employee newEmployee) throws SQLException {
        int res = 0;
        try (Connection connection = dataSource.getConnection()) {
            String insertEmployee = "insert into employee (first_name,last_name,passport,chain_id,store_id) values(?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertEmployee, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, newEmployee.getFirstName());
            preparedStatement.setString(2, newEmployee.getLastName());
            preparedStatement.setString(3, newEmployee.getPassport());
            preparedStatement.setObject(4, newEmployee.getChainId());
            preparedStatement.setObject(5, newEmployee.getStoreId());
            res = preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    newEmployee.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating employee failed, no ID obtained.");
                }
            }

        }
        return res == 1;

    }


    public List<Chain> getListOfChains() throws SQLException {
        List<Chain> list = new ArrayList<>();
        String select = "select chain_id,name,subchain from chain";
        try (Connection connection = dataSource.getConnection()) {

            ResultSet res = connection.prepareStatement(select).executeQuery();
            while (res.next()) {
                Chain temp = new Chain(res.getInt("chain_id"),
                        res.getString("name"),
                        res.getString("subchain"));
                list.add(temp);
            }

        }
        return list;
    }

    public List<Mall> getListOfMalls() throws SQLException {
        List<Mall> list = new ArrayList<>();

        String select = "select id,name,address,mall_group from mall";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(select);

            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                list.add(new Mall(res.getInt("id"),
                        res.getString("name"),
                        res.getString("address"),
                        res.getString("mall_group")));

            }

        }
        return list;
    }

    public List<Store> getListOfStores() throws SQLException {
        List<Store> list = new ArrayList<>();
        String select = "select s.id, c.name, s.address, m.name, m.address,s.store_num from shops.store s inner join shops.chain c on s.id_chain_fk=c.chain_id left join shops.mall m on s.mall_id = m.id";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(select);

            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                list.add(new Store(res.getInt(1),
                                res.getString(2),
                                res.getString(3),
                                res.getString(4),
                                res.getString(5),
                                res.getString(6)
                        )
                );

            }

        }
        return list;
    }

    public List<Store> getStoresOfCertainMallGroup(String group) throws SQLException {
        List<Store> list = new ArrayList<>();
        String select = "select s.id, c.name, s.address, m.name, m.address,s.store_num from shops.store s inner join shops.chain c on s.id_chain_fk=c.chain_id left join shops.mall m on s.mall_id = m.id where m.mall_group = ?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setString(1, group);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                list.add(new Store(res.getInt(1),
                                res.getString(2),
                                res.getString(3),
                                res.getString(4),
                                res.getString(5),
                                res.getString(6)
                        )
                );

            }

        }
        return list;
    }
    public List<Store> getStoresOfCertainMall(int mallId) throws SQLException {
        List<Store> list = new ArrayList<>();
        String select = "select s.id, c.name, s.address, m.name, m.address,s.store_num from store s inner join chain c on s.id_chain_fk=c.chain_id left join mall m on s.mall_id = m.id where m.id = ?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setInt(1, mallId);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                list.add(new Store(res.getInt(1),
                                res.getString(2),
                                res.getString(3),
                                res.getString(4),
                                res.getString(5),
                                res.getString(6)
                        )
                );

            }

        }
        return list;
    }

    public List<Employee> getEmployeesOfCertainChain(int chainId) throws SQLException {
        List<Employee> list = new ArrayList<>();
        String select = "select e.id, e.first_name, e.last_name, e.passport, c.name from employee e inner join shops.chain c on e.chain_id=c.chain_id where c.chain_id=?" +
                " union all" +
                " select e.id, e.first_name, e.last_name, e.passport, c.name from employee e inner join store s on e.store_id=s.id inner join chain c on s.id_chain_fk=c.chain_id  where c.chain_id=?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setInt(1, chainId);
            preparedStatement.setInt(2, chainId);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                list.add(new Employee(res.getInt(1),
                                res.getString(2),
                                res.getString(3),
                                res.getString(4),
                                res.getString(5)

                        )
                );

            }

        }
        return list;

    }

    public Store getAllDetailsOfAShop(int storeId) throws SQLException {

        String select = "select s.id, c.name, s.address, m.name, m.address,s.store_num from store s " +
                "inner join shops.chain c on s.id_chain_fk=c.chain_id left join shops.mall m on s.mall_id = m.id  where s.id = ?";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setInt(1, storeId);
            ResultSet res = preparedStatement.executeQuery();
            if (res.next()) {
                return new Store(res.getInt(1),
                                res.getString(2),
                                res.getString(3),
                                res.getString(4),
                                res.getString(5),
                                res.getString(6)

                );

            }

        }
        return null;
    }
}
