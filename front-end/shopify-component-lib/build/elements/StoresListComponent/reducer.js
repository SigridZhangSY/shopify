'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _extends = Object.assign || function (target) { for (var i = 1; i < arguments.length; i++) { var source = arguments[i]; for (var key in source) { if (Object.prototype.hasOwnProperty.call(source, key)) { target[key] = source[key]; } } } return target; };

var FETCH_STORES_LIST = exports.FETCH_STORES_LIST = 'FETCH_STORES_LIST';
var SAVE_STORES_LIST = exports.SAVE_STORES_LIST = 'SAVE_STORES_LIST';

var initial = {
  storesList: []
};

var storesListReducer = function storesListReducer() {
  var state = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : initial;
  var action = arguments[1];

  switch (action.type) {
    case SAVE_STORES_LIST:
      return _extends({}, state, {
        storesList: action.payload.storesList
      });
    default:
      return state;
  }
};

exports.default = storesListReducer;