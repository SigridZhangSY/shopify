import React, { Component } from 'react';
import { connect } from 'react-redux';
import { fetchStoreListAction } from '../../../lib/elements/StoresList/action';

import './styles.css';

const mapStateToProps = (state) => ({
  stores: state.storesList,
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
        <p className="stores_list_title">Store List</p>
        {stores.length > 0 &&
          <div className="stores_list">
          {
            stores.map((store, index) => (
              <div key={index} className="store_item">
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
