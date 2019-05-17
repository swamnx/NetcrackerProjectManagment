import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { AuthService } from 'src/app/services/auth-service.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { TaskServiceService } from 'src/app/services/task-service.service';
import { TaskForTable } from 'src/app/DTOs/TaskMain/TaskMain';
import { BehaviorSubject, Observable, of, merge, fromEvent, timer } from 'rxjs';
import { CollectionViewer, DataSource } from '@angular/cdk/collections';
import { catchError, finalize, tap, debounceTime, distinctUntilChanged } from 'rxjs/operators';
import { MatPaginator, MatSort } from '@angular/material';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-tasks-table',
  templateUrl: './tasks-table.component.html',
  styleUrls: ['./tasks-table.component.css']
})
export class TasksTableComponent implements OnInit {

  dataSource: TasksDataSource;
  displayedColumns = ["projectCode", "code", "description", "status", "assignedTo", "createDate", "updateDate", "dueDate"];

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @Input() type: string;
  @Input() idProject: number;
  searchControl = new FormControl('');

  constructor(private auth: AuthService, private http: HttpClient, private router: Router, private taskService: TaskServiceService) {
    if (!auth.authenticated) this.auth.authenticateNot();
  }
  clickOnProjectCell(idProject) {
    this.router.navigateByUrl('/projects/' + idProject);
  }
  clickOnUserCell(idUser) {
    this.router.navigateByUrl('/users/' + idUser);
  }
  clickOnTaskCell(idTask) {
    this.router.navigateByUrl('/tasks/' + idTask);
  }
  ngOnInit() {
  }
  ngOnChanges() {
    this.dataSource = new TasksDataSource(this.taskService, this.paginator, this.router);
    this.dataSourceLoadTasks();
  }
  dataSourceLoadTasks() {
    this.dataSource.loadTasks(this.type, this.sort.active, this.sort.direction, this.paginator.pageIndex, this.paginator.pageSize, this.searchControl.value, this.idProject);
  }
  ngAfterViewInit() {
    this.searchControl.valueChanges.pipe(
      debounceTime(500),
      tap(
        () => {
          this.paginator.pageIndex = 0;
          this.dataSourceLoadTasks();
        }
      )
    ).subscribe();
    this.sort.sortChange.subscribe(() => this.paginator.pageIndex = 0);
    merge(this.sort.sortChange, this.paginator.page).
      pipe(tap(() => this.dataSourceLoadTasks())).subscribe();
  }

}
export class TasksDataSource implements DataSource<TaskForTable>{

  private tasksSubject = new BehaviorSubject<TaskForTable[]>([]);
  private loadingSubject = new BehaviorSubject<boolean>(false);
  public loading$ = this.loadingSubject.asObservable();

  constructor(private taskService: TaskServiceService, private paginator: MatPaginator, private router: Router) {

  }

  connect(collectionViewer: CollectionViewer): Observable<TaskForTable[]> {
    return this.tasksSubject.asObservable();
  }
  disconnect(collectionViewer: CollectionViewer): void {
    this.tasksSubject.complete();
    this.loadingSubject.complete();
  }
  loadTasks(type, fieldSort = 'code', directionSort = 'asc', page = 0, size = 3, search = '', idProject = -1) {
    this.loadingSubject.next(true);
    this.tasksSubject.next([]);
    timer(500).subscribe(
      val => {
        this.taskService.getPageOfTasksForUser(type, fieldSort, directionSort, page, size, search, idProject).pipe(
          catchError((error) => this.router.navigateByUrl('/error/' + error.status)),
          finalize(() => this.loadingSubject.next(false))
        ).subscribe(
          page => {
            this.paginator.length = page['totalElements'];
            this.tasksSubject.next(page['content'])
          }
        )
      }
    )
  }
}
