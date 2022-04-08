package vttp2022.loginpage.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vttp2022.loginpage.repository.LoginRepo;
import vttp2022.loginpage.service.LoginService;

@Controller
@RequestMapping
public class LoginController {

    @Autowired
    private LoginService service;
    
    @PostMapping("/authenticate")
    public ModelAndView showHomepage(@RequestParam String username, @RequestParam String password
        , HttpSession session){
        ModelAndView mvc =  new ModelAndView();

        if (service.authenticateUser(username, password)){
            mvc = new ModelAndView("redirect:/protected/homepage");
            session.setAttribute("username", username);
        } else {
            mvc.setStatus(HttpStatus.UNAUTHORIZED);
            mvc.setViewName("error");
        }

        return mvc;
    } 

    @GetMapping("/")
    public String getLogin(){
        return "index";
    }

    @GetMapping("/logout")
    public String getLogout(HttpSession session){
        session.invalidate();
        return "index";
    }
}
