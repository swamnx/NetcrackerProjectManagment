import { Task } from './task';
import { User } from './user';

export class Comment{
    idComment:number;
    comment:string;
    commentTask:Task;
    commentUser:User;

    static cloneBase(comment:Comment):Comment {
        const clonedComment:Comment = new Comment();
        clonedComment.comment=comment.comment;
        clonedComment.idComment=comment.idComment;
        clonedComment.commentTask=comment.commentTask;
        clonedComment.commentUser=comment.commentUser;
        return clonedComment;
    }
}