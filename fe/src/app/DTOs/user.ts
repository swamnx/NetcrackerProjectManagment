import { Project } from '../models/project';

export class UserForAuth{

    email: string;
    password:string;
    role:string;
    name:string;

    static cloneBase(user: UserForAuth): UserForAuth {
        const clonedUser: UserForAuth = new UserForAuth();
        clonedUser.email = user.email;
        clonedUser.name = user.name;
        clonedUser.password=user.password;
        clonedUser.role=user.role;
        return clonedUser;
      }
}