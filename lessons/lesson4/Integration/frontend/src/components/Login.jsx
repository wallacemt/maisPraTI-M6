import { useState } from "react";
import api from "../services/api";

const Login = ({ onLogin }) => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const handleLogin = async (event) => {
    event.preventDefault();
    try {
      await api.post("/login", {}, { auth: { username, password } });
      onLogin();
    } catch (error) {
      setError("Credenciais inválidas");
    }
  };

  return (
    <div className="d-flex justify-content-center align-items-center vh-100 vw-100 bg-black">
      <div className="card p-4 shadow-lg" style={{ width: "400px" }}>
        <h2 className="text-center mb-4">Login</h2>
        {error && <p className="text-center text-danger">{error}</p>}
        <form onSubmit={handleLogin} className="d-flex flex-column justify-content-center align-items-center">
          <div className="mb-3">
            <label className="form-label">Usuário</label>
            <input
              type="text"
              className="form-control"
              value={username}
              onChange={(event) => setUsername(event.target.value)}
              placeholder="Digite seu usuário"
            />
          </div>
          <div className="mb-3">
            <label className="form-label">Senha</label>
            <input
              type="password"
              className="form-control"
              value={password}
              onChange={(event) => setPassword(event.target.value)}
              placeholder="Digite sua senha"
            />
          </div>
          <button type="submit" className="btn btn-primary w-100">
            Entrar
          </button>
        </form>
      </div>
    </div>
  );
};

export default Login;
