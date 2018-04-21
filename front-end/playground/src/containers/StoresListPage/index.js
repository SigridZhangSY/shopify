import React, { Component } from 'react';
import { hashHistory } from 'react-router';

import { StoreList } from 'shopify-components-lib';

import Image1 from './assets/img_1.jpg';
import Image2 from './assets/img_2.jpg';
import Image3 from './assets/img_3.jpg';
import Image4 from './assets/img_4.jpg';
import Image5 from './assets/img_5.jpg';

const images = [ Image1, Image2, Image3, Image4, Image5];
class StoreListPage extends Component {
  render() {
    return (
      <StoreList onItemClick={(url) => hashHistory.push(url)} images={images}/>
    )
  }
};

export default StoreListPage;
