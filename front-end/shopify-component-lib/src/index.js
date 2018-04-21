import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import { createStore, applyMiddleware, compose } from 'redux';
import createSagaMiddleware from 'redux-saga';
import { fetchStoresListSaga } from './redux/sagas/storeListSaga';

import ProductsList from '../lib/elements/ProductsList'
import reducers from './redux/reducers';

const sagaMiddleware = createSagaMiddleware()
let middlewares = []
middlewares.push(sagaMiddleware)

const createStoreWithMiddleware = applyMiddleware(...middlewares)(createStore)
const store = createStoreWithMiddleware(reducers, window.devToolsExtension ? window.devToolsExtension() : undefined)

sagaMiddleware.run(fetchStoresListSaga)


ReactDOM.render(
  <Provider store={store}>
    <ProductsList />
  </Provider>
  , document.querySelector('.container'));
