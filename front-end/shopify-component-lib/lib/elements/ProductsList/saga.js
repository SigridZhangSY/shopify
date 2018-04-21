import { take, put, call } from 'redux-saga/effects'
import { get } from '../../../src/utils/api';


import { fetchProductsListAction, saveProductsListAction} from './action';

export function* fetchProductsListSaga() {
  while(true) {
    const { storeId } = yield take(fetchProductsListAction().type)

    try {
      // const response = yield call(get, 'http://localhost:8081/stores/' + storeId + '/products');
      const response = {
        items: [
          {
            name: 'Casebook of Sherlock Holmes',
            price: '23.5',
          },
          {
            name: 'Casebook of Sherlock Holmes',
            price: '23.5',
          },
          {
            name: 'Casebook of Sherlock Holmes',
            price: '23.5',
          },
          {
            name: 'Casebook of Sherlock Holmes',
            price: '23.5',
          }
        ]
      }

      yield put(saveProductsListAction(response.items))
    } catch (error) {
      console.log(error)
    }
  }
}
