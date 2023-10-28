package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {
    private Connection conn;
    public DepartmentDaoJDBC(Connection conn) {this.conn = conn;}


    @Override
    public void insert(Department department) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement("INSERT INTO department "
            + "(Name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, department.getName());
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if(resultSet.next()) {
                    int id = resultSet.getInt(1);
                    department.setId(id);
                }
                DB.closeResultSet(resultSet);
            } else {
                throw new DbException("Erro, nenhuma linha foi alterada");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
        }
    }

    @Override
    public void update(Department department) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = conn.prepareStatement("SELECT * FROM department WHERE id = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return instantiateDepartment(resultSet);
            }
            return null;
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }
    private Department instantiateDepartment(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getInt("Id"));
        department.setName(resultSet.getString("Name"));

        return department;
    }


    @Override
    public List<Department> findAll() {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = conn.prepareStatement("SELECT * FROM department ORDER BY id");
            resultSet = statement.executeQuery();

            List<Department> list = new ArrayList<>();

            while (resultSet.next()) {
                Department department = instantiateDepartment(resultSet);
                list.add(department);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

    }
}
