/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.wsy.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wsy.struts.bean.TopicListBean;
import com.wsy.struts.form.ForumForm;
import com.wsy.struts.util.StringTrans;
import com.wsy.struts.util.pageBean;

/** 
 * MyEclipse Struts
 * Creation date: 11-06-2007
 * 
 * XDoclet definition:
 * @struts.action path="/topicList" name="topicListForm" input="/topicList.jsp" scope="request" validate="true"
 */
public class TopicListAction extends Action {
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
		StringTrans s=new StringTrans();
		ForumForm ForumForm = (ForumForm) form;// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		String forumId=ForumForm.getForumid();
		String forumName=ForumForm.getForumname();
		String iflogin=request.getParameter("iflogin");
		if(iflogin!=null&&!iflogin.equals("1")){
			return mapping.findForward("error");
		}
		int pageid=ForumForm.getPageid();
		String name=(String)session.getAttribute("name");
		session.setAttribute("name", name);
		session.setAttribute("forumName", forumName);
		session.setAttribute("forumId", forumId);
		session.setAttribute("pageid", pageid+"");
		if(pageid<0){
			pageid=0;
		}
		DataSource datasource = getDataSource(request,"dataSources");//取struts配置的数据源
		TopicListBean t=new TopicListBean();
		try {
			List l = t.search(datasource, pageid, forumId);//查询该论坛的主题
			pageBean p=new pageBean();
			p.getAvailableCount(datasource, forumId);
			p.countMaxPage();
			int maxpage=p.maxPage;
			session.setAttribute("maxpage", maxpage+"");
			session.setAttribute("List", l);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.getInputForward();
	}
}