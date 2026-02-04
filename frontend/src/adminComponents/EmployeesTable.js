import React, { useEffect, useState } from "react";
import api from "../services/api";

const EmployeesTable = () => {
  const [employees, setEmployees] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchEmployees = async () => {
      try {
        const response = await api.get("/employees");
        setEmployees(response.data);
      } catch (err) {
        console.error("Error fetching employees:", err);
      } finally {
        setLoading(false);
      }
    };

    fetchEmployees();
  }, []);

  if (loading) return <div>Loading Employees...</div>;

  return (
    <div className="bg-white p-4 rounded shadow col-span-2">
      <h3 className="text-xl font-semibold mb-2">Employees</h3>
      <table className="table-auto w-full border">
        <thead>
          <tr className="bg-gray-200">
            <th className="border px-4 py-2">ID</th>
            <th className="border px-4 py-2">Name</th>
            <th className="border px-4 py-2">Department</th>
            <th className="border px-4 py-2">Role</th>
            <th className="border px-4 py-2">Salary</th>
          </tr>
        </thead>
        <tbody>
          {employees.map((e) => (
            <tr key={e.id}>
              <td className="border px-4 py-2">{e.id}</td>
              <td className="border px-4 py-2">{e.name}</td>
              <td className="border px-4 py-2">{e.department}</td>
              <td className="border px-4 py-2">{e.role}</td>
              <td className="border px-4 py-2">₹{e.salary}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default EmployeesTable;
