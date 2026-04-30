import "./Agendar.css";
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import Sidebar from "../Components/Sidebar";

function Agendar() {
  const navigate = useNavigate();
  const [horaSeleccionada, setHoraSeleccionada] = useState("11:30");

  const horas = [
    { hora: "09:00", estado: "ocupado" },
    { hora: "10:30", estado: "disponible" },
    { hora: "11:30", estado: "disponible" },
    { hora: "13:00", estado: "disponible" },
    { hora: "14:30", estado: "disponible" },
    { hora: "16:00", estado: "disponible" },
    { hora: "17:30", estado: "ocupado" },
    { hora: "19:00", estado: "disponible" },
  ];

  return (
    <div className="container">
      {/* Sidebar reutilizable */}
      <Sidebar />

      <main className="main">
        <span className="volver" onClick={() => navigate("/")}>
          ← VOLVER
        </span>

        <h2>Agendar Nueva Cita</h2>

        <div className="form">
          <h4>INFORMACIÓN DEL CLIENTE</h4>

          <div className="inputs">
            <input placeholder="Buscar cliente..." />
            <input placeholder="Nombre nuevo..." />
          </div>

          <h4>SERVICIO Y BARBERO</h4>
          <div className="inputs">
            <select>
              <option>Corte Clásico</option>
              <option>Barba</option>
            </select>

            <select>
              <option>Don Carlos</option>
              <option>Juan</option>
            </select>
          </div>

          <h4>HORARIOS</h4>
          <div className="horas">
            {horas.map((h, i) => (
              <div
                key={i}
                className={`horaBox 
                  ${h.estado === "ocupado" ? "ocupado" : ""}
                  ${horaSeleccionada === h.hora ? "activa" : ""}`}
                onClick={() =>
                  h.estado !== "ocupado" && setHoraSeleccionada(h.hora)
                }
              >
                {h.hora}
              </div>
            ))}
          </div>

          <div className="acciones">
            <button className="cancelar" onClick={() => navigate("/")}>
              CANCELAR
            </button>
            <button className="confirmar">
              CONFIRMAR RESERVA
            </button>
          </div>
        </div>
      </main>
    </div>
  );
}

export default Agendar;