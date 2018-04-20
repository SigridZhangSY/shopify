import React, { Component } from 'react';
import { connect } from 'react-redux';
import { fetchStoreListAction } from '../../redux/actions/storesListAction';

import './styles.css';

const mapStateToProps = (state) => ({
  stores: state.stores.storesList,
});

const mapDispatchToProps = (dispatch) => ({
  fetchStoreList: () => dispatch(fetchStoreListAction())
});

class StoreList extends Component {
  componentWillMount() {
    const { fetchStoreList } = this.props;
    fetchStoreList();
  }

  render() {
    const { stores } = this.props;

    return(
      <div className="stores_list_wrapper">
        <p className="stores_list_title">Stores List</p>
        {stores.length > 0 &&
          <div className="stores_list">
          {
            stores.map(store => (
              <div className="store_item">
                <p>{ store.name.toUpperCase() }</p>
              </div>
            ))
          }
        </div>}
      </div>
    )
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(StoreList);
