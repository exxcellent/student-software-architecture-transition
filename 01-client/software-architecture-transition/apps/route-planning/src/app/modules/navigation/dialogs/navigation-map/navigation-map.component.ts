import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'nav-navigation-map',
  templateUrl: './navigation-map.component.html',
  styleUrls: ['./navigation-map.component.css']
})
export class NavigationMapComponent implements OnInit {

  latitude = -28.68352;
  longitude = -147.20785;
  mapType = 'satellite';

  constructor() { }

  ngOnInit(): void {
  }

}
