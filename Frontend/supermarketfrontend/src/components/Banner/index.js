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
          fresh and
          {' '} 
         organic
          {' '}
          products
        </h3>
        <p>
        Welcome to HappyDeals where every visit is a step into a visually stunning and user-friendly marketplace!
        </p>
        <a href="#products">
        <button type="button" className="btn"  >
          shop now
        </button>
        </a>
      </div>
    </section>
  );
}
