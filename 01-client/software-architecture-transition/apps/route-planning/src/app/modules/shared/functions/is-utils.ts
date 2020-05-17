export function isNullOrUndefined(value: any): boolean {
  return value === undefined || value === null;
}

export function isNullOrEmpty(value: string | any[]): boolean {
  return isNullOrUndefined(value) || value.length === 0;
}

export function exists(value: any): boolean {
  return !isNullOrUndefined(value) && !isNullOrEmpty(value);
}

export function isNull(value: any): boolean {
  return value === null;
}

export function isUndefined(value: any): boolean {
  return value === undefined;
}

export function isNumber(value: any): boolean {
  return typeof value === 'number';
}

export function isString(value: any): boolean {
  return typeof value === 'string';
}

export function isBoolean(value: any): boolean {
  return typeof value === 'boolean';
}

export function isObject(value: any): boolean {
  return value !== null && typeof value === 'object';
}

export function isPrimitive(value: any): boolean {
  return (typeof value !== 'object' && typeof value !== 'function') || value === null;
}

export function isArray(value: any): boolean {
  return Array.isArray(value);
}

