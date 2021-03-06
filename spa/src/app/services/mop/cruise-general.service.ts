import { Injectable, OnDestroy } from '@angular/core';
import { CruiseGeneral } from '../../model/mop/CruiseGeneral';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class CruiseGeneralService implements OnDestroy {

    currentCruises: CruiseGeneral[] = [];
    popup = 'hidden';

    private readonly url: string;

    constructor(private httpClient: HttpClient) {
        this.url = environment.appUrl + '/cruises/current';
    }

    private static parseDate(stringDate: any): any {
        if (!stringDate) {
            return undefined;
        }
        return new Date(stringDate.toString().split('[UTC]')[ 0 ]);
    }

    private static parseDates(cruiseGeneral: CruiseGeneral[]): CruiseGeneral[] {
        cruiseGeneral.forEach(cruise => cruise.startDate = this.parseDate(cruise.startDate));
        cruiseGeneral.forEach(cruise => cruise.endDate = this.parseDate(cruise.endDate));
        return cruiseGeneral;
    }

    getCurrentCruises(): any {
        return this.httpClient.get<any>(this.url, {
            observe: 'body',
            responseType: 'json'
        });
    }

    readCurrentCruises(response: CruiseGeneral[]): void {
        this.currentCruises = response;
        this.currentCruises = CruiseGeneralService.parseDates(this.currentCruises);
    }


    ngOnDestroy(): void {
        this.popup = 'hidden';
        this.currentCruises = [];
    }
}
