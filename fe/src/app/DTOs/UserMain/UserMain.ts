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
    taskComments:Array<Comment>;
}
export class User{
    idUser:number;
    name:string;
    role:string;
    email:string;
    userProjects:Array<Project>
    userTasks:Array<Task>
}
export class UserWithPassword{
    password:string;
    idUser:number;
    name:string;
    role:string;
    email:string;
    userProjects:Array<Project>
    userTasks:Array<Task>
}
export class UserAuth{
    idUser:number;
    name:string;
    role:string;
    email:string;
}
export class AuthToken{
    token:string;
}
export class Project{
    idProject:number;
    code:number;
    description:string;
}
export class Comment{
    idComment:number;
    comment:string;
    user:User;
}
