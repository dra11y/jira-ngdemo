import { Injectable } from '@angular/core';
import { Meta } from '@angular/platform-browser';

@Injectable({ providedIn: 'root' })
export class AssetService {
  assetsUrl: string;

  constructor(private meta: Meta) {
    if (meta.getTag('name=assetsUrl') != null) {
        this.assetsUrl = meta.getTag('name=assetsUrl').content;
    } else {
        this.assetsUrl = 'assets';
    }
  }

  public url(asset: string) {
    return this.assetsUrl + '/' + asset;
  }
}
