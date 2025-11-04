import React, { useState } from "react";
import axios from "axios";

export default function ExcelConverter() {
  const [file, setFile] = useState(null);
  const [jsonResult, setJsonResult] = useState("");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const handleFileChange = (e) => {
    setFile(e.target.files[0]);
    setJsonResult("");
    setError("");
  };

  const handleUpload = async () => {
    if (!file) {
      setError("⚠️ Veuillez sélectionner un fichier Excel (.xlsx)");
      return;
    }

    if (!file.name.endsWith(".xlsx")) {
      setError("❌ Seuls les fichiers .xlsx sont supportés !");
      return;
    }

    const formData = new FormData();
    formData.append("file", file);

    try {
      setLoading(true);
      setError("");

      const response = await axios.post(
        "http://localhost:9091/api/conversion/excel-to-procedure",
        formData,
        {
          headers: { "Content-Type": "multipart/form-data" },
        }
      );

      setJsonResult(JSON.stringify(response.data, null, 2));
    } catch (err) {
      console.error(err);
      setError(
        "🚫 Erreur lors de la conversion : " +
          (err.response?.data?.error || err.message)
      );
      setJsonResult("");
    } finally {
      setLoading(false);
    }
  };

  const handleCopy = () => {
    if (!jsonResult) return;
    navigator.clipboard.writeText(jsonResult);
    alert("✅ Résultat JSON copié dans le presse-papier !");
  };


  const handleDownload = () => {
    if (!jsonResult) return;

    const blob = new Blob([jsonResult], { type: "application/json" });
    const url = URL.createObjectURL(blob);

    const link = document.createElement("a");
    link.href = url;
    link.download = `${file?.name?.replace(".xlsx", "") || "result"}.json`;
    document.body.appendChild(link);
    link.click();

    document.body.removeChild(link);
    URL.revokeObjectURL(url);
  };

  return (
    <div
      style={{
        maxWidth: "900px",
        margin: "40px auto",
        padding: "30px",
        backgroundColor: "#ffffff", //#ffffff
        borderRadius: "12px",
        boxShadow: "0 6px 20px rgba(0,0,0,0.1)",
        textAlign: "center",
      }}
    >
      <h1 style={{ color: "#2563eb", marginBottom: "10px" }}>
        🧾 Convertisseur Excel → JSON
      </h1>
      <p style={{ color: "#555" }}>
        Sélectionnez un fichier <strong>.xlsx</strong> pour le convertir en JSON.
      </p>

      <input
        type="file"
        accept=".xlsx"
        onChange={handleFileChange}
        style={{
          marginTop: "20px",
          padding: "10px",
          border: "1px solid #ccc",
          borderRadius: "6px",
        }}
      />

      <div style={{ marginTop: "20px" }}>
        <button
          onClick={handleUpload}
          disabled={loading}
          style={{
            padding: "10px 25px",
            backgroundColor: loading ? "#e5e7eb" : "#4f46e5",
            color: "#fff",
            border: "none",
            borderRadius: "6px",
            cursor: loading ? "not-allowed" : "pointer",
            transition: "background-color 0.3s",
          }}
        >
          {loading ? "⏳ Conversion..." : "🚀 Convertir"}
        </button>
      </div>

      {error && (
        <p style={{ color: "red", marginTop: "15px", fontWeight: "500" }}>
          {error}
        </p>
      )}

      {jsonResult && (
        <div style={{ marginTop: "30px", textAlign: "left" }}>
          <h3 style={{ color: "#1e293b" }}>📦 Résultat JSON :</h3>
          <textarea
            readOnly
            value={jsonResult}
            rows="20"
            style={{
              width: "100%",
              fontFamily: "monospace",
              backgroundColor: "#f8fafc",
              border: "1px solid #ccc",
              borderRadius: "8px",
              padding: "10px",
              marginTop: "10px",
              whiteSpace: "pre",
            }}
          />


          <div
            style={{
              display: "flex",
              justifyContent: "flex-end",
              gap: "10px",
              marginTop: "10px",
            }}
          >
            <button
              onClick={handleCopy}
              style={{
                padding: "8px 18px",
                backgroundColor: "#16a34a",
                color: "white",
                border: "none",
                borderRadius: "6px",
                cursor: "pointer",
              }}
            >
              📋 Copier
            </button>

            <button
              onClick={handleDownload}
              style={{
                padding: "8px 18px",
                backgroundColor: "#0ea5e9",
                color: "white",
                border: "none",
                borderRadius: "6px",
                cursor: "pointer",
              }}
            >
              💾 Télécharger
            </button>
          </div>
        </div>
      )}
    </div>
  );
}
