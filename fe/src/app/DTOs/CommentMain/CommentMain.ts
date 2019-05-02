export class CommentForCreating{
    idComment:number;
    comment:string;
    commentUser:User;
    commentTask:Task;
    constructor(){
        this.commentTask = new Task();
        this.commentUser = new User();
    }
}
export class User{
    idUser:number;
    name:string;
    email:string;
    role:string;
}
export class Task{
    idTask:number;
}