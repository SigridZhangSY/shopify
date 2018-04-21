import React, { Component } from 'react';
import { Provider } from 'react-redux';
import { createStore, applyMiddleware } from 'redux';
import createSagaMiddleware from 'redux-saga';

import StoresListContainer from '../../../src/containers/StoresListContainer';

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

    const { onItemClick } = this.props;

    return (
      <Provider store={store}>
        <StoresListContainer onItemClick={onItemClick}/>
      </Provider>
    )

  }
}

export default StoresList;