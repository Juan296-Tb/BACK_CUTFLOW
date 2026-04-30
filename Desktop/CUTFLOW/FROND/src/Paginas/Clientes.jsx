import "./Clientes.css";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import Sidebar from "../Components/Sidebar";

function Clientes() {
  const navigate = useNavigate();

  const [clientes, setClientes] = useState([]);
  const [selected, setSelected] = useState(null);
  const [search, setSearch] = useState("");

  // 🔥 Cargar clientes
  useEffect(() => {
    fetch("http://localhost:8080/api/clientes")
      .then((res) => res.json())
      .then((data) => setClientes(data))
      .catch((err) => console.error("Error cargando clientes:", err));
  }, []);

  // 🔎 filtro simple (sin romper nada)
  const filtrados = clientes.filter((c) =>
    c.nombre.toLowerCase().includes(search.toLowerCase())
  );

  return (
    <div className="container">
      <Sidebar />

      <main className="main">

        {/* HEADER */}
        <div className="header">
          <div>
            <h2>Directorio de Clientes</h2>
            <p className="sub">Fieles Caballeros de la Casa</p>
          </div>

          <div className="acciones-top">
            <input
              placeholder="🔍 Buscar caballero..."
              value={search}
              onChange={(e) => setSearch(e.target.value)}
            />

            <button
              className="btn"
              onClick={() => navigate("/nuevo-cliente")}
            >
              REGISTRAR NUEVO
            </button>
          </div>
        </div>

        {/* LISTA */}
        <div className="lista">
          {filtrados.map((c) => (
            <div className="card" key={c.id}>

              <div className="col info">
                <h3>{c.nombre}</h3>
                <p>{c.telefono}</p>
                <small>{c.correo}</small>
              </div>

              <div className="col pref">
                <span>Servicio</span>
                <p>{c.preferencias}</p>
              </div>

              <div className="col pref">
                <span>Barbero</span>
                <p>{c.barberoPreferido || "No definido"}</p>
              </div>

              <div className="col action">
                <button onClick={() => setSelected(c)}>
                  Ver perfil
                </button>
              </div>

            </div>
          ))}
        </div>

      </main>

      {/* MODAL */}
      {selected && (
        <div className="modal-overlay">
          <div className="modal">

            <div className="modal-header">
              <h3>{selected.nombre}</h3>
              <span onClick={() => setSelected(null)}>✖</span>
            </div>

            <div className="perfil-grid">
              <p><b>📞</b> {selected.telefono}</p>
              <p><b>✉️</b> {selected.correo}</p>
              <p><b>💈 Servicio:</b> {selected.preferencias}</p>
              <p><b>👤 Barbero:</b> {selected.barberoPreferido || "No definido"}</p>
            </div>

            <div className="notas">
              <h4>Notas</h4>
              <p>{selected.notas || "Sin notas registradas"}</p>
            </div>

            <button onClick={() => setSelected(null)}>
              Cerrar
            </button>

          </div>
        </div>
      )}

    </div>
  );
}

export default Clientes;