import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {User} from '../model/user.model';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {AppConstants} from "../constants/app.constants";
import {getCookie} from "typescript-cookie";

@Injectable()
export class AuthActivateRouteGuard implements CanActivate {
    user = new User();

    constructor(private router: Router, private http: HttpClient) {

    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        if (sessionStorage.getItem('userdetails')) {
            this.user = JSON.parse(sessionStorage.getItem('userdetails')!);
        } else if (sessionStorage.getItem('accessToken')) {
            this.http.get<User>(environment.rooturl + AppConstants.CURRENT_USER_API_URL).subscribe(res => {
                if (res) {
                    res.authStatus = 'AUTH';
                    sessionStorage.setItem('userdetails', JSON.stringify(res));
                    // let xsrf = getCookie('XSRF-TOKEN')!;
                    // window.sessionStorage.setItem("XSRF-TOKEN", xsrf);
                } else {
                    this.router.navigate(['login']);
                }
            })
        }
        return this.user ? true : false;
    }

}
