export class Task {
    idTask: number;
    idCreatedBy: number;
    code: number;
    priority: string;
    status: string;
    description: string;
    createDate: Date;
    updateDate: Date;
    dueDate: Date;
    estimationDate: Date;
    taskUser: User;
    name:string;
    taskComments: Comment[];
}
export class User {
    idUser: number;
    name: string;
    role: string;
    email: string;
}
export class Project {
    idProject: number;
    code: number;
    description: string;
    projectUsers: User[]
    projectTasks: Task[]
}
export class Comment {
    idComment: number;
    comment: string;
    user: User;
}