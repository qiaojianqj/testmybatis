import com.leo.dao.UserMapper;
import com.leo.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class Main {
    public static void main(String... args) {
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
