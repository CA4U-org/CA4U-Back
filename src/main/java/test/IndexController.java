package test;


import com.example.ca4u.domain.guild.GuildService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
@RequiredArgsConstructor
@Controller
public class IndexController {
    private final GuildService guildService;
/*    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser member) {
        model.addAttribute("posts", guildService.getGuildArticles(1));

        if (member != null) {
            model.addAttribute("userName", member.getName());
        }
        return "index";
    }*/

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", guildService.getGuildArticles(1));

        return "index";
    }
}
