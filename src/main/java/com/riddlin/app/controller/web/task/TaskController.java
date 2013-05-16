package com.riddlin.app.controller.web.task;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import com.riddlin.app.controller.web.AbstractPublicPageController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.riddlin.app.domain.task.Task;
import com.riddlin.app.message.Message;
import com.riddlin.app.message.MessageType;


/**
 * Controller for public task list, task view pages.
 */
@Controller
@RequestMapping("/tasks")
public class TaskController extends AbstractPublicPageController {
    // private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    public TaskController() {
    }

    /**
     * Display list of all tasks. 
     * 
     * @param uiModel
     * @param request
     * @return
     */
    @RequestMapping( method = RequestMethod.GET)
    public String getTasks(Model uiModel, HttpServletRequest request) {

        //Get all tasks, add to model
        List<Task> tasks = taskService.getAllTasks();
        if (tasks.size() > 0) {
            uiModel.addAttribute("tasks", tasks);
        } 

        //If no tasks, return a message
        else {
            uiModel.addAttribute("tasks", null);
            uiModel.addAttribute("taskMessage", 
                    new Message(MessageType.INFO, "No tasks yet. Stay tuned..."));

        }
        return "pages/tasks";
    }

    @RequestMapping(value="/{taskId}", method = RequestMethod.GET)
    public String getTask(
            @PathVariable("taskId") String taskId,
            Model uiModel, HttpServletRequest request) {

        //Get task by Id.
        Task task = taskService.findTaskById(taskId);
        if (task != null) {
            uiModel.addAttribute("task", task.getTask());
            uiModel.addAttribute("answer", task.getAnswer());
        } 

        //If no task, return a message
        else {
            uiModel.addAttribute("task", null);
            uiModel.addAttribute("taskMessage", 
                    new Message(MessageType.INFO, "Sorry, that task doesn't exist"));

        }
        return "pages/task";
    }

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public String create(Model uiModel,
            @RequestParam(value="task", required=true) String task,
            @RequestParam(value="answer", required=true) String answer){

        Task newTask = new Task();
        newTask.setTask(task);
        newTask.setAnswer(answer);
    
        uiModel.addAttribute("task",taskService.create(newTask));

        return "pages/task";
    }

    @RequestMapping(value="/create", method=RequestMethod.GET)
    public String create(Model uiModel, HttpServletRequest request){
    
        uiModel.addAttribute("create",true);

        return "pages/task";
    }


}








