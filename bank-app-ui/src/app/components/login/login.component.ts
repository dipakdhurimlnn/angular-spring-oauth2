import {Component, OnInit} from '@angular/core';
import {User} from "src/app/model/user.model";
import {NgForm} from '@angular/forms';
import {LoginService} from 'src/app/services/login/login.service';
import {ActivatedRoute, Router} from '@angular/router';
import {getCookie} from "typescript-cookie";
import {environment} from "../../../environments/environment";
import {AppConstants} from "../../constants/app.constants";
import {HttpClient} from "@angular/common/http";
import {TokenOb} from "../../model/token.model";


@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
    authStatus: string = "";
    model = new User();

    constructor(private route: ActivatedRoute, private loginService: LoginService, private router: Router, private http: HttpClient) {

    }

    ngOnInit(): void {
        this.validateUser();
        console.log("page loaded")
        // this.route.queryParams.subscribe(params => {
        //     console.log('Query Params:', params);
        //     if (params['code']) {
        //
        //         const grant_type = "authorization_code";
        //         const client_id = "oidc-client"
        //         const CODE_VERIFIER = "KImxEAikOHgWrAGTgbF3YXnAZ3RBy_1Oijcenvpi3Z_oL2Kfk0vxIhKhmxSZW4IHQhTyB7Rh1_07E1u6RJFw_2G41f9NyP4mMR4BRAhRgBKRDuYbXIIYTwkfoZs_YfDL";
        //         const payloadOb = new FormData();
        //         payloadOb.append("code", params['code']);
        //         payloadOb.append("grant_type", grant_type);
        //         payloadOb.append("client_id", client_id);
        //         payloadOb.append("code_verifier", CODE_VERIFIER);
        //         this.http.post<TokenOb>(environment.rooturl + AppConstants.TOKEN_URL, payloadOb).subscribe(
        //             res => {
        //                 if (res) {
        //                     console.log(res.access_token);
        //                     if (res.access_token) {
        //                         window.sessionStorage.setItem("accessToken", res['access_token']);
        //                         this.router.navigate(['/dashboard']);
        //                     }
        //                 }
        //             }
        //         )
        //     }
        // });
    }

    validateUser(loginForm?: NgForm) {
        this.loginService.validateLoginDetails(this.model).subscribe(res => {

        });

        // .subscribe(
        // responseData => {
        //     window.sessionStorage.setItem("Authorization", responseData.headers.get('Authorization')!);
        //
        //     this.model = <any>responseData.body;
        //     this.model.authStatus = 'AUTH';
        //     window.sessionStorage.setItem("userdetails", JSON.stringify(this.model));
        //
        //     let xsrf = getCookie('XSRF-TOKEN')!;
        //     window.sessionStorage.setItem("XSRF-TOKEN", xsrf);
        //     this.router.navigate(['dashboard']);
        // });

    }

}
