import { Component } from '@angular/core';
import { ElectronService } from '../../services/electron.service';

@Component({
  selector: 'app-configuration',
  standalone: true,
  imports: [],
  templateUrl: './configuration.component.html',
  styleUrl: './configuration.component.css'
})
export class ConfigurationComponent {

  constructor(private electronService: ElectronService) {}

  sizes = [
    { index: 0, label: '800x600', width: 800, height: 600 },
    { index: 1, label: '1024x768', width: 1024 , height: 768 },
    { index: 2, label: '1280x720', width: 1280 , height: 720 },
    { index: 3, label: '1920x1080', width: 1920 , height: 1080 }
  ]

  onSizeSelect(event: any) {
    const selectedSize = this.sizes.find(size => size.index == event.target.value);
    if (selectedSize) {
      this.electronService.changeWindowSize(selectedSize.width, selectedSize.height);
    }
  }
}
