package Controllers;

import com.google.gson.Gson;
import ds.ToDoList;
import ds.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController
{
    ToDoList tdl = new ToDoList();
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestBody String user) throws Exception
    {
        Gson parser = new Gson();
        User loggingIn = parser.fromJson(user, User.class);
        if (tdl.login(loggingIn.getLogin(), loggingIn.getPassword()) != null)
        {
            loggingIn.setId(tdl.getLoggedIn().getId());
            return parser.toJson(loggingIn);
        }
        return "Incorrect login data";
    }

    @RequestMapping (value = "projectlist", method = RequestMethod.GET)
    @ResponseBody
    public String projectlist()
    {
        Gson parser = new Gson();
        return parser.toJson(tdl.getAllProjects());
    }

    @RequestMapping (value = "tasklist", method = RequestMethod.GET)
    @ResponseBody
    public String tasklist()
    {
        Gson parser = new Gson();
        return parser.toJson(tdl.getAllProjects());
    }

    @RequestMapping (value = "userlist", method = RequestMethod.GET)
    @ResponseBody
    public String userlist()
    {
        Gson parser = new Gson();
        return parser.toJson(tdl.getAllUsers());
    }

}
