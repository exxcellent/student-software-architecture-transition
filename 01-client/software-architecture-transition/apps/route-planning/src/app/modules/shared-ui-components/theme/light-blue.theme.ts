import {Theme} from './theme.interface';

export const LIGHT_BLUE: Theme = {
  name: 'LIGHT_BLUE',
  properties: {
    '--background': 'white',
    '--fontWeight': '400',
    '--text': '#333333',
    '--text-light': '#ccd3d7',

    '--primary': '#0060a9',
    '--primary-light': '#ccdeee',
    '--primary-text': 'white',
    '--primary-text-disabled': 'rgba(255, 255, 255, .7)',

    '--secondary': '#c5c800',
    '--secondary-light': '#e1e37b',
    '--secondary-text': 'white',
    '--secondary-text-disabled': 'rgba(255, 255, 255, .7)',

    '--accent': '#a568a8',
    '--accent-light': '#e6d3ff',
    '--accent-text': 'white',
    '--accent-text-disabled': 'rgba(255, 255, 255, .7)',

    '--warn': '#f0bb29',
    '--warn-light': '#ffd543',
    '--warn-text': 'white',
    '--warn-text-disabled': 'rgba(255, 255, 255, .7)',

    '--error': '#c81919',
    '--error-light': '#fb4c4c',
    '--error-text': 'white',
    '--error-text-disabled': 'rgba(255, 255, 255, .7)',

    '--font-family': '\'Roboto\', sans-serif',
    '--contentWidth': '100%'
  }
};
