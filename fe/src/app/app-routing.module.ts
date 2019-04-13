import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from 'src/app/modules/home/home.component';
import { NotFoundComponent } from 'src/app/modules/not-found/not-found.component';
import { LoginComponent } from './modules/login/login.component';
import { RegistrationComponent } from './modules/registration/registration.component';
import { TestComponent } from './modules/test/test.component';
import { ErrorComponent } from './modules/error/error.component';
import { UserFullComponent } from './modules/user/user-full/user-full.component';
import { TaskFullComponent } from './modules/task/task-full/task-full.component';
import { UserBasicComponent } from './modules/user/user-basic/user-basic.component';
import { TableTasksAvailableComponent } from './modules/table-tasks-available/table-tasks-available.component';
import { CreateProjectComponent } from './modules/create-project/create-project.component';

const routes: Routes = [
  {path:'', component: HomeComponent},
  {path:'createProject',component:CreateProjectComponent},
  {path:'users/:idUser', component:UserBasicComponent},
  {path:'tasks/:idTask', component:TaskFullComponent},
  {path:'availableTasks',component:TableTasksAvailableComponent},
  {path:'test',component:TestComponent},
  {path:'login', component: LoginComponent},
  {path:'registration', component: RegistrationComponent},
  {path:'error/:error',component: ErrorComponent},
  {path:'**',component: NotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
