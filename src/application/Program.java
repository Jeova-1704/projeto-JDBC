package application;

import model.entities.Department;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Department department = new Department(1, "Books");
        System.out.println(department);
    }
}
