package com.freetests4u.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.uuid.Generators;
import com.freetests4u.dao.UserDao;
import com.freetests4u.model.User;

@Component
public class UserDaoImpl implements UserDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void createUser(User user){
		// TODO Auto-generated method stub
		
		String uuid = Generators.timeBasedGenerator().generate().toString();
		user.setId(uuid);
		System.out.println("inside the createUser");
		
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		try {
		session.save(user);
		trans.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			trans.rollback();
//			throw e;
		}
	}

	@Override
	public User getUser(String email, String password) {
		// TODO Auto-generated method stub
		
		String hql = "from User where email=:email AND socialId=:socialId";
		
		
		return (User) sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("email", email)
				.setParameter("socialId", password)
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers(User user) {
		// TODO Auto-generated method stub
		
		String hql = "from users where ";
		
		boolean flag = false;
		
		if(user.getName()!=null && !user.getName().isEmpty()) {
			
			hql = hql + "name:=name ";
			flag = true;
		}
		
		if(user.getEmail()!=null && !user.getEmail().isEmpty()) {
			hql = flag==false ? hql + "email:=email " : hql + "AND email:=email ";
		}
		
		if(user.getLastloginDate()!=null) {
			hql = flag==false ? hql + "lastloginDate:=lastloginDate " : hql + "AND lastloginDate:=lastloginDate";
		}
		
		if(user.getRegistrationDate()!=null) {
			hql = flag==false ? hql + "registrationDate:=registrationDate " : hql + "AND registrationDate:=registrationDate";
		}
		
		if(user.getId()!=null) {
			hql = flag==false ? hql + "Id:=Id " : hql + "AND Id:Id ";
		}
		
		
		return sessionFactory.getCurrentSession().createQuery(hql)
				.setProperties(user)
				.list();
	}
	
	

}
