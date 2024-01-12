// StarRating.js
import React from "react";
import PropTypes from "prop-types";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faStar } from "@fortawesome/free-solid-svg-icons";

const StarRating = ({ rating }) => {
  const maxStars = 5;
  const fullStars = Math.floor(rating);
  const halfStar = rating - fullStars >= 0.5;

  const starIcons = [];

  for (let i = 0; i < fullStars; i++) {
    starIcons.push(<FontAwesomeIcon key={i} icon={faStar} color="gold" />);
  }

  if (halfStar) {
    starIcons.push(<FontAwesomeIcon key="half" icon={faStar} half color="gold" />);
  }

  const emptyStars = maxStars - fullStars - (halfStar ? 1 : 0);

  for (let i = 0; i < emptyStars; i++) {
    starIcons.push(<FontAwesomeIcon key={`empty-${i}`} icon={faStar} color="gray" />);
  }

  return <div>{starIcons}</div>;
};

StarRating.propTypes = {
  rating: PropTypes.number.isRequired,
};

export default StarRating;
