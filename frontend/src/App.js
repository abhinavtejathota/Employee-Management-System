import './App.css';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./pages/Login";
import AdminDashboard from "./pages/Admin/AdminDashboard";
import Departments from "./pages/Admin/Departments";
import Employees from "./pages/Admin/Employees";
import Payrolls from "./pages/Admin/Payrolls";
import AuditLogs from "./pages/Admin/AuditLogs";
import NotAuthorized from "./pages/NotAuthorized";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/admin/dashboard" element={<AdminDashboard />} />
		<Route path="/admin/departments" element={<Departments />} />
		<Route path="/admin/employees" element={<Employees />} />
		<Route path="/admin/payrolls" element={<Payrolls />} />
		<Route path="/admin/audit-logs" element={<AuditLogs />} />
        <Route path="/not-authorized" element={<NotAuthorized />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
