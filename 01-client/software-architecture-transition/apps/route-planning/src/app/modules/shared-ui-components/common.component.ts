import {ActivatedRoute, Params} from '@angular/router';
import {exists} from '../shared/functions';

export class CommonComponent {

  constructor(protected activatedRoute: ActivatedRoute) { }

  currentQueryParams(): Params {
    return this.activatedRoute.snapshot.queryParams;
  }

  get isMobile(): boolean {
    const mode = this.activatedRoute.snapshot?.queryParamMap?.get('mode');
    return exists(mode) && mode === 'mobile';
  }
}
