import {Component, OnInit} from '@angular/core';
import {User} from "src/app/model/user.model";
import {NgForm} from '@angular/forms';
import {LoginService} from 'src/app/services/login/login.service';
import {ActivatedRoute, Router} from '@angular/router';
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
    model = new User();

    constructor(private route: ActivatedRoute, private loginService: LoginService, private router: Router, private http: HttpClient) {

    }

    ngOnInit(): void {
        this.route.queryParams.subscribe(params => {
            if (params['code']) {

                const grant_type = "authorization_code";
                const client_id = "oidc-client"
                const CODE_VERIFIER = "KImxEAikOHgWrAGTgbF3YXnAZ3RBy_1Oijcenvpi3Z_oL2Kfk0vxIhKhmxSZW4IHQhTyB7Rh1_07E1u6RJFw_2G41f9NyP4mMR4BRAhRgBKRDuYbXIIYTwkfoZs_YfDL";
                const payloadOb = new FormData();
                payloadOb.append("code", params['code']);
                payloadOb.append("grant_type", grant_type);
                payloadOb.append("client_id", client_id);
                payloadOb.append("code_verifier", CODE_VERIFIER);
                this.http.post<TokenOb>(environment.rooturl + AppConstants.TOKEN_URL, payloadOb).subscribe(
                    res => {
                        if (res) {
                            if (res.access_token) {
                                window.sessionStorage.setItem("accessToken", res['access_token']);
                                this.router.navigate(['/dashboard']);
                            }
                        }
                    }
                )
            }
        });
    }

    validateUser(loginForm?: NgForm) {
        this.loginService.validateLoginDetails(this.model).subscribe(res => {
            if (res) {
                res.authStatus = "AUTH";
                // window.sessionStorage.setItem("userdetails", JSON.stringify(res));
                const response_type = "code";
                const client_id = "oidc-client"
                const code_challenge = "tpv22FEqJbXNrge_mtAYpNP2gTTm7WF8cPrVI8gpNBY";
                const code_challenge_method = "S256"
                const authObject = {
                    response_type: response_type,
                    client_id: client_id,
                    code_challenge: code_challenge,
                    code_challenge_method: code_challenge_method
                }
                const authUrl = `http://localhost:8080/oauth2/authorize?response_type=code&client_id=${client_id}&code_challenge=${code_challenge}&code_challenge_method=S256`;
                window.location.href = authUrl;
            }
        }, err => {
            window.alert("Invalid username or password!")
        });

    }

}
