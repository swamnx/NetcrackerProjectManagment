import {User} from 'src/app/models/user';
import {Task} from 'src/app/models/task'

export class Project{

    idProject:number;
    code:string;
    description:string;
    projectUsers:Array<User>;
    projectTasks:Array<Task>;

    static cloneBase(project: Project): Project {

        const clonedProject: Project = new Project();

        clonedProject.idProject = project.idProject;
        clonedProject.code = project.code;
        clonedProject.description = project.description;

        for(let i of project.projectUsers){
          clonedProject.projectUsers.push(i);
        }
        for(let i of project.projectTasks){
          clonedProject.projectTasks.push(i);
        }
        return clonedProject;
      }
}