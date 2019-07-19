import com.leo.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserMapperTest.class)
public class UserMapperTest {
    private Logger logger = LoggerFactory.getLogger ( UserMapperTest.class );
    private static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public static void init() {
        try {
           Reader reader = Resources.getResourceAsReader ( "mybatis_config.xml" );
           sqlSessionFactory = new SqlSessionFactoryBuilder ().build ( reader );
           reader.close ();
        } catch (IOException ignore) {
            ignore.printStackTrace ();
        }
    }

    @Test
    public void testGetUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession ();
        try {
           List<User> users = sqlSession.selectList ( "getUser" );
           logger.info ( "{}", users );
        } finally {
            sqlSession.close ();
        }
    }
}