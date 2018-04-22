import { take, put, call } from 'redux-saga/effects'
import { get } from '../../../src/utils/api';


import { fetchProductsListAction, saveProductsListAction} from './action';

export function* fetchProductsListSaga() {
  while(true) {
    const { payload: { storeId } } = yield take(fetchProductsListAction().type)

    try {
      const { items } = yield call(get, 'http://localhost:8081/stores/' + storeId + '/products');
      for(let i = 0; i < items.length; i++ ) {
        var callEffect = yield call(get, 'http://localhost:8082' + items[i].links.currentPrice);
        const { value } = callEffect;
        items[i].price = value;
      }

      yield put(saveProductsListAction(items))
    } catch (error) {
      console.log(error)
    }
  }
}
