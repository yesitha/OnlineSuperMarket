// Navbar
import React from 'react';
import PropTypes from 'prop-types';
import './Navbar.css';

export default function Navbar(props) {
  const { active } = props;
  return (
    <nav className={`navbar ${active ? 'active' : ''}`}>
      <a href="#home">home</a>
      <a href="#features">Delivery Persons</a>
      <a href="#products">Orders</a>
      {/* <a href="#footer">contact us</a>
      <a href="#footer">get our updates</a> */}
    </nav>
  );
}
Navbar.propTypes = {
  active: PropTypes.bool,
}.isRequired;
