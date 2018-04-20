import React, { Component } from 'react';
import { connect } from 'react-redux';
import { increase, decrease } from '../redux/actions/counterAction'
import { fetchStoreListAction } from '../redux/actions/storesListAction';

const mapStateToProps = (state) => ({
  counterValue: state.counter.counterValue,
  stores: state.stores.storesList,
})

const mapDispatchToProps = (dispatch) => ({
  increaseCounterValue: () => dispatch(increase()),
  decreaseCounterValue: () => dispatch(decrease()),
  fetchStoreList: () => dispatch(fetchStoreListAction())
})

class Counter extends Component {
  render() {
    const { counterValue, increaseCounterValue, decreaseCounterValue, fetchStoreList } = this.props

    return (
      <div className="container">
        <h4>Value: {counterValue} </h4>
        <div style={{display: 'flex', justifyContent: 'space-between', width: '400px'}}>
          <button
            style={{width: '100px', height: '40px', borderRadius: '10px', background: '#bdbcbc'}}
            onClick={() => increaseCounterValue()}>+
          </button>
          <button
            style={{width: '100px', height: '40px', borderRadius: '10px', background: '#bdbcbc'}}
            onClick={() => decreaseCounterValue()}>-
          </button>
          <button
            style={{width: '100px', height: '40px', borderRadius: '10px', background: '#bdbcbc'}}
            onClick={() => fetchStoreList()}>fetch stores
          </button>
        </div>
      </div>
    )
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(Counter);