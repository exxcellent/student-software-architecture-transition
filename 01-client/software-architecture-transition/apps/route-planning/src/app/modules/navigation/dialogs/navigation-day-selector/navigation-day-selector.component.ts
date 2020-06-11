import {Component, OnInit} from '@angular/core';
import {NavigationDaySelectorDiaglogCore} from './navigation-day-selector.dialogcore';
import {equalDate, today, tomorrow, yesterday} from '@software-architecture-transition/shared';

@Component({
  selector: 'nav-navigation-day-selector',
  templateUrl: './navigation-day-selector.component.html',
  styleUrls: ['./navigation-day-selector.component.css'],
  providers: [NavigationDaySelectorDiaglogCore]
})
export class NavigationDaySelectorComponent implements OnInit {

  currentDay: Date;

  constructor(private dialogCore: NavigationDaySelectorDiaglogCore) { }

  ngOnInit(): void {
    this.dialogCore.currentDay$.subscribe(currentDay => {
      this.currentDay = currentDay;
    })
  }

  onPreviousDay(): void {
    this.dialogCore.previousDay();
  }
  onNextDay(): void {
    console.log('onNextDay pressed')
    this.dialogCore.nextDay();
  }

  get currentDayString(): string {
    if (equalDate(this.currentDay, today())) {
      return 'HEUTE';
    } else if (equalDate(this.currentDay, tomorrow())) {
      return 'MORGEN';
    } else if (equalDate(this.currentDay, yesterday())) {
      return 'GESTERN';
    } else {
      return undefined;
    }
  }
}
