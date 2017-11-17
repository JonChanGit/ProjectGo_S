// https://eslint.org/docs/user-guide/configuring

module.exports = {
  root: true,
  parser: 'babel-eslint',
  parserOptions: {
    sourceType: 'module'
  },
  env: {
    browser: true,
  },
  // https://github.com/standard/standard/blob/master/docs/RULES-en.md
  "extends": "eslint:recommended",
  // required to lint *.vue files
  plugins: [
    'html'
  ],
  // add your custom rules here
  'rules': {
    // allow paren-less arrow functions
    'arrow-parens': 0,
    // allow async-await
    'generator-star-spacing': 0,
    // allow debugger during development
    'no-debugger': process.env.NODE_ENV === 'production' ? 2 : 0,
    quotes: ['error', 'single'],
    'no-unused-vars': ['warn'],
    indent: [0, 2],
    'linebreak-style': ['error', 'windows'],
    //增加分号
    semi: ['error', 'always'],
    // 允许控制台输出
    'no-console': 0,
    'spaced-comment': ["warn", "always"],
  }
}
