import React, { useEffect, useState } from "react";
import Sidebar from "../../components/Sidebar";
import Navbar from "../../components/Navbar";
import api from "../../services/api";
import { isAdmin } from "../../services/auth";
import { Navigate } from "react-router-dom";
import { BarChart, Bar, XAxis, YAxis, Tooltip, CartesianGrid } from "recharts";

const AuditLogs = () => {
  const [logs, setLogs] = useState([]);
  const [chartData, setChartData] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const entriesPerPage = 15;

  useEffect(() => {
    const fetchLogs = async () => {
      try {
        const res = await api.get("/audit-logs");
        setLogs(res.data);

        // Group by entity for chart
        const grouped = res.data.reduce((acc, log) => {
          const entity = log.entity || "Unknown";
          acc[entity] = (acc[entity] || 0) + 1;
          return acc;
        }, {});
        const chartArr = Object.entries(grouped).map(([name, count]) => ({ name, count }));
        setChartData(chartArr);
      } catch (err) {
        console.error("Error fetching audit logs:", err);
      }
    };

    fetchLogs();
  }, []);

  if (!isAdmin()) return <Navigate to="/not-authorized" />;

  const formatDate = (dateString) => {
    const d = new Date(dateString);
    return isNaN(d) ? "N/A" : d.toLocaleString();
  };

  // Pagination calculations
  const totalPages = Math.ceil(logs.length / entriesPerPage);
  const indexOfLastEntry = currentPage * entriesPerPage;
  const indexOfFirstEntry = indexOfLastEntry - entriesPerPage;
  const currentLogs = logs.slice(indexOfFirstEntry, indexOfLastEntry);

  return (
    <div className="flex flex-col min-h-screen">
      <Navbar />
      <div className="flex flex-1">
        <Sidebar />
        <div className="flex-1 bg-gray-100 p-6">
          <h1 className="text-3xl font-bold mb-6">Audit Logs</h1>

          {/* Chart */}
          <div className="mb-6 bg-white p-5 rounded-2xl shadow-md">
            <h2 className="text-xl font-semibold mb-3">Audit Logs Overview</h2>
            <BarChart width={600} height={300} data={chartData}>
              <CartesianGrid strokeDasharray="3 3" />
              <XAxis dataKey="name" />
              <YAxis />
              <Tooltip />
              <Bar dataKey="count" fill="#2563eb" />
            </BarChart>
          </div>

          {/* Table */}
          <table className="min-w-full bg-white shadow rounded">
            <thead>
              <tr className="bg-gray-200 text-left">
                <th className="p-3 border">User ID</th>
                <th className="p-3 border">Entity</th>
                <th className="p-3 border">Action</th>
                <th className="p-3 border">Timestamp</th>
              </tr>
            </thead>
            <tbody>
              {currentLogs.map((log) => (
                <tr key={log.audit_id} className="border-t">
                  <td className="p-3">{log.userId || "N/A"}</td>
                  <td className="p-3">{log.entity || "Unknown"}</td>
                  <td className="p-3">{log.action}</td>
                  <td className="p-3">{formatDate(log.createdAt)}</td>
                </tr>
              ))}
            </tbody>
          </table>

          {/* Pagination Controls */}
          <div className="flex justify-center mt-4 gap-2">
            <button
              onClick={() => setCurrentPage((prev) => Math.max(prev - 1, 1))}
              disabled={currentPage === 1}
              className="px-3 py-1 bg-gray-300 rounded disabled:opacity-50"
            >
              Previous
            </button>

            {[...Array(totalPages)].map((_, i) => (
              <button
                key={i}
                onClick={() => setCurrentPage(i + 1)}
                className={`px-3 py-1 rounded ${
                  currentPage === i + 1 ? "bg-blue-600 text-white" : "bg-gray-200"
                }`}
              >
                {i + 1}
              </button>
            ))}

            <button
              onClick={() => setCurrentPage((prev) => Math.min(prev + 1, totalPages))}
              disabled={currentPage === totalPages}
              className="px-3 py-1 bg-gray-300 rounded disabled:opacity-50"
            >
              Next
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AuditLogs;
