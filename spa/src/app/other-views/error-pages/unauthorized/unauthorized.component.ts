import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
    selector: 'app-unauthorized',
    templateUrl: './unauthorized.component.html',
    styleUrls: ['./unauthorized.component.less']
})
export class UnauthorizedComponent implements OnInit {

    constructor(private router: Router) {}

    goToHomePage(): void {
        this.router.navigate(['/']);
    }

    ngOnInit(): void {
    }
}
