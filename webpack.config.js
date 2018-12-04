const HtmlWebpackPlugin = require('html-webpack-plugin');
const UglifyJsPlugin = require('uglifyjs-webpack-plugin');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');
const WriteFilePlugin = require('write-file-webpack-plugin');
const sharp = require('sharp');
const path = require('path');

function copyRevealJsFiles(pattern, outputDirectory = '') {
    return {
        from: `node_modules/reveal.js/${pattern}`,
        to: outputDirectory,
        transformPath(targetPath) {
            return targetPath.replace('node_modules/reveal.js/', '');
        }
    }
}

function copyAndResizePictures(pattern) {
    return {
        from: path.join('src', pattern),
        to: '',
        transformPath(targetPath) {
            return targetPath.replace('src/', '')
        },
        transform(content, path) {
            if (path.endsWith('.jpg')) {
                return sharp(content)
                    .resize({
                        width: 2000,
                        height: 1333,
                        fit: sharp.fit.outside
                    })
                    .jpeg()
                    .toBuffer();
            }
            return content;
        }
    };
}

module.exports = {
    module: {
        rules: [
            {
                test: /\.(css|scss)$/,
                use: [
                    {
                        loader: MiniCssExtractPlugin.loader
                    },
                    {
                        loader: 'css-loader',
                        options: {minimize: true}
                    },
                    {
                        loader: 'postcss-loader',
                        options: {
                            plugins: () => [
                                require('autoprefixer')
                            ]
                        }
                    },
                    {
                        loader: 'sass-loader',
                        options: {
                            includePaths: ['node_modules/']
                        }
                    }
                ]
            },
            {
                test: /\.(png|jpg|gif|svg|eot|woff2?|ttf|otf)$/,
                use: {
                    loader: 'file-loader',
                    options: {
                        name: '[path][name].[ext]',
                        context: '.'
                    }
                }
            },
            {
                test: require.resolve('reveal.js'),
                use: {
                    loader: 'expose-loader',
                    options: 'Reveal'
                }
            }
        ]
    },
    plugins: [
        new UglifyJsPlugin(),
        new MiniCssExtractPlugin({
            filename: '[name].css',
            chunkFilename: '[id].css'
        }),
        new HtmlWebpackPlugin({
            template: path.resolve('src', 'index.html')
        }),
        new CopyWebpackPlugin([
            copyRevealJsFiles('plugin/notes/*'),
            copyRevealJsFiles('plugin/markdown/*'),
            copyRevealJsFiles('plugin/highlight/*'),
            copyRevealJsFiles('css/reveal.css', 'css'),
            copyRevealJsFiles('css/theme/white.css', 'css/theme'),
            copyRevealJsFiles('lib/font/source-sans-pro/source-sans-pro.css', 'lib/font/source-sans-pro'),
            copyAndResizePictures('pictures/*')
        ]),
        new WriteFilePlugin()
    ],
    entry: {
        slides: path.resolve('src', 'js', 'slides.js')
    },
    output: {
        path: path.resolve('build'),
        filename: '[name].js'
    },
    devServer: {
        contentBase: path.join('build'),
        compress: true,
        port: 9000
    }
};
