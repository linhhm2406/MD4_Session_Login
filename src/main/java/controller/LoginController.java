package controller;

import config.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class LoginController {

    @GetMapping()
    public String showForm(@CookieValue(defaultValue = "") String username, @CookieValue(defaultValue = "") String password, Model model) {
        model.addAttribute("username",username);
        model.addAttribute("password",password);

        return "login";
    }

    @PostMapping()
    public String login(@ModelAttribute User user, @RequestParam(defaultValue = "") String rememberMe,
                        Model model, HttpServletResponse httpServletResponse){

        if (!rememberMe.equals("")) {
            Cookie savedUsername = new Cookie("username", user.getUsername());
            Cookie savedPassword = new Cookie("password", user.getPassword());

            httpServletResponse.addCookie(savedPassword);
            httpServletResponse.addCookie(savedUsername);
        }
        return "redirect:/";
    }
}
