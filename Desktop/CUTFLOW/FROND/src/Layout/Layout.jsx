import Sidebar from "../Components/Sidebar";

function Layout({ children }) {
  return (
    <div className="container">
      <Sidebar />
      <main className="main">{children}</main>
    </div>
  );
}

export default Layout;