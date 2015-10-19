package com.mec.blogSystem.result;

import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.User;
import org.apache.struts2.ServletActionContext;
import org.codehaus.jackson.JsonParser;
import org.eclipse.jdt.internal.compiler.lookup.MethodScope;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import com.mec.blogSystem.model.UserInfo;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;

public class JsonTypeResult implements Result {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2215211534457237954L;	
	
	public void execute(ActionInvocation arg0) throws Exception {
		if(arg0.getAction() != null){
			HttpServletResponse response = ServletActionContext.getResponse();
			UserInfo user = new UserInfo();
			user.setUserName("wangxh");
			user.setPassword("111111");
			JSONObject jsonObject = JSONObject.fromObject(user);
			PrintWriter out = response.getWriter();
			
			Object action = arg0.getAction();
			
			Class actionCls = action.getClass();
			
			Method[] methods = actionCls.getDeclaredMethods();
			
			for(int i = 0; i < methods.length; i++){
				Method method = methods[i];
				String methodName = method.getName();
				if(methodName.indexOf("get") == 0){
					String properName = methodName.substring(3,4).toLowerCase() + methodName.substring(4);
					String value = method.invoke(action) + "";
					out.println(properName + ": " + value + ",");
				}
			}
		}
	}
}
