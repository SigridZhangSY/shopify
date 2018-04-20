export const INCREASE_ACTION = 'ADD_ACTION'
export const DECREASE_ACTION = 'DECREASE_ACTION'

const initial = {
  counterValue: 0
}

const counterReducer = (state = initial, action) => {
  switch(action.type) {
    case INCREASE_ACTION:
      return {
        ...state,
        counterValue: state.counterValue + 1
      };
    case DECREASE_ACTION:
      return {
        ...state,
        counterValue: state.counterValue - 1
      };
    default:
      return state;
  }
}

export default counterReducer