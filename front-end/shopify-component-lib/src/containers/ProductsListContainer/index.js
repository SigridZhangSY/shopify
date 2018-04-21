import React, { Component } from 'react';
import { connect } from 'react-redux';
import { fetchProductsListAction } from '../../../lib/elements/ProductsList/action';

import './styles.css';

const mapStateToProps = (state, ownProps) => ({
  products: state.productsList,
  images: ownProps.images,
  storeId: ownProps.storeId,
});

const mapDispatchToProps = (dispatch) => ({
  fetchProductsList: (storeId) => dispatch(fetchProductsListAction(storeId))
});

class ProductsListContainer extends Component {
  componentWillMount() {
    const { fetchProductsList, storeId } = this.props;
    fetchProductsList(storeId);
  }

  renderProductsList = (products, images) => (
    <div className="products_list">
      {
        products.map((product, index) => (
          <div key={index} className="product_item">
            <div className="name">{product.name}</div>
            <div className="price">￥ {product.price}</div>
            <img src={images[index % images.length]} />
          </div>
        ))
      }
    </div>
  )

  renderEmptyTip = () => (
    <div className="empty_tip">Empty Products List</div>
  )

  render() {

    const { products, images } = this.props;

    return (
      <div className="products_list_wrapper">
        <div className="products_list_title">PRODUCTS LIST</div>
        { products.length > 0 ? this.renderProductsList(products, images) : this.renderEmptyTip()}
      </div>
    )
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(ProductsListContainer);