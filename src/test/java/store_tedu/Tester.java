package store_tedu;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import store.bean.User;
import store.service.IUserService;

public class Tester {

	@Test
	public void testMapperInsert() {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml", "spring-service.xml");
		IUserService userService = context.getBean("userService", IUserService.class);
		// UserMapper userMapper = context.getBean("userMapper", UserMapper.class);
		User user = new User();
		user.setUsername("刘单风");
		user.setPassword("1111");

		userService.register(user);
		context.close();
	}
}
