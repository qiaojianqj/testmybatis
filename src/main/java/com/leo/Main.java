package com.leo;

import com.leo.domain.User;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.sql.DriverManager;
import java.util.Enumeration;
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

    @PostConstruct
    public void getSQLDrivers() {
        Enumeration drivers = DriverManager.getDrivers ();
        System.out.println("------Start: 加载的diver--------");
        while(drivers.hasMoreElements()) {
            System.out.println(drivers.nextElement().getClass().getName());
        }
        System.out.println("------End: 加载的diver--------");
    }

    /**
     * for ssl
     */

    //@Bean
    //public ServletWebServerFactory servletContainer() {
    //    TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
    //        @Override
    //        protected void postProcessContext(Context context) {
    //            SecurityConstraint securityConstraint = new SecurityConstraint();
    //            securityConstraint.setUserConstraint("CONFIDENTIAL");
    //            SecurityCollection collection = new SecurityCollection();
    //            collection.addPattern("/*");
    //            securityConstraint.addCollection(collection);
    //            context.addConstraint(securityConstraint);
    //        }
    //    };
    //    tomcat.addAdditionalTomcatConnectors(redirectConnector());
    //    return tomcat;
    //}

    //private Connector redirectConnector() {
    //    Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
    //    connector.setScheme("http");
    //    connector.setPort(8080);
    //    connector.setSecure(false);
    //    connector.setRedirectPort(1234);
    //    return connector;
    //}
}
