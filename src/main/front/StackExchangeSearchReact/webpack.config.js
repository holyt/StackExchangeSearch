const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const HtmlWebpackPlugin = require("html-webpack-plugin");
const path = require('path');

module.exports = {
  output: {
        path: path.resolve(__dirname, '../../resources/static')
  },
  externals: [],
  module: {
    rules: [
      {
        test: /\.(js|jsx)$/,
        exclude: /node_modules/,
        use: {
          loader: "babel-loader"
        }
      },
      {
        test: /\.s?[ac]ss$/,
        use:  ['style-loader', MiniCssExtractPlugin.loader, 'css-loader', 'sass-loader']
      }
    ]
  },
  plugins: [ 
    new MiniCssExtractPlugin({
      filename: 'style.css',
    }),
      new HtmlWebpackPlugin({
          inject: false,
          hash: true,
          template: './src/index.html',
          filename: 'index.html'
      })
  ]
};