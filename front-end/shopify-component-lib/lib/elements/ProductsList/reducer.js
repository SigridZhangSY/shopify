export const FETCH_PRODUCTS_LIST = 'FETCH_PRODUCTS_LIST'
export const SAVE_PRODUCTS_LIST = 'SAVE_PRODUCTS_LIST'

const initial = {
  productsList: []
};

const productsListReducer = (state = initial, action) => {
  switch(action.type) {
    case SAVE_PRODUCTS_LIST:
      return {
        ...state,
        productsList: action.payload.productsList
      };
    default:
      return state;
  }
}

export default productsListReducer