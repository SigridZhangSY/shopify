import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import { createStore, applyMiddleware } from 'redux';
import createSagaMiddleware from 'redux-saga';
import { Router, Route, IndexRedirect, hashHistory } from 'react-router';

import { fetchStoresListSaga } from './redux/sagas/storeListSaga';

import reducers from './redux/reducers';
import App from './components/App';
import StorePage from './containers/StorePage';
import StoreListPage from './containers/StoresListPage';

const sagaMiddleware = createSagaMiddleware()
let middlewares = []
middlewares.push(sagaMiddleware)

const createStoreWithMiddleware = applyMiddleware(...middlewares)(createStore)
const store = createStoreWithMiddleware(reducers, window.devToolsExtension ? window.devToolsExtension() : undefined)

sagaMiddleware.run(fetchStoresListSaga)


ReactDOM.render(
  <Provider store={store}>
    <Router history={hashHistory}>
      <Route path="/" component={App}>
        <IndexRedirect to="stores"/>
        <Route path="/stores" component={StoreListPage}/>
        <Route path="/stores/:id" component={StorePage} />
      </Route>
    </Router>
  </Provider>
  , document.querySelector('.container'));
