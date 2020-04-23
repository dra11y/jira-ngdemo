import { Component } from '@angular/core';
import { AssetService } from './asset.service';

declare var AJS: any;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Tour of Heroes';

  constructor(private assetService: AssetService) {}

  ngOnInit() {
    AJS.sidebar('.aui-sidebar');
  }
}
