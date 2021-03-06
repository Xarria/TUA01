import { Injectable } from '@angular/core';
import {
    HttpBackend,
    HttpClient,
    HttpErrorResponse,
    HttpEvent,
    HttpHandler,
    HttpInterceptor,
    HttpRequest
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { AuthService } from '../mok/auth.service';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { NgZone } from '@angular/core';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    private httpClient: HttpClient;

    constructor(handler: HttpBackend,
                private authService: AuthService,
                private cookieService: CookieService,
                private zone: NgZone,
                private router: Router) {
        this.httpClient = new HttpClient(handler);
    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const actualDate = new Date();
        const tokenDate = new Date(parseInt(this.cookieService.get('expirationTime'), 10) * 1000);

        if (req.url !== environment.appUrl + '/auth'
            && this.cookieService.get('token') !== null
            && this.cookieService.get('token') !== ''
            && tokenDate > actualDate) {
            this.httpClient.get(environment.appUrl + '/auth', {
                headers: {
                    Authorization: 'Bearer ' + this.cookieService.get('token')
                }, observe: 'body', responseType: 'text'
            }).subscribe(
                (response: string) => {
                    this.authService.setSession(response);
                },
                (error: HttpErrorResponse) => {
                    if (error.status === 403) {
                        this.authService.signOut();
                        this.zone.run(() => this.router.navigateByUrl('/error/forbidden'));
                    }
                }
            );
        } else {
            this.authService.signOut();
        }

        const authReq = req.clone({setHeaders: {Authorization: 'Bearer ' + this.cookieService.get('token')}});
        return next.handle(authReq);
    }
}
