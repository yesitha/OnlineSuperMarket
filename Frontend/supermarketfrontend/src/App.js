import React from 'react';
import Header from './components/Header';
import Banner from './components/Banner';
import Features from './components/Features';
import Products from './components/Products';
import Footer from './components/Footer';
import './App.css';

function App() {
  return (
    <div className="app">
      <Header />
      <Banner />
      <Features />
      <Products />
      <Footer />
    </div>
  );
}

export default App;
