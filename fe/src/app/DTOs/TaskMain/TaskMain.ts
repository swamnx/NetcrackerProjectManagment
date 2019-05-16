export class Task{
    idTask:number;
    idCreatedBy:number;
    code:number;
    priority:string;
    status:string;
    description:string;
    createDate:Date;
    updateDate:Date;
    dueDate:Date;
    estimationDate:Date;
    taskUser:User;
    taskProject:Project;
    taskComments:Array<Comment>;
    constructor(){
        this.taskProject=new Project();
    }
}
export class User{
    idUser:number;
    name:string;
    role:string;
    email:string;
}
export class Project{
    idProject:number;
    code:number;
}
export class Comment{
    date:Date;
    idComment:number;
    comment:string;
    commentUser:User;
}
export class UserForTable{
    idUser:number;
    name:string;
}
export class TaskForTable{
    idTask:number;
    code:number;
    status:string;
    description:string;
    createDate:Date;
    updateDate:Date;
    dueDate:Date;
    estimationDate:Date;
    taskUser:UserForTable;
    taskProject:Project;
}
export class PageForTasksTable{
    content:Array<TaskForTable>;
    firstPage:boolean;
    lastPage:boolean;
    number:number;
    numberOfElements:number;
    size:number;
    sort:string;
    totalElements:number;
    totalPages:number;
}
