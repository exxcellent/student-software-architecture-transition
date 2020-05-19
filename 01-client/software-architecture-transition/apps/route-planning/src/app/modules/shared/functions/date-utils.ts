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

export function tomorrow(): Date {
  return modifyDay(new Date(Date.now()), 1);
}

export function yesterday(): Date {
  return modifyDay(new Date(Date.now()), -1);
}

export function equals(fistDate: Date, secondsDate: Date): boolean {
  return fistDate.getTime() === secondsDate.getTime();
}
export function equalDate(fistDate: Date, secondsDate: Date): boolean {
  return toISODateString(fistDate) === toISODateString(secondsDate);
}
export function isEqualsDate(fistDate: Date, secondsDate: Date): boolean {
  return toISODateString(fistDate) === toISODateString(secondsDate);
}
export function modifyDay(date: Date, modifyDay: number): Date {
  const day = new Date(date.getTime());

  day.setDate(day.getDate() + modifyDay);

  return day;
}
