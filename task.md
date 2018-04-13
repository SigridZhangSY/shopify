### Description

------

- 电商系统，功能包括商品的上架、库存、定价管理，订单生成、支付、配送，退款（可对订单部分退款）。


![snap](https://github.com/SigridZhangSY/shopify/blob/master/model.JPG?raw=true)

### Tasking `1770`

#### scaffold   `60` 

---

— backend   `20`

— frontend  `40` 



#### product service `295`

------

— API

- /stores         post `15`      get `10`
- /stores/{sid}           get `10`


- /stores/{sid}/products     post`15`	   get`10`    
- /products/{pid}     get`20`   
- /stores/{sid}/products/{pid}      put `10`

— Dockerize

- Dockerfile `5`
- docker compose service and database `10`

— Eureka

- integeration client with service `10`
- compose server as side car `20`

— Front end

- store list `30`
- product list `30`
- product detail `40`
- list a product `40`
- remove a product from list `20`

#### price service`95`

------

— API

- /products/{pid}/price-list     post `15`     get`10`

- /products/{pid}/current-price      get`10`

— Dockerize

- Dockerfile`5`

- docker compose service and database`10`

— Eureka

- integeration client with service`10`
- compose server as side car`15`

— Front end

- price a product `20min`

#### inventory service `110`

------

— API

- /products/{pid}/inventory-list       post`15`      get`10`
- /products/{pid}/current-inventory          get`10`
- /products/{pid}/inventory-requests  post`15`    get`10`


— Dockerize

- Dockerfile `5`
- docker compose service and database`10`

— Eureka

- integeration client with service`5`
- compose server as side car`10`

— Front end

- change product inventory`20`


#### order service`345`

---

— API

- /orders		post  `15`        get`10`
- /orders/{oid}            get`10`
- /orders/{oid}/order-items       get`10`
- /orders/{oid}/order-items/{oiid}       get`10`
- /orders/{oid}/payment         post  `15`         get`10`
- /orders/{oid}/logistics-order          post`15`         get`10`
- /orders/{oid}/recieve-confirm         post`15`         get`10`


— Dockerize

- Dockerfile`5`
- docker compose service and database`10`

— Eureka

- integeration client with service`5`

- compose server as side car`10`

— Front  end

- order list`30`
- order detail`30`
- order item`25`
- create payment`20`
- payment detail`30`
- create recieve confirm`25`
- recieve confirm detail`25`

#### refund service`285`

---

— API

- /refund-requests       post`15`       get`10`
- /refund-requests/{rid}       get`10`
- /refund-requests/{rid}/refund-items    get`10`
- /refund-requests/{rid}/refund-items/{riid}    get`10`
- /refund-requests/{rid}/refund       post`15`         get`10`
- /refund-requests/{rid}/refund/{rrid}        get`10`
- /refund-requests/{rid}/refund-confirm       post`15`       get`10`

— Dockerize

- Dockerfile`5`
- docker compose service and database`10`

— Eureka

- integeration client with service`5`
- compose server as side car`10`

— Front  end

- create refund request`30`
- refund request detail`30`
- refund request item`30`
- create refund confirm`20`
- refund confirm detail`30`

#### user service`120`

---

- spike Oauth`60`
- implementation`60`


#### cart service`140`

---

— API

- /cart-items      post `15`   get`10`   delete`5`


— Dockerize

- Dockerfile`5`
- docker compose service and database`10`

— Eureka

- integeration client with service`5`
- compose server as side car`10`

— Front  end

- add to cart`30`
- remove from cart`20`
- cart detail`30`



#### Eureka  service `40`

#### proxy service`90`

---

— forward logic `60`

— deploy service `30`

####front end npm package`70`

---

— spike`60`

— implementation`60`



#### front end service implementation `120`









