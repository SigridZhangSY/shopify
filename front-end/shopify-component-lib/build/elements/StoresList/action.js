'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.saveStoresListAction = exports.fetchStoreListAction = undefined;

var _reducer = require('./reducer');

var StoresList = _interopRequireWildcard(_reducer);

function _interopRequireWildcard(obj) { if (obj && obj.__esModule) { return obj; } else { var newObj = {}; if (obj != null) { for (var key in obj) { if (Object.prototype.hasOwnProperty.call(obj, key)) newObj[key] = obj[key]; } } newObj.default = obj; return newObj; } }

var fetchStoreListAction = exports.fetchStoreListAction = function fetchStoreListAction() {
  return {
    type: StoresList.FETCH_STORES_LIST
  };
};

var saveStoresListAction = exports.saveStoresListAction = function saveStoresListAction(storesList) {
  return {
    type: StoresList.SAVE_STORES_LIST,
    payload: { storesList: storesList }
  };
};