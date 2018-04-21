import React, { Component } from 'react';
import { ProductsList } from 'shopify-components-lib';

import './styles.css';
import Image1 from '../../assets/img_1.jpg';
import Image2 from '../../assets/img_2.jpg';
import Image3 from '../../assets/img_3.jpg';
import Image4 from '../../assets/img_4.jpg';
import Image5 from '../../assets/img_5.jpg';
import BookCover from '../../assets/book_cover.jpg';

export const images = [ BookCover ];
class StorePage extends Component {
  render() {
    return (
      <div>
        <section className="store_banner">
          <img src={require('../../assets/img_1.jpg')} />
          <p>WELCOME</p>
        </section>
        <ProductsList storeId={this.props.params.storeId} images={images}/>
      </div>
    )
  }
}

export default StorePage;