import {ActivatedRoute, Params} from '@angular/router';

export class CommonComponent {

  constructor(protected activatedRoute: ActivatedRoute) { }

  currentQueryParams(): Params {
    return this.activatedRoute.snapshot.queryParams;
  }
}
