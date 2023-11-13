// Products
import React from 'react';
import './Products.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faStar, faStarHalfAlt } from '@fortawesome/free-solid-svg-icons';
import { Swiper, SwiperSlide } from 'swiper/react';
import SwiperCore from 'swiper/core';
import { Autoplay } from 'swiper/modules';
//import 'swiper/swiper.min.css';
import 'swiper/css';



export default function Products() {
  SwiperCore.use([Autoplay]);
  return (
    <section className="products" id="products">
      <h1 className="heading">
        <span>Orders</span>
      </h1>
      <div className="products-slider slider">
        <div className="wrapper swiper-wrapper">
          <Swiper
            loop
            spaceBetween={20}
            autoplay={{ delay: 3000, disableOnInteraction: false }}
            slidesPerView={1}
            pagination={{ clickable: true }}
            speed={500}
            centeredSlides
            breakpoints={{
              0: {
                slidesPerView: 1,
              },
              768: {
                slidesPerView: 1,
              },
              1024: { slidesPerView: 3 },
            }}
            style={{ padding: '1rem' }}
          >
            <SwiperSlide>
              <div className="box">
                <img src="image/order1.png" alt="" />
                <h3>OrderID - 8175959774</h3>
                <div className="price">Rs 80/ -- 120/-</div>
                <button type="button" className="btn">
                  View Status
                </button>
              </div>
            </SwiperSlide>
            <SwiperSlide>
              <div className="box">
                <img src="image/order1.png" alt="" />
                <h3>OrderID - 8175959776</h3>
                <div className="price">Rs 250/ -- 300/-</div>
                <button type="button" className="btn">
                View Status
                </button>
              </div>
            </SwiperSlide>
            <SwiperSlide>
              <div className="box">
                <img src="image/order1.png" alt="" />
                <h3>OrderID - 8175959777</h3>
                <div className="price">Rs 1100/ -- 1400/-</div>
                <button type="button" className="btn">
                View Status
                </button>
              </div>
            </SwiperSlide>
            <SwiperSlide>
              <div className="box">
                <img src="image/order1.png" alt="" />
                <h3>OrderID - 8175959781</h3>
                <div className="price">Rs 240/ -- 320/-</div>
                <button type="button" className="btn">
                View Status
                </button>
              </div>
            </SwiperSlide>
          </Swiper>
        </div>
      </div>

      <div className="products-slider">
        <div className="wrapper swiper-wrapper">
          <Swiper
            loop
            spaceBetween={20}
            autoplay={{ delay: 3000, disableOnInteraction: false }}
            slidesPerView={1}
            pagination={{ clickable: true }}
            speed={500}
            centeredSlides
            breakpoints={{
              0: {
                slidesPerView: 1,
              },
              768: {
                slidesPerView: 3,
              },
              1024: { slidesPerView: 3 },
            }}
            style={{ padding: '1rem' }}
          >
            <SwiperSlide>
              <div className="box">
                <img src="image/order1.png" alt="" />
                <h3>OrderID - 8175959782</h3>
                <div className="price">Rs 200/ -- 240/-</div>
                <button type="button" className="btn">
                View Status
                </button>
              </div>
            </SwiperSlide>
            <SwiperSlide>
              <div className="box">
                <img src="image/order1.png" alt="" />
                <h3>OrderID - 8175959801</h3>
                <div className="price">Rs 45/ -- 65/-</div>
                <button type="button" className="btn">
                View Status
                </button>
              </div>
            </SwiperSlide>
            <SwiperSlide>
              <div className="box">
                <img src="image/order1.png" alt="" />
                <h3>OrderID - 8175959802</h3>
                <div className="price">Rs 150/ -- 200/-</div>
                <button type="button" className="btn">
                View Status
                </button>
              </div>
            </SwiperSlide>
            <SwiperSlide>
              <div className="box">
                <img src="image/order1.png" alt="" />
                <h3>OrderID - 8175959805</h3>
                <div className="price">Rs 90/ -- 200/-</div>
                <button type="button" className="btn">
                View Status
                </button>
              </div>
            </SwiperSlide>
          </Swiper>
        </div>
      </div>
    </section>
  );
}
