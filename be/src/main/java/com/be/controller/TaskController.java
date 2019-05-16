package com.be.controller;

import com.be.DTO.TaskMain.TaskMainMapper;
import com.be.entity.*;
import com.be.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/onProjectForTaskAssign")
    public ResponseEntity<List<com.be.DTO.TaskMain.User>> getUsersOnProjectForTaskAssign(@RequestParam String role,@RequestParam String firstEmailLetters, @RequestParam int idProject){
        Project projectFound = projectRepository.findProjectByIdProject(idProject);
        if(projectFound == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        List<User> usersResult = userRepository.findFirst10ByUserProjectsContainsAndEmailStartingWithAndRole(projectFound,firstEmailLetters,role);
        return new ResponseEntity<>(TaskMainMapper.INSTANCE.usersToUserDTOs(usersResult),HttpStatus.OK);
    }

    @GetMapping("/page")
    public Page<com.be.DTO.TaskMain.TaskForTable> getPageOfTasks(@RequestParam Map<String,String> allParams) {
        User user = userRepository.findUserByEmail(allParams.get("email"));
        PageRequest pageRequest =PageRequest.of(
                Integer.parseInt(allParams.get("page")),
                Integer.parseInt(allParams.get("size")),
                Sort.by((allParams.get("directionSort").equals("asc"))?Sort.Direction.ASC  : Sort.Direction.DESC,allParams.get("fieldSort"))
                );
        String search=allParams.get("search");
        Page<Task> entityPage;
        if(allParams.get("type").equals("real")){
            entityPage=taskRepository.findAllByTaskUserAndDescriptionContains(pageRequest,user,search);
        }
        else if(allParams.get("type").equals("available")){
            entityPage=taskRepository.findAllByTaskProjectInAndDescriptionContains(pageRequest,user.getUserProjects(),search);
        }
        else if(allParams.get("type").equals("project")){
            Project project = projectRepository.findProjectByIdProject(Integer.parseInt(allParams.get("idProject")));
            entityPage = taskRepository.findAllByTaskProjectAndDescriptionContains(pageRequest,project,search);
        }
        else{
            entityPage = taskRepository.findAllByDescriptionContains(pageRequest,search);
        }
        List<Task> tasks = entityPage.getContent();
        List<com.be.DTO.TaskMain.TaskForTable> tasksDTO = TaskMainMapper.INSTANCE.tasksToTasksForTable(tasks);
        return new PageImpl<>(tasksDTO,pageRequest,entityPage.getTotalElements());
    }

    @GetMapping("/{idTask}")
    public ResponseEntity<com.be.DTO.TaskMain.Task> getTaskById(@PathVariable int idTask){
        Task taskResult = taskRepository.findTaskByIdTask(idTask);
        if(taskResult==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(TaskMainMapper.INSTANCE.taskToTaskDTO(taskResult),HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity createTask(@RequestBody com.be.DTO.TaskMain.Task taskDTO){
        Task task = TaskMainMapper.INSTANCE.taskDTOToTask(taskDTO);
        Project project = projectRepository.findProjectByIdProject(task.getTaskProject().getIdProject());
        Set<Task> tasks = project.getProjectTasks();
        if(tasks.size()==0){
            task.setCode(0);
        }
        else{
            int lastcode=0;
            for (Task i:tasks) {
                if(i.getCode()>lastcode)
                    lastcode=i.getCode();
            }
            task.setCode(++lastcode);
        }
        Task taskResult = taskRepository.save(task);
        if(taskResult==null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(TaskMainMapper.INSTANCE.taskToTaskDTO(taskResult),HttpStatus.OK);
    }
    @PatchMapping("")
    public ResponseEntity<com.be.DTO.TaskMain.Task> updateTask(@RequestBody com.be.DTO.TaskMain.Task taskDTO){
        Task task = TaskMainMapper.INSTANCE.taskDTOToTask(taskDTO);
        Task foundTask = taskRepository.findTaskByIdTask(task.getIdTask());
        foundTask.setStatus(task.getStatus());
        foundTask.setPriority(task.getPriority());
        foundTask.setUpdateDate(task.getUpdateDate());
        foundTask.setDueDate(task.getDueDate());
        foundTask.setEstimationDate(task.getEstimationDate());
        foundTask.setTaskComments(task.getTaskComments());
        foundTask.setTaskUser(task.getTaskUser());
        Task taskResult = taskRepository.save(foundTask);
        if(taskResult==null)

                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(TaskMainMapper.INSTANCE.taskToTaskDTO(taskResult),HttpStatus.OK);
    }

}
