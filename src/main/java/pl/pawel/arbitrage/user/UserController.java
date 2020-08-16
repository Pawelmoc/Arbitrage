package pl.pawel.arbitrage.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("user", new User());
        return "userForm";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "userForm";
        }
        userRepository.save(user);
        return "redirect:all";
    }

    @RequestMapping("/all")
    public String get(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "listUsers";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:../all";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable Long id) {
        User user = userRepository.findById(id).get();
        model.addAttribute("user", user);
        return "userForm";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "userForm";
        }
        userRepository.save(user);
        return "redirect:../all";
    }
}
