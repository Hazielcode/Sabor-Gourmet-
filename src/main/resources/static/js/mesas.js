const API_URL = "/api/mesas";
const tabla = document.querySelector("#tabla-mesas tbody");
const btnNueva = document.getElementById("btnNueva");
const form = document.getElementById("formMesa");
const modal = new bootstrap.Modal(document.getElementById("modalMesa"));
const idInput = document.getElementById("mesaId");
const modalLabel = document.getElementById("modalMesaLabel");

// 🚀 Cargar lista de mesas
async function cargarMesas() {
    const res = await fetch(API_URL);
    const data = await res.json();
    tabla.innerHTML = "";

    if (data.length === 0) {
        tabla.innerHTML = `
            <tr>
                <td colspan="5" class="text-center text-muted py-4">
                    <i class="bi bi-info-circle me-1"></i> No hay mesas registradas
                </td>
            </tr>`;
        return;
    }

    data.forEach(m => {
        const fila = document.createElement("tr");
        fila.innerHTML = `
            <td class="text-center">${m.id}</td>
            <td class="text-center">${m.numero}</td>
            <td class="text-center">${m.capacidad}</td>
            <td class="text-center">
                <span class="badge ${m.estado === 'Disponible' ? 'bg-success' : m.estado === 'Ocupada' ? 'bg-danger' : 'bg-warning'}">
                    ${m.estado}
                </span>
            </td>
            <td class="text-center">
                <button class="btn btn-warning btn-sm me-1" onclick="editarMesa(${m.id})">
                    <i class="bi bi-pencil-square"></i>
                </button>
                <button class="btn btn-danger btn-sm" onclick="eliminarMesa(${m.id})">
                    <i class="bi bi-trash3"></i>
                </button>
            </td>
        `;
        tabla.appendChild(fila);
    });
}

// ➕ Nueva Mesa
btnNueva.addEventListener("click", () => {
    form.reset();
    idInput.value = "";
    modalLabel.textContent = "Nueva Mesa";
    modal.show();
});

// 💾 Guardar (crear o actualizar)
form.addEventListener("submit", async e => {
    e.preventDefault();
    const mesa = {
        numero: parseInt(form.numero.value),
        capacidad: parseInt(form.capacidad.value),
        estado: form.estado.value
    };

    let method = "POST", url = API_URL;
    if (idInput.value) {
        method = "PUT";
        url = `${API_URL}/${idInput.value}`;
    }

    await fetch(url, {
        method,
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(mesa)
    });

    modal.hide();
    cargarMesas();
});

// ✏️ Editar mesa
async function editarMesa(id) {
    const res = await fetch(`${API_URL}/${id}`);
    const m = await res.json();

    idInput.value = m.id;
    form.numero.value = m.numero;
    form.capacidad.value = m.capacidad;
    form.estado.value = m.estado;

    modalLabel.textContent = "Editar Mesa";
    modal.show();
}

// 🗑️ Eliminar mesa
async function eliminarMesa(id) {
    if (confirm("¿Deseas eliminar esta mesa?")) {
        await fetch(`${API_URL}/${id}`, { method: "DELETE" });
        cargarMesas();
    }
}

// Inicializar
cargarMesas();
