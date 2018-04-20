import { take, put, call } from 'redux-saga/effects'
import { get } from '../../utils/api';
import * as StoresList from '../reducers/storesListReducer';


import { fetchStoreListAction, saveStoresListAction} from '../actions/storesListAction';

export function* fetchStoresListSaga() {
  while(true) {
    yield take(fetchStoreListAction().type)

    try {
      const response = yield call(get, 'http://localhost:8081/stores');
      console.log(response.items)
      yield put(saveStoresListAction(response.items))
    } catch (error) {
      console.log(error)
    }
  }
}
