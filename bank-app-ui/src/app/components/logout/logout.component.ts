import {Component, OnInit} from '@angular/core';
import {LoginService} from 'src/app/services/login/login.service';
import {Router} from '@angular/router';
import {User} from 'src/app/model/user.model';
import {HttpClient} from '@angular/common/http';
import {TokenOb} from "../../model/token.model";
import {environment} from "../../../environments/environment";
import {AppConstants} from "../../constants/app.constants";

@Component({
    selector: 'app-logout',
    templateUrl: './logout.component.html',
    styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

    user = new User();

    constructor(private router: Router, private http: HttpClient) {

    }

    ngOnInit(): void {

        const payloadOb = new FormData();
        payloadOb.append("token", window.sessionStorage.getItem("accessToken") || "");
        // payloadOb.append("token_type_hint", "access_token");
        this.http.post(environment.rooturl + AppConstants.TOKEN_REVOKE_URL, payloadOb).subscribe(
            res => {
                console.log("logout success");
                window.sessionStorage.removeItem("accessToken");
                window.sessionStorage.removeItem("userdetails");
                window.location.href = environment.rooturl + '/logout';
            }
        )

    }


}
