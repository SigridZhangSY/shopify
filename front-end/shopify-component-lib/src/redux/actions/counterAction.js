import * as Counter from '../reducers/counterReducer';

export const increase = () => ({
    type: Counter.INCREASE_ACTION
})

export const decrease = () => ({
    type: Counter.DECREASE_ACTION
})
