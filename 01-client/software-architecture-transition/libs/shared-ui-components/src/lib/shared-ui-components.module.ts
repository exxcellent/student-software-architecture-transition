import {ModuleWithProviders, NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {APP_THEME, DEFAULT_THEME, Theme, ThemeDirective} from './theme';
import {
  CenteredLayoutComponent,
  CenterOnPageComponent,
  ColLayoutComponent,
  ContentAreaComponent,
  GridLayoutComponent,
  RowLayoutComponent
} from './layout';
import {PageComponent} from './layout/page/page.component';
import {FadeInAnimationComponent, RotateAnimationComponent} from './animations';
import {ButtonComponent} from './form-controls';

@NgModule({
  declarations: [
    ThemeDirective,
    FadeInAnimationComponent,
    RotateAnimationComponent,
    CenterOnPageComponent,
    CenteredLayoutComponent,
    ColLayoutComponent,
    ContentAreaComponent,
    GridLayoutComponent,
    PageComponent,
    RowLayoutComponent,
    ButtonComponent,
  ],
  imports: [
    CommonModule
  ],
  exports: [
    // animation
    FadeInAnimationComponent,
    RotateAnimationComponent,
    // theme
    ThemeDirective,
    // layout
    CenterOnPageComponent,
    CenteredLayoutComponent,
    ColLayoutComponent,
    ContentAreaComponent,
    GridLayoutComponent,
    PageComponent,
    RowLayoutComponent,
    // form controls
    ButtonComponent
  ]
})
export class SharedUiComponentsModule {

  public static forRoot(options: { useTheme: Theme } = { useTheme: DEFAULT_THEME }): ModuleWithProviders {
    return {
      ngModule: SharedUiComponentsModule,
      providers: [
        {
          provide: APP_THEME,
          useValue: options.useTheme
        }
      ]
    }
  }

  public static forChild(): ModuleWithProviders {
    return {
      ngModule: SharedUiComponentsModule,
      providers: []
    }
  }

}
