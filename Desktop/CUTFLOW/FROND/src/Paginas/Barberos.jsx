import "./Barberos.css";
import { useNavigate } from "react-router-dom";
import Sidebar from "../Components/Sidebar";

function Barberos() {
  const navigate = useNavigate();

  const barberos = [
    {
      id: 1,
      nombre: "Don Carlos (Maestro)",
      especialidad: "Corte Clásico",
      telefono: "+52 55 11 11 2222",
      horario: "09:00 AM - 05:00 PM",
    },
    {
      id: 2,
      nombre: "Julián 'Navaja' Ramírez",
      especialidad: "Afeitado y Barba",
      telefono: "+52 55 3533 4444",
      horario: "11:00 AM - 07:00 PM",
    },
    {
      id: 3,
      nombre: "Diego Hernández",
      especialidad: "Cortes Modernos",
      telefono: "+52 55 5555 6666",
      horario: "10:00 AM - 06:00 PM",
    },
  ];

  return (
    <div className="container">
      <Sidebar />

      <main className="main">
        <div className="header">
          <div>
            <h2>Directorio de Barberos</h2>
            <p className="sub">Los Maestros de la Casa</p>
          </div>

          <button 
            className="btn"
            onClick={() => navigate("/nuevo-barbero")}
          >
            REGISTRAR BARBERO
          </button>
        </div>

        <div className="lista">
          {barberos.map((b) => (
            <div key={b.id} className="card barbero-card">
              
              <div className="col">
                <span>Especialidad</span>
                <p>{b.especialidad}</p>
              </div>

              <div className="col info">
                <h3>{b.nombre}</h3>
                <p>{b.telefono}</p>
              </div>

              <div className="col">
                <span>Horario</span>
                <p>{b.horario}</p>
              </div>

              <div className="col action">
                <button>EDITAR PERFIL</button>
                <button className="danger">SUSPENDER</button>
              </div>

            </div>
          ))}
        </div>
      </main>
    </div>
  );
}

export default Barberos;