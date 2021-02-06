package gui;

import service.EmployeeManager;
import service.Manager;
import vo.Employee;

import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeUI implements UI{
    Scanner scanner;
    Manager manager;

    public EmployeeUI() {
        scanner = new Scanner(System.in);
        manager = new EmployeeManager();

        String select = null;

        while(true){
            printMainMenu();
            select = scanner.nextLine();
            switch (select){
                case "1":
                    insertEmployee();
                    break;
                case "2":
                    findEmployee();
                    break;
                case "3":
                    deleteEmployee();
                    break;
                case"4":
                    manager.showAll();
                    break;
                case "9":
                    System.out.println("Exit this Application.");
                    System.out.println("All data will save the place.");
                    manager.saveFile();
                    System.exit(0);
            }
        }//while
    }//constructor

    @Override
    public void insertEmployee() {
        System.out.println("==================================");
        System.out.println("====[KITA Regist new Employee]====");
        System.out.println("==================================");
        System.out.println("1. Employee ID Number : " + (Employee.serial + 1));
        System.out.println("2. Employee Name : " );
        String name = scanner.nextLine();

        System.out.println("3. Salary Of New Employee : ");
        int salary = scanner.nextInt();

        ArrayList<String> license = new ArrayList<String>();
        while (true){
            Scanner scnForLicense = new Scanner(System.in);
            System.out.println("4. >>>>>> License : ");
            String temp = scnForLicense.nextLine();

            if(temp.length() == 0){
                break;
            }

            license.add(temp);
        }//while

        Employee employee = new Employee(name, salary, license);
        manager.insertEmployee(employee);
    }

    @Override
    public void deleteEmployee() {
        System.out.println(" Input Employee ID number which you find Employee. : ");
        String eid = scanner.nextLine();

        Employee e = manager.findEmployee(eid);

        if(e == null){
            System.out.println("There is no ID Number which you want.");
            return;
        }

        System.out.println("is " + e.getName() + "resign?(Y/N)");
        String answer = scanner.nextLine();

        if(answer.equalsIgnoreCase("y")){
            manager.deleteEmployee(eid);
        }
        else{
            System.out.println("It is Cancled.");
        }
    }

    @Override
    public void findEmployee() {
        System.out.println(" Input Employee ID number which you find Employee. : ");
        String eid = scanner.nextLine();

        Employee e = manager.findEmployee(eid);
        System.out.println(e);
    }

    @Override
    public void printMainMenu() {
        System.out.println("======================================");
        System.out.println("===[KITA Employee Managment System]===");
        System.out.println("======================================");
        System.out.println("1. Regist New Employee");
        System.out.println("2. Find Employee");
        System.out.println("3. Resign Employee");
        System.out.println("4. Show all Employee");
        System.out.println("9. Exit");
        System.out.println("======================================");
    }
}
