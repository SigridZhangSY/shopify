import React, { Component } from 'react';
import { hashHistory } from 'react-router';


class StorePage extends Component {
  render() {
    return (
      <div onClick={() => hashHistory.push('/stores')}>store detail</div>
    )
  }
}

export default StorePage;