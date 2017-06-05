package per.piers.onlineJudge.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import per.piers.onlineJudge.util.DataAccessObject;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@ComponentScan
public class RootConfig {

    @Bean
    public DataSource dataSource() throws NamingException {
        Context context = new InitialContext();
        DataSource dataSource = (DataSource) context.lookup("java:comp/env/mybatis");
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws IOException {
        ClassLoader classLoader = RootConfig.class.getClassLoader();
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(classLoader.getResourceAsStream("config/mybatis/mybatis-config.xml"));
        return sqlSessionFactory;
    }

    @Autowired
    @Bean
    public DataAccessObject dataAccessObject(SqlSessionFactory sqlSessionFactory) {
        return new DataAccessObject(sqlSessionFactory);
    }

    @Bean
    public StandardServletMultipartResolver standardServletMultipartResolver(){
        return new StandardServletMultipartResolver();
    }

}
