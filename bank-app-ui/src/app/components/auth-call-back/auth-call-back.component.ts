import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpClient} from "@angular/common/http";
import {AppConstants} from "../../constants/app.constants";
import {environment} from "../../../environments/environment";
import {TokenOb} from "../../model/token.model";

@Component({
    selector: 'app-auth-call-back',
    templateUrl: './auth-call-back.component.html',
    styleUrls: ['./auth-call-back.component.css']
})
export class AuthCallBackComponent implements OnInit {
    constructor(private route: ActivatedRoute, private router: Router, private http: HttpClient) {
    }

    ngOnInit(): void {
        this.route.queryParams.subscribe(params => {
            console.log(params)
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

}
