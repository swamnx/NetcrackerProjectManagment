export class User{
    idUser:number;
    name:string;
    role:string;
    email:string;
    userProjects:Array<Project>
}
export class UserWithPassword{
    password:string;
    name:string;
    role:string;
    email:string;
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
