/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.wsy.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.wsy.struts.bean.ForumBean;
import com.wsy.struts.bean.TopicListBean;
import com.wsy.struts.form.ForumDelForm;

/** 
 * MyEclipse Struts
 * Creation date: 11-15-2007
 * 
 * XDoclet definition:
 * @struts.action path="/forumDel" name="forumDelForm" input="/forumndel.jsp" scope="request" validate="true"
 */
public class ForumDelAction extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ForumDelForm forumDelForm = (ForumDelForm) form;// TODO Auto-generated method stub
		ActionMessages message=new ActionMessages();
		HttpSession session=request.getSession();
		DataSource datasource = getDataSource(request,"dataSources");//取struts配置的数据源
		String id=(String)session.getAttribute("id");
		ForumBean forum=new ForumBean();
		TopicListBean topic=new TopicListBean();
		int i=forum.deleteRecord(id, datasource);
		int j=topic.DelRecordforForumn(id, datasource);//在主题表将此论坛相关的主题删除
		System.out.println("ForumDelJ"+j);
		System.out.println("ForumDel"+i);
		if(i==1){
			message.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("success.del"));
			saveErrors(request,message);
			request.setAttribute("sucess", "ok");
		}
		return mapping.getInputForward();
	}
}