package com.project.life.controllers.user;
import javax.servlet.http.HttpSession;   
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.life.models.user.User;
import com.project.life.services.user.UserService;
import com.project.life.validators.UserValidator;
@Controller
public class UserController {
	private final UserService userService;

	// NEW
	private final UserValidator userValidator;

	// NEW
	public UserController(UserService userService, UserValidator userValidator) {
		this.userService = userService;
		this.userValidator = userValidator;
	}

	@RequestMapping("/")
	public String logreg(@ModelAttribute("user") User user) {
		return "user/logreg.jsp";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			return "user/logreg.jsp";
		}
		User u = userService.registerUser(user);
		session.setAttribute("userId", u.getId());
		return "redirect:/dashboard";
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginUser(@ModelAttribute("user") User user,@RequestParam("email") String email, @RequestParam("password") String password, Model model,
			HttpSession session) {

		boolean isAuth = userService.authenticateUser(email, password);
		if (isAuth) {
			User userLog = userService.findByEmail(email);
			session.setAttribute("userId", userLog.getId());
			return "redirect:/dashboard";
		} else {
			model.addAttribute("error", "Invalid email/password. Try again.");
			return "user/logreg.jsp";
		}
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("/dashboard")
	public String dashboard(Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(userId);
		model.addAttribute("user", user);
		return "user/dashboard.jsp";
	}
}
