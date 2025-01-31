package cn.bmy;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletDemo1 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	//location头的应用:Location：服务器通过这个头，来告诉浏览器跳到哪里
	public void test1(HttpServletRequest request, HttpServletResponse response)
	{
		response.setStatus(302);//跳转至指定页面
		response.setHeader("location", "/ex_day04/1.html");
	}

	//数据压缩
	public void test2(HttpServletRequest requset, HttpServletResponse response) throws IOException
	{
		String data = "abcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcd" +
				"abcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabc" +
				"dabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcd" +
				"abcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcda" +
				"bcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabc" +
				"dabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdab" +
				"cdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdab" +
				"cdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdab" +
				"cdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdab" +
				"cdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabc" +
				"dabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcd" +
				"abcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdab" +
				"cdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcd" +
				"abcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdab" +
				"cdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdab" +
				"cdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdab" +
				"cdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdab" +
				"cdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdab" +
				"cdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcda" +
				"bcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcd" +
				"abcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdab" +
				"cdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcda" +
				"bcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcd" +
				"abcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcd" +
				"abcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabc" +
				"dabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabc" +
				"dabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabc" +
				"dabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcd" +
				"abcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcda" +
				"bcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdab" +
				"cdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabc" +
				"dabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcd" +
				"abcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcd" +
				"abcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabc" +
				"dabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdab" +
				"cdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdab" +
				"cdabcdabcdabcdabcdabcd";
		
		System.out.println("原始数据的大小为："+data.getBytes().length);
		
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		//GZIPOutputStream通过write()方法将数据压缩，因为不一定写满，所以不一定写到底层流中
		//需要close()方法，拿到缓存到底层的数据
		//GZIPOutputStream是自带缓冲的buffer
		GZIPOutputStream gout = new GZIPOutputStream(bout);//buffer
		gout.write(data.getBytes());
		gout.close();
		
		//得到压缩后的数据
		byte g[] = bout.toByteArray();
		//设置响应头，告诉浏览器压缩格式和数据长度，防止压缩后的乱码
		response.setHeader("Content-Encoding", "gzip");
		response.setHeader("Content-Length", g.length+"");
		
		response.getOutputStream().write(g);

	}
	
	//指定回送数据类型
	public void test3(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		//设置服务器向浏览器回送数据的类型，可以到配置文件中查看
		//c:\apache-tomcat-6.0.20\conf\web.xml		
		response.setHeader("content-type", "image/jpeg");
		
		InputStream in = this.getServletContext().getResourceAsStream("/1.jpg");
		byte buffer[] = new byte[1024];
		int len = 0;
		OutputStream out = response.getOutputStream();
		while((len=in.read(buffer))>0)
		{
			out.write(buffer, 0, len);
		}
	}
	
	//指定浏览器定时刷新
	public void test4(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		response.setHeader("refresh", "3;url='http://www.sina.com'");//url是进行跳转
		response.getWriter().write("abce");
	}
	
	//指定浏览器下载
	public void test5(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		//设置响应头，告诉浏览器一下载方式处理回送的数据
		response.setHeader("content-disposition", "attachment;filename=xxx.jpg");
		//读取回送的数据		
		InputStream in = this.getServletContext().getResourceAsStream("/2.jpg");
		byte buffer[] = new byte[1024];
		int len = 0;
		OutputStream out = response.getOutputStream();
		while((len = in.read(buffer))>0)
		{
			out.write(buffer, 0, len);
		}
	}
		
	
}
