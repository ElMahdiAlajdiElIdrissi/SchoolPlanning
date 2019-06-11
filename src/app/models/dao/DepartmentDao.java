package app.models.dao;

import app.models.entities.Department;

import java.sql.*;

public class DepartmentDao {
    private String url;
    private String user;
    private String password;

    public DepartmentDao() {

    }

    public DepartmentDao(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Department getDepartmentByID(int id) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM department WHERE Id = ?")) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    Department department = new Department();
                    department.setId(id);
                    department.setDepartmentName(result.getString("Department_Name"));
                    return department;
                } else {
                    return null;
                }
            }
        } catch (SQLException se) {
            throw new SQLException("Something went wrong");
        }
    }

    public void updateDepartment(Department department) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement("UPDATE department SET Department_Name=? WHERE Id=? ")) {
            statement.setString(1, department.getDepartmentName());
            statement.setInt(2, department.getId());
            statement.executeUpdate();
        } catch (SQLException se) {
            throw new SQLException("Something went wrong");
        }
    }

    public void insertNewDepartment(String departmentName) throws SQLException{
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement("INSERT IGNORE INTO Department (Department_Name)" +
                     " VALUES  + (" + departmentName + "),")){
            statement.execute();
        } catch (SQLException se){
            throw new SQLException("You tried to create a new departement and failed");
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
