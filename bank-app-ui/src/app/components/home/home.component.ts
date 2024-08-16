import {Component, OnInit} from '@angular/core';
import {environment} from "../../../environments/environment";
import {AppConstants} from "../../constants/app.constants";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

    constructor(private route: ActivatedRoute, private router: Router, private http: HttpClient) {
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
                this.http.post(environment.rooturl + AppConstants.TOKEN_URL, payloadOb).subscribe(
                    res => {
                        if (res) {
                            console.log(res);
                            this.router.navigate(['/dashboard']);
                        }
                    }
                )
            }
        });

    }

}
