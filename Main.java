/**
 * BabyNames- run queries from babynames database
 * CMSY167 Spring 2023
 * @author Samuel Dolle
 * @version 1.0
 *
 */
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
public class Main {
    public static void main(String[] args) {
        final String DATABASE_URL = "jdbc:derby:D:/Downloads/babynames"; //establish filepath


        try (
           Connection connection = DriverManager.getConnection( //connect to database
              DATABASE_URL, "user", "password"))
             {
                 Statement s = connection.createStatement();
                 String sql1 //establish sql commands to run
                         = "SELECT name FROM babynames WHERE us_state = 'MD' AND gender = 'M' AND date_year = 1991 ORDER BY num_babies DESC FETCH FIRST 1 ROWS ONLY";
                 String sql2
                         = "SELECT date_year FROM babynames WHERE name = 'Christopher' AND gender = 'M' ORDER BY num_babies DESC FETCH FIRST 1 ROWS ONLY";
                 String sql3
                         = "SELECT date_year FROM babynames WHERE name = 'Rosemary' AND gender = 'F' ORDER BY num_babies DESC FETCH FIRST 1 ROWS ONLY";
                 String sql4
                         = "SELECT name FROM babynames WHERE us_state = 'MD' AND date_year = 2000 AND num_babies > 500 ORDER BY num_babies DESC";
                 String sql5
                         = "SELECT us_state FROM babynames WHERE name = 'Xavier' AND date_year = 2014 AND gender = 'M' ORDER BY num_babies ASC FETCH FIRST 1 ROWS ONLY";
                 String sql6
                         = "SELECT us_state FROM babynames WHERE name = 'Hannah' AND date_year = 1997 AND gender = 'F' ORDER BY num_babies DESC FETCH FIRST 1 ROWS ONLY";
                 String sql7
                         = "INSERT INTO babynames (id, name, date_year, gender, us_state, num_babies) VALUES (10000000, 'Joseph', 2016, 'M', 'PA', 476)";
                 String sql8
                         = "DELETE FROM babynames WHERE id = 10000000";

                 System.out.println("What was the most common boy’s name in Maryland in 1991?");
                 ResultSet rs = s.executeQuery(sql1); //execute query related to question
                 ResultSetMetaData metaData = rs.getMetaData();
                 int numberOfColumns = metaData.getColumnCount();
                    while (rs.next()) { //iterate through table columns to pull appropriate values
                         for (int v = 1; v <= numberOfColumns; v++) {
                             System.out.printf("%-8s\t", rs.getObject(v));
                         }
                         System.out.println();
                     }
                 System.out.println();

                 System.out.println("In which year were the most baby boy’s named “Christopher” born in any state?"); //repeat process for all new queries
                 rs = s.executeQuery(sql2);
                 metaData = rs.getMetaData();
                 numberOfColumns = metaData.getColumnCount();
                 while (rs.next()) {
                     for (int v = 1; v <= numberOfColumns; v++) {
                         System.out.printf("%-8s\t", rs.getObject(v));
                     }
                     System.out.println();
                 }
                 System.out.println();

                 System.out.println("In which year were the most baby girls named “Rosemary” born in any state?");
                 rs = s.executeQuery(sql3);
                 metaData = rs.getMetaData();
                 numberOfColumns = metaData.getColumnCount();
                 while (rs.next()) {
                     for (int v = 1; v <= numberOfColumns; v++) {
                         System.out.printf("%-8s\t", rs.getObject(v));
                     }
                     System.out.println();
                 }
                 System.out.println();

                 System.out.println("In 2000, which baby names were used more than than 500 times in Maryland?");
                 rs = s.executeQuery(sql4);
                 metaData = rs.getMetaData();
                 numberOfColumns = metaData.getColumnCount();
                 while (rs.next()) {
                     for (int v = 1; v <= numberOfColumns; v++) {
                         System.out.printf("%-8s\t", rs.getObject(v));
                     }
                     System.out.println();
                 }
                 System.out.println();

                 System.out.println("In 2014, which state had the fewest boys named “Xavier”?");
                 rs = s.executeQuery(sql5);
                 metaData = rs.getMetaData();
                 numberOfColumns = metaData.getColumnCount();
                 while (rs.next()) {
                     for (int v = 1; v <= numberOfColumns; v++) {
                         System.out.printf("%-8s\t", rs.getObject(v));
                     }
                     System.out.println();
                 }
                 System.out.println();

                 System.out.println("In 1997, which state had the most girls named “Hannah?");
                 rs = s.executeQuery(sql6);
                 metaData = rs.getMetaData();
                 numberOfColumns = metaData.getColumnCount();
                 while (rs.next()) {
                     for (int v = 1; v <= numberOfColumns; v++) {
                         System.out.printf("%-8s\t", rs.getObject(v));
                     }
                     System.out.println();
                 }
                 System.out.println();

                 s.addBatch(sql7); //simultaneously run add and remove commands
                 s.addBatch(sql8);
                 s.executeBatch();

             }
         catch (SQLException sqlException) {
            sqlException.printStackTrace();
         }

    }
}