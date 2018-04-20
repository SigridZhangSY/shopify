import * as StoresList from '../reducers/storesListReducer';

export const fetchStoreListAction = () => ({
  type: StoresList.FETCH_STORES_LIST
})

export const saveStoresListAction = (storesList) => ({
  type: StoresList.SAVE_STORES_LIST,
  payload: { storesList }
})