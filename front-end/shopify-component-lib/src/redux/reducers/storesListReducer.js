export const FETCH_STORES_LIST = 'FETCH_STORES_LIST'
export const SAVE_STORES_LIST = 'SAVE_STORES_LIST'

const initial = {
  storesList: []
};

const storesListReducer = (state = initial, action) => {
  switch(action.type) {
    case SAVE_STORES_LIST:
      return {
        ...state,
        storesList: action.payload.storesList
      };
    default:
      return state;
  }
}

export default storesListReducer