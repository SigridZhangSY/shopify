import * as ProductsList from './reducer';

export const fetchProductsListAction = (storeId) => ({
  type: ProductsList.FETCH_PRODUCTS_LIST,
  payload: { storeId }
})

export const saveProductsListAction = (productsList) => ({
  type: ProductsList.SAVE_PRODUCTS_LIST,
  payload: { productsList }
})