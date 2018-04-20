import React, { Component } from 'react';
import StoreList from '../containers/StoresList';
import { Button } from 'component-lib'

export default class App extends Component {
  render() {
    return (
      <div>
        <StoreList/>
        <Button/>
      </div>
    );
  }
}
