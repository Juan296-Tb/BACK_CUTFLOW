import "./NuevoCliente.css";
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import Sidebar from "../Components/Sidebar";
import Modal from "../Components/Modal";

function NuevoCliente() {
  const navigate = useNavigate();

  const [form, setForm] = useState({
    nombre: "",
    telefono: "",
    correo: "",
    preferencias: "",
    barberoPreferido: "",
    notas: ""
  });

  const [modal, setModal] = useState({
    visible: false,
    mensaje: "",
    tipo: "info"
  });

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value
    });
  };

  const guardarCliente = async () => {
    if (!form.nombre || !form.correo || !form.preferencias) {
      setModal({
        visible: true,
        mensaje: "Completa los campos obligatorios",
        tipo: "error"
      });
      return;
    }

    try {
      const res = await fetch("http://localhost:8080/api/clientes", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(form)
      });

      if (!res.ok) {
        const error = await res.text();
        throw new Error(error);
      }

      await res.json();

      setModal({
        visible: true,
        mensaje: "Cliente creado correctamente",
        tipo: "success"
      });

    } catch (error) {
      setModal({
        visible: true,
        mensaje: error.message,
        tipo: "error"
      });
    }
  };

  return (
    <div className="container">
      <Sidebar />

      <main className="main">

        {/* 🔥 TU DISEÑO ORIGINAL */}
        <div className="top">
          <span className="back" onClick={() => navigate("/clientes")}>
            ← VOLVER AL DIRECTORIO
          </span>

          <div className="titles">
            <h2>Registrar Nuevo Caballero</h2>
            <p>Uniéndose a nuestra distinguida clientela</p>
          </div>
        </div>

        <div className="form">

          <label>Nombre completo</label>
          <div className="input-icon">
            <input
              name="nombre"
              value={form.nombre}
              onChange={handleChange}
              placeholder="Ej: Fernando Valdés"
            />
            <span>👤</span>
          </div>

          <div className="row">
            <div>
              <label>Teléfono</label>
              <div className="input-icon">
                <input
                  name="telefono"
                  value={form.telefono}
                  onChange={handleChange}
                  placeholder="Ej: +57 300 000 0000"
                />
                <span>📞</span>
              </div>
            </div>

            <div>
              <label>Correo electrónico</label>
              <div className="input-icon">
                <input
                  name="correo"
                  value={form.correo}
                  onChange={handleChange}
                  placeholder="Ej: correo@mail.com"
                />
                <span>✉️</span>
              </div>
            </div>
          </div>

          <label>Preferencias de servicio</label>
          <div className="row">
            <select
              name="preferencias"
              value={form.preferencias}
              onChange={handleChange}
            >
              <option value="">Seleccione...</option>
              <option value="CORTE">Corte</option>
              <option value="BARBA">Barba</option>
              <option value="AFEITADO">Afeitado</option>
            </select>

            <select
              name="barberoPreferido"
              value={form.barberoPreferido}
              onChange={handleChange}
            >
              <option value="">Sin barbero</option>
              <option value="DON_CARLOS">Don Carlos</option>
            </select>
          </div>

          <label>Notas adicionales</label>
          <textarea
            name="notas"
            value={form.notas}
            onChange={handleChange}
            placeholder="Alergias, estilo particular..."
          />

          <div className="acciones">
            <button
              className="cancelar"
              onClick={() => navigate("/clientes")}
            >
              CANCELAR
            </button>

            <button
              className="guardar"
              onClick={guardarCliente}
            >
              GUARDAR REGISTRO
            </button>
          </div>

        </div>
      </main>

      <Modal
        visible={modal.visible}
        tipo={modal.tipo}
        mensaje={modal.mensaje}
        onClose={() => {
          setModal({ ...modal, visible: false });

          if (modal.tipo === "success") {
            navigate("/clientes");
          }
        }}
      />
    </div>
  );
}

export default NuevoCliente;