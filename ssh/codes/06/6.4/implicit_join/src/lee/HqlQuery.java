package lee;

import org.hibernate.Transaction;
import org.hibernate.Session;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.*;

import org.crazyit.app.domain.*;
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
public class HqlQuery
{
	public static void main(String[] args)
		throws Exception
	{
		HqlQuery mgr = new HqlQuery();
		// ���ò�ѯ����
		mgr.findPersons();
		// ���õڶ�����ѯ����
		mgr.findPersonsByHappenDate();
		mgr.findPersonsFetchMyEvent();
	}

	//c��һ����ѯ����
	private void findPersons()
	{
		// ���Hibernate Session
		Session sess = HibernateUtil.currentSession();
		// ��ʼ����
		Transaction tx = sess.beginTransaction();
		// ��HQL��䴴��Query����,HQL���ʹ����ʽ����
		List pl = sess.createQuery("from Person p where p.myEvent.title > :title")
			// ִ��setString()����ΪHQL���Ĳ�����ֵ
			.setString("title", " ")
			//Query����list()�������ʲ�ѯ��ȫ��ʵ��
			.list();
		// ������ѯ��ȫ�����
		for (Object ele : pl)
		{
			Person p = (Person)ele;
			System.out.println(p.getName());
		}
		// �ύ����
		tx.commit();
		HibernateUtil.closeSession();
	}
	// �ڶ�����ѯ����
	private void findPersonsByHappenDate()throws Exception
	{
		// ���Hibernate Session����
		Session sess = HibernateUtil.currentSession();
		Transaction tx = sess.beginTransaction();
		System.out.println("====��ʼͨ�����ڲ�����====");
		// ��HQL��䴴��Query����,HQL���ʹ����ʽ����
		// ��Ϊû��ʹ��fetch�ؼ��֣����Է���List����Ԫ���Ǳ���ѯʵ��
		List pl = sess.createQuery("select p from Person p left join "
			+ "p.myEvent event where event.happenDate < :endDate")
			.setDate("endDate",new Date())
			.list();
		// ���������
		for (Object ele : pl)
		{
			Person p = (Person)ele;
			System.out.println(p.getName());
			// ��Ϊִ��HQLʱû��ʹ��fetch��
			// ����ֻ����Sessionû�йر�ʱ����Person����ʵ�������
			System.out.println(p.getMyEvent().getTitle());
		}
		tx.commit();
		HibernateUtil.closeSession();
	}

	// ��������ѯ����������fetch�ؼ���
	private void findPersonsFetchMyEvent()throws Exception
	{
		// ���Hibernate Session����
		Session sess = HibernateUtil.currentSession();
		Transaction tx = sess.beginTransaction();
		System.out.println("====����fetch��ѯ====");
		// ��HQL��䴴��Query����,HQL���ʹ����ʽ����
		// ��Ϊʹ����fetch�ؼ��֣����Է��ؽ�������а�������ʵ��
		List pl = sess.createQuery("from Person p left join fetch "
			+ "p.myEvent event where event.happenDate < :endDate")
			.setDate("endDate",new Date())
			.list();
		tx.commit();
		HibernateUtil.closeSession();
		// ���������
		for (Object ele : pl)
		{
			Person p = (Person)ele;
			System.out.println(p.getName());
			// ��ΪHQL�����ʹ����fecth�ؼ���
			// ���Կ�����Session�رպ����Person����ʵ�������
			System.out.println(p.getMyEvent().getTitle());
		}
	}
}