import React from "react";

const DashboardCard = ({ title, value, color }) => {
  return (
    <div className={`p-6 rounded shadow text-white ${color}`}>
      <h3 className="text-lg font-semibold">{title}</h3>
      <p className="text-2xl mt-2">{value}</p>
    </div>
  );
};

export default DashboardCard;
