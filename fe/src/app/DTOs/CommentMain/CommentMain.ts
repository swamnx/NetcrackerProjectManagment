export class CommentForCreating {
    idComment: number;
    comment: string;
    commentUser: User;
    commentTask: Task;
    date: Date;
    constructor() {
        this.commentTask = new Task();
        this.commentUser = new User();
        this.date = new Date();
    }
}
export class User {
    idUser: number;
    name: string;
    email: string;
    role: string;
}
export class Task {
    idTask: number;
}