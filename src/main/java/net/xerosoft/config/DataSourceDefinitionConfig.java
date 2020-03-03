package net.xerosoft.config;

import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
@DataSourceDefinition(
        name = "java:app/store",
        className = "org.h2.jdbcx.JdbcDataSource",
        url = "jdbc:h2:mem:store"
)
public class DataSourceDefinitionConfig {
}
