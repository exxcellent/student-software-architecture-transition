import {Directive, ElementRef, Inject, InjectionToken, Input, OnChanges, OnDestroy, OnInit} from '@angular/core';
import {Theme} from './theme.interface';
import {DEFAULT_THEME} from './default.theme';

export const APP_THEME = new InjectionToken('APP_THEME');

@Directive({
  // tslint:disable-next-line:directive-selector
  selector: '[with-theme]'
})
export class ThemeDirective implements OnInit, OnDestroy, OnChanges {
  @Input() withDefaultTheme = false;
  @Input() theme: Theme;

  constructor(
    private elementRef: ElementRef,
    @Inject(APP_THEME) public appTheme: Theme = DEFAULT_THEME
  ) {
    if (!!appTheme) {
      this.theme = appTheme;
    }
  }

  ngOnChanges() {
    if (this.withDefaultTheme) {
      this.updateTheme(this.appTheme)
    } else {
      if (!!this.theme) {
        this.updateTheme(this.theme)
      }
    }
  }

  ngOnInit() {
    if (this.withDefaultTheme) {
      this.updateTheme(this.appTheme)
    } else {
      if (!!this.theme) {
        this.updateTheme(this.theme)
      }
    }
  }

  ngOnDestroy() {
  }

  updateTheme(theme: Theme) {
    console.log('ignoreParentTheme ? ' + this.withDefaultTheme + ' for ' + theme.name)
    if (!!theme && (this.withDefaultTheme || theme.name !== this.appTheme.name)) {
      console.log('Update theme: ', theme.name);

      // project properties onto the element
      if (!!theme.properties) {
        for (const key of Object.keys(theme.properties)) {
          this.elementRef.nativeElement.style.setProperty(key, theme.properties[key]);
        }
      }
    }
  }
}
