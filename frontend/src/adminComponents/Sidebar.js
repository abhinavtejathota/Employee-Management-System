import React from "react";
import { NavLink } from "react-router-dom";

const Sidebar = () => {
  return (
    <div className="w-64 bg-gray-800 text-white min-h-screen p-4">
      <h2 className="text-2xl font-bold mb-6">Admin Panel</h2>
      <nav className="flex flex-col gap-3">
        <NavLink
          to="/admin/dashboard"
          className={({ isActive }) =>
            isActive ? "bg-gray-700 px-3 py-2 rounded" : "px-3 py-2 hover:bg-gray-700 rounded"
          }
        >
          Dashboard
        </NavLink>
        <NavLink
          to="/admin/departments"
          className={({ isActive }) =>
            isActive ? "bg-gray-700 px-3 py-2 rounded" : "px-3 py-2 hover:bg-gray-700 rounded"
          }
        >
          Departments
        </NavLink>
        <NavLink
          to="/admin/employees"
          className={({ isActive }) =>
            isActive ? "bg-gray-700 px-3 py-2 rounded" : "px-3 py-2 hover:bg-gray-700 rounded"
          }
        >
          Employees
        </NavLink>
        <NavLink
          to="/admin/payrolls"
          className={({ isActive }) =>
            isActive ? "bg-gray-700 px-3 py-2 rounded" : "px-3 py-2 hover:bg-gray-700 rounded"
          }
        >
          Payroll
        </NavLink>
        <NavLink
          to="/admin/audit-logs"
          className={({ isActive }) =>
            isActive ? "bg-gray-700 px-3 py-2 rounded" : "px-3 py-2 hover:bg-gray-700 rounded"
          }
        >
          Audit Logs
        </NavLink>
      </nav>
    </div>
  );
};

export default Sidebar;
