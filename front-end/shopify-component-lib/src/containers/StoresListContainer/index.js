import React, { Component } from 'react';
import { connect } from 'react-redux';
import { fetchStoreListAction } from '../../../lib/elements/StoresList/action';
import './styles.css';


const defaultDescription = 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. '

const mapStateToProps = (state, ownProps) => ({
  stores: state.storesList,
  onItemClick: ownProps.onItemClick,
  images: ownProps.images,
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
    const { stores = [], onItemClick, images=[] } = this.props;

    return(
      <div className="stores_list_wrapper">
        <p className="stores_list_title">Store List</p>
        {stores.length > 0 &&
          <div className="stores_list">
          {
            stores.map((store, index) => (
              <div key={index} className="store_item" onClick={() => { onItemClick && onItemClick(store.self)}}>
                <div>
                  <p className="name">{ store.name.toUpperCase() }</p>
                  { images.length > 0 && <img src={images[index % images.length + 1]} />}
                </div>
                <p className="description">{ store.description ? store.description : defaultDescription}</p>
              </div>
            ))
          }
        </div>}
      </div>
    )
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(StoresListContainer);
