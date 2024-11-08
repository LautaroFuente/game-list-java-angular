import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class ElectronService {
  constructor() {}

  changeWindowSize(width: number, height: number) {
    window.electron.ipcRenderer.send('resize-window', { width, height });
  }
}

