package mybatis;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisDemo {

	static void init(){
		InputStream in=null;
		SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
		SqlSessionFactory factory=builder.build(in);
		SqlSession session=factory.openSession();
	}
}
