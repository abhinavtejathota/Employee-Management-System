import React, { useEffect, useState } from "react";
import { BarChart, Bar, XAxis, YAxis, Tooltip, CartesianGrid } from "recharts";
import api from "../services/api";

const AuditLogsChart = () => {
  const [data, setData] = useState([]);

  useEffect(() => {
    const fetchLogs = async () => {
      try {
        const res = await api.get("/audit-logs");
        const grouped = res.data.reduce((acc, log) => {
          const entity = log.entity || "Unknown"; // corrected
          acc[entity] = (acc[entity] || 0) + 1;
          return acc;
        }, {});

        const chartData = Object.entries(grouped).map(([name, count]) => ({ name, count }));
        setData(chartData);
      } catch (err) {
        console.error("Error fetching audit logs:", err);
      }
    };

    fetchLogs();
  }, []);

  return (
    <div className="bg-white p-5 rounded-2xl shadow-md">
      <h2 className="text-xl font-semibold mb-3">Audit Logs Overview</h2>
      <BarChart width={500} height={300} data={data}>
        <CartesianGrid strokeDasharray="3 3" />
        <XAxis dataKey="name" />
        <YAxis />
        <Tooltip />
        <Bar dataKey="count" fill="#2563eb" />
      </BarChart>
    </div>
  );
};

export default AuditLogsChart;
