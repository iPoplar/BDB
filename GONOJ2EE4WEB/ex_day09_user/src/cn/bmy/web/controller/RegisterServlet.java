package cn.bmy.web.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import cn.bmy.domain.User;
import cn.bmy.exception.UserExistException;
import cn.bmy.service.BusinessService;
import cn.bmy.service.impl.BusinessServiceImpl;
import cn.bmy.utils.WebUtils;
import cn.bmy.web.formbean.RegisterFormBean;

public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		RegisterFormBean formbean = WebUtils.request2Bean(request, RegisterFormBean.class);
		
		//表单校验
		if(formbean.validate()==false)
		{
			request.setAttribute("formbean", formbean);
			request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
			return;
		}
		
		//把表单的数据填充到javabean中
		User user = new User();
		
		//注册字符串到日期转换器
		try {
			ConvertUtils.register(new DateLocaleConverter(), Date.class);
			BeanUtils.copyProperties(user, formbean);
			user.setId(WebUtils.makeId());
			BusinessService service = new BusinessServiceImpl();
			service.registerUser(user);
			
			request.setAttribute("message", "注册成功！！！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}catch (UserExistException e) 
		{
			formbean.getErrors().put("username", "用户已存在!1");
			request.setAttribute("formbean", formbean);
			request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
			
		}
		 catch (Exception e) {
			e.printStackTrace();//在后台记录异常
			request.setAttribute("message", "对不起，注册失败！！！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
