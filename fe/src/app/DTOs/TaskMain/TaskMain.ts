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
}
export class User{
    idUser:number;
    name:string;
    role:string;
    email:string;
}
export class Project{
    code:number;
}
export class Comment{
    idComment:number;
    comment:string;
    user:User;
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
export class TaskForProjectTable{
    code:number;
    priority:string;
    status:string;
    description:string;
    taskUser:UserForTable;
}
export class PageForProjectTable{
    content:Array<TaskForProjectTable>;
    firstPage:boolean;
    lastPage:boolean;
    number:number;
    numberOfElements:number;
    size:number;
    sort:string;
    totalElements:number;
    totalPages:number;
}