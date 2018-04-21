import React, { Component } from 'react';
import { hashHistory } from 'react-router';

import { StoreList } from 'shopify-components-lib';

class StoreListPage extends Component {
  render() {
    return (
      <StoreList onItemClick={(url) => hashHistory.push(url)}/>
    )
  }
};

export default StoreListPage;
