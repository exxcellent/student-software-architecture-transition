export function toISODateString(date: Date | number): string {
  let d: Date;
  if (date instanceof Date) {
    d = date
  } else {
    d = new Date(d);
  }

  return d.toISOString().split('T')[0];
}

export function today(): Date {
  return new Date(Date.now());
}

export function equals(fistDate: Date, secondsDate: Date): boolean {
  return fistDate.getTime() === secondsDate.getTime();
}
export function isEqualsDate(fistDate: Date, secondsDate: Date): boolean {
  return toISODateString(fistDate) === toISODateString(secondsDate);
}
