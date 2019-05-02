import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { AuthService } from './auth-service.service';
import { Observable } from 'rxjs';
import {CommentForCreating} from 'src/app/DTOs/CommentMain/CommentMain';
import {Comment} from 'src/app/DTOs/TaskMain/TaskMain';
@Injectable({
  providedIn: 'root'
})
export class CommentServiceService {

  constructor(private http:HttpClient, private auth:AuthService) { }
  createComment(comment:CommentForCreating):Observable<Comment>{
    return this.http.post<Comment>('api/comments',comment);
  }
}
