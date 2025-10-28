export const getUserRole = () => localStorage.getItem("role");
export const getToken = () => localStorage.getItem("token");
export const isAdmin = () => getUserRole() === "ADMIN";
export const logout = () => localStorage.clear();
