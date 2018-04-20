import { take, put, call } from 'redux-saga/effects'
import { get } from '../../../src/utils/api';


import { fetchStoreListAction, saveStoresListAction} from './action';

export function* fetchStoresListSaga() {
  while(true) {
    yield take(fetchStoreListAction().type)

    try {
      const response = yield call(get, 'http://localhost:8081/stores');

      yield put(saveStoresListAction(response.items))
    } catch (error) {
      console.log(error)
    }
  }
}
