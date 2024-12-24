import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {User} from '../model/user.model';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {AppConstants} from "../constants/app.constants";
import {map, Observable, of} from "rxjs";
import {catchError} from "rxjs/operators";

@Injectable()
export class AuthActivateRouteGuard implements CanActivate {
    user?: User;

    constructor(private router: Router, private http: HttpClient) {

    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
        if (sessionStorage.getItem('userdetails')) {
            this.user = JSON.parse(sessionStorage.getItem('userdetails')!);
            return of(true);
        } else if (sessionStorage.getItem('accessToken')) {
            return this.getCurrentUser();
        } else {
            this.router.navigate(['login']);
            return of(false);
        }
    }

    getCurrentUser(): Observable<boolean> {
        return this.http.get<User>(`${environment.rooturl}${AppConstants.CURRENT_USER_API_URL}`).pipe(
            map(res => {
                if (res) {
                    res.authStatus = 'AUTH';
                    sessionStorage.setItem('userdetails', JSON.stringify(res));
                    return true;
                } else {
                    this.router.navigate(['login']);
                    return false;
                }
            }),
            catchError(error => {
                this.router.navigate(['login']);
                return of(false);
            })
        );
    }
}
