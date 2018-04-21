import React, { Component } from 'react';
import { connect } from 'react-redux';
import { fetchStoreListAction } from '../../../lib/elements/StoresList/action';

import './styles.css';

const mapStateToProps = (state, ownProps) => ({
  stores: state.storesList,
  onItemClick: ownProps.onItemClick,
});

const mapDispatchToProps = (dispatch) => ({
  fetchStoreList: () => dispatch(fetchStoreListAction())
});

class StoresListContainer extends Component {

  componentWillMount() {
    const { fetchStoreList } = this.props;
    fetchStoreList();
  }

  render() {
    const { stores = [], onItemClick } = this.props;

    return(
      <div className="stores_list_wrapper">
        <p className="stores_list_title">Stores List</p>
        {stores.length > 0 &&
          <div className="stores_list">
          {
            stores.map((store, index) => (
              <div key={index} className="store_item" onClick={() => { onItemClick && onItemClick(store.self)}}>
                <p>{ store.name.toUpperCase() }</p>
              </div>
            ))
          }
        </div>}
      </div>
    )
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(StoresListContainer);
