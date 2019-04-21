import { tokenKey } from '@angular/core/src/view';

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
    idProject:number;
    code:number;
    description:string;
    projectUsers:Array<User>;
}
export class Comment{
    idComment:number;
    comment:string;
    user:User;
}
export class Page{
    content:Array<Task>;
    firstPage:boolean;
    lastPage:boolean;
    number:number;
    numberOfElements:number;
    size:number;
    sort:string;
    totalElements:number;
    totalPages:number;
}