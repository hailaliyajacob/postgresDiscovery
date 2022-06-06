package com.example.postgres;


import com.google.api.client.util.Value;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;

public class TcpConnectionPoolFactory extends ConnectionPoolFactory {

    // Note: Saving credentials in environment variables is convenient, but not
    // secure - consider a more secure solution such as
    // Cloud Secret Manager (https://cloud.google.com/secret-manager) to help
    // keep secrets safe.
    //@Value("${DB_USER}")
    private static String DB_USER="postgres";

   // @Value("${DB_PORT}")
    private static String DB_PORT="5432";

   // @Value("${DB_PASS}")
    private static String DB_PASS= "postgres";

    @Value("${DB_NAME}")
    private static String DB_NAME="test";

    //@Value("${INSTANCE_HOST}")
    private static String INSTANCE_HOST="10.48.128.2";


 /*private static final String DB_USER = System.getenv("DB_USER");
    private static final String DB_PASS = System.getenv("DB_PASS");
    private static final String DB_NAME = System.getenv("DB_NAME");

    private static final String INSTANCE_HOST = System.getenv("INSTANCE_HOST");
    private static final String DB_PORT = System.getenv("DB_PORT");*/



    public static DataSource createConnectionPool() {
        // The configuration object specifies behaviors for the connection pool.
        HikariConfig config = new HikariConfig();

        // The following URL is equivalent to setting the config options below:
        // jdbc:postgresql://<INSTANCE_HOST>:<DB_PORT>/<DB_NAME>?user=<DB_USER>&password=<DB_PASS>
        // See the link below for more info on building a JDBC URL for the Cloud SQL JDBC Socket Factory
        // https://github.com/GoogleCloudPlatform/cloud-sql-jdbc-socket-factory#creating-the-jdbc-url

        // Configure which instance and what database user to connect with.
        config.setJdbcUrl(String.format("jdbc:postgresql://%s:%s/%s", INSTANCE_HOST, DB_PORT, DB_NAME));
        config.setUsername(DB_USER); // e.g. "root", "postgres"
        config.setPassword(DB_PASS); // e.g. "my-password"


        // ... Specify additional connection properties here.
        // ...

        // Initialize the connection pool using the configuration object.
        return new HikariDataSource(config);
    }
}
