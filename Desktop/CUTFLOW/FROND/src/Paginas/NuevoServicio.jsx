import "./NuevoServicio.css";
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import Sidebar from "../Components/Sidebar";
import Modal from "../Components/Modal";

function NuevoServicio() {
  const navigate = useNavigate();

  const [categoria, setCategoria] = useState("CORTE");

  // inputs
  const [nombre, setNombre] = useState("");
  const [descripcion, setDescripcion] = useState("");
  const [precio, setPrecio] = useState("");
  const [duracion, setDuracion] = useState("45");

  // modal
  const [modal, setModal] = useState({
    visible: false,
    tipo: "",
    mensaje: "",
  });

  // 🔥 guardar servicio
  const handleSubmit = async () => {
    const servicio = {
      nombre,
      descripcion,
      precio: parseFloat(precio),
      duracion: parseInt(duracion),
      clasificacion: categoria,
    };

    try {
      const res = await fetch("http://localhost:8080/api/servicios", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(servicio),
      });

      if (!res.ok) throw new Error();

      await res.json();

      setModal({
        visible: true,
        tipo: "success",
        mensaje: "Servicio registrado correctamente",
      });

    } catch (error) {
      setModal({
        visible: true,
        tipo: "error",
        mensaje: "Error al registrar el servicio",
      });
    }
  };

  return (
    <div className="container">
      {/* Sidebar reutilizable */}
      <Sidebar />

      {/* Main */}
      <main className="main">
        <div className="top">
          <span className="back" onClick={() => navigate("/servicios")}>
            ← Volver al catálogo
          </span>
          <h2>Añadir Nuevo Servicio</h2>
          <p className="sub">Ampliando nuestra tradición</p>
        </div>

        <div className="form">

          <label>Nombre del servicio</label>
          <input
            value={nombre}
            onChange={(e) => setNombre(e.target.value)}
            placeholder="Ej: Recorte de Bigote Estilo Inglés"
          />

          <label>Descripción del ritual</label>
          <textarea
            value={descripcion}
            onChange={(e) => setDescripcion(e.target.value)}
            placeholder="Detalle los pasos, técnicas y productos..."
          />

          <div className="row">
            <div>
              <label>Precio (MXN)</label>
              <input
                type="number"
                value={precio}
                onChange={(e) => setPrecio(e.target.value)}
                placeholder="$ 0.00"
              />
            </div>

            <div>
              <label>Duración estimada</label>
              <select
                value={duracion}
                onChange={(e) => setDuracion(e.target.value)}
              >
                <option value="30">30 Minutos</option>
                <option value="45">45 Minutos</option>
                <option value="60">60 Minutos</option>
              </select>
            </div>
          </div>

          <label>Clasificación</label>
          <div className="categorias">
            <button
              className={categoria === "CORTE" ? "active" : ""}
              onClick={() => setCategoria("CORTE")}
              type="button"
            >
              Corte / Cabello
            </button>

            <button
              className={categoria === "BARBA" ? "active" : ""}
              onClick={() => setCategoria("BARBA")}
              type="button"
            >
              Barba / Afeitado
            </button>

            <button
              className={categoria === "COMPLETO" ? "active" : ""}
              onClick={() => setCategoria("COMPLETO")}
              type="button"
            >
              Paquete Completo
            </button>
          </div>

          <div className="acciones">
            <button
              className="descartar"
              onClick={() => navigate("/servicios")}
              type="button"
            >
              DESCARTAR
            </button>

            <button
              className="guardar"
              onClick={handleSubmit}
              type="button"
            >
              REGISTRAR SERVICIO
            </button>
          </div>

        </div>
      </main>

      {/* 🔥 MODAL REUTILIZABLE */}
      <Modal
        visible={modal.visible}
        tipo={modal.tipo}
        mensaje={modal.mensaje}
        onClose={() => {
          setModal({ ...modal, visible: false });

          if (modal.tipo === "success") {
            navigate("/servicios");
          }
        }}
      />
    </div>
  );
}

export default NuevoServicio;