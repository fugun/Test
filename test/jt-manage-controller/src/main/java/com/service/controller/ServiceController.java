package com.service.controller;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.service.MenuService;

@Controller
@RequestMapping(value = "/service")
public class ServiceController  extends HttpServlet {
	private static final Logger log = Logger.getLogger(MenuService.class);  
    private static final long serialVersionUID = 1L; 
   
    /**
         * GET:验证token
         * OST:发送数据
         * @param request 作用域
         * @param response 作用域
         * @return  String
         */
    @RequestMapping(value= {"/getProcessRequest"},method= {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
     public String getProcessRequest(HttpServletRequest request,HttpServletResponse response) {
    	
    	
		return null;
	     
    }
}
