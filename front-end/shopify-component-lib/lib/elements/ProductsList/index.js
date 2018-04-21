import React, { Component } from 'react';
import { Provider } from 'react-redux';
import { createStore, applyMiddleware } from 'redux';
import createSagaMiddleware from 'redux-saga';

import ProductsListContainer from '../../../src/containers/ProductsListContainer';

import { fetchProductsListSaga } from './saga';
import reducers from './reducer';

const sagaMiddleware = createSagaMiddleware()
let middlewares = []
middlewares.push(sagaMiddleware)

const createStoreWithMiddleware = applyMiddleware(...middlewares)(createStore)
const store = createStoreWithMiddleware(reducers, window.devToolsExtension ? window.devToolsExtension() : undefined)

sagaMiddleware.run(fetchProductsListSaga)

class ProductsList extends Component {
  render() {

    return (
      <Provider store={store}>
        <ProductsListContainer {...this.props}/>
      </Provider>
    )

  }
}

export default ProductsList;

