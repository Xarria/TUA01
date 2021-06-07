import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { CruiseDetails } from '../../model/mop/CruiseDetails';

@Injectable({
    providedIn: 'root'
})
export class CruiseDetailsService {

    cruise: CruiseDetails = {
        startDate: new Date(),
        endDate: new Date(),
        route: {
            start: {
                city: 'City1',
                code: 'ABC'
            },
            destination: {
                city: 'City2',
                code: 'DEF'
            },
            code: 'ABCDEF'
        },
        ferry: {
            name: 'name',
            onDeckCapacity: 2,
            vehicleCapacity: 10,
        },
        number: 'ABCXYZ100200',
        modificationDate: new Date(),
        modifiedBy: undefined,
        creationDate: new Date(),
        createdBy: {
            login: 'string',
            active: true,
            firstName: 'Adam',
            lastName: 'Adam',
            accessLevel: [],
        },
    };
    private readonly url: string;

    constructor(private http: HttpClient) {
        this.url = environment.appUrl + '/cruises/cruise';
    }

    getCruise(code: string): void {
        return;
    }
}
