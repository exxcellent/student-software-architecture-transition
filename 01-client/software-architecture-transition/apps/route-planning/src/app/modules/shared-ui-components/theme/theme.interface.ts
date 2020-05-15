export interface Theme {
  name: string;
  properties: {

    // colors
    '--background': string, // e.g. background-color
    '--text': string, // e.g. color
    '--text-light': string, // e.g. color

    '--primary': string,
    '--primary-light': string,
    '--primary-text': string,
    '--primary-text-disabled': string,

    '--secondary': string,
    '--secondary-light': string,
    '--secondary-text': string,
    '--secondary-text-disabled': string,

    '--accent': string,
    '--accent-light': string,
    '--accent-text': string,
    '--accent-text-disabled': string,

    '--warn': string,
    '--warn-light': string,
    '--warn-text': string,
    '--warn-text-disabled': string,

    '--error': string,
    '--error-light': string,
    '--error-text': string,
    '--error-text-disabled': string,

    // typography
    '--font-family': string,

    [key: string]: string;
  };
}
