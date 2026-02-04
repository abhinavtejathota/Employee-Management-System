import React from "react";

const ManagerDashboard = () => {
  return (
    <div className="flex flex-col min-h-screen">
      <div className="bg-purple-700 text-white p-4 text-xl font-semibold">
        Manager Dashboard
      </div>

      <div className="flex flex-1">
        <div className="w-60 bg-gray-900 text-white p-6">
          <ul className="space-y-4">
            <li className="font-semibold">Team Overview</li>
            <li className="font-semibold">Attendance Reports</li>
            <li className="font-semibold">Payroll Review</li>
            <li className="font-semibold">Leave Approvals</li>
          </ul>
        </div>

        <div className="flex-1 bg-gray-100 p-6">
          <h1 className="text-3xl font-bold mb-6">Welcome Manager!</h1>

          <div className="grid grid-cols-3 gap-6 mb-6">
            <div className="bg-red-500 p-6 rounded-xl shadow text-white">
              <h2 className="text-xl font-semibold">Team Members</h2>
              <p className="text-3xl mt-2">12</p>
            </div>

            <div className="bg-blue-600 p-6 rounded-xl shadow text-white">
              <h2 className="text-xl font-semibold">Pending Leave Requests</h2>
              <p className="text-3xl mt-2">3</p>
            </div>

            <div className="bg-green-600 p-6 rounded-xl shadow text-white">
              <h2 className="text-xl font-semibold">Payrolls to Review</h2>
              <p className="text-3xl mt-2">2</p>
            </div>
          </div>

          <div className="bg-white shadow rounded-xl p-6">
            <h2 className="text-2xl font-semibold mb-4">Team Summary</h2>
            <p className="text-gray-700 mb-2">
              • Track team performance, attendance, and work status.
            </p>
            <p className="text-gray-700 mb-2">
              • Approve or reject leave requests directly from the dashboard.
            </p>
            <p className="text-gray-700 mb-2">
              • Quickly review payrolls before they are forwarded to Admin.
            </p>
            <p className="text-gray-700">
              • View department-level insights for better management.
            </p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ManagerDashboard;
