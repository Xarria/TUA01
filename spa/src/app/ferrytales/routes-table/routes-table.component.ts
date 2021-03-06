import { Component, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { RouteGeneralService } from '../../services/mop/route-general.service';
import { RouteGeneral } from '../../model/mop/RouteGeneral';
import { ErrorHandlerService } from '../../services/error-handlers/error-handler.service';

@Component({
    selector: 'app-routes-table',
    templateUrl: './routes-table.component.html',
    styleUrls: ['./routes-table.component.less']
})
export class RoutesTableComponent implements OnDestroy {

    result = 'hidden';
    isConfirmationVisible = false;
    routeCode = '';

    routes: RouteGeneral[] = [];

    constructor(private router: Router,
                public routeGeneralService: RouteGeneralService,
                private errorHandlerService: ErrorHandlerService) {
        this.getRoutes();
    }

    goToHomepageBreadcrumb(): void {
        this.routeGeneralService.popup = 'hidden';
        this.router.navigate(['/']);
    }

    getRoutes(): void {
        this.routeGeneralService.getAllRoutes().subscribe((response: RouteGeneral[]) => {
            this.routes = response;
        });
    }

    addRoute(): void {
        this.routeGeneralService.popup = 'hidden';
        this.router.navigate(['/ferrytales/routes/add']);
    }

    refresh(): void {
        this.routeGeneralService.popup = 'hidden';
        this.getRoutes();
        this.result = 'hidden';
    }

    removeRouteClick(routeCode: string): void {
        this.routeGeneralService.popup = 'hidden';
        this.isConfirmationVisible = true;
        this.routeCode = routeCode;
    }

    removeRoute(code: string): void {
        this.result = 'hidden';
        this.routeGeneralService.removeRoute(code).subscribe(
            () => {
                this.result = 'success';
                this.getRoutes();
            },
            (error: any) => {
                if (error.status === 409) {
                    this.result = 'failure';
                } else if (error.status === 410){
                    this.result = 'gone';
                } else {
                    this.errorHandlerService.handleError(error);
                }
                this.getRoutes();
            });
    }

    ngOnDestroy(): void {
        this.routes = [];
    }

    confirmationResult(confirmationResult: boolean): void {
        this.isConfirmationVisible = false;
        if (confirmationResult) {
            this.removeRoute(this.routeCode);
        }
    }
}
