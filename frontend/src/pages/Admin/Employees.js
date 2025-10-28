import React, { useEffect, useState } from "react";
import Sidebar from "../../components/Sidebar";
import Navbar from "../../components/Navbar";
import api from "../../services/api";
import { isAdmin } from "../../services/auth";
import { Navigate } from "react-router-dom";

const Employees = () => {
  const [employees, setEmployees] = useState([]);
  const [payroll, setPayroll] = useState([]);
  const [departments, setDepartments] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [selectedEmployee, setSelectedEmployee] = useState(null);

  const [newEmployee, setNewEmployee] = useState({
    firstName: "",
    lastName: "",
    dateOfBirth: "",
    gender: "",
    nationality: "",
    departmentId: "",
    jobTitle: "",
    managerId: "",
    status: "Active"
  });

  // Fetch employees
  const fetchEmployees = async () => {
    try {
      const res = await api.get("/employees");
      setEmployees(res.data);
    } catch (err) {
      console.error("Error fetching employees:", err);
    }
  };

  // Fetch payroll items
  const fetchPayroll = async () => {
    try {
      const res = await api.get("/payroll-items");
      setPayroll(res.data);
    } catch (err) {
      console.error("Error fetching payroll:", err);
    }
  };

  // Fetch departments
  const fetchDepartments = async () => {
    try {
      const res = await api.get("/departments");
      setDepartments(res.data);
    } catch (err) {
      console.error("Error fetching departments:", err);
    }
  };

  useEffect(() => {
    fetchEmployees();
    fetchPayroll();
    fetchDepartments();
  }, []);

  const employeesWithSalary = employees.map(emp => {
    const payrollItem = payroll.find(p => p.employeeId === emp.employeeId);
    return { ...emp, netAmount: payrollItem ? payrollItem.netAmount : "N/A" };
  });

  // Add employee
  const handleAddEmployee = async () => {
    if (!newEmployee.firstName || !newEmployee.lastName || !newEmployee.departmentId) {
      alert("Please fill required fields: First Name, Last Name, Department");
      return;
    }
    try {
      await api.post("/employees", newEmployee);
      setNewEmployee({
        firstName: "",
        lastName: "",
        dateOfBirth: "",
        gender: "",
        nationality: "",
        departmentId: "",
        jobTitle: "",
        managerId: "",
        status: "Active"
      });
      fetchEmployees();
    } catch (err) {
      console.error("Error adding employee:", err);
    }
  };

  // Delete employee
  const confirmDelete = (emp) => {
    setSelectedEmployee(emp);
    setShowModal(true);
  };

  const handleDeleteEmployee = async () => {
    if (!selectedEmployee) return;
    try {
      await api.delete(`/employees/${selectedEmployee.employeeId}`);
      setShowModal(false);
      setSelectedEmployee(null);
      fetchEmployees();
    } catch (err) {
      console.error("Error deleting employee:", err);
    }
  };

  if (!isAdmin()) return <Navigate to="/not-authorized" />;

  return (
    <div className="flex flex-col min-h-screen">
      <Navbar />
      <div className="flex flex-1">
        <Sidebar />
        <div className="flex-1 bg-gray-100 p-6">
          <h1 className="text-3xl font-bold mb-6">Employees</h1>

          {/* Add Employee Form */}
          <div className="mb-6 bg-white p-4 shadow rounded">
            <h2 className="text-xl font-semibold mb-4">Add New Employee</h2>
            <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
              <input
                type="text"
                placeholder="First Name *"
                value={newEmployee.firstName}
                onChange={(e) => setNewEmployee({ ...newEmployee, firstName: e.target.value })}
                className="border rounded p-2"
              />
              <input
                type="text"
                placeholder="Last Name *"
                value={newEmployee.lastName}
                onChange={(e) => setNewEmployee({ ...newEmployee, lastName: e.target.value })}
                className="border rounded p-2"
              />
              <input
                type="date"
                placeholder="Date of Birth"
                value={newEmployee.dateOfBirth}
                onChange={(e) => setNewEmployee({ ...newEmployee, dateOfBirth: e.target.value })}
                className="border rounded p-2"
              />
              <input
                type="text"
                placeholder="Gender"
                value={newEmployee.gender}
                onChange={(e) => setNewEmployee({ ...newEmployee, gender: e.target.value })}
                className="border rounded p-2"
              />
              <input
                type="text"
                placeholder="Nationality"
                value={newEmployee.nationality}
                onChange={(e) => setNewEmployee({ ...newEmployee, nationality: e.target.value })}
                className="border rounded p-2"
              />

              {/* Department Dropdown */}
              <select
                value={newEmployee.departmentId}
                onChange={(e) => setNewEmployee({ ...newEmployee, departmentId: e.target.value })}
                className="border rounded p-2"
              >
                <option value="">Select Department *</option>
                {departments.map((dept) => (
                  <option key={dept.departmentId} value={dept.departmentId}>
                    {dept.departmentName}
                  </option>
                ))}
              </select>

              <input
                type="text"
                placeholder="Job Title"
                value={newEmployee.jobTitle}
                onChange={(e) => setNewEmployee({ ...newEmployee, jobTitle: e.target.value })}
                className="border rounded p-2"
              />

              {/* Manager Dropdown */}
              <select
                value={newEmployee.managerId}
                onChange={(e) => setNewEmployee({ ...newEmployee, managerId: e.target.value })}
                className="border rounded p-2"
              >
                <option value="">Select Manager</option>
                {employees.map((emp) => (
                  <option key={emp.employeeId} value={emp.employeeId}>
                    {emp.firstName} {emp.lastName}
                  </option>
                ))}
              </select>
            </div>
            <button
              onClick={handleAddEmployee}
              className="mt-4 bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
            >
              Add Employee
            </button>
          </div>

          {/* Employees Table */}
          <table className="min-w-full bg-white shadow rounded">
            <thead>
              <tr className="bg-gray-200 text-left">
                <th className="p-3 border">ID</th>
                <th className="p-3 border">Name</th>
                <th className="p-3 border">DOB</th>
                <th className="p-3 border">Gender</th>
                <th className="p-3 border">Nationality</th>
                <th className="p-3 border">Department</th>
                <th className="p-3 border">Job Title</th>
                <th className="p-3 border">Manager</th>
                <th className="p-3 border">Status</th>
                <th className="p-3 border">Salary</th>
                <th className="p-3 border">Actions</th>
              </tr>
            </thead>
            <tbody>
              {employeesWithSalary.map((emp) => (
                <tr key={emp.employeeId} className="border-t">
                  <td className="p-3">{emp.employeeId}</td>
                  <td className="p-3">{`${emp.firstName} ${emp.lastName}`}</td>
                  <td className="p-3">{emp.dateOfBirth}</td>
                  <td className="p-3">{emp.gender}</td>
                  <td className="p-3">{emp.nationality}</td>
                  <td className="p-3">{emp.departmentName}</td>
                  <td className="p-3">{emp.jobTitle}</td>
                  <td className="p-3">{emp.managerName || "N/A"}</td>
                  <td className="p-3">{emp.status}</td>
                  <td className="p-3">₹{emp.netAmount}</td>
                  <td className="p-3">
                    <button
                      onClick={() => confirmDelete(emp)}
                      className="bg-red-600 text-white px-3 py-1 rounded hover:bg-red-700"
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>

          {/* Delete Modal */}
          {showModal && (
            <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
              <div className="bg-white rounded shadow-lg p-6 w-96">
                <h2 className="text-xl font-bold mb-4">Confirm Deletion</h2>
                <p className="mb-6">
                  Are you sure you want to delete employee{" "}
                  <span className="font-semibold">
                    {selectedEmployee?.firstName} {selectedEmployee?.lastName}
                  </span>?
                </p>
                <div className="flex justify-end gap-2">
                  <button
                    onClick={() => setShowModal(false)}
                    className="px-4 py-2 rounded border border-gray-300 hover:bg-gray-100"
                  >
                    Cancel
                  </button>
                  <button
                    onClick={handleDeleteEmployee}
                    className="px-4 py-2 rounded bg-red-600 text-white hover:bg-red-700"
                  >
                    Delete
                  </button>
                </div>
              </div>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default Employees;
