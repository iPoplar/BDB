<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>      
    <title>My JSP 'left.jsp' starting page</title>  	
  </head>
  
  <body>
 
   <a href="${pageContext.request.contextPath }/servlet/ListUserServlet" target="body">用户管理</a><br/><br/>
    <a href="${pageContext.request.contextPath }/servlet/ListRoleServlet" target="body">角色管理</a><br/><br/>
    <a href="${pageContext.request.contextPath }/servlet/ListPrivilegeServlet" target="body">权限管理</a><br/><br/>
    
  </body>
</html>
