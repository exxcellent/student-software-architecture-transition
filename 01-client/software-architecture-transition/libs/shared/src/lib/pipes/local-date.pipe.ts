import {Pipe, PipeTransform} from '@angular/core';
import {formatDate} from '@angular/common';

@Pipe({
  name: 'localDate'
})
export class LocalDatePipe implements PipeTransform {

  constructor() {
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
    return formatDate(value, format, 'en-US');
  }

}
