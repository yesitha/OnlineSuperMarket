// Features
import React from 'react';
import './Features.css';

export default function Features() {
  return (
    <section className="features" id="features">
      <div className="content">
        <h1 className="heading">
       <span> Delivery Persons</span>
        </h1>
        <div className="box-container">
          <div className="box">
            <img src="image/pic-1.png" alt="" />
            <h3>Michael Smith</h3>
            <button type="button" className="btn">
                  View Details
                </button>
          </div>
          <div className="box">
            <img src="image/pic-4.png" alt="" />
            <h3>Susan Jones</h3>
            <button type="button" className="btn">
                  View Details
                </button>
          </div>
          <div className="box">
            <img src="image/pic-3.png" alt="" />
            <h3>David Brown</h3>
            <button type="button" className="btn">
                  View Details
                </button>
          </div>
        </div>
      </div>
    </section>
  );
}
