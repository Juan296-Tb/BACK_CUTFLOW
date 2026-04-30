import "./Reservaciones.css";
import { useNavigate } from "react-router-dom";
import Sidebar from "../Components/Sidebar";

const citas = [
  {
    hora: "09:00",
    cliente: "Arturo de la Cruz",
    servicio: "Afeitado Clásico a Navaja",
    barbero: "Don Carlos",
  },
  {
    hora: "10:30",
    cliente: "Miguel Sánchez",
    servicio: "Corte Ejecutivo y Arreglo de Barba",
    barbero: "Don Carlos",
  },
];

function Reservaciones() {
  const navigate = useNavigate();

  return (
    <div className="container">
      {/* Sidebar reutilizable */}
      <Sidebar />

      {/* Main */}
      <main className="main">
        <div className="header">
          <div>
            <h2>Reservaciones</h2>
            <span className="date">Octubre 24, 2026</span>
          </div>

          <button className="btn" onClick={() => navigate("/agendar")}>
            AGENDAR NUEVA
          </button>
        </div>

        <div className="citas">
          {citas.map((cita, index) => (
            <div className="card" key={index}>
              <div className="hora">
                {cita.hora}
                <span>AM</span>
              </div>

              <div className="info">
                <h3>{cita.cliente}</h3>
                <p>{cita.servicio}</p>
              </div>

              <div className="extra">
                <span>BARBERO: {cita.barbero}</span>
                <button className="cancelar">CANCELAR</button>
              </div>
            </div>
          ))}
        </div>
      </main>
    </div>
  );
}

export default Reservaciones;