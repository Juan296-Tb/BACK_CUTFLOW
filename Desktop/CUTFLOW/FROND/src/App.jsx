import { Routes, Route } from "react-router-dom";
import Reservaciones from "./Paginas/Reservaciones";
import Agendar from "./Paginas/Agendar";
import Servicios from "./Paginas/Servicios";
import NuevoServicio from "./Paginas/NuevoServicio";
import Clientes from "./Paginas/Clientes";
import NuevoCliente from "./Paginas/NuevoCliente";
import Barberos from "./Paginas/Barberos";
import NuevoBarbero from "./Paginas/NuevoBarbero";

function App() {
  return (
    <Routes>
      <Route path="/" element={<Reservaciones />} />
      <Route path="/agendar" element={<Agendar />} />
      <Route path="/servicios" element={<Servicios />} />
      <Route path="/nuevo-servicio" element={<NuevoServicio />} />
      <Route path="/clientes" element={<Clientes />} /> 
      <Route path="/nuevo-cliente" element={<NuevoCliente />} />
      <Route path="/barberos" element={<Barberos/>} />
      <Route path="/nuevo-barbero" element={<NuevoBarbero/>} />
    </Routes>
  );
}

export default App;