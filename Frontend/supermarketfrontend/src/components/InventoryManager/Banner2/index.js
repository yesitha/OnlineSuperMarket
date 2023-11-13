// Banner
import React from 'react';
import './Banner.css';

export default function Banner() {
  return (
    <section
      className="banner"
      id="banner"
      style={{
        background: 'url("../image/banner-bg.webp") no-repeat',
        backgroundPosition: 'center',
        backgroundSize: 'cover',
      }}
    >
      <div className="content">
        <h3>
          Manage
          {' '} 
         Your
          {' '}
          Inventory
        </h3>
        <p>
        Effortless  Inventory Control for Efficient Online Shopping Management
        </p>
        <a href="#products">
        <button type="button" className="btn">
          Add Product
        </button>
        <button type="button" className="btn"  >
          Add Product Catogory
        </button>
        </a>
      </div>
    </section>
  );
}
