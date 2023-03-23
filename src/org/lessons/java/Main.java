package org.lessons.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String URL = "jdbc:mysql://localhost:3306/db_nations";
        String USER = "root";
        String PASSWORD = "root";

        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)){
            String query =
                    """
                    select c.name as country_name , c.country_id , r.name as region_name ,c2.name as continent_name 
                    from countries c
                    join regions r on r.region_id = c.region_id\s
                    join continents c2 on r.continent_id = r.region_id\s
                    order by c.name;
                    """;
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
