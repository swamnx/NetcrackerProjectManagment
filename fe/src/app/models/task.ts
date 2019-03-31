import {User} from 'src/app/models/user';
import {Project} from 'src/app/models/project';
export class Task{

    idTask:number;
    code:string;
    priority:string;
    status:string;
    description:string;
    createDate:Date;
    updateDate:Date;
    dueDate:Date;
    estimationDate:Date;
    reporter:User;
    taskUser:User;
    taskProject:Project;
    static cloneBase(task:Task):Task {
        const clonedTask:Task = new Task();
        clonedTask.idTask = task.idTask;
        clonedTask.code = task.code;
        clonedTask.priority = task.priority;
        clonedTask.status = task.status;
        clonedTask.description = task.description;
        clonedTask.createDate = task.createDate;
        clonedTask.updateDate = task.updateDate;
        clonedTask.dueDate = task.dueDate;
        clonedTask.estimationDate = task.estimationDate;
        clonedTask.reporter = task.reporter;
        clonedTask.taskUser = task.taskUser;
        clonedTask.taskProject = task.taskProject;
        return clonedTask;
    }
}