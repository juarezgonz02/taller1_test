package com.escruadronlobo.devs.taller1.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date; 
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.escruadronlobo.devs.taller1.models.dto.AuthUserDto;
import com.escruadronlobo.devs.taller1.models.entities.User;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/app")
public class LoginController {

	public static List<User> users = new ArrayList<>();
	private static User loggedUser;
	
	static{
	    users.add(new User("AD123400", "Victor", "Cortez", "23/03/2023", true, "admin", "1234"));
	    users.add(new User("AF123401", "Oscar", "Juarez", "23/03/2023", true, "user", "1234"));
	    users.add(new User("AJ123400", "Marcela", "Melendez", "23/03/2023", false, "user", "1234"));
	    users.add(new User("AL123400", "Diana", "Valenzuela", "23/03/2023", true, "admin", "1234"));
	}

	@GetMapping("/app")
	public String appMain(Model model) {
	
		
		if(loggedUser == null) {
			
			return "redirect:/app/login";
		}
		
		model.addAttribute("name", loggedUser.getName());
		model.addAttribute("time", Calendar.getInstance().getTime());
		
		if(loggedUser.getRol() == "Admin") {
			model.addAttribute("userList", users);
		}
		
		return "main";
	}
	
	
	@GetMapping("/login")
	public String login() {
	
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout() {
	
		if(loggedUser != null) {			
			loggedUser = null;
		}
		return "redirect:/app/login";
	}
	
	@SuppressWarnings("deprecation")
	@PostMapping("/login/auth")
	public String auth(@ModelAttribute @Valid AuthUserDto  userToLoginData, 
						BindingResult result,
						Model model
	) {
		
		User userToLog = users.stream()
				  .filter(user -> userToLoginData.getUserCode().equals(user.getCode()))
				  .findAny()
				  .orElse(null);

		if(userToLog == null) {
			model.addAttribute("error", "404 NO ENCONTRADO" );
			return "error";
		}
		
		if(userToLog.getIsActive() == false) {
			model.addAttribute("error", "El Usuario No está activo" );
			return "error";
		}
		

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date hiringDate = new Date();
		try {
			hiringDate = sdf.parse(userToLog.getHiringDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    	Date now = Calendar.getInstance().getTime();

        now.setMonth(now.getMonth()-1);
       

        long diff = hiringDate.getTime() - now.getTime();
        
        TimeUnit time = TimeUnit.DAYS; 
        long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);

        System.out.println("The difference in days is : "+diffrence);
        
        if(diffrence < 30) {
			model.addAttribute("error", "El Usuario aún no ha cumplido fecha de inicio" );
			return "error";
        }

		
    	return "redirect:/app/";
	}


}
