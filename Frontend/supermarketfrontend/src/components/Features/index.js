// Features
import React from 'react';
import './Features.css';

export default function Features() {
  return (
    <section className="features" id="features">
      <div className="content">
        <h1 className="heading">
          our
          {' '}
          <span>features</span>
        </h1>
        <div className="box-container">
          <div className="box">
            <img src="image/fresh-and-organic.png" alt="" />
            <h3>fresh and organic</h3>
            <p>Savor the freshest and finest organic produce, handpicked just for you!</p>
            <a href="/" className="btn">read more</a>
          </div>
          <div className="box">
            <img src="image/Tracking-Order.png" alt="" />
            <h3>Tracking Order</h3>
            <p>Track your order with ease and anticipation as your selected items make their way to your doorstep!</p>
            <a href="/" className="btn">read more</a>
          </div>
          <div className="box">
            <img src="image/easy-payment.png" alt="" />
            <h3>easy payment</h3>
            <p>Enjoy stress-free payments for your selected goodies with our easy and secure payment options!</p>
            <a href="/" className="btn">read more</a>
          </div>
        </div>
      </div>
    </section>
  );
}
