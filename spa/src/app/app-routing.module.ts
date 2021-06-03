import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainPageComponent } from './main/main-page/main-page.component';
import { FerrytalesComponent } from './ferrytales/ferrytales/ferrytales.component';
import { AccountDetailsComponent } from './ferrytales/account-details/account-details.component';
import { UsersTableComponent } from './ferrytales/users-table/users-table.component';
import { EditUserComponent } from './ferrytales/edit-user/edit-user.component';
import { ConfirmAccountComponent } from './other-views/confirm-account/confirm-account.component';
import { ConfirmEmailChangeComponent } from './other-views/confirm-email-change/confirm-email-change.component';
import { NewPasswordComponent } from './other-views/new-password/new-password.component';
import { ForbiddenComponent } from './other-views/error-pages/forbidden/forbidden.component';
import { InternalServerErrorComponent } from './other-views/error-pages/internal-server-error/internal-server-error.component';
import { NotFoundComponent } from './other-views/error-pages/not-found/not-found.component';
import { UnauthorizedComponent } from './other-views/error-pages/unauthorized/unauthorized.component';
import { SeaportDetailsComponent } from './ferrytales/seaport-details/seaport-details.component';

const ferrytalesChildren: Routes = [
    { path: 'accounts', component: UsersTableComponent },
    { path: 'accounts/:login', component: AccountDetailsComponent },
    { path: 'accounts/edit/:login', component: EditUserComponent },
    { path: 'seaport/:code', component: SeaportDetailsComponent },
];

const routes: Routes = [
    { path: '', component: MainPageComponent },
    { path: 'ferrytales', component: FerrytalesComponent, children: ferrytalesChildren },
    { path: 'confirm/account/:url', component: ConfirmAccountComponent },
    { path: 'confirm/email/:url', component: ConfirmEmailChangeComponent },
    { path: 'reset/password/:url', component: NewPasswordComponent },
    { path: 'error/internal', component: InternalServerErrorComponent },
    { path: 'error/forbidden', component: ForbiddenComponent },
    { path: 'error/notfound', component: NotFoundComponent },
    { path: 'error/unauthorized', component: UnauthorizedComponent },
    { path: '**', component: NotFoundComponent },
];

@NgModule({
    imports: [RouterModule.forRoot(routes, { useHash: true })],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
