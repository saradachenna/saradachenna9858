package com.example.fullStackProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.fullStackProject.model.Administration;
import com.example.fullStackProject.model.Student;
import com.example.fullStackProject.services.Studentservice;

@Controller
public class StudentController {
	private Studentservice studentServ;
	public StudentController (Studentservice studentServ) {// constructor
		this.studentServ=studentServ;
	}
	@GetMapping("/addNewStudent")//adding a new record of student from db
	public String createStudent(Model model) {
		Student stdcreateObj= new Student();
		model.addAttribute("stdObj", stdcreateObj);
		return "createStudent";
	}
	@GetMapping("/seeallstudents")// to see all the students
	public String getAllStudentsFromDb(Model model){
		model.addAttribute("studentsList", studentServ.readAllStudents());
		return "studentFrontEnd";

	}
	@GetMapping("/home")// welcome page
	public String homepage(){
		return "home";

	}
	@PostMapping("/insertStudents")
	public String insertToDb(@ModelAttribute("stdObj")  Student std) {
		studentServ.saveStudent(std);  //std is insert into db by using save 
		return "redirect:/seeallstudents";
	}
	@GetMapping("/updateStudent/{id}")
    public String updateStudent(@PathVariable int id, Student fromdb,Model model) {

        model.addAttribute("update" ,studentServ.updatestudent(id,fromdb));
        return "updateStudent";
    }
	@PostMapping("/updateAndSaveStudent/{id}")//when update is running old record will be replaced with the new record
	public String updateandSave(@PathVariable int id, @ModelAttribute("update")Student newfromdb ,Student fromdb) {
		Student existingdb=studentServ.updatestudent(id,fromdb);
		existingdb.setFirstname(newfromdb.getFirstname());
		existingdb.setLastname(newfromdb.getLastname());
		existingdb.setFavSub(newfromdb.getFavSub());
		return "redirect:/seeallstudents";
	}
	@GetMapping("/deleteStudent/{id}")
	public String deleteFromDb(@PathVariable int id) {
		
		studentServ.deleteStudent(id);
		return "redirect:/seeallstudents";
	}
	@GetMapping("/contactme")
	public String contact() {
			
		return "contactUs";
	}
	@GetMapping("/logout")
	public String logout() {
			
		return "logout";
	}
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		Administration admin=new Administration();
		model.addAttribute("adminObj",admin);
		return "login";
	}
	@GetMapping(" /login")
	public String validateLoginCredentials(@ModelAttribute("adminObj")Administration adminDetails) {
		if(adminDetails.getUserName().equals("Saradachenna9858@gmail.com")&& adminDetails.getPassword().equals("abcd")) {
			return "redirect:/seeallstudents";
		}
		else {
			return "login";
		}
	}
	
}
