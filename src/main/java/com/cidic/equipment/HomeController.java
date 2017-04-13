package com.cidic.equipment;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cidic.equipment.exception.EquipmentException;
import com.cidic.equipment.model.ResultModel;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@ExceptionHandler(EquipmentException.class)
	public @ResponseBody ResultModel handleCustomException(EquipmentException ex) {
		ResultModel resultModel = new ResultModel();
		resultModel.setResultCode(ex.getErrCode());
		resultModel.setMessage(ex.getErrMsg());
		resultModel.setSuccess(false);
		return resultModel;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		return "main";
	}
	
	@RequestMapping(value = "/qiniu/getUploadKey", method = RequestMethod.GET)
	@ResponseBody
	public ResultModel getUploadKey(HttpServletRequest request, HttpServletResponse response) {

		ResultModel resultModel = null;
		final String ACCESS_KEY = "Q-DeiayZfPqA0WDSOGSf-ekk345VrzuZa_6oBrX_";
		final String SECRET_KEY = "fIiGiRr3pFmHOmBDR2Md1hTCqpMMBcE_gvZYMzwD";
		final String bucketname = "equipment-system";
		try {
			StringMap strMap = new StringMap().putNotNull("returnBody",
					"{\"key\": $(key), \"hash\": $(etag), \"w\": $(imageInfo.width), \"h\": $(imageInfo.height)}");
			Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
			String token = auth.uploadToken(bucketname, null, 3600, strMap);
			resultModel = new ResultModel();
			resultModel.setResultCode(200);
			resultModel.setUptoken(token);
		} catch (Exception e) {
			throw new EquipmentException(500, "获取Token出错");
		}

		return resultModel;
	}
}
