import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from "src/app/model/user.model";
import {Observable, of} from "rxjs";
import {Location} from "@angular/common";
import {environment} from "../../../environments/environment";
import {AppConstants} from "../../constants/app.constants";

@Injectable({
    providedIn: 'root'
})
export class LoginService {

    constructor(private http: HttpClient) {

    }

    validateLoginDetails(user: User): Observable<User> {
        return this.http.post<User>(environment.rooturl + AppConstants.LOGIN_URL, user);
    }

}
