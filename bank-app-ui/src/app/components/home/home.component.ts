import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {TokenOb} from "../../model/token.model";
import {environment} from "../../../environments/environment";
import {AppConstants} from "../../constants/app.constants";
import {LoginService} from "../../services/login/login.service";

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

    constructor(private route: ActivatedRoute, private router: Router, private http: HttpClient, public loginService: LoginService) {
    }

    ngOnInit(): void {
        this.route.queryParams.subscribe(params => {
            console.log(params)
            if (params['code']) {

                const grant_type = "authorization_code";
                const client_id = "oidc-client"
                const CODE_VERIFIER = this.loginService.getCodeVerifier();
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
                                sessionStorage.removeItem('code_challenge');
                                sessionStorage.removeItem('code_verifier');
                                this.router.navigate(['/dashboard']);
                            }
                        }
                    }
                )
            }
        });
    }

}
