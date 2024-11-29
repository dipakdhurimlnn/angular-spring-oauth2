import {Component, OnInit} from '@angular/core';
import {User} from "src/app/model/user.model";


@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
    model = new User();

    constructor() {

    }

    ngOnInit(): void {
        this.validateUser();
    }

    validateUser() {
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


}
