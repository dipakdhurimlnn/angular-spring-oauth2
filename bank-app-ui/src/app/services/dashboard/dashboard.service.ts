import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AppConstants} from "../../constants/app.constants";
import {environment} from '../../../environments/environment';
import {User} from '../../model/user.model';
import {Contact} from '../../model/contact.model';

@Injectable({
    providedIn: 'root'
})
export class DashboardService {

    constructor(private http: HttpClient) {
    }

    getAuthorizationCode(url: string) {
        return this.http.get(environment.rooturl + AppConstants.AUTHORIZE_URL + url);
    }

    getAccountDetails(email: number) {
        return this.http.get(environment.rooturl + AppConstants.ACCOUNT_API_URL + "?id=" + email);
    }

    getAccountTransactions(email: number) {
        return this.http.get(environment.rooturl + AppConstants.BALANCE_API_URL + "?id=" + email);
    }

    getLoansDetails(email: number) {
        return this.http.get(environment.rooturl + AppConstants.LOANS_API_URL + "?id=" + email);
    }

    getCardsDetails(email: number) {
        return this.http.get(environment.rooturl + AppConstants.CARDS_API_URL + "?id=" + email);
    }

    getNoticeDetails() {
        return this.http.get(environment.rooturl + AppConstants.NOTICES_API_URL);
    }

    saveMessage(contact: Contact) {
        let contacts = [];
        contacts.push(contact);
        return this.http.post(environment.rooturl + AppConstants.CONTACT_API_URL, contacts);
    }

}
