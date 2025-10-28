import React from "react";
import { Link } from "react-router-dom";

const NotAuthorized = () => {
  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-100">
      <div className="text-center relative">
        {/* Blurry text */}
        <h1 className="text-8xl font-bold text-gray-300 blur-sm animate-pulse">
          403
        </h1>
        <h2 className="text-3xl font-semibold mt-4 text-gray-400 blur-sm animate-pulse">
          Not Authorized
        </h2>

        <p className="mt-6 text-gray-500 text-lg">
          You do not have permission to access this page.
        </p>

        <Link
          to="/"
          className="inline-block mt-6 px-6 py-3 bg-blue-500 text-white font-bold rounded-lg shadow-lg hover:bg-blue-600 transition duration-300"
        >
          Go Home
        </Link>
      </div>

      {/* Extra style for pulsing blur */}
      <style>{`
        @keyframes pulseBlur {
          0%, 100% { filter: blur(2px); }
          50% { filter: blur(6px); }
        }
        .animate-pulse {
          animation: pulseBlur 2s infinite;
        }
      `}</style>
    </div>
  );
};

export default NotAuthorized;
