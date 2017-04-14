package com.cidic.equipment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cidic.equipment.service.VehicleInfoService;
import com.cidic.equipment.service.impl.VehicleInfoServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class VehicleTest {

	@Autowired
	@Qualifier(value = "vehicleInfoServiceImpl")
	private VehicleInfoService vehicleInfoServiceImpl;
	
	@Test
	public void getDataByCondition(){
		vehicleInfoServiceImpl.getVehicleInfoByPage(0, 10);
	}
}
