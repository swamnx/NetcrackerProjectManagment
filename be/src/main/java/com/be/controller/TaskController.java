package com.be.controller;

import com.be.DTO.TaskMain.TaskMainMapper;
import com.be.entity.*;
import com.be.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
            entityPage=taskRepository.findAllByTaskUserAndNameContains(pageRequest,user,search);
        }
        else if(allParams.get("type").equals("available")){
            entityPage=taskRepository.findAllByTaskProjectInAndNameContains(pageRequest,user.getUserProjects(),search);
        }
        else if(allParams.get("type").equals("project")){
            Project project = projectRepository.findProjectByIdProject(Integer.parseInt(allParams.get("idProject")));
            entityPage = taskRepository.findAllByTaskProjectAndNameContains(pageRequest,project,search);
        }
        else{
            entityPage = taskRepository.findAllByNameContains(pageRequest,search);
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
    public ResponseEntity createTask(@Valid @RequestBody Task task){
        if(task.getTaskProject()==null || task.getTaskUser()==null)
            return new ResponseEntity (HttpStatus.BAD_REQUEST);
        Project project = projectRepository.findProjectByIdProject(task.getTaskProject().getIdProject());
        if(project == null)
            return new ResponseEntity (HttpStatus.BAD_REQUEST);
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
        if(task.getEstimationDate().compareTo(task.getDueDate())==1){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User userFound = userRepository.findUserByIdUser(task.getIdCreatedBy());
        if(userFound == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        Task taskResult = taskRepository.save(task);
        if(taskResult==null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(TaskMainMapper.INSTANCE.taskToTaskDTO(taskResult),HttpStatus.OK);
    }
    @PatchMapping("")
    public ResponseEntity<com.be.DTO.TaskMain.Task> updateTask(@Valid @RequestBody com.be.DTO.TaskMain.Task taskDTO){
        Task task = TaskMainMapper.INSTANCE.taskDTOToTask(taskDTO);
        Task foundTask = taskRepository.findTaskByIdTask(task.getIdTask());
        if(foundTask==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if(task.getEstimationDate().compareTo(task.getDueDate())==1){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(task.getTaskUser() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        foundTask.setStatus(task.getStatus());
        foundTask.setDescription(task.getDescription());
        foundTask.setPriority(task.getPriority());
        foundTask.setUpdateDate(task.getUpdateDate());
        foundTask.setDueDate(task.getDueDate());
        foundTask.setEstimationDate(task.getEstimationDate());
        foundTask.setTaskUser(task.getTaskUser());
        Task taskResult = taskRepository.save(foundTask);
        return new ResponseEntity<>(TaskMainMapper.INSTANCE.taskToTaskDTO(taskResult),HttpStatus.OK);
    }

}
