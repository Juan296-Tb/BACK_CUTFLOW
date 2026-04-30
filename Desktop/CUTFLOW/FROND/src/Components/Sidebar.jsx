// components/Sidebar.jsx
import { useNavigate, useLocation } from "react-router-dom";

function Sidebar() {
  const navigate = useNavigate();
  const location = useLocation();

  return (
    <aside className="sidebar">
      <h1 className="logo">CUTFLOW</h1>
      <p className="est">Est. 2026</p>

      <nav>
        <ul>
          <li
            className={location.pathname === "/" ? "active" : ""}
            onClick={() => navigate("/")}
          >
            Reservaciones
          </li>

          <li
            className={location.pathname.includes("/servicios") ? "active" : ""}
            onClick={() => navigate("/servicios")}
          >
            Servicios
          </li>

          <li
            className={location.pathname === "/clientes" ? "active" : ""}
            onClick={() => navigate("/clientes")}
          >
            Clientes
          </li>

          <li
            className={
              location.pathname.includes("/barberos") ||
              location.pathname.includes("/nuevo-barbero")
                ? "active"
                : ""
            }
            onClick={() => navigate("/barberos")}
          >
            Barberos
          </li>
        </ul>
      </nav>
    </aside>
  );
}

export default Sidebar;