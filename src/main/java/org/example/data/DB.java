package org.example.data;

import org.sql2o.Sql2o;

public class DB {

    public static Sql2o sql20 = new Sql2o("jdbc:postgresql://localhost:5432/my_property", "moringa", "Access");
}
