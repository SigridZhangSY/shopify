'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.fetchStoresListSaga = fetchStoresListSaga;

var _effects = require('redux-saga/effects');

var _api = require('../../../src/utils/api');

var _action = require('./action');

var _marked = /*#__PURE__*/regeneratorRuntime.mark(fetchStoresListSaga);

function fetchStoresListSaga() {
  var response;
  return regeneratorRuntime.wrap(function fetchStoresListSaga$(_context) {
    while (1) {
      switch (_context.prev = _context.next) {
        case 0:
          if (!true) {
            _context.next = 16;
            break;
          }

          _context.next = 3;
          return (0, _effects.take)((0, _action.fetchStoreListAction)().type);

        case 3:
          _context.prev = 3;
          _context.next = 6;
          return (0, _effects.call)(_api.get, 'http://localhost:8081/stores');

        case 6:
          response = _context.sent;
          _context.next = 9;
          return (0, _effects.put)((0, _action.saveStoresListAction)(response.items));

        case 9:
          _context.next = 14;
          break;

        case 11:
          _context.prev = 11;
          _context.t0 = _context['catch'](3);

          console.log(_context.t0);

        case 14:
          _context.next = 0;
          break;

        case 16:
        case 'end':
          return _context.stop();
      }
    }
  }, _marked, this, [[3, 11]]);
}