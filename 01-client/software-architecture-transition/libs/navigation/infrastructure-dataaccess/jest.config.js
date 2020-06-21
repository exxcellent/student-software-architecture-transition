module.exports = {
  name: 'navigation-infrastructure-dataaccess',
  preset: '../../../jest.config.js',
  coverageDirectory:
    '../../../coverage/libs/navigation/infrastructure-dataaccess',
  snapshotSerializers: [
    'jest-preset-angular/build/AngularNoNgAttributesSnapshotSerializer.js',
    'jest-preset-angular/build/AngularSnapshotSerializer.js',
    'jest-preset-angular/build/HTMLCommentSerializer.js',
  ],
};
