import {Component, OnInit} from '@angular/core';
import {NavigationDaySelectorDiaglogCore} from './navigation-day-selector.dialogcore';

@Component({
  selector: 'nav-navigation-day-selector',
  templateUrl: './navigation-day-selector.component.html',
  styleUrls: ['./navigation-day-selector.component.css'],
  providers: [NavigationDaySelectorDiaglogCore]
})
export class NavigationDaySelectorComponent implements OnInit {

  constructor(private dialogCore: NavigationDaySelectorDiaglogCore) { }

  ngOnInit(): void {
  }

}
