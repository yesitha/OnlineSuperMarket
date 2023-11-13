import React from 'react';
import Header from './components/Customer/Header';
import Banner from './components/Customer/Banner';
import Features from './components/Customer/Features';
import Products from './components/Customer/Products';
import ProductCategory from './components/Customer/ProductCategory';
import Footer from './components/Customer/Footer';
import './App.css';

function CustomerPage() {
  return (
    <div className="app">
      <Header />
      <Banner />
      <Features />
      <Products />
      <ProductCategory/>
      <Footer />
    </div>
  );
}

export default CustomerPage;
