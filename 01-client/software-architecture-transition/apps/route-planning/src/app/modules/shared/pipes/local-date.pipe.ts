import {Pipe, PipeTransform} from '@angular/core';
import {formatDate} from '@angular/common';
import {SessionService} from '../services';

@Pipe({
  name: 'localDate'
})
export class LocalDatePipe implements PipeTransform {

  constructor(private session: SessionService) {
  }

  /**
   * Formats the date with the current locale configuration and date format
   *
   * @param value of the date
   * @param format to override the date format manually
   */
  transform(value: any, format: string) {
    if (!value) {
      return '';
    }
    if (!format) {
      format = this.session.getLocalDateFormat();
    }
    return formatDate(value, format, this.session.getLocale());
  }

}
