import "./Servicios.css";
import { useNavigate } from "react-router-dom";
import Sidebar from "../Components/Sidebar";
import { useEffect, useState } from "react";

function Servicios() {
  const navigate = useNavigate();

  const [servicios, setServicios] = useState([]);

  // 🔥 modal editar
  const [editModal, setEditModal] = useState({
    visible: false,
    servicio: null,
  });

  // 🔥 form editar
  const [form, setForm] = useState({
    nombre: "",
    descripcion: "",
    precio: "",
    duracion: "",
  });

  // cargar servicios
  useEffect(() => {
    cargarServicios();
  }, []);

  const cargarServicios = async () => {
    try {
      const res = await fetch("http://localhost:8080/api/servicios");
      const data = await res.json();
      setServicios(data);
    } catch (error) {
      console.error(error);
    }
  };

  // abrir modal
  const abrirEditar = (servicio) => {
    setEditModal({
      visible: true,
      servicio,
    });

    setForm({
      nombre: servicio.nombre,
      descripcion: servicio.descripcion,
      precio: servicio.precio,
      duracion: servicio.duracion,
    });
  };

  // guardar cambios
  const guardarCambios = async () => {
    try {
      await fetch(
        `http://localhost:8080/api/servicios/${editModal.servicio.id}`,
        {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            ...form,
            precio: parseFloat(form.precio),
            duracion: parseInt(form.duracion),
            clasificacion: editModal.servicio.clasificacion,
          }),
        }
      );

      setEditModal({ visible: false, servicio: null });

      cargarServicios(); // refresh lista
    } catch (error) {
      console.error("Error actualizando:", error);
    }
  };

  return (
    <div className="container">
      <Sidebar />

      <main className="main">
        <div className="header">
          <h2>Servicios</h2>

          <button
            className="btn"
            onClick={() => navigate("/nuevo-servicio")}
          >
            AÑADIR SERVICIO
          </button>
        </div>

        <div className="lista">
          {servicios.map((servicio) => (
            <div className="card" key={servicio.id}>

              <div className="precio">
                ${servicio.precio}
                <span>MXN</span>
              </div>

              <div className="info">
                <h3>{servicio.nombre}</h3>
                <p>{servicio.descripcion}</p>
              </div>

              <div className="extra">
                <span>{servicio.duracion} min</span>

                <button
                  className="editar"
                  onClick={() => abrirEditar(servicio)}
                >
                  EDITAR
                </button>
              </div>

            </div>
          ))}
        </div>
      </main>

      {/* 🔥 MODAL EDITAR */}
      {editModal.visible && (
        <div className="modal-overlay">
          <div className="modal info">

            <h3>Editar Servicio</h3>

            <input
              value={form.nombre}
              onChange={(e) =>
                setForm({ ...form, nombre: e.target.value })
              }
              placeholder="Nombre"
            />

            <textarea
              value={form.descripcion}
              onChange={(e) =>
                setForm({ ...form, descripcion: e.target.value })
              }
              placeholder="Descripción"
            />

            <input
              type="number"
              value={form.precio}
              onChange={(e) =>
                setForm({ ...form, precio: e.target.value })
              }
              placeholder="Precio"
            />

            <select
              value={form.duracion}
              onChange={(e) =>
                setForm({ ...form, duracion: e.target.value })
              }
            >
              <option value="30">30 min</option>
              <option value="45">45 min</option>
              <option value="60">60 min</option>
            </select>

            <div style={{ marginTop: "15px" }}>
              <button onClick={guardarCambios}>
                GUARDAR
              </button>

              <button
                onClick={() =>
                  setEditModal({ visible: false, servicio: null })
                }
                style={{ marginLeft: "10px" }}
              >
                CANCELAR
              </button>
            </div>

          </div>
        </div>
      )}

    </div>
  );
}

export default Servicios;