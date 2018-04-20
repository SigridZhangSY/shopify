import React, { Component } from 'react';
import { Provider } from 'react-redux';
import { createStore, applyMiddleware } from 'redux';
import createSagaMiddleware from 'redux-saga';

import StoreListComponent from '../../../src/containers/StoresList';

import { fetchStoresListSaga } from './saga';
import reducers from './reducer';

const sagaMiddleware = createSagaMiddleware()
let middlewares = []
middlewares.push(sagaMiddleware)

const createStoreWithMiddleware = applyMiddleware(...middlewares)(createStore)
const store = createStoreWithMiddleware(reducers, window.devToolsExtension ? window.devToolsExtension() : undefined)

sagaMiddleware.run(fetchStoresListSaga)

class StoresList extends Component {
  render() {
    return (
      <Provider store={store}>
        <StoreListComponent />
      </Provider>
    )
  }
}

export default StoresList;