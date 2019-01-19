package com.skillIndia.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.skillIndia.model.Candidate;
import com.skillIndia.model.Course;
import com.skillIndia.model.Establishment;
import com.skillIndia.service.AdminService;
import com.skillIndia.service.CandidateService;

@Controller
public class CandidateController {

	@Autowired
	private CandidateService candidateService;

	public void setCandidateServiceObject(CandidateService candidateServiceObject) {
		this.candidateService = candidateServiceObject;
	}
	
	@Autowired
	private AdminService adminService;
	
	public void setAdminServiceObject(AdminService adminServiceObject) {
		this.adminService = adminServiceObject;
	}

	@RequestMapping("/CandidateRegistration")
	public String showRegPage(Model model) {
		model.addAttribute("candidate", new Candidate());
		return "CandidateRegistration";
	}
	
	//To add a Candidate
	@RequestMapping(value = "/add", 
			method = RequestMethod.POST)
	public String addCandidate(
			@ModelAttribute("candidate") 
			@Valid Candidate candidate, 
			BindingResult result, 
			Model model) {
		if(result.hasErrors())
		{
			return UniqueConstraints();
		}
		this.candidateService.addCandidate(candidate);
			return "CandidateLogin";
			
		}

	@ExceptionHandler(SQLException.class)
	public String UniqueConstraints() {
		
		return "Unique";
	}
	
	@RequestMapping(value = "/candidatelogin")
	public String LoginPage(Model model) {
		model.addAttribute("candidate",new Candidate());
		return "CandidateLogin";
	}
	
	@RequestMapping(value = "/candidateloginprocess",
			method = RequestMethod.POST)
	public ModelAndView candidateLogin(
			@ModelAttribute("candidate") 
			@Valid Candidate candidate, 
			BindingResult result, 
			Model model,HttpSession session, HttpServletRequest request) {
	
		ModelAndView mav = new ModelAndView();
		if(candidate.getEmailId().equalsIgnoreCase("admin")&&candidate.getNewPassword().equalsIgnoreCase("admin"))
		{
			
			session.setAttribute("adminusername",candidate.getEmailId());
			
			session.setAttribute("adminpassword", candidate.getNewPassword());
			
			List<Candidate> candidateList =this.adminService.listAllCandidate();
			
			model.addAttribute("CandidateList", candidateList);
			System.out.println(candidateList);
			List<Establishment> establishmentList =this.adminService.listAllEstablishment();
			System.out.println(establishmentList);
			model.addAttribute("EstablishmentList", establishmentList);
			mav.setViewName("AdminDashboard");
			
			return mav;
		}
		
	if(this.candidateService.loginCandidate(candidate))
		{				
				Candidate logCandidate = this.candidateService.returnCandidate(candidate);
				session.setAttribute("candidate",logCandidate);
				Integer id =  logCandidate.getUserId();
				session.setAttribute("candidateId", id);
				session.setAttribute("candidatename", logCandidate.getCandidateName());
				//session.setAttribute("UserId", candidate.getUserId());
				mav.addObject("candidateid", candidate.getUserId());
				mav.setViewName("CandidateDashboard");
				
		}
		else {
			mav.setViewName("error");
		}
			System.out.println("7");
			return mav;
		}
	
	@RequestMapping(value="/activeCourse",method=RequestMethod.GET)
	public String courseList(Model model, HttpSession session) {
		Integer id = (Integer)session.getAttribute("candidateId");
		System.out.println(id);
		List<Course> courseList=this.candidateService.listCourse(id);
		System.out.println(courseList);
		model.addAttribute("CourseList",courseList);
		return "CourseList";
	}
	
	@RequestMapping(value="/browseCourse",method = RequestMethod.GET)
	public String courseStatus(Model model,HttpSession session)
	{
	Integer id=(Integer)session.getAttribute("candidateId");
		List<Course> courseList=this.candidateService.browseCourse();
		//System.out.println(courseList);
		model.addAttribute("CourseList",courseList);
		Course course = new Course();
		session.setAttribute("Course", course);
		return "CourseList";
	}
	
	@RequestMapping(value="/applyCourse/{courseId}",method=RequestMethod.GET)
	public String applyCourse(@PathVariable("courseId") int courseId,Model model, HttpSession session) {
		System.out.println("1");
		Integer id = (Integer)session.getAttribute("candidateId");
		Candidate currentCandidate = (Candidate)session.getAttribute("candidate");
		System.out.println("2");
		//Integer courseId = (Integer)session.getAttribute("courseId");
		//Course course = (Course)session.getAttribute("Course");
		 if(("REJECTED").equals(currentCandidate.getRequestStatus())) {
				return "RejectedCandidate";
			}
		 else if(("ACCEPTED").equals(currentCandidate.getRequestStatus()) && currentCandidate.getCourse()==null) {
		Course applycourse = this.candidateService.returnCourse(courseId);
		System.out.println("3");
		 this.candidateService.applyCourse(applycourse,id);
		 System.out.println(applycourse);
		 return "CandidateDashboard";
		}//System.out.println(course);
		else {
			return "OneCourse";
		}
		}
	
	@RequestMapping(value="/reject/adminsignout",method= RequestMethod.GET)
	public String acceptadminsignout(Model model ,HttpSession session)
	{
		session.invalidate();
		model.addAttribute("candidate",new Candidate());
		return "CandidateLogin";
	}
	@RequestMapping(value="/accept/adminsignout",method= RequestMethod.GET)
	public String rejectadminsignout(Model model ,HttpSession session)
	{
		session.invalidate();
		model.addAttribute("candidate",new Candidate());
		return "CandidateLogin";
	}
	


@RequestMapping("/accept/{id}")
public String acceptCandidate(
		@PathVariable("id") int id,Model model) 
{
	
	this.adminService.acceptCandidate(id);

	List<Candidate> candidateList = this.adminService.listAllCandidate();

	model.addAttribute("Candidatelist", candidateList);

	return "AdminDashboard";
}

@RequestMapping("/reject/{id}")
public String rejectCandidate(
		@PathVariable("id") int id,Model model) 
{
	this.adminService.rejectCandidate(id);
	List<Candidate> candidateList = this.adminService.listAllCandidate();
	model.addAttribute("Candidatelist", candidateList);
	return "AdminDashboard";
}

@RequestMapping("/accepte/{estId}")
public String acceptEstablishment(
		@PathVariable("estId") int id,Model model) 
{
	this.adminService.acceptEstablishment(id);
	List<Establishment> establishmentList = this.adminService.listAllEstablishment();
	model.addAttribute("Establishmentlist", establishmentList);
	return "AdminDashboard";
}

@RequestMapping("/rejecte/{estId}")
public String rejectEstablishment(
		@PathVariable("estId") int id,Model model) 
{
	this.adminService.rejectEstablishment(id);
	List<Establishment> establishmentList = this.adminService.listAllEstablishment();
	model.addAttribute("Establishmentlist", establishmentList);
	return "AdminDashboard";
}


	@RequestMapping(value="/adminsignout",method= RequestMethod.GET)
	public String adminsignout(	Model model ,HttpSession session)
	{
		session.invalidate();
		model.addAttribute("candidate",new Candidate());
		return "redirect:";
	}
	
	@RequestMapping(value="/signout",method= RequestMethod.GET)
	public String signout(	Model model ,HttpSession session)
	{
		session.invalidate();
		model.addAttribute("candidate",new Candidate());
		return "CandidateLogin";
	}
}