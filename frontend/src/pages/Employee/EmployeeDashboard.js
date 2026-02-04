import React from "react";

const EmployeeDashboard = () => {
  return (
    <div className="flex flex-col min-h-screen">
      <div className="bg-blue-600 text-white p-4 text-xl font-semibold">
        Employee Dashboard
      </div>

      <div className="flex flex-1">
        <div className="w-60 bg-gray-800 text-white p-6">
          <ul className="space-y-4">
            <li className="font-semibold">My Profile</li>
            <li className="font-semibold">My Attendance</li>
            <li className="font-semibold">My Payroll</li>
            <li className="font-semibold">My Leave Requests</li>
          </ul>
        </div>

        <div className="flex-1 bg-gray-100 p-6">
          <h1 className="text-3xl font-bold mb-6">Welcome Employee!</h1>

          <div className="grid grid-cols-3 gap-6 mb-6">
            <div className="bg-yellow-500 p-6 rounded-xl shadow text-white">
              <h2 className="text-xl font-semibold">My Attendance</h2>
              <p className="text-3xl mt-2">92%</p>
            </div>

            <div className="bg-indigo-500 p-6 rounded-xl shadow text-white">
              <h2 className="text-xl font-semibold">Leaves Taken</h2>
              <p className="text-3xl mt-2">4 Days</p>
            </div>

            <div className="bg-teal-500 p-6 rounded-xl shadow text-white">
              <h2 className="text-xl font-semibold">Last Payroll</h2>
              <p className="text-3xl mt-2">₹42,000</p>
            </div>
          </div>

          <div className="bg-white shadow rounded-xl p-6">
            <h2 className="text-2xl font-semibold mb-4">Employee Summary</h2>
            <p className="text-gray-700 mb-2">
              • View your attendance, payroll, and leave history easily.
            </p>
            <p className="text-gray-700 mb-2">
              • Keep track of upcoming holidays & announcements.
            </p>
            <p className="text-gray-700">
              • Access all employee-related features in one place.
            </p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default EmployeeDashboard;
