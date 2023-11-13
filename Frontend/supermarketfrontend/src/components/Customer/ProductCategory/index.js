// Products
import React from 'react';
import './ProductCategory.css';
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
        our
        {' '}
        <span>product categories</span>
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
                <img src="image/vegi.jpg" alt="" width={270}/>
                <h3>Vegetables</h3>
                {/* <div className="price">Rs 80/ -- 120/-</div>
                <div className="stars">
                  <FontAwesomeIcon icon={faStar} />
                  <FontAwesomeIcon icon={faStar} />
                  <FontAwesomeIcon icon={faStar} />
                  <FontAwesomeIcon icon={faStar} />
                  <FontAwesomeIcon icon={faStarHalfAlt} />
                </div> */}
                <button type="button" className="btn">
                  view products
                </button>
              </div>
            </SwiperSlide>
            <SwiperSlide>
              <div className="box">
                <img src="image/f.jpg" alt="" />
                <h3>Fruits</h3>
                {/* <div className="price">Rs 250/ -- 300/-</div>
                <div className="stars">
                  <FontAwesomeIcon icon={faStar} />
                  <FontAwesomeIcon icon={faStar} />
                  <FontAwesomeIcon icon={faStar} />
                  <FontAwesomeIcon icon={faStar} />
                  <FontAwesomeIcon icon={faStarHalfAlt} />
                </div> */}
                <button type="button" className="btn">
                view products
                </button>
              </div>
            </SwiperSlide>
            <SwiperSlide>
              <div className="box">
                <img src="image/d.jpg" alt="" />
                <h3>Dairy</h3>
                {/* <div className="price">Rs 1100/ -- 1400/-</div>
                <div className="stars">
                  <FontAwesomeIcon icon={faStar} />
                  <FontAwesomeIcon icon={faStar} />
                  <FontAwesomeIcon icon={faStar} />
                  <FontAwesomeIcon icon={faStar} />
                  <FontAwesomeIcon icon={faStarHalfAlt} />
                </div> */}
                <button type="button" className="btn">
                view products
                </button>
              </div>
            </SwiperSlide>
            <SwiperSlide>
              <div className="box">
                <img src="image/me.jpg" alt="" />
                <h3>Meat and Seafood</h3>
                {/* <div className="price">Rs 240/ -- 320/-</div>
                <div className="stars">
                  <FontAwesomeIcon icon={faStar} />
                  <FontAwesomeIcon icon={faStar} />
                  <FontAwesomeIcon icon={faStar} />
                  <FontAwesomeIcon icon={faStar} />
                  <FontAwesomeIcon icon={faStarHalfAlt} />
                </div> */}
                <button type="button" className="btn">
                view products
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
                <img src="image/fro.jpg" alt="" />
                <h3>Frozen Foods</h3>
                {/* <div className="price">Rs 200/ -- 240/-</div>
                <div className="stars">
                  <FontAwesomeIcon icon={faStar} />
                  <FontAwesomeIcon icon={faStar} />
                  <FontAwesomeIcon icon={faStar} />
                  <FontAwesomeIcon icon={faStar} />
                  <FontAwesomeIcon icon={faStarHalfAlt} />
                </div> */}
                <button type="button" className="btn">
                view products
                </button>
              </div>
            </SwiperSlide>
            <SwiperSlide>
              <div className="box">
                <img src="image/beverage.jpg" alt="" width={250} />
                <h3>Beverage</h3>
                {/* <div className="price">Rs 45/ -- 65/-</div>
                <div className="stars">
                  <FontAwesomeIcon icon={faStar} />
                  <FontAwesomeIcon icon={faStar} />
                  <FontAwesomeIcon icon={faStar} />
                  <FontAwesomeIcon icon={faStar} />
                  <FontAwesomeIcon icon={faStarHalfAlt} />
                </div> */}
                <button type="button" className="btn">
                view products
                </button>
              </div>
            </SwiperSlide>
            <SwiperSlide>
              <div className="box">
                <img src="image/sn.jpg" alt="" />
                <h3>Snacks</h3>
                {/* <div className="price">Rs 150/ -- 200/-</div>
                <div className="stars">
                  <FontAwesomeIcon icon={faStar} />
                  <FontAwesomeIcon icon={faStar} />
                  <FontAwesomeIcon icon={faStar} />
                  <FontAwesomeIcon icon={faStar} />
                  <FontAwesomeIcon icon={faStarHalfAlt} />
                </div> */}
                <button type="button" className="btn">
                view products
                </button>
              </div>
            </SwiperSlide>
            <SwiperSlide>
              <div className="box">
                <img src="image/beauty.jpg" alt="" />
                <h3>Health and Beauty</h3>
                {/* <div className="price">Rs 90/ -- 200/-</div>
                <div className="stars">
                  <FontAwesomeIcon icon={faStar} />
                  <FontAwesomeIcon icon={faStar} />
                  <FontAwesomeIcon icon={faStar} />
                  <FontAwesomeIcon icon={faStar} />
                  <FontAwesomeIcon icon={faStarHalfAlt} />
                </div> */}
                <button type="button" className="btn">
                view products
                </button>
              </div>
            </SwiperSlide>
          </Swiper>
        </div>
      </div>
    </section>
  );
}
