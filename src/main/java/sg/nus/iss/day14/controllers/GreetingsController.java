package sg.nus.iss.day14.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = {"/", ""})
public class GreetingsController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping
    public String getGreetings(Model model){
        ValueOperations <String, Object> ops = redisTemplate.opsForValue();
        Object greetings = ops.get("greetings");
        model.addAttribute("greetings", greetings);
        return "index";
    }

    @PostMapping
    public String postGreeetings(@RequestBody MultiValueMap<String, String> form, Model model){
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        String text = form.getFirst("text");
        ops.set("greetings", text );
        model.addAttribute("hello", text);
        return "index";


    }
    
}
