import "./Modal.css";

function Modal({ visible, tipo = "info", mensaje, onClose }) {
  if (!visible) return null;

  return (
    <div className="modal-overlay">
      <div className={`modal ${tipo}`}>
        <h3>
          {tipo === "success"
            ? "Éxito"
            : tipo === "error"
            ? "Error"
            : "Información"}
        </h3>

        <p>{mensaje}</p>

        <button onClick={onClose}>
          ACEPTAR
        </button>
      </div>
    </div>
  );
}

export default Modal;