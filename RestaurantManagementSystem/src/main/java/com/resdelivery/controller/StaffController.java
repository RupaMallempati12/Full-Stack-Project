package com.resdelivery.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.resdelivery.model.Login;
import com.resdelivery.model.Staff;
import com.resdelivery.repository.StaffRepository;

@RequestMapping("/staff")
@Controller
public class StaffController {

	@Autowired
	StaffRepository repo;
	
	@RequestMapping()
    public String login(Model model) {
        return "staff_login";
    }	
	
	
	@RequestMapping("/logout")
    public String logout(Model model, HttpServletRequest req) {
        req.getSession().invalidate();
        return "logout";
    }
	
	@PostMapping("/login")
	public String show(Login login, Model model, HttpServletRequest request) {
		if(login.getEmail().equals("admin") && login.getPassword().equals("admin"))
		{
			request.getSession().setAttribute("id", "1001");
			request.getSession().setAttribute("userid", "STAFF00001");
			request.getSession().setAttribute("usertype", "staff");
			request.getSession().setAttribute("name", "Admin");
			return "staff_home";
			
		}
		Optional<Staff> obj = repo.findByEmailAndPassword(login.getEmail(),login.getPassword());
		System.out.println(login.getEmail()+" - "+login.getPassword() );
		
		
		if(obj.isPresent())
		{
			model.addAttribute("name",obj.get().getName());
			request.getSession().setAttribute("id", obj.get().getId());
			request.getSession().setAttribute("userid", obj.get().getStaffId());
			request.getSession().setAttribute("usertype", "staff");
			request.getSession().setAttribute("name", obj.get().getName());
			return "staff_home";
		}
		else
		{
			model.addAttribute("msg","Invalid Login Credentials");
			return "index";
		}
	}
	
	
	@RequestMapping("/list")
    public String home(Model model, HttpServletRequest request) {
        model.addAttribute("datalist", repo.findAll());
        return "staff";
    }
	
	@RequestMapping("/create")
	public String create(Model model, HttpServletRequest request) {
		return "staff_create";
	}
	
	@RequestMapping("/save")
	public String save(Staff obj){
		Optional<Staff> idobj = repo.findTopByOrderByIdDesc();
		String id = null;
		if(idobj.isPresent())
		{
			int idnum = Integer.parseInt(idobj.get().getStaffId().substring(5));
			idnum++;
			id = "STAFF"+idnum;
		}
		else
		{
			id = "STAFF21301";
		}
		obj.setStaffId(id);
		repo.save(obj);		
		return "redirect:/staff/list";
	}
	
	@RequestMapping("/show/{id}")
	public String show(@PathVariable String id, Model model, HttpServletRequest request) {
		model.addAttribute("obj",repo.findById(id).get());
		return "staff_show";
	}
	
	 @RequestMapping("/delete")
	    public String delete(@RequestParam String id) {
	        Optional<Staff> obj = repo.findById(id);
	        repo.delete(obj.get());

	        return "redirect:/staff/list";
	    }
	    
	    @RequestMapping("/edit/{id}")
	    public String edit(@PathVariable String id, Model model) {
	        model.addAttribute("obj", repo.findById(id).get());
	        return "staff_edit";
	    }
	    
	    @RequestMapping("/update")
	    public String update(Staff obj) {
	        repo.save(obj);
	        return "redirect:/staff/show/" + obj.getId();
	    }
}
