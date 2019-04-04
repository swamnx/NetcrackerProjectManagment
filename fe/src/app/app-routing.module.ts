import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from 'src/app/modules/home/home.component';
import { NotFoundComponent } from 'src/app/modules/not-found/not-found.component';
import { LoginComponent } from './modules/login/login.component';
import { RegistrationComponent } from './modules/registration/registration.component';
import { TestComponent } from './modules/test/test.component';
import { TableTasksComponent } from './modules/table-tasks/table-tasks.component';
import { ErrorComponent } from './modules/error/error.component';
import { UserFullComponent } from './modules/user/user-full/user-full.component';

const routes: Routes = [
  {path:'', component: HomeComponent},
  {path:'users/:idUser', component:UserFullComponent},
  {path:'table',component:TableTasksComponent},
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
