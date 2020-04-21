import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';
import { environment } from './environments/environment';

declare var AJS: any;

/***************************************************************************************************
 * Zone JS is required by default for Angular itself.
 */
import 'zone.js/dist/zone'; // Included with Angular CLI.

if (environment.production) {
  enableProdMode();
}

let bootstrap = function() {
  platformBrowserDynamic().bootstrapModule(AppModule)
    .catch(err => console.error(err));
}

if (typeof AJS !== 'undefined') {
  AJS.toInit(function() {
    bootstrap();
  });
} else {
  bootstrap();
}
