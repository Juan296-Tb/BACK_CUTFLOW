import "./NuevoBarbero.css";
import { useNavigate } from "react-router-dom";
import Sidebar from "../Components/Sidebar";
import { useState } from "react";

function NuevoBarbero() {
  const navigate = useNavigate();

  const [form, setForm] = useState({
    nombre: "",
    telefono: "",
    especialidad: "Afeitado y Barba",
    horaEntrada: "09:00",
    horaSalida: "17:00",
    biografia: "",
  });

  const guardarBarbero = async () => {
    try {
      await fetch("http://localhost:8080/api/barberos", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(form),
      });
      navigate("/barberos");
    } catch (error) {
      console.error("Error guardando barbero:", error);
    }
  };

  return (
    <div className="container">
      <Sidebar />

      <main className="main">

        <div className="back-link" onClick={() => navigate("/barberos")}>
          ← &nbsp; VOLVER AL DIRECTORIO
        </div>

        <div className="page-header">
          <h2>Registrar Nuevo Barbero</h2>
          <span className="page-subtitle">Integrando un nuevo maestro a la casa</span>
        </div>

        <div className="form-card">

          {/* Nombre */}
          <div className="field-group">
            <label className="field-label">Nombre Completo</label>
            <input
              className="field-input"
              type="text"
              placeholder="Ej: Julián 'Navaja' Ramírez"
              value={form.nombre}
              onChange={(e) => setForm({ ...form, nombre: e.target.value })}
            />
          </div>

          {/* Teléfono + Especialidad */}
          <div className="field-row">
            <div>
              <label className="field-label">Teléfono de Contacto</label>
              <input
                className="field-input"
                type="text"
                placeholder="Ej: +52 55 0000 0000"
                value={form.telefono}
                onChange={(e) => setForm({ ...form, telefono: e.target.value })}
              />
            </div>
            <div>
              <label className="field-label">Especialidad Principal</label>
              <select
                className="field-input"
                value={form.especialidad}
                onChange={(e) => setForm({ ...form, especialidad: e.target.value })}
              >
                <option>Afeitado y Barba</option>
                <option>Corte Clásico</option>
                <option>Cortes Modernos</option>
                <option>Coloración</option>
                <option>Tratamientos Capilares</option>
              </select>
            </div>
          </div>

          {/* Horario */}
          <div className="field-group">
            <label className="field-label">Horario de Disponibilidad</label>
            <div className="field-row" style={{ marginBottom: 0 }}>
              <div>
                <span className="sub-label">Hora de Entrada</span>
                <input
                  className="field-input"
                  type="time"
                  value={form.horaEntrada}
                  onChange={(e) => setForm({ ...form, horaEntrada: e.target.value })}
                />
              </div>
              <div>
                <span className="sub-label">Hora de Salida</span>
                <input
                  className="field-input"
                  type="time"
                  value={form.horaSalida}
                  onChange={(e) => setForm({ ...form, horaSalida: e.target.value })}
                />
              </div>
            </div>
          </div>

          {/* Biografía */}
          <div className="field-group">
            <label className="field-label">Biografía o Notas del Maestro</label>
            <textarea
              className="field-input"
              placeholder="Años de experiencia, estilo preferido, herramientas que utiliza..."
              value={form.biografia}
              onChange={(e) => setForm({ ...form, biografia: e.target.value })}
            />
          </div>

          <hr className="divider" />

          <div className="actions">
            <button className="btn-cancel" onClick={() => navigate("/barberos")}>
              CANCELAR
            </button>
            <button className="btn-save" onClick={guardarBarbero}>
              GUARDAR REGISTRO
            </button>
          </div>

        </div>
      </main>
    </div>
  );
}

export default NuevoBarbero;