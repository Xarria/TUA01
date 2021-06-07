import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class RouteGeneralService {

    private readonly url: string;

    constructor(private httpClient: HttpClient) {
        this.url = environment.appUrl + '/routes';
    }

    getAllRoutes(): any {
        return this.httpClient.get<any>(this.url, {
            observe: 'body',
            responseType: 'json'
        });
    }
}
