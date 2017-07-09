package lee;

import org.springframework.context.*;
import org.springframework.context.support.*;
import org.springframework.context.annotation.*;

import org.crazyit.app.service.*;
import org.crazyit.app.config.*;
/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a>
 * <br/>Copyright (C), 2001-2016, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class BeanTest
{
	public static void main(String[] args)throws Exception
	{
		// ����Spring����
		ApplicationContext ctx = new
			AnnotationConfigApplicationContext(AppConfig.class);
//		ApplicationContext ctx = new
//			ClassPathXmlApplicationContext("beans.xml");
		// ��ȡchinese ʵ��
		Person p = ctx.getBean("chinese" , Person.class);
		// ����useAxe()����
		p.useAxe();
	}
}