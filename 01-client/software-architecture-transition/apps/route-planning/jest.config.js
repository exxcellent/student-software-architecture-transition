module.exports = {
  name: 'route-planning',
  preset: '../../jest.config.js',
  coverageDirectory: '../../coverage/apps/route-planning',
  snapshotSerializers: [
    'jest-preset-angular/build/AngularNoNgAttributesSnapshotSerializer.js',
    'jest-preset-angular/build/AngularSnapshotSerializer.js',
    'jest-preset-angular/build/HTMLCommentSerializer.js',
  ],
};
