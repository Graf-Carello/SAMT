package at.fh.swenga.samt.controller;

import java.util.ArrayList;
import java.util.List;

import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.samt.dao.HomeworkRepository;
import at.fh.swenga.samt.dao.UserRepository;
import at.fh.swenga.samt.model.HomeworkModel;
import at.fh.swenga.samt.model.UserModel;

@Controller
@RequestMapping("/homework")
public class HomeworkController {


	@Autowired
	HomeworkRepository homeworkRepository;
	
	@Autowired
	UserRepository userRepository;

	@RequestMapping("")
	public String index(Model model) {
		List<HomeworkModel> homeworks = homeworkRepository.findAll(); //get all emp
		model.addAttribute("homeworks", homeworks); //put into model
		model.addAttribute("type", "findAll"); //type request parameter
		return "index"; //all empl distplay on bootstrap
	}

	@RequestMapping("/getPage")
	public String getPageHomework(Pageable page,Model model) {
		//page contains list and some addit. info
		Page<HomeworkModel> homeworks = homeworkRepository.findAll(page);
		model.addAttribute("homeworks", homeworks.getContent()); //liste mit empl zeigen 
		model.addAttribute("homeworksPage", homeworks); //die Seite zeigen
		return "index";
	}

	@RequestMapping(value = { "/find" })
	//search for reqpar with type and return as type
	public String find(Model model, @RequestParam String searchString, @ModelAttribute("type")  String type) {
		List<HomeworkModel> homeworks = null;
		int count=0;

		switch (type) {
		case "findAll":
			homeworks=homeworkRepository.findAll();
			break;
			
		default:
			homeworks = homeworkRepository.findAll(); //defaut einstellungen, falls nichts gibt, wird find all ausgeführt
		}
		
		model.addAttribute("homeworks", homeworks);
		model.addAttribute("count", count);

		return "index";
	}

	@RequestMapping(value = { "/findById" })
	public String findById(@RequestParam("id") HomeworkModel e, Model model) {

		
		List<HomeworkModel> homeworks = new ArrayList<>();
		homeworks.add(e);
		model.addAttribute("homeworks", homeworks);
		
		return "index";
	}
	
	@RequestMapping("/fill")
	@Transactional
	public String fillData(Model model) { 

		DataFactory df = new DataFactory(); //random die Namen von employes erstellen, für test zwecken
		UserModel user = null;
		for(int i = 0; i<100; i++) { //0 devided by 10 is 0; first iteration: create or use existing one
			
			//(String firstName, String lastName, String degreeCourse, String email, String password,
			//String profilePicture)
			
			
			if(i%10==0){
				String userUserName = "testUser11";
				String userFirstName = df.getFirstName();
				String userLastName = df.getLastName();
				String degreeCourse = "IMA";
				String email = userFirstName + userLastName + "@gmail.com";
				String password = "password";
				String profilePicture = "picture";
				user = userRepository.findFirstByEmail(userFirstName + userLastName + "@gmail.com"); //weil in model string name gibt für comrepository
				if(user == null) { //if company not in db then create one in db
					user = new UserModel(userUserName, userFirstName, userLastName, degreeCourse, email, password, profilePicture); //unmanaged; but when we ask it from db, than a managed instance is returned
				}
			}
			
			//int id, String description, Date deadline, UserModel user
			
			HomeworkModel hm = new HomeworkModel(df.getNumber(), df.getRandomText(50), df.getDateBetween(df.getDate(2016, 3, 1), df.getDate(2016, 7, 10)), user);
			homeworkRepository.save(hm);
		
		}
		return "forward:list";
	}

	@RequestMapping("/delete")
	public String deleteData(Model model, @RequestParam int id) {
		homeworkRepository.delete(id);

		return "forward:list";
	}

	@ExceptionHandler(Exception.class) //catch block, exception handler, for example if asked for id(string), but there is only id(int)
	public String handleAllException(Exception ex) {

		return "showError";
	}
}
