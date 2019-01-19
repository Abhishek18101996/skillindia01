package com.skillIndia.dao;



import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.skillIndia.model.Candidate;
import com.skillIndia.model.Course;
import com.skillIndia.model.Establishment;

@Repository
@Transactional
public class CandidateDaoImpl implements CandidateDao{

	static Transaction tcx;
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
	this.sessionFactory = sf;
    }
    
    private static final Logger logger = LoggerFactory.getLogger(CandidateDaoImpl.class);
    
	@Override
	public void addCandidate(Candidate candidate) {
		Session session = this.sessionFactory.openSession();
		tcx = session.beginTransaction();
		logger.info("Candidate details added successfully "+candidate);
		session.save(candidate);
		tcx.commit();
		session.close();
	}

	@Override
	public boolean verifyCandidate(String EmailId, String newPassword) {
		Session session = this.sessionFactory.openSession();
		String query = "from Candidate where EmailId=:EmailId and newPassword=:newPassword";
		System.out.println("query");
		org.hibernate.Query q = session.createQuery(query);
		q.setString("EmailId", EmailId);
		q.setString("newPassword", newPassword);
		List<Candidate> candidateList = q.list();
		if(candidateList.size()==0) {
		return false;
		}
		session.close();
		return true;
	}

	@Override
	public Candidate returnCandidate(Candidate candidate) {
		
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String EmailId = candidate.getEmailId();
		String NewPassword = candidate.getNewPassword();
		String query = "from Candidate c where c.EmailId=:EmailId and c.newPassword=:NewPassword";
		org.hibernate.Query q = session.createQuery(query);
		q.setString("EmailId", EmailId);
		q.setString("NewPassword", NewPassword);
		List<Candidate> candidatelist = q.list();
		Iterator<Candidate> canitr = candidatelist.iterator();
		Candidate c = new Candidate();
		while (canitr.hasNext()) {
			c = (Candidate) canitr.next();
		}
		return c;
	}

	

	@Override
	public boolean loginCandidate(Candidate candidate) {
		System.out.println("1");
		Session session = this.sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		System.out.println("2");
		String EmailId=candidate.getEmailId();
		String NewPassword=candidate.getNewPassword();
		String query="from Candidate c where c.EmailId=:EmailId and c.newPassword=:NewPassword";
		org.hibernate.Query q = session.createQuery(query);
		System.out.println("3");
		q.setString("EmailId", EmailId);
		q.setString("NewPassword",NewPassword);
		System.out.println("3");
		List<Candidate> candidateList=q.list();
		System.out.println("4");
		tx.commit();
		session.close();
		if(candidateList.size()==0)
		return false;
		else
			return true;
	}

	@Override
	public List<Course> browseCourse() {
		Session session = this.sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		String query="from Course";
		org.hibernate.Query q = session.createQuery(query);
		
		//q.setString("courseName", courseName);
		List<Course> course=q.list();
		tx.commit();
		session.close();
		return course;
	}

	
	
	@Override
	public List<Course> listCourse(int UserId) {
		Session session = this.sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		String query="select e.ESTNAME,e.DOMAIN,c.";
		org.hibernate.Query q = session.createQuery(query);
		q.setInteger("candidateId", UserId);
		List<Course> courseList=q.list();
		tx.commit();
		session.close();
		return courseList;
	}

	@Override
	public void applyCourse(Course course,int candidateId) {
		System.out.println("1");
		Session session = this.sessionFactory.openSession();
		System.out.println("2");
		Transaction tx = session.beginTransaction();
		System.out.println("3");
		Candidate candidate = (Candidate)session.get(Candidate.class, candidateId);
		System.out.println("4");
		candidate.setCourse(course);
		System.out.println("5");
		session.saveOrUpdate(candidate);
		System.out.println("6");
		tx.commit();
		System.out.println("7");
		session.close();
	}
	
	@Override
	public boolean checkConstraints(Candidate candidate) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		System.out.println("1");
		/*org.hibernate.SQLQuery query = session.createSQLQuery("select * from Candidate c where c.EmailId=:EmailId"
				+ "and c.phoneNumber=:phoneNumber and c.aadhaarNumber=:aadhaarNumber");
		*/System.out.println("1");
		/*query.setString("EmailId", candidate.getEmailId());
		query.setString("phoneNumbaer", candidate.getPhoneNumber());
		query.setString("aadhaarNumber", candidate.getAadhaarNumber());
	
	*/ 
		Criteria crt=session.createCriteria(Candidate.class);
		crt.add(Restrictions.like("emailId", candidate.getEmailId()));
		crt.add(Restrictions.like("phoneNumber", candidate.getPhoneNumber()));
		crt.add(Restrictions.like("aadhaarNumber", candidate.getAadhaarNumber()));

		
		
		System.out.println("1");
		List<Candidate> canList = crt.list();
       System.out.println(canList.size());
		tx.commit();
		System.out.println("1");
		session.close();
		if(canList.size()>0) {
			System.out.println("true");
			return true;
		}
		else {
			System.out.println("false");
			return false;
		}
	}
	
	@Override
	public Course returnCourse(int  courseid) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		System.out.println("inside return course");
		String query = "from Course c where c.courseId=:courseid";
		System.out.println("after query");
		org.hibernate.Query q = session.createQuery(query);
		System.out.println("Query object");
		q.setInteger("courseid", courseid);
		
		System.out.println("courseId fetched");
		List<Course> courselist = q.list();
		Iterator<Course> couitr = courselist.iterator();
		System.out.println("List initialized");
		Course c = new Course();
		while (couitr.hasNext()) {
			c =  couitr.next();
		}
		System.out.println("course returned");
		return c;
	}

}
