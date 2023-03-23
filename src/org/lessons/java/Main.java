package org.lessons.java;

import java.sql.*;

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
            try(PreparedStatement ps = connection.prepareStatement(query)){
                try(ResultSet rs = ps.executeQuery()){
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
