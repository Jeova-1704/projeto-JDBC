package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.List;
import java.util.Scanner;

public class program2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("==== TEST 1: department findById ====");
        Department department = departmentDao.findById(1);
        System.out.println(department);

        System.out.println("\n==== TEST 2: department findAll ====");
        List<Department> list = departmentDao.findAll();
        for (Department dep : list) {
            System.out.println(dep);
        }

        System.out.println("\n==== TEST 3: department inserte ====");
        Department newDepartment = new Department(null, "Programação");
        departmentDao.insert(newDepartment);
        System.out.println("Department inserte successfully");


        System.out.println("\n==== TEST 4: department update ====");
        department = departmentDao.findById(1);
        department.setName("Jogos");
        departmentDao.update(department);
        System.out.println("Update completed");

        System.out.println("\n==== TEST 5: department delete ====");
        System.out.println("Enter id for delete teste:");
        int id = scanner.nextInt();
        departmentDao.deleteById(id);
        System.out.println("Delete completed");


        scanner.close();






    }
}
