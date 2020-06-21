module.exports = {
  name: 'common-shared',
  preset: '../../../jest.config.js',
  coverageDirectory: '../../../coverage/libs/common/shared',
  snapshotSerializers: [
    'jest-preset-angular/build/AngularNoNgAttributesSnapshotSerializer.js',
    'jest-preset-angular/build/AngularSnapshotSerializer.js',
    'jest-preset-angular/build/HTMLCommentSerializer.js',
  ],
};
