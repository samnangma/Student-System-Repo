import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

public class Jdbc {
    public DataSource dataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUser("postgres");
        dataSource.setPassword("12345");
        dataSource.setDatabaseName("Data");

        return dataSource;

    }
}
