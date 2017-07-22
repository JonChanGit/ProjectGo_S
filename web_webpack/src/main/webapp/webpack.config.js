const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

const PATHS = {
	app: path.join(__dirname, '_app'),
	build: path.join(__dirname, 'build'),
	css: path.join(__dirname, 'wp/css')
};

module.exports = {
	// Entries have to resolve to files! They rely on Node
	// convention by default so if a directory contains *index.js*,
	// it resolves to that.
	devServer: {
		host: process.env.HOST, // Defaults to `localhost`
		port: 80, // Defaults to 8080
	},
	entry: {
		app: PATHS.app,
	},
	output: {
		path: PATHS.build,
		filename: '[name].js',
	},
	plugins: [
		new HtmlWebpackPlugin({
			title: 'Webpack demo',
		}),
	],
};