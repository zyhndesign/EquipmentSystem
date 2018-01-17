package com.cidic.equipment;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cidic.equipment.model.Role;
import com.cidic.equipment.model.User;
import com.cidic.equipment.model.UserRole;
import com.cidic.equipment.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class UserTest {

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userServiceImpl;
	
	@Test
	public void createUser(){
		Role role = new Role();
		User user = new User();
		user.setEmail("cidic@cidic.cn");
		user.setPassword("cidic001");
		user.setRealname("中意创新中心");
		user.setCreatetime(new Date());
		
		role.setId(1);
		Set<UserRole> userRoles = new HashSet<UserRole>();
		UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		userRoles.add(userRole);
		
		user.setUserRoles(userRoles);
		
		userServiceImpl.createUser(user);
	}
}
