module.exports = {
  name: 'model',
  preset: '../../jest.config.js',
  coverageDirectory: '../../coverage/libs/model',
  snapshotSerializers: [
    'jest-preset-angular/build/AngularNoNgAttributesSnapshotSerializer.js',
    'jest-preset-angular/build/AngularSnapshotSerializer.js',
    'jest-preset-angular/build/HTMLCommentSerializer.js',
  ],
};
