import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import {Routes} from "@angular/router";

import { AppComponent } from './app.component';
import { MyheaderComponent } from 'src/app/modules/myheader/myheader.component';
import { AboutComponent } from './modules/about/about.component';
import { NotFoundComponent } from 'src/app/modules/not-found/not-found.component';
import { LoginComponent } from './modules/login/login.component';
import { RegistrationComponent } from './modules/registration/registration.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatCheckboxModule, MatNativeDateModule} from '@angular/material';
import {MatButtonModule} from '@angular/material';
import {MatInputModule} from '@angular/material/input';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatRadioModule} from '@angular/material/radio';
import {MatSelectModule} from '@angular/material/select';
import {MatSliderModule} from '@angular/material/slider';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {MatMenuModule} from '@angular/material/menu';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatListModule} from '@angular/material/list';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatCardModule} from '@angular/material/card';
import {MatStepperModule} from '@angular/material/stepper';
import {MatTabsModule} from '@angular/material/tabs';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatChipsModule} from '@angular/material/chips';
import {MatIconModule} from '@angular/material/icon';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatDialogModule} from '@angular/material/dialog';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatTableModule} from '@angular/material/table';
import {MatSortModule} from '@angular/material/sort';
import {MatPaginatorModule} from '@angular/material/paginator';
import { UserBasicComponent } from './modules/user/user-basic/user-basic.component';
import { ErrorComponent } from './modules/error/error.component';
import { ProjectBasicComponent, AddUserDialog } from './modules/project/project-basic/project-basic.component';
import { TaskFullComponent, AssignDialog, EditDialog } from './modules/task/task-full/task-full.component';
import { CreateProjectComponent } from './modules/create-project/create-project.component';
import { CreateTaskComponent } from './modules/create-task/create-task.component';
import { AuthInterceptor } from './services/auth-interceptor.service';
import { TasksTableComponent } from './modules/tasks-table/tasks-table.component';
import { TasksComponent } from './modules/task/tasks/tasks.component';
import { SpinnerOverlayComponent, DialogSpinnerComponent } from './modules/spinner-overlay/spinner-overlay.component';
import { FooterComponent } from './modules/footer/footer.component';
@NgModule({
  declarations: [
    AssignDialog,
    AddUserDialog,
    EditDialog,
    DialogSpinnerComponent,
    AppComponent,
    MyheaderComponent,
    AboutComponent,
    NotFoundComponent,
    LoginComponent,
    RegistrationComponent,
    UserBasicComponent,
    ErrorComponent,
    ProjectBasicComponent,
    TaskFullComponent,
    CreateProjectComponent,
    CreateTaskComponent,
    TasksTableComponent,
    TasksComponent,
    SpinnerOverlayComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    MatCheckboxModule,
    MatButtonModule,
    MatInputModule,
    MatNativeDateModule,
    MatAutocompleteModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatRadioModule,
    MatSelectModule,
    MatSliderModule,
    MatSlideToggleModule,
    MatMenuModule,
    MatSidenavModule,
    MatToolbarModule,
    MatListModule,
    MatGridListModule,
    MatCardModule,
    MatStepperModule,
    MatTabsModule,
    MatExpansionModule,
    MatButtonToggleModule,
    MatChipsModule,
    MatIconModule,
    MatProgressSpinnerModule,
    MatProgressBarModule,
    MatDialogModule,
    MatTooltipModule,
    MatSnackBarModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule
  ],
  entryComponents:[AssignDialog,AddUserDialog,EditDialog,DialogSpinnerComponent],
  providers: [{provide:HTTP_INTERCEPTORS,useClass:AuthInterceptor,multi:true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
