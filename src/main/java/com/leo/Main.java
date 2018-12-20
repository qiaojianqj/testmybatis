package com.leo;

import com.leo.dao.RoleMapper;
import com.leo.dao.UserMapper;
import com.leo.domain.Role;
import com.leo.domain.RoleExample;
import com.leo.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.InputStream;

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
        UserMapper userMapper = session.getMapper ( UserMapper.class );
        User user = userMapper.getUser ( 4 );
        System.out.println ( "id: " + user.getId () + "; name: " + user.getName ());
        session.close ();
    }
}
