package haagahelia.fi.stockapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import haagahelia.fi.stockapp.domain.AppUser;
import haagahelia.fi.stockapp.domain.AppUserRepository;
import haagahelia.fi.stockapp.domain.SignupForm;
import jakarta.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private AppUserRepository repository;

    @RequestMapping(value = "signup")
    public String addStock(Model model) {
        model.addAttribute("signupform", new SignupForm());
        return "signup";
    }

    /**
     * Create new user
     * Check if user already exists & form validation
     * 
     * @param signupForm
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "saveuser", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) { // validation errors
            if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match
                String pwd = signupForm.getPassword();
                BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
                String hashPwd = bc.encode(pwd);

                AppUser newUser = new AppUser();
                newUser.setPasswordHash(hashPwd);
                newUser.setUsername(signupForm.getUsername());
                newUser.setEmail(signupForm.getEmail());
                newUser.setRole("USER");
                if (repository.findByUsername(signupForm.getUsername()) == null) { // Check if user exists
                    repository.save(newUser);
                    System.out.println("JUKKATEST");
                    System.out.println(newUser);
                    System.out.println(newUser.getId());
                } else {
                    bindingResult.rejectValue("username", "err.username", "Username already exists");
                    return "signup";
                }
            } else {
                bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");
                return "signup";
            }
        } else {
            return "signup";
        }
        return "redirect:/login";
    }
}