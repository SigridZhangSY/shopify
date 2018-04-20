import { combineReducers } from 'redux';

import counterReducer from './counterReducer';
import storesListReducer from './storesListReducer';

const rootReducer = combineReducers({
  counter: counterReducer,
  stores: storesListReducer,
});

export default rootReducer;
