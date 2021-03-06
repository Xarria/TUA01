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
import { BookingTableComponent } from './ferrytales/booking-table/booking-table.component';
import { SeaportsTableComponent } from './ferrytales/seaports-table/seaports-table.component';
import { SeaportDetailsComponent } from './ferrytales/seaport-details/seaport-details.component';
import { FerriesTableComponent } from './ferrytales/ferries-table/ferries-table.component';
import { RoutesTableComponent } from './ferrytales/routes-table/routes-table.component';
import { BookingFormComponent } from './ferrytales/booking-form/booking-form.component';
import { CruiseDetailsComponent } from './ferrytales/cruise-details/cruise-details.component';
import { FerryDetailsComponent } from './ferrytales/ferry-details/ferry-details.component';
import { BookingDetailsComponent } from './ferrytales/booking-details/booking-details.component';
import { CurrentCruisesTableComponent } from './ferrytales/current-cruises-table/current-cruises-table.component';
import { AddSeaportComponent } from './ferrytales/add-seaport/add-seaport.component';
import { EditCabinComponent } from './ferrytales/edit-cabin/edit-cabin.component';
import { RouteDetailsComponent } from './ferrytales/route-details/route-details.component';
import { BookingsTableOwnComponent } from './ferrytales/bookings-table-own/bookings-table-own.component';
import { AddCabinComponent } from './ferrytales/add-cabin/add-cabin.component';
import { CabinDetailsComponent } from './ferrytales/cabin-details/cabin-details.component';
import { AddCruiseComponent } from './ferrytales/add-cruise/add-cruise.component';
import { AddFerryComponent } from './ferrytales/add-ferry/add-ferry.component';
import { AddRouteComponent } from './ferrytales/add-route/add-route.component';
import { GoneComponent } from './other-views/error-pages/gone/gone.component';

const ferrytalesChildren: Routes = [
    { path: 'accounts', component: UsersTableComponent },
    { path: 'accounts/:login', component: AccountDetailsComponent },
    { path: 'accounts/edit/:login', component: EditUserComponent },
    { path: 'bookings', component: BookingTableComponent },
    { path: 'bookings/:number', component: BookingDetailsComponent },
    { path: 'bookings/own/:number', component: BookingDetailsComponent },
    { path: 'booking/create/:number', component: BookingFormComponent },
    { path: 'my/bookings', component: BookingsTableOwnComponent },
    { path: 'routes', component: RoutesTableComponent },
    { path: 'routes/add', component: AddRouteComponent },
    { path: 'routes/:code', component: RouteDetailsComponent },
    { path: 'routes/:code/cruise/add', component: AddCruiseComponent },
    { path: 'cruises/:number', component: CruiseDetailsComponent },
    { path: 'ferries', component: FerriesTableComponent },
    { path: 'ferries/add', component: AddFerryComponent },
    { path: 'ferries/edit/:ferry/:cabin', component: EditCabinComponent },
    { path: 'ferries/:name', component: FerryDetailsComponent },
    { path: 'ferries/:name/addCabin', component: AddCabinComponent },
    { path: 'ferries/:ferry/:cabin', component: CabinDetailsComponent },
    { path: 'cabin/edit', component: EditCabinComponent },
    { path: 'current', component: CurrentCruisesTableComponent },
    { path: 'cruises/:number', component: CruiseDetailsComponent },
    { path: 'seaports', component: SeaportsTableComponent },
    { path: 'seaports/add', component: AddSeaportComponent },
    { path: 'seaports/:code', component: SeaportDetailsComponent },
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
    { path: 'error/gone', component: GoneComponent },
    { path: '**', component: NotFoundComponent },
];

@NgModule({
    imports: [RouterModule.forRoot(routes, { useHash: true })],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
