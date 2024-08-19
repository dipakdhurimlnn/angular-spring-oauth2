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
    
    }
}
