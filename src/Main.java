import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Jdbc jdbc;
    public static void main(String[] args) {
        int ch = 0;
        Main main = new Main();
        student students = new student();
        jdbc = new Jdbc(); // Data Source
        Scanner input = new Scanner(System.in);
        do{
            System.out.println("-------------- Welcome to Student Management System -------------");
            System.out.println("1.DISPLAY All Students");
            System.out.println("2.INSERT New Student");
            System.out.println("3.Update Student");
            System.out.println("4.DISPLAY Student By Name");
            System.out.println("5.DISPLAY Student By ID");
            System.out.println("6.DELETE by ID");
            System.out.print("Enter your Choice: ");
            ch = input.nextInt();

            switch (ch){
                case 1 :
                    selectStudent();
                    break;
                case 2 :
                    input.nextLine();
                    System.out.println("Enter id: ");
                    students.setId(input.nextInt());

                    System.out.println("Enter name: ");
                    input.nextLine();
                    students.setName(input.nextLine());

                    System.out.println("Enter phone_number: ");
                    students.setPhone_number(input.nextInt());

                    insetStudent(students);
                    break;
                case 3 :
                    input.nextLine();
                    System.out.println("Enter id: ");
                    students.setId(input.nextInt());

                    System.out.println("Enter name: ");
                    input.nextLine();
                    students.setName(input.nextLine());

                    update(students);
                    break;
                case 4 :
                    input.nextLine();
                    System.out.println("Enter name: ");
                    students.setName(input.nextLine());

                    selectByName(students);
                    break;
                case 5 :
                    input.nextLine();
                    System.out.println("Enter ID: ");
                    input.nextInt();
                    selectByID(students);

                    break;

                    case 6 :
                        input.nextLine();
                        System.out.println("Enter DeleteById: ");
                        students.setId(input.nextInt());
                        DeleteByID(students);
                    break;





            }
        } while (ch !=0);


    }
//    Insert recond
    private static void insetStudent(student students){
        try(Connection connection = jdbc.dataSource().getConnection()) {
            String insertSql = "INSERT INTO students (id,name,phone)" + "VALUES(?,?,?)";
            PreparedStatement statement = connection.prepareStatement(insertSql);
            statement.setInt(1,students.getId());
            statement.setString(2,students.getName());
            statement.setInt(3, students.getPhone_number());

            statement.executeUpdate();
            System.out.println("Insert Successfully ");

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //Create update operation
    private  static void update(student students){
        try (Connection connection = jdbc.dataSource().getConnection()) {
            String update = "UPDATE students SET name = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(update);
            statement.setString(1,students.getName());
            statement.setInt(2,students.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing student was updated successfully!");
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Create delete by ID operation;
    private  static  void  DeleteByID(student students){
        try {
            Connection connection = jdbc.dataSource().getConnection();
            // Create Sql Statement Delete by ID
            String deleteSqlByID = "DELETE From students WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(deleteSqlByID);
            statement.setInt(1,students.getId());
            statement.executeQuery();
            System.out.println("Delete Successfully ");
        } catch (Exception e){
            e.printStackTrace();
        }


    }
    // Create select by ID operation;
    private static void selectByID(student students){
        try {
            Connection connection = jdbc.dataSource().getConnection();
            // Create Sql Statement Select by ID
            String selectSqlByID = "SELECT * FROM students WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(selectSqlByID);
            statement.setInt(1,students.getId());
            ResultSet resultSet = statement.executeQuery();


            List<student> Students = new ArrayList<>();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int phone_number = resultSet.getInt("phone");
                Students.add(new student(id,name,phone_number));

            }
            System.out.println(Students);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
    // Create select by Name operation;
    private  static void selectByName(student students){
        try {
            Connection connection = jdbc.dataSource().getConnection();
            String selectSqlByName= "SELECT * FROM students WHERE name = ? ";
            PreparedStatement statement = connection.prepareStatement(selectSqlByName);
            statement.setString(1,students.getName());
            ResultSet resultSet = statement.executeQuery();

            List<student> Students = new ArrayList<>();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int phone_number = resultSet.getInt("phone");
                Students.add(new student(id,name,phone_number));

            }
            System.out.println(Students);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
    // display the student
    private static void selectStudent(){
        try {
            Connection connection = jdbc.dataSource().getConnection();
            //1. Create Sql Statement object
            String selectSql = "SELECT *  From students";
            PreparedStatement statement = connection.prepareStatement(selectSql);
            ResultSet resultSet = statement.executeQuery();

            List<student> students = new ArrayList<>();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int phone_number = resultSet.getInt("phone");
                students.add(new student(id,name,phone_number));

            }
            System.out.println(students);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}



// Define modal


// DB Operations:
// 1. Query / Report / Statistic (select)
// 2. Transaction(Insert, Delete, (Update) Affected Rows
// Class.forName("org.postgresql.Driver")


// Connection by old style
//        String url = "jdbc:postgresql://localhost:5432/Data3";
//        String names = "postgres";
//        String password="12345";


//        try {
////            Connection connection = DriverManager.getConnection(url,names ,password);
//            }
//            //1. Create Sql Statement object
//            String selectSql = "SELECT *  From students";
//            PreparedStatement statement = connection.prepareStatement(selectSql);
//
//            ResultSet resultSet = statement.executeQuery();
//
//            List<student> students = new ArrayList<>();
//            while (resultSet.next()){
//                int id = resultSet.getInt("id");
//                String name = resultSet.getString("name");
//                int phone_number = resultSet.getInt("phone");
//                students.add(new student(id,name,phone_number));
//
//            }
//            System.out.println(students);
//        } catch (SQLException e) {
//            e.printStackTrace();
////            throw new RuntimeException(e);
//        }
