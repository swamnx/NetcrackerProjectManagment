import {Project} from 'src/app/models/project';
import {Task} from 'src/app/models/task'

export class User {

    idUser: number;
    email: string;
    name:string;
    password:string;
    role:string;
    userProjects:Array<Project>;
    userTasks:Array<Task> 
  
    static cloneBase(user: User): User {

      const clonedUser: User = new User();

      clonedUser.idUser = user.idUser;
      clonedUser.email = user.email;
      clonedUser.name = user.name;
      clonedUser.password = user.password;
      
      for(let i of user.userProjects){
        clonedUser.userProjects.push(i);
      }
      for(let i of user.userTasks){
        clonedUser.userTasks.push(i);
      }
      return clonedUser;
    }
  }
  