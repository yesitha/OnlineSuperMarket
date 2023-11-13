import React from "react";
import Header2 from "./components/InventoryManager/Header2";
import Banner2 from './components/InventoryManager/Banner2';
import Features2 from './components/InventoryManager/Features2';
import Products2 from './components/InventoryManager/Products2';
import Footer2 from './components/InventoryManager/Footer2';
import './App.css';

function InventoryPage() {
    return (
        <div className="app">
        <Header2 />
        <Banner2 />
        <Features2 />
        <Products2 />
        <Footer2 />
        </div>
    );
    }

export default InventoryPage;