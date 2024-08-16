import {Injectable} from '@angular/core';
import {HttpInterceptor, HttpRequest, HttpHandler, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import {tap} from 'rxjs/operators';
import {User} from 'src/app/model/user.model';

import {throwError as observableThrowError} from "rxjs";

import {catchError} from "rxjs/operators";

@Injectable()
export class XhrInterceptor implements HttpInterceptor {

    user = new User();

    constructor(private router: Router) {
    }

    intercept(req: HttpRequest<any>, next: HttpHandler) {
        // if (req.url.indexOf("oauth2/") < 0) {
        let httpHeaders = new HttpHeaders();
        if (sessionStorage.getItem('userdetails')) {
            this.user = JSON.parse(sessionStorage.getItem('userdetails')!);
        }
        if (this.user && this.user.password && this.user.email) {
            console.log(this.user)
            // httpHeaders = httpHeaders.append('Authorization', window.btoa(this.user.email + ':' + this.user.password));
            console.log(window.btoa(this.user.email + ':' + this.user.password))
        } else {
            let authorization = sessionStorage.getItem('Authorization');
            if (authorization) {
                httpHeaders = httpHeaders.append('Authorization', authorization);
            }
        }
        let xsrf = sessionStorage.getItem("XSRF-TOKEN");
        if (xsrf) {
            httpHeaders = httpHeaders.append('X-XSRF-TOKEN', xsrf);
        }

        httpHeaders = httpHeaders.append('X-Requested-With', 'XMLHttpRequest');
        const xhr = req.clone({
            headers: httpHeaders
        });
        return next.handle(xhr).pipe(tap(
            (err: any) => {
                if (err instanceof HttpErrorResponse) {
                    if (err.status !== 401) {
                        return;
                    }
                    this.router.navigate(['dashboard']);
                }
            }));
        // }
        //
        // return next.handle(req).pipe(catchError((error, caught) => {
        //     return observableThrowError(error);
        // })) as any;

    }
}
