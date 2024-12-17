import {enableProdMode} from '@angular/core';
import {platformBrowserDynamic} from '@angular/platform-browser-dynamic';

import {AppModule} from './app/app.module';
import {environment} from './environments/environment';


if (environment.production) {
    enableProdMode();
}
// Dynamically set base href based on environment
const baseHref = environment.production ? '/eazybank/' : '/';
const baseElement = document.getElementById('baseHref');
if (baseElement) {
    baseElement.setAttribute('href', baseHref);
}
platformBrowserDynamic().bootstrapModule(AppModule)
    .catch(err => console.error(err));
