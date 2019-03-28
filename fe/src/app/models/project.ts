import {User} from 'src/app/models/user';
import {Task} from 'src/app/models/task'

export class Project{

    idProject:number;
    codeProject:string;
    descriptionProject:string;
    projectUsers:Array<User>;
    projectTasks:Array<Task>;

    static cloneBase(project: Project): Project {

        const clonedProject: Project = new Project();

        clonedProject.idProject = project.idProject;
        clonedProject.codeProject = project.codeProject;
        clonedProject.descriptionProject = project.descriptionProject;

        for(let i of project.projectUsers){
          clonedProject.projectUsers.push(i);
        }
        for(let i of project.projectTasks){
          clonedProject.projectTasks.push(i);
        }
        return clonedProject;
      }
}