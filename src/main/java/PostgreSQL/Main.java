package PostgreSQL;

import java.sql.*;

public class Main {
    static Connection connection = null;
    static Statement statement = null;
    static ResultSet resultSet = null;

    public static void main(String[] args) {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/java_students",
                    "postgres", "root");

            statement = connection.createStatement();

            System.out.println("Query 1 :");
            QueryOne(statement);

            System.out.println("\nQuery 2 :");
            QueryTwo(statement);

            System.out.println("\nQuery 3 :");
            QueryThree(statement);

            System.out.println("\nQuery 4 :");
            QueryFour(statement);
        }
        catch (SQLException e) {
            System.out.println("Connection to java_students database failed..");
            throw new RuntimeException(e);
        }
    }

    /**
     * Вывести список студентов, сдавших определенный предмет, на оценку выше 3
     * @param statement the object used for executing a static SQL statement and returning the results it produces
     * @throws SQLException an exception that provides information on a database access error or other errors
     */
    public static void QueryOne(Statement statement) throws SQLException {
        resultSet = statement.executeQuery("SELECT stud.name as studName, subj.name as subjName, p.grade " +
                "FROM students AS stud JOIN progress AS p ON stud.id = p.student_id JOIN subjects AS subj " +
                "ON p.subject_id = subj.id WHERE p.grade >= 3 AND p.subject_id = 2;"
        );

        while (resultSet.next()) {
            String studName = resultSet.getString("studName");
            String subjName = resultSet.getString("subjName");
            int grade = resultSet.getInt("grade");

            System.out.printf("%s %s %d\n", studName, subjName, grade);
        }
    }

    /**
     * Посчитать средний бал по определенному предмету - Управление данными (чтобы поменять сменить subject_id = 2 на
     * любой другой)
     * @param statement the object used for executing a static SQL statement and returning the results it produces
     * @throws SQLException an exception that provides information on a database access error or other errors
     */
    public static void QueryTwo(Statement statement) throws SQLException{
        resultSet = statement.executeQuery("SELECT subjects.name, AVG(progress.grade) as avg FROM progress " +
                "JOIN subjects ON progress.subject_id = subjects.id WHERE subject_id = 2 GROUP BY subjects.name;"
        );

        while (resultSet.next()) {
            String subjName = resultSet.getString("name");
            float avgGrade = resultSet.getFloat("avg");

            System.out.printf("%s %f\n", subjName, avgGrade);
        }
    }

    /**
     * Посчитать средний балл по определенному студенту - Артем (чтобы поменять сменить student_id = 1 на
     * любой другой)
     * @param statement the object used for executing a static SQL statement and returning the results it produces
     * @throws SQLException an exception that provides information on a database access error or other errors
     */
    public static void QueryThree(Statement statement) throws SQLException {
        resultSet = statement.executeQuery("SELECT students.name, AVG(progress.grade) as avg FROM progress JOIN subjects " +
                "ON progress.subject_id = subjects.id JOIN students ON students.id = progress.student_id WHERE " +
                "student_id = 1 GROUP BY students.name;"
        );

        while (resultSet.next()) {
            String studName = resultSet.getString("name");
            float avgGrade = resultSet.getFloat("avg");

            System.out.printf("%s %f\n", studName, avgGrade);
        }
    }

    /**
     * Найти три предмета, которые сдали наибольшее количество студентов
     * @param statement the object used for executing a static SQL statement and returning the results it produces
     * @throws SQLException an exception that provides information on a database access error or other errors
     */
    public static void QueryFour(Statement statement) throws SQLException {
        resultSet = statement.executeQuery("SELECT subj.name FROM subjects AS subj JOIN progress " +
                "ON subj.id = progress.subject_id WHERE (SELECT AVG(grade)\n" +
                "FROM progress JOIN subjects ON subj.id = progress.subject_id) >= 3 GROUP BY subj.name;"
        );

        while (resultSet.next()) {
            String subjName = resultSet.getString("name");

            System.out.printf("%s\n", subjName);
        }
    }

}
