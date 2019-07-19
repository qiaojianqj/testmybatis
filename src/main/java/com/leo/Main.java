package com.leo;

import com.leo.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
//Auto Gen Mapper Dependency Inject
@MapperScan("com.leo.dao")
public class Main {
    public static void main(String... args) {
        SpringApplication application = new SpringApplication(Main.class);
        application.run(args);
    }

    @PostConstruct
    public void notAutoGen_UserMapperUser() {
        String resource = "mybatis_config.xml";
        InputStream is = Main.class.getClassLoader ().getResourceAsStream ( resource );
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder ().build(is);
        SqlSession session = sessionFactory.openSession ();
        List<User> users = session.selectList ( "getUser" );
        System.out.println ( users.get ( 0 ).getId () + ";" + users.get ( 0 ).getName () );
        session.close ();
    }
}
