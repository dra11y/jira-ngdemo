let RESOURCES_CONTEXT = 'com.grushka.ngdemo.client-resources';

class ManifestBuilderPlugin {
  apply(compiler) {
    // emit is asynchronous hook, tapping into it using tapAsync, you can use tapPromise/tap(synchronous) as well
    compiler.hooks.emit.tapAsync('ManifestBuilderPlugin', (compilation, callback) => {

      var xml = '<?xml version="1.0" encoding="UTF-8"?>\n' +
        '<bundles>\n' +
        '\t<web-resource key="client-resources" name="Client Resources">\n';

      // Loop through all compiled assets,
      // adding a new line item for each filename.
      for (var filename in compilation.assets) {
        xml += '\t\t<resource name="' +
          filename + '" type="download" location="client/' +
          filename + '" />\n';
      }

      xml // += '\t\t<resource name="assets" location="client/assets/" />\n'
        += '\t\t<context>' + RESOURCES_CONTEXT + '</context>\n' +
        '\t</web-resource>\n' +
        '</bundles>\n';

      console.log('\n\n\n' + xml + '\n\n');

      // Insert this list into the webpack build as a new file asset:
      compilation.assets['../META-INF/plugin-descriptor/webpack-manifest.xml'] = {
        source: function() {
          return xml;
        },
        size: function() {
          return xml.length;
        }
      };

      callback();
    });
  }
}

module.exports = {
  plugins: [
    new ManifestBuilderPlugin()
  ]
};
