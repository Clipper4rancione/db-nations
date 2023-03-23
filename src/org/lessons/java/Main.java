package org.lessons.java;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String URL = "jdbc:mysql://localhost:3306/db_nations";
        String USER = "root";
        String PASSWORD = "root";

        Scanner scan = new Scanner(System.in);
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)){
            String query =
                    """
                    SELECT c.name AS country_name , c.country_id , r.name AS region_name ,c2.name AS continent_name
                    FROM countries c
                    JOIN regions r ON r.region_id = c.region_id\s
                    JOIN continents c2 ON r.continent_id = r.region_id\s
                    WHERE c.name LIKE ?
                    ORDER BY c.name;
                    """;



            try(PreparedStatement ps = connection.prepareStatement(query)){

                System.out.print("Che paese vuoi cercare? ");
                String search = scan.nextLine();
                ps.setString(1, "%" + search + "%");

                try(ResultSet rs = ps.executeQuery()){

                    System.out.println("PAESE" + "  " + "ID" + "  "  + "REGIONE" + "  " + "CONTINENTE");

                    while (rs.next()){





                        String countryName = rs.getString(1);
                        int countryId = rs.getInt(2);
                        String regionName = rs.getString(3);
                        String continentName = rs.getString(4);



                        System.out.println(countryName + "  " + countryId + "  " + regionName + "  " + continentName);


                    }
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
