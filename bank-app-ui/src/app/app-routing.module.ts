import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {ContactComponent} from './components/contact/contact.component';
import {LoginComponent} from './components/login/login.component';
import {DashboardComponent} from './components/dashboard/dashboard.component';
import {LogoutComponent} from './components/logout/logout.component';
import {AccountComponent} from '../app/components/account/account.component';
import {BalanceComponent} from '../app/components/balance/balance.component';
import {NoticesComponent} from './components/notices/notices.component';
import {LoansComponent} from './components/loans/loans.component';
import {CardsComponent} from './components/cards/cards.component';
import {AuthActivateRouteGuard} from './routeguards/auth.routeguard';
import {HomeComponent} from './components/home/home.component';
import {AuthCallBackComponent} from "./components/auth-call-back/auth-call-back.component";

const routes: Routes = [
    {path: '', component: HomeComponent},
    {path: 'home', component: HomeComponent},
    {path: 'login', component: LoginComponent},
    {path: 'contact', component: ContactComponent},
    {path: 'notices', component: NoticesComponent},
    {path: 'dashboard', component: DashboardComponent, canActivate: [AuthActivateRouteGuard], data: {}},
    {path: 'logout', component: LogoutComponent},
    {path: 'myAccount', component: AccountComponent, canActivate: [AuthActivateRouteGuard], data: {roles: ['USER']}},
    {
        path: 'myBalance',
        component: BalanceComponent,
        canActivate: [AuthActivateRouteGuard],
        data: {roles: ['USER', 'ADMIN']}
    },
    {path: 'myLoans', component: LoansComponent, canActivate: [AuthActivateRouteGuard], data: {}},
    {path: 'myCards', component: CardsComponent, canActivate: [AuthActivateRouteGuard], data: {roles: ['USER']}},
    {path: 'authCallback', component: AuthCallBackComponent},
    {path: '**', redirectTo: 'dashboard', pathMatch: "full"},
];

@NgModule({
    imports: [RouterModule.forRoot(routes, {useHash: false})],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
