package com.skillIndia.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.skillIndia.model.Candidate;
import com.skillIndia.model.Course;
import com.skillIndia.model.Establishment;

@Repository
public class AdminDaoImpl implements AdminDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<Course>  listAllCourse() {
		Session session = this.sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		String query="from Course";
		Query q=session.createQuery(query);
		List<Course> courseList=q.list();
		tx.commit();
		session.close();
		return courseList;
	}

	
	@Override
	public void acceptCandidate(int UserId) {
		Session session = this.sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		Candidate candidate = 
                (Candidate)session.get(Candidate.class, UserId); 
    candidate.setRequestStatus("ACCEPTED");
     session.update(candidate); 
     tx.commit();
		session.close();
		
	}

	@Override
	public void rejectCandidate(int UserId) {
		Session session = this.sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		Candidate candidate = 
                (Candidate)session.get(Candidate.class, UserId); 
    candidate.setRequestStatus("REJECTED");
     session.update(candidate); 
     tx.commit();
		session.close();
		
	}

	@Override
	public void acceptEstablishment(int estId) {
		
		Session session = this.sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		Establishment establishment = 
                (Establishment)session.get(Establishment.class, estId); 
		establishment.setAppStatus("ACCEPTED");
     session.update(establishment); 
     tx.commit();
		session.close();
	}

	@Override
	public void rejectEstablishment(int estId) {
		Session session = this.sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		Establishment establishment = 
                (Establishment)session.get(Establishment.class, estId); 
		establishment.setAppStatus("REJECTED");
     session.update(establishment); 
     tx.commit();
		session.close();
		
	}

	public void verifyContract(int estId) {
		
		
	}

	@Override
	public List<Candidate> listAllCandidate() {	
		Session session = this.sessionFactory.openSession();
		Transaction tx=session.beginTransaction();	
		String query="from Candidate where requestStatus LIKE 'PENDING'"	;
		Query q=session.createQuery(query);
		List<Candidate> candidateList=q.list();
		tx.commit();
		session.close();
		return candidateList;
	}

	@Override
	public List<Establishment> listAllEstablishment() {
		Session session = this.sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		System.out.println("1");
		String query="from Establishment where appStatus LIKE 'PENDING'";
		System.out.println("1");
		Query q=session.createQuery(query);
		System.out.println("1");
		List<Establishment> estList=q.list();
		System.out.println("1");
		tx.commit();
		System.out.println("1");
		session.close();
		return estList;
	}

}
