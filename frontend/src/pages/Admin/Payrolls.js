import React, { useEffect, useState } from "react";
import Sidebar from "../../components/Sidebar";
import Navbar from "../../components/Navbar";
import api from "../../services/api";
import { isAdmin } from "../../services/auth";
import { Navigate } from "react-router-dom";

const Payrolls = () => {
  const [payrolls, setPayrolls] = useState([]);

  useEffect(() => {
    const fetchPayrolls = async () => {
      try {
        const res = await api.get("/payrolls");
        setPayrolls(res.data);
      } catch (err) {
        console.error("Error fetching payrolls:", err);
      }
    };
    fetchPayrolls();
  }, []);

  if (!isAdmin()) return <Navigate to="/not-authorized" />;

  return (
    <div className="flex flex-col min-h-screen">
      <Navbar />
      <div className="flex flex-1">
        <Sidebar />
        <div className="flex-1 bg-gray-100 p-6">
          <h1 className="text-3xl font-bold mb-6">Payrolls</h1>

          <table className="min-w-full bg-white shadow rounded">
            <thead>
              <tr className="bg-gray-200 text-left">
                <th className="p-3">ID</th>
                <th className="p-3">Employees</th>
                <th className="p-3">Period</th>
                <th className="p-3">Amount</th>
                <th className="p-3">Status</th>
              </tr>
            </thead>
            <tbody>
              {payrolls.map((p) => (
                <tr key={p.payrollId} className="border-t">
                  <td className="p-3">{p.payrollId}</td>
                  <td className="p-3">
                    {p.payrollItems && p.payrollItems.length > 0
                      ? p.payrollItems.map((item) => item.employeeId).join(", ")
                      : "N/A"}
                  </td>
                  <td className="p-3">
                    {p.periodStart} - {p.periodEnd}
                  </td>
                  <td className="p-3">₹{p.totalAmount || 0}</td>
                  <td className="p-3">{p.status}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default Payrolls;
