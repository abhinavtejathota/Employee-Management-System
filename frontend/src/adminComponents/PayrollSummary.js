import React, { useEffect, useState } from "react";
import api from "../services/api";

const PayrollSummary = () => {
  const [payrolls, setPayrolls] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchPayrolls = async () => {
      try {
        const response = await api.get("/payrolls");
        setPayrolls(response.data);
      } catch (err) {
        console.error(err);
        setError("Failed to fetch payroll data.");
      } finally {
        setLoading(false);
      }
    };
    fetchPayrolls();
  }, []);

  return (
    <div className="bg-white p-6 rounded-lg shadow-lg">
      <h2 className="text-2xl font-semibold mb-4">Payroll Summary</h2>
      {loading ? (
        <p>Loading payrolls...</p>
      ) : error ? (
        <p className="text-red-500">{error}</p>
      ) : payrolls.length === 0 ? (
        <p>No payroll data available</p>
      ) : (
        <div className="space-y-4">
          {payrolls.map((payroll) => (
            <div key={payroll.payrollId} className="border p-4 rounded-lg shadow-sm">
              <h3 className="text-lg font-semibold">
                Payroll ID: {payroll.payrollId} | Status: {payroll.status}
              </h3>
              <p>
                Employees:{" "}
                {payroll.payrollItems && payroll.payrollItems.length > 0
                  ? payroll.payrollItems.map((item) => item.employeeId).join(", ")
                  : "N/A"}
              </p>
              <p>Total Salary: ₹{payroll.totalAmount || 0}</p>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default PayrollSummary;
