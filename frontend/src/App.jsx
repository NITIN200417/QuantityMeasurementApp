import { useState, useEffect } from "react";

// ─── CONFIG ───────────────────────────────────────────────
const BASE_URL = "http://localhost:8080";
const API_URL = `${BASE_URL}/api/v1/quantity`;

// ─── CONSTANTS ────────────────────────────────────────────
const TYPES = [
  { id: "length",      label: "Length",      icon: "✏️" },
  { id: "weight",      label: "Weight",      icon: "⚖️" },
  { id: "temperature", label: "Temperature", icon: "🌡️" },
  { id: "volume",      label: "Volume",      icon: "🧊" },
];

const UNITS = {
  length:      ["FEET", "INCH", "METER", "KILOMETER", "MILE", "YARD", "CENTIMETER", "MILLIMETER"],
  weight:      ["KILOGRAM", "GRAM", "MILLIGRAM", "POUND", "OUNCE", "TON"],
  temperature: ["CELSIUS", "FAHRENHEIT", "KELVIN"],
  volume:      ["LITER", "MILLILITER", "CUBIC_METER", "GALLON", "FLUID_OUNCE", "CUP"],
};

const OP_ENDPOINTS = { "+": "add", "-": "subtract", "*": "multiply", "/": "divide" };

// ─── STYLES ───────────────────────────────────────────────
const S = {
  wrapper:      { fontFamily: "'Nunito', sans-serif", background: "#f0f2f8", minHeight: "100vh", display: "flex", alignItems: "center", justifyContent: "center", padding: 16 },
  app:          { width: "100%", maxWidth: 680, borderRadius: 16, overflow: "hidden", boxShadow: "0 8px 40px rgba(80,100,200,0.15)", background: "#fff" },
  header:       { background: "#4a63e0", color: "white", textAlign: "center", padding: "20px", fontSize: "1.15rem", fontWeight: 700, display: "flex", alignItems: "center", justifyContent: "center", gap: 12 },
  body:         { padding: "28px 32px 32px", background: "#fafbff" },
  label:        { fontSize: "0.7rem", fontWeight: 800, color: "#aab0cc", letterSpacing: "0.1em", textTransform: "uppercase", marginBottom: 12 },
  typeGrid:     { display: "grid", gridTemplateColumns: "repeat(4,1fr)", gap: 12, marginBottom: 24 },
  typeCard:     (a) => ({ border: `2px solid ${a ? "#4a63e0" : "#e2e6f8"}`, borderRadius: 12, padding: "16px 10px", textAlign: "center", cursor: "pointer", background: a ? "#f5f7ff" : "#fff", transition: "all 0.18s" }),
  actionGroup:  { display: "flex", borderRadius: 8, overflow: "hidden", border: "1.5px solid #e2e6f8", marginBottom: 24, background: "#fff" },
  actionBtn:    (a) => ({ flex: 1, padding: "10px 0", fontSize: "0.82rem", fontWeight: 700, color: a ? "#fff" : "#7a85aa", cursor: "pointer", background: a ? "#4a63e0" : "transparent", border: "none", fontFamily: "'Nunito',sans-serif", transition: "all 0.15s" }),
  row2:         { display: "grid", gridTemplateColumns: "1fr 1fr", gap: 16, marginBottom: 16 },
  inputBox:     { border: "1.5px solid #e2e6f8", borderRadius: 10, background: "#fff", overflow: "hidden" },
  bigInput:     { fontSize: "1.5rem", fontWeight: 800, color: "#222", border: "none", outline: "none", background: "transparent", width: "100%", padding: "10px 14px 4px", fontFamily: "'Nunito',sans-serif" },
  bigInputRead: { fontSize: "1.5rem", fontWeight: 800, color: "#4a63e0", border: "none", outline: "none", background: "transparent", width: "100%", padding: "10px 14px 4px", fontFamily: "'Nunito',sans-serif" },
  unitSelect:   { width: "100%", borderTop: "1.5px solid #e8eaf4", background: "#fff", padding: "8px 14px", fontSize: "0.82rem", color: "#666", fontFamily: "'Nunito',sans-serif", cursor: "pointer", outline: "none", border: "none" },
  arithGrid:    { display: "grid", gridTemplateColumns: "1fr auto 1fr", gap: 12, alignItems: "end", marginBottom: 16 },
  opBtn:        { width: 36, height: 36, borderRadius: "50%", background: "#f0f2f8", border: "1.5px solid #e2e6f8", fontSize: "1.2rem", cursor: "pointer", display: "flex", alignItems: "center", justifyContent: "center" },
  resultBlock:  { borderLeft: "4px solid #4a63e0", background: "#fff", borderRadius: "0 10px 10px 0", padding: "14px 18px", marginTop: 8, display: "flex", alignItems: "center", justifyContent: "space-between", boxShadow: "0 2px 12px rgba(74,99,224,0.07)" },
  resultValue:  { fontSize: "1.6rem", fontWeight: 800, color: "#4a63e0" },
  resultUnitSelect: { border: "1.5px solid #e2e6f8", borderRadius: 8, padding: "6px 12px", fontSize: "0.82rem", background: "#fff", color: "#555", fontFamily: "'Nunito',sans-serif" },
  compareResult:{ background: "#fff", border: "1.5px solid #e2e6f8", borderRadius: 10, padding: "14px 18px", marginTop: 8, display: "flex", alignItems: "center", gap: 10, boxShadow: "0 2px 12px rgba(74,99,224,0.07)" },
  badge:        (ok) => ({ padding: "4px 14px", borderRadius: 20, fontSize: "0.82rem", fontWeight: 700, background: ok ? "#e8ffee" : "#ffe8e8", color: ok ? "#1a8a3a" : "#c0392b" }),
  errBox:       { background: "#fff3f3", border: "1.5px solid #f5c6cb", borderRadius: 10, padding: "12px 16px", color: "#c0392b", fontSize: "0.85rem", marginTop: 8 },
  loginBtn:     { width: "100%", padding: "14px", background: "#4a63e0", color: "#fff", border: "none", borderRadius: 10, fontSize: "1rem", fontWeight: 700, cursor: "pointer", fontFamily: "'Nunito',sans-serif", marginTop: 8 },
  tokenInput:   { flex: 1, padding: "10px 14px", border: "1.5px solid #e2e6f8", borderRadius: 10, fontSize: "0.85rem", fontFamily: "'Nunito',sans-serif", outline: "none" },
  tokenRow:     { display: "flex", gap: 8, marginBottom: 20, marginTop: 8 },
  saveBtn:      { padding: "10px 18px", background: "#4a63e0", color: "#fff", border: "none", borderRadius: 10, fontSize: "0.82rem", fontWeight: 700, cursor: "pointer", fontFamily: "'Nunito',sans-serif", whiteSpace: "nowrap" },
  submitBtn:    { width: "100%", padding: "12px", background: "#4a63e0", color: "#fff", border: "none", borderRadius: 10, fontSize: "0.9rem", fontWeight: 700, cursor: "pointer", fontFamily: "'Nunito',sans-serif", marginBottom: 12 },
  logoutBtn:    { padding: "6px 14px", background: "transparent", color: "white", border: "1.5px solid white", borderRadius: 8, fontSize: "0.78rem", fontWeight: 700, cursor: "pointer", fontFamily: "'Nunito',sans-serif" },
  authBanner:   { marginBottom: 16, padding: "8px 14px", background: "#e8ffee", borderRadius: 8, fontSize: "0.78rem", color: "#1a8a3a", fontWeight: 700 },
};

// ─── MAIN APP ─────────────────────────────────────────────
export default function App() {
  const [token,      setToken]      = useState(localStorage.getItem("jwt") || "");
  const [tokenInput, setTokenInput] = useState("");
  const [type,       setType]       = useState("length");
  const [action,     setAction]     = useState("Comparison");

  // Comparison & Conversion
  const [fromVal,  setFromVal]  = useState("1");
  const [fromUnit, setFromUnit] = useState("FEET");
  const [toVal,    setToVal]    = useState("");   // auto-filled by backend
  const [toUnit,   setToUnit]   = useState("INCH");

  // Arithmetic
  const [val1,    setVal1]    = useState("1");
  const [unit1,   setUnit1]   = useState("FEET");
  const [val2,    setVal2]    = useState("12");
  const [unit2,   setUnit2]   = useState("INCH");
  const [outUnit, setOutUnit] = useState("FEET");
  const [op,      setOp]      = useState("+");

  const [result,  setResult]  = useState(null);
  const [loading, setLoading] = useState(false);
  const [error,   setError]   = useState("");

  const units = UNITS[type];

  // ── Auto-read JWT from URL after Google OAuth redirect ──
  useEffect(() => {
    const params   = new URLSearchParams(window.location.search);
    const urlToken = params.get("token");
    if (urlToken) {
      localStorage.setItem("jwt", urlToken);
      setToken(urlToken);
      window.history.replaceState({}, "", "/");
    }
  }, []);

  // ── Auto-convert when fromVal, fromUnit, or toUnit changes ──
  useEffect(() => {
    if (action !== "Conversion") return;
    if (!token) return;
    if (!fromVal || isNaN(fromVal)) { setToVal(""); return; }

    const timer = setTimeout(() => {
      callConvert();
    }, 400); // debounce 400ms

    return () => clearTimeout(timer);
  }, [fromVal, fromUnit, toUnit, action]);

  // ── Auth ──
  const saveToken = () => {
    localStorage.setItem("jwt", tokenInput);
    setToken(tokenInput);
    setTokenInput("");
  };

  const logout = () => {
    localStorage.removeItem("jwt");
    setToken("");
    setResult(null);
    setToVal("");
    setError("");
  };

  const handleGoogleLogin = () => {
    window.location.href = `${BASE_URL}/oauth2/authorization/google`;
  };

  // ── Type change ──
  const handleType = (t) => {
    setType(t);
    const u = UNITS[t];
    setFromUnit(u[0]);
    setToUnit(u[1] || u[0]);
    setUnit1(u[0]);
    setUnit2(u[1] || u[0]);
    setOutUnit(u[0]);
    setResult(null);
    setToVal("");
    setError("");
  };

  // ── Generic API call ──
  const callApi = async (endpoint, body) => {
    setLoading(true);
    setError("");
    setResult(null);
    try {
      const res = await fetch(`${API_URL}/${endpoint}`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(body),
      });
      if (res.status === 401) { setError("Unauthorized — please check your JWT token."); return null; }
      if (!res.ok) { setError(`Server error: ${res.status}`); return null; }
      return await res.json();
    } catch (e) {
      setError("Could not connect to backend. Make sure it's running on http://localhost:8080");
      return null;
    } finally {
      setLoading(false);
    }
  };

  // ── Convert (auto-fill To field) ──
  const callConvert = async () => {
    if (!token || !fromVal || isNaN(fromVal)) return;
    setError("");
    try {
      const res = await fetch(`${API_URL}/convert`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify({
          q1: { value: parseFloat(fromVal), unit: fromUnit, type: type.toUpperCase() },
          q2: { value: 0, unit: toUnit,    type: type.toUpperCase() },
        }),
      });
      if (res.status === 401) { setError("Unauthorized — please check your JWT token."); return; }
      if (!res.ok) { setError(`Server error: ${res.status}`); return; }
      const data = await res.json();
      setToVal(data);
    } catch (e) {
      setError("Could not connect to backend.");
    }
  };

  // ── Compare submit ──
  const handleCompare = async () => {
    if (!token) { setError("Please login or enter your JWT token first."); return; }
    const data = await callApi("compare", {
      q1: { value: parseFloat(fromVal), unit: fromUnit, type: type.toUpperCase() },
      q2: { value: parseFloat(toVal || 0), unit: toUnit, type: type.toUpperCase() },
    });
    if (data !== null) setResult(data);
  };

  // ── Arithmetic submit ──
  const handleArithmetic = async () => {
    if (!token) { setError("Please login or enter your JWT token first."); return; }
    const data = await callApi(OP_ENDPOINTS[op], {
      q1: { value: parseFloat(val1), unit: unit1, type: type.toUpperCase() },
      q2: { value: parseFloat(val2), unit: unit2, type: type.toUpperCase() },
    });
    if (data !== null) setResult(data);
  };

  const cycleOp = () => {
    const ops = ["+", "-", "*", "/"];
    setOp(ops[(ops.indexOf(op) + 1) % ops.length]);
    setResult(null);
  };

  // ─── RENDER ───────────────────────────────────────────
  return (
      <div style={S.wrapper}>
        <div style={S.app}>

          {/* Header */}
          <div style={S.header}>
            <span>Welcome To Quantity Measurement</span>
            {token && <button style={S.logoutBtn} onClick={logout}>Logout</button>}
          </div>

          <div style={S.body}>

            {/* Auth */}
            {!token ? (
                <div style={{ marginBottom: 24 }}>
                  <div style={S.label}>Authentication Required</div>
                  <button style={S.loginBtn} onClick={handleGoogleLogin}>
                    🔐 Login with Google
                  </button>
                  <div style={{ textAlign: "center", color: "#aab0cc", fontSize: "0.78rem", margin: "10px 0" }}>
                    — or paste your JWT token manually —
                  </div>
                  <div style={S.tokenRow}>
                    <input
                        style={S.tokenInput}
                        placeholder="Paste JWT token here..."
                        value={tokenInput}
                        onChange={(e) => setTokenInput(e.target.value)}
                    />
                    <button style={S.saveBtn} onClick={saveToken}>Save</button>
                  </div>
                </div>
            ) : (
                <div style={S.authBanner}>✅ Authenticated — JWT token active</div>
            )}

            {/* Type Selector */}
            <div style={S.label}>Choose Type</div>
            <div style={S.typeGrid}>
              {TYPES.map((t) => (
                  <div key={t.id} style={S.typeCard(type === t.id)} onClick={() => handleType(t.id)}>
                    <div style={{ fontSize: "1.7rem", marginBottom: 6 }}>{t.icon}</div>
                    <div style={{ fontSize: "0.78rem", fontWeight: 700, color: "#444" }}>{t.label}</div>
                  </div>
              ))}
            </div>

            {/* Action Selector */}
            <div style={S.label}>Choose Action</div>
            <div style={S.actionGroup}>
              {["Comparison", "Conversion", "Arithmetic"].map((a) => (
                  <button key={a} style={S.actionBtn(action === a)}
                          onClick={() => { setAction(a); setResult(null); setToVal(""); setError(""); }}>
                    {a}
                  </button>
              ))}
            </div>

            {/* ── CONVERSION ── */}
            {action === "Conversion" && (
                <>
                  <div style={S.row2}>
                    <div>
                      <div style={S.label}>From</div>
                      <div style={S.inputBox}>
                        <input
                            style={S.bigInput}
                            value={fromVal}
                            type="number"
                            onChange={(e) => { setFromVal(e.target.value); setToVal(""); }}
                        />
                        <select style={S.unitSelect} value={fromUnit}
                                onChange={(e) => { setFromUnit(e.target.value); setToVal(""); }}>
                          {units.map((u) => <option key={u}>{u}</option>)}
                        </select>
                      </div>
                    </div>
                    <div>
                      <div style={S.label}>To (auto-converted)</div>
                      <div style={S.inputBox}>
                        {/* READ ONLY — filled by backend */}
                        <input
                            style={S.bigInputRead}
                            value={loading ? "..." : toVal}
                            readOnly
                            placeholder="—"
                        />
                        <select style={S.unitSelect} value={toUnit}
                                onChange={(e) => { setToUnit(e.target.value); setToVal(""); }}>
                          {units.map((u) => <option key={u}>{u}</option>)}
                        </select>
                      </div>
                    </div>
                  </div>
                  <button style={S.submitBtn} onClick={callConvert} disabled={loading}>
                    {loading ? "Converting..." : "Convert"}
                  </button>
                  {toVal !== "" && !error && (
                      <div style={S.compareResult}>
                        <span style={S.badge(true)}>✅ Result</span>
                        <span style={{ fontSize: "0.9rem", color: "#444", fontWeight: 600 }}>
                    {fromVal} {fromUnit} = {toVal} {toUnit}
                  </span>
                      </div>
                  )}
                </>
            )}

            {/* ── COMPARISON ── */}
            {action === "Comparison" && (
                <>
                  <div style={S.row2}>
                    <div>
                      <div style={S.label}>From</div>
                      <div style={S.inputBox}>
                        <input
                            style={S.bigInput}
                            value={fromVal}
                            type="number"
                            onChange={(e) => { setFromVal(e.target.value); setResult(null); }}
                        />
                        <select style={S.unitSelect} value={fromUnit}
                                onChange={(e) => { setFromUnit(e.target.value); setResult(null); }}>
                          {units.map((u) => <option key={u}>{u}</option>)}
                        </select>
                      </div>
                    </div>
                    <div>
                      <div style={S.label}>To</div>
                      <div style={S.inputBox}>
                        {/* EDITABLE — user enters value to compare against */}
                        <input
                            style={S.bigInput}
                            value={toVal}
                            type="number"
                            placeholder="enter value"
                            onChange={(e) => { setToVal(e.target.value); setResult(null); }}
                        />
                        <select style={S.unitSelect} value={toUnit}
                                onChange={(e) => { setToUnit(e.target.value); setResult(null); }}>
                          {units.map((u) => <option key={u}>{u}</option>)}
                        </select>
                      </div>
                    </div>
                  </div>
                  <button style={S.submitBtn} onClick={handleCompare} disabled={loading}>
                    {loading ? "Comparing..." : "Compare"}
                  </button>
                  {result !== null && !error && (
                      <div style={S.compareResult}>
                  <span style={S.badge(result)}>
                    {result ? "✅ Equal" : "❌ Not Equal"}
                  </span>
                        <span style={{ fontSize: "0.9rem", color: "#444", fontWeight: 600 }}>
                    {fromVal} {fromUnit} {result ? "==" : "!="} {toVal} {toUnit}
                  </span>
                      </div>
                  )}
                </>
            )}

            {/* ── ARITHMETIC ── */}
            {action === "Arithmetic" && (
                <>
                  <div style={S.arithGrid}>
                    <div>
                      <div style={S.label}>Value 1</div>
                      <div style={S.inputBox}>
                        <input style={S.bigInput} value={val1} type="number"
                               onChange={(e) => { setVal1(e.target.value); setResult(null); }} />
                        <select style={S.unitSelect} value={unit1}
                                onChange={(e) => { setUnit1(e.target.value); setResult(null); }}>
                          {units.map((u) => <option key={u}>{u}</option>)}
                        </select>
                      </div>
                    </div>
                    <div style={{ display: "flex", alignItems: "center", paddingBottom: 8 }}>
                      <button style={S.opBtn} onClick={cycleOp}>{op}</button>
                    </div>
                    <div>
                      <div style={S.label}>Value 2</div>
                      <div style={S.inputBox}>
                        <input style={S.bigInput} value={val2} type="number"
                               onChange={(e) => { setVal2(e.target.value); setResult(null); }} />
                        <select style={S.unitSelect} value={unit2}
                                onChange={(e) => { setUnit2(e.target.value); setResult(null); }}>
                          {units.map((u) => <option key={u}>{u}</option>)}
                        </select>
                      </div>
                    </div>
                  </div>
                  <button style={S.submitBtn} onClick={handleArithmetic} disabled={loading}>
                    {loading ? "Calculating..." : `Calculate (${op})`}
                  </button>
                  {result !== null && !error && (
                      <div style={S.resultBlock}>
                        <div>
                          <div style={{ fontSize: "0.68rem", fontWeight: 800, color: "#aab0cc", letterSpacing: "0.1em", textTransform: "uppercase", marginBottom: 4 }}>
                            Result
                          </div>
                          <div style={S.resultValue}>{result}</div>
                        </div>
                        <select style={S.resultUnitSelect} value={outUnit} onChange={(e) => setOutUnit(e.target.value)}>
                          {units.map((u) => <option key={u}>{u}</option>)}
                        </select>
                      </div>
                  )}
                </>
            )}

            {/* Error */}
            {error && <div style={S.errBox}>⚠️ {error}</div>}

          </div>
        </div>
      </div>
  );
}