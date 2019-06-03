import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NotFoundComponent } from 'src/app/modules/not-found/not-found.component';
import { LoginComponent } from './modules/login/login.component';
import { RegistrationComponent } from './modules/registration/registration.component';
import { ErrorComponent } from './modules/error/error.component';
import { TaskFullComponent } from './modules/task/task-full/task-full.component';
import { UserBasicComponent } from './modules/user/user-basic/user-basic.component';
import { CreateProjectComponent } from './modules/create-project/create-project.component';
import { ProjectBasicComponent } from './modules/project/project-basic/project-basic.component';
import { TasksTableComponent } from './modules/tasks-table/tasks-table.component';
import { AboutComponent } from './modules/about/about.component';
import { TasksComponent } from './modules/task/tasks/tasks.component';
import { CreateTaskComponent } from './modules/create-task/create-task.component';

const routes: Routes = [
  {path:'', component:LoginComponent},
  //{path:'about',component:AboutComponent},
  {path:'createTask',component:CreateTaskComponent},
  {path:'projects/:idProject',component:ProjectBasicComponent},
  {path:'createProject',component:CreateProjectComponent},
  {path:'users/:idUser', component:UserBasicComponent},
  {path:'tasks/:idTask', component:TaskFullComponent},
  {path:'tasks',component:TasksComponent},
  {path:'login', component: LoginComponent},
  {path:'registration', component: RegistrationComponent},
  {path:'error/:error',component: ErrorComponent},
  {path:'notFound',component: NotFoundComponent},
  {path:'**',component: NotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
