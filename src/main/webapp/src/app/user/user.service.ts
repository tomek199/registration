import { Injectable } from '@angular/core';
import {HttpClient } from "@angular/common/http";
import {User} from "./User";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  public create(user: User) {
    let url = `${environment.apiUrl}/users`;
    return this.http.post<User>(url, user)
      .pipe()
      .toPromise()
      .then(response => response as User)
  }
}
