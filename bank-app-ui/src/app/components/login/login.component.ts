import {Component, OnInit} from '@angular/core';
import {User} from "src/app/model/user.model";
import {LoginService} from "../../services/login/login.service";


@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
    model = new User();

    constructor(public loginService: LoginService) {

    }

    ngOnInit(): void {
        this.validateUser();
    }

    validateUser() {
        if (!sessionStorage.getItem('code_challenge') || !sessionStorage.getItem('code_verifier')) {
            this.loginService.generatePkcePair();
        }
        const response_type = "code";
        const client_id = "oidc-client"
        const code_challenge = this.loginService.getCodeChallenge();
        const code_challenge_method = this.loginService.getCodeChallengeMethod();
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
