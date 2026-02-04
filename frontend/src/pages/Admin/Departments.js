import React, { useEffect, useState } from "react";
import Sidebar from "../../adminComponents/Sidebar";
import Navbar from "../../adminComponents/Navbar";
import api from "../../services/api";
import { isAdmin } from "../../services/auth";
import { Navigate } from "react-router-dom";

const Departments = () => {
  const [departments, setDepartments] = useState([]);
  const [newDept, setNewDept] = useState("");
  const [showModal, setShowModal] = useState(false);
  const [selectedDept, setSelectedDept] = useState(null);

  useEffect(() => {
    fetchDepartments();
  }, []);

  const fetchDepartments = async () => {
    try {
      const res = await api.get("/departments");
      setDepartments(res.data);
    } catch (err) {
      console.error("Error fetching departments:", err);
    }
  };

  const handleAddDepartment = async () => {
    if (!newDept.trim()) return;
    try {
      await api.post("/departments", { name: newDept });
      setNewDept("");
      fetchDepartments();
    } catch (err) {
      console.error("Error adding department:", err);
    }
  };

  const confirmDelete = (dept) => {
    setSelectedDept(dept);
    setShowModal(true);
  };

  const handleDeleteDepartment = async () => {
    if (!selectedDept) return;
    try {
      await api.delete(`/departments/${selectedDept.departmentId}`);
      setShowModal(false);
      setSelectedDept(null);
      fetchDepartments();
    } catch (err) {
      console.error("Error deleting department:", err);
    }
  };

  if (!isAdmin()) return <Navigate to="/not-authorized" />;

  return (
    <div className="flex flex-col min-h-screen">
      <Navbar />
      <div className="flex flex-1">
        <Sidebar />
        <div className="flex-1 bg-gray-100 p-6">
          <h1 className="text-3xl font-bold mb-6">Departments</h1>

          <div className="mb-4 flex gap-2">
            <input
              type="text"
              placeholder="Add new department"
              value={newDept}
              onChange={(e) => setNewDept(e.target.value)}
              className="border rounded p-2 flex-1"
            />
            <button
              onClick={handleAddDepartment}
              className="bg-blue-600 text-white px-4 py-2 rounded"
            >
              Add
            </button>
          </div>

          <table className="min-w-full bg-white shadow rounded">
            <thead>
              <tr className="bg-gray-200 text-left">
                <th className="p-3">ID</th>
                <th className="p-3">Department Name</th>
                <th className="p-3">Actions</th>
              </tr>
            </thead>
            <tbody>
              {departments.map((dept) => (
                <tr key={dept.departmentId} className="border-t">
                  <td className="p-3">{dept.departmentId}</td>
                  <td className="p-3">{dept.departmentName}</td>
                  <td className="p-3">
                    <button
                      onClick={() => confirmDelete(dept)}
                      className="bg-red-600 text-white px-3 py-1 rounded hover:bg-red-700"
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>

      {/* Modal */}
      {showModal && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
          <div className="bg-white rounded shadow-lg p-6 w-96">
            <h2 className="text-xl font-bold mb-4">Confirm Deletion</h2>
            <p className="mb-6">
              Are you sure you want to delete the department{" "}
              <span className="font-semibold">{selectedDept?.departmentName}</span>?
            </p>
            <div className="flex justify-end gap-2">
              <button
                onClick={() => setShowModal(false)}
                className="px-4 py-2 rounded border border-gray-300 hover:bg-gray-100"
              >
                Cancel
              </button>
              <button
                onClick={handleDeleteDepartment}
                className="px-4 py-2 rounded bg-red-600 text-white hover:bg-red-700"
              >
                Delete
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default Departments;
