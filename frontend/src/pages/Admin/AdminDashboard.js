import React, { useEffect, useState } from "react";
import Sidebar from "../../adminComponents/Sidebar";
import Navbar from "../../adminComponents/Navbar";
import DashboardCard from "../../adminComponents/DashboardCards";
import PayrollSummary from "../../adminComponents/PayrollSummary";
import api from "../../services/api";
import { isAdmin } from "../../services/auth";
import { Navigate } from "react-router-dom";

const AdminDashboard = () => {
  const [stats, setStats] = useState({
    totalEmployees: 0,
    totalPayroll: 0,
    totalDepartments: 0
  });

  useEffect(() => {
    const fetchStats = async () => {
      try {
        const [empRes, payrollRes, deptRes] = await Promise.all([
          api.get("/employees"),
          api.get("/payrolls"),
          api.get("/departments")
        ]);

        const totalPayroll = payrollRes.data.reduce(
          (sum, payroll) => sum + (payroll.totalAmount || 0),
          0
        );

        setStats({
          totalEmployees: empRes.data.length,
          totalPayroll,
          totalDepartments: deptRes.data.length
        });
      } catch (err) {
        console.error("Error fetching dashboard stats:", err);
      }
    };
    fetchStats();
  }, []);

  if (!isAdmin()) return <Navigate to="/not-authorized" />;

  return (
    <div className="flex flex-col min-h-screen">
      <Navbar />
      <div className="flex flex-1">
        <Sidebar />
        <div className="flex-1 bg-gray-100 p-6">
          <h1 className="text-3xl font-bold mb-6">Admin Dashboard</h1>

          {/* Dashboard Cards */}
          <div className="grid grid-cols-3 gap-6 mb-6">
            <DashboardCard
              title="Total Employees"
              value={stats.totalEmployees}
              color="bg-green-500"
            />
            <DashboardCard
              title="Total Payroll"
              value={`₹${stats.totalPayroll}`}
              color="bg-blue-500"
            />
            <DashboardCard
              title="Total Departments"
              value={stats.totalDepartments}
              color="bg-purple-500"
            />
          </div>

          {/* Payroll Summary*/}
          <div className="grid grid-cols-2 gap-6">
            <PayrollSummary />
          </div>
        </div>
      </div>
    </div>
  );
};

export default AdminDashboard;
