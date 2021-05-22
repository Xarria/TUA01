import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainPageComponent } from './main/main-page/main-page.component';
import { FerrytalesComponent } from './ferrytales/ferrytales/ferrytales.component';
import { AccountDetailsComponent } from './ferrytales/account-details/account-details.component';
import { UsersTableComponent } from './ferrytales/users-table/users-table.component';
import { EditUserComponent } from './ferrytales/edit-user/edit-user.component';
import { ConfirmAccountComponent } from './other-views/confirm-account/confirm-account.component';
import { ConfirmEmailChangeComponent } from './other-views/confirm-email-change/confirm-email-change.component';
import { ForbiddenComponent } from './other-views/error-pages/forbidden/forbidden.component';
import { InternalServerErrorComponent } from './other-views/error-pages/internal-server-error/internal-server-error.component';
import { NotFoundComponent } from './other-views/error-pages/not-found/not-found.component';

const ferrytalesChildren: Routes = [
    { path: 'accounts', component: UsersTableComponent },
    { path: 'accounts/:login', component: AccountDetailsComponent },
    { path: 'accounts/edit/:login', component: EditUserComponent }
];

const routes: Routes = [
    { path: '', component: MainPageComponent },
    { path: 'ferrytales', component: FerrytalesComponent, children: ferrytalesChildren },
    { path: 'confirm/account/:url', component: ConfirmAccountComponent },
    { path: 'confirm/email/:url', component: ConfirmEmailChangeComponent },
    { path: 'error/forbidden', component: ForbiddenComponent },
    { path: 'error/internal', component: InternalServerErrorComponent },
    { path: 'error/forbidden', component: ForbiddenComponent },
    { path: 'error/notfound', component: NotFoundComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes, { useHash: true })],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
