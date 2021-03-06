import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { FerryDetailsService } from '../../services/mop/ferry-details.service';
import { CabinGeneralService } from '../../services/mop/cabin-general.service';
import { CabinGeneral } from '../../model/mop/CabinGeneral';
import { ErrorHandlerService } from '../../services/error-handlers/error-handler.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
    selector: 'app-add-cabin',
    templateUrl: './add-cabin.component.html',
    styleUrls: ['./add-cabin.component.less']
})
export class AddCabinComponent implements OnInit {

    name = '';
    error = false;

    constructor(private router: Router,
                private ferryDetailsService: FerryDetailsService,
                private cabinGeneralService: CabinGeneralService,
                private errorHandlerService: ErrorHandlerService) {
        this.name = ferryDetailsService.ferry.name;
    }

    form = new FormGroup({
        capacity: new FormControl('', [Validators.required, Validators.min(1), Validators.max(99)]),
        number: new FormControl('', [Validators.required, Validators.pattern('[A-Z][0-9]{3}')]),
    });

    ngOnInit(): void {
    }

    goToHomeBreadcrumb(): void {
        this.router.navigate(['/']);
    }

    goToFerriesListBreadcrumb(): void {
        this.router.navigate(['/ferrytales/ferries']);
    }

    goToFerryBreadcrumb(): void {
        this.router.navigate(['/ferrytales/ferries/' + this.name]);
    }

    addCabin(capacity: string, cabinType: string, number: string): any {
        switch (cabinType) {
            case 'Pierwsza klasa':
                cabinType = 'First class';
                break;
            case 'Druga klasa':
                cabinType = 'Second class';
                break;
            case 'Trzecia klasa':
                cabinType = 'Third class';
                break;
            case 'Klasa inwalidzka':
                cabinType = 'Disabled class';
                break;
        }
        const cabin: CabinGeneral = {
            capacity,
            cabinType,
            number
        };
        this.ferryDetailsService.popup = 'hidden';
        this.cabinGeneralService.addCabin(cabin, this.name).subscribe(
            () => {
                this.goToFerryBreadcrumb();
                this.ferryDetailsService.popup = 'add_cabin_success';
                setTimeout(() => this.ferryDetailsService.popup = 'hidden', 5000);
            },
            (error: HttpErrorResponse) => {
                if (error.error === 'ERROR.CABIN_FERRY_NUMBER_UNIQUE') {
                    this.error = true;
                } else {
                    this.errorHandlerService.handleError(error);
                }
            }
        );
    }

    getCurrentType(): string {
        const selector = document.querySelector('input[name="radio"]:checked');
        if (selector != null) {
            return (selector as HTMLInputElement).value;
        } else {
            return 'First class';
        }
    }

}
