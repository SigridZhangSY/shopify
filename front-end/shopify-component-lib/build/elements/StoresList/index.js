'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _react = require('react');

var _react2 = _interopRequireDefault(_react);

var _reactRedux = require('react-redux');

var _redux = require('redux');

var _reduxSaga = require('redux-saga');

var _reduxSaga2 = _interopRequireDefault(_reduxSaga);

var _StoresListContainer = require('../../../src/containers/StoresListContainer');

var _StoresListContainer2 = _interopRequireDefault(_StoresListContainer);

var _saga = require('./saga');

var _reducer = require('./reducer');

var _reducer2 = _interopRequireDefault(_reducer);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var sagaMiddleware = (0, _reduxSaga2.default)();
var middlewares = [];
middlewares.push(sagaMiddleware);

var createStoreWithMiddleware = _redux.applyMiddleware.apply(undefined, middlewares)(_redux.createStore);
var store = createStoreWithMiddleware(_reducer2.default, window.devToolsExtension ? window.devToolsExtension() : undefined);

sagaMiddleware.run(_saga.fetchStoresListSaga);

var StoresList = function (_Component) {
  _inherits(StoresList, _Component);

  function StoresList() {
    _classCallCheck(this, StoresList);

    return _possibleConstructorReturn(this, (StoresList.__proto__ || Object.getPrototypeOf(StoresList)).apply(this, arguments));
  }

  _createClass(StoresList, [{
    key: 'render',
    value: function render() {

      return _react2.default.createElement(
        _reactRedux.Provider,
        { store: store },
        _react2.default.createElement(_StoresListContainer2.default, this.props)
      );
    }
  }]);

  return StoresList;
}(_react.Component);

exports.default = StoresList;