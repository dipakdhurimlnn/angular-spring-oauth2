import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpClient} from "@angular/common/http";
import {AppConstants} from "../../constants/app.constants";
import {environment} from "../../../environments/environment";

@Component({
    selector: 'app-auth-call-back',
    templateUrl: './auth-call-back.component.html',
    styleUrls: ['./auth-call-back.component.css']
})
export class AuthCallBackComponent implements OnInit {
    constructor(private route: ActivatedRoute, private router: Router, private http: HttpClient) {
    }

    ngOnInit(): void {
        // Capture query parameters or fragment from the URL
        this.route.queryParams.subscribe(params => {
            console.log('Query Params:', params);
            // Handle query params here, e.g., store tokens, navigate to a different page, etc.
            if (params['code']) {

                const grant_type = "authorization_code";
                const client_id = "oidc-client"
                const CODE_VERIFIER = "KImxEAikOHgWrAGTgbF3YXnAZ3RBy_1Oijcenvpi3Z_oL2Kfk0vxIhKhmxSZW4IHQhTyB7Rh1_07E1u6RJFw_2G41f9NyP4mMR4BRAhRgBKRDuYbXIIYTwkfoZs_YfDL";
                const code_challenge_method = "S256"
                const payloadOb = new FormData();
                payloadOb.append("code", params['code']);
                payloadOb.append("grant_type", grant_type);
                payloadOb.append("client_id", client_id);
                payloadOb.append("code_verifier", CODE_VERIFIER);
                this.http.post(environment.rooturl + AppConstants.TOKEN_URL, payloadOb).subscribe(
                    res => {
                        if (res) {
                            console.log(res);
                            this.router.navigate(['/home']);
                        }
                    }
                )
            }
        });

        // // Alternatively, capture URL fragment (if OAuth provider uses fragment instead of query params)
        // this.route.fragment.subscribe(fragment => {
        //     console.log('URL Fragment:', fragment);
        //     // Handle fragment here
        // });
    }
}
