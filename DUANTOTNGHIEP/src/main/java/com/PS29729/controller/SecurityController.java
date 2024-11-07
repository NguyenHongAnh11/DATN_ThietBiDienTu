package com.PS29729.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.PS29729.entity.Account;
import com.PS29729.service.AccountService;

@Controller
public class SecurityController {
	@RequestMapping("/security/login/form")
	public String loginForm(Model model) {
		model.addAttribute("message", "Vui lòng đăng nhập!");
		return "security/login";
	}

	@RequestMapping("/security/login/success")
	public String loginSuccess(Model model) {
		model.addAttribute("message", "Đăng nhập thành công!");
		return "layout/inforuser";
	}

	@RequestMapping("/security/login/error")
	public String loginError(Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập!");
		return "security/login";
	}

	@RequestMapping("/security/unauthoried")
	public String unauthoried(Model model) {
		model.addAttribute("message", "Không có quyền truy xuất!");
		return "security/login";
	}

	@RequestMapping("/user/infor")
	public String userinf(Model model) {
		return "layout/inforuser";
	}

	@RequestMapping("/security/logoff/success")
	public String logoffSuccess(Model model) {
		model.addAttribute("message", "Bạn đã đăng xuất!");
		return "security/login";
	}

	@CrossOrigin("*")
	@ResponseBody
	@RequestMapping("/rest/security/authentication")
	public Object getAuthentication(HttpSession session) {
		return session.getAttribute("authentication");
	}

	@RequestMapping("/security/register")
	public String registerForm(Model model) {
		return "security/register";
	}

	@Autowired
	private AccountService accountRepository;


	@RequestMapping("/security/register/form")
	public String registeraForm(Model model) {
		model.addAttribute("message", "Please register!");
		return "security/register";
	}

	@RequestMapping("/security/register/success")
	public String registerSuccess(Model model) {
		model.addAttribute("message", "Registration successful!");
		return "security/register-success";
	}

	@RequestMapping(value = "/security/register", method = RequestMethod.POST)
	public RedirectView registerUser(@RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("fullname") String fullname,
			@RequestParam("email") String email, RedirectAttributes redirectAttributes) {

		if (accountRepository.existsById(username)) {
			redirectAttributes.addFlashAttribute("message", "Username already exists!");
			return new RedirectView("/security/register");
		}
		if (accountRepository.existsByEmail(email)) {
			redirectAttributes.addFlashAttribute("message", "Email already exists!");
			return new RedirectView("/security/register");
		}

		Account newAccount = new Account();
		newAccount.setUsername(username);
		newAccount.setPassword(password); 
		newAccount.setFullname(fullname);
		newAccount.setEmail(email);
		newAccount.setPhoto("user.png");


		accountRepository.save(newAccount);
		redirectAttributes.addFlashAttribute("message", "Registration successful!");

		return new RedirectView("/security/login/form");
	}
}
