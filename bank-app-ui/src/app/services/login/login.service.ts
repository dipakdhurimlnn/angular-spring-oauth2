import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from "src/app/model/user.model";
import {of} from "rxjs";
import {Location} from "@angular/common";
import {environment} from "../../../environments/environment";
import {AppConstants} from "../../constants/app.constants";

@Injectable({
    providedIn: 'root'
})
export class LoginService {

    constructor(private http: HttpClient) {

    }

    validateLoginDetails(user: User) {
        console.log(user)
        // window.sessionStorage.setItem("userdetails", JSON.stringify(user));
        const response_type = "code";
        const client_id = "oidc-client"
        const code_challenge = "tpv22FEqJbXNrge_mtAYpNP2gTTm7WF8cPrVI8gpNBY";
        const code_challenge_method = "S256"
        // const credentials = window.btoa(user.email + ':' + user.pwd)
        const authObject = {
            response_type: response_type,
            client_id: client_id,
            code_challenge: code_challenge,
            code_challenge_method: code_challenge_method
        }
        const authUrl = `http://localhost:8080/oauth2/authorize?response_type=code&client_id=${client_id}&code_challenge=${code_challenge}&code_challenge_method=S256`;
        // // http://localhost:8080/oauth2/authorize?response_type=code&client_id=oidc-client&code_challenge=tpv22FEqJbXNrge_mtAYpNP2gTTm7WF8cPrVI8gpNBY&code_challenge_method=S256`;
        window.location.href = authUrl;
        // return this.http.get(environment.rooturl + AppConstants.AUTHORIZE_URL + `?response_type=code&client_id=${client_id}&code_challenge=${code_challenge}&code_challenge_method=S256`);
        return of(true);
    }

}
