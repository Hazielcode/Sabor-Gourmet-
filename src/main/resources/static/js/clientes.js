const API_URL = "/api/clientes";
const tabla = document.querySelector("#tabla-clientes tbody");
const btnNuevo = document.getElementById("btnNuevo");
const form = document.getElementById("formCliente");
const modal = new bootstrap.Modal(document.getElementById("modalCliente"));
const idInput = document.getElementById("clienteId");
const modalLabel = document.getElementById("modalClienteLabel");

async function cargarClientes() {
    const res = await fetch(API_URL);
    const data = await res.json();
    tabla.innerHTML = "";

    if (data.length === 0) {
        tabla.innerHTML = `
            <tr>
                <td colspan="6" class="text-center text-muted py-4">
                    <i class="bi bi-info-circle me-1"></i> No hay clientes registrados
                </td>
            </tr>`;
        return;
    }

    data.forEach(c => {
        const fila = document.createElement("tr");
        fila.innerHTML = `
            <td class="text-center">${c.id}</td>
            <td>${c.nombres} ${c.apellidos}</td>
            <td>${c.correo}</td>
            <td class="text-center">${c.telefono}</td>
            <td class="text-center">
                <span class="badge ${c.activo ? 'bg-success' : 'bg-danger'}">
                    ${c.activo ? 'Activo' : 'Inactivo'}
                </span>
            </td>
            <td class="text-center">
                <button class="btn btn-warning btn-sm me-1" onclick="editarCliente(${c.id})">
                    <i class="bi bi-pencil-square"></i>
                </button>
                <button class="btn btn-danger btn-sm" onclick="eliminarCliente(${c.id})">
                    <i class="bi bi-trash3"></i>
                </button>
            </td>
        `;
        tabla.appendChild(fila);
    });
}

btnNuevo.addEventListener("click", () => {
    form.reset();
    idInput.value = "";
    modalLabel.textContent = "Nuevo Cliente";
    modal.show();
});

form.addEventListener("submit", async e => {
    e.preventDefault();
    const cliente = {
        nombres: form.nombres.value,
        apellidos: form.apellidos.value,
        correo: form.correo.value,
        telefono: form.telefono.value,
        activo: form.activo.value === "true"
    };

    let method = "POST", url = API_URL;
    if (idInput.value) {
        method = "PUT";
        url = `${API_URL}/${idInput.value}`;
    }

    await fetch(url, {
        method,
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(cliente)
    });

    modal.hide();
    cargarClientes();
});

async function editarCliente(id) {
    const res = await fetch(`${API_URL}/${id}`);
    const c = await res.json();
    idInput.value = c.id;
    form.nombres.value = c.nombres;
    form.apellidos.value = c.apellidos;
    form.correo.value = c.correo;
    form.telefono.value = c.telefono;
    form.activo.value = c.activo;
    modalLabel.textContent = "Editar Cliente";
    modal.show();
}

async function eliminarCliente(id) {
    if (confirm("¿Deseas eliminar este cliente?")) {
        await fetch(`${API_URL}/${id}`, { method: "DELETE" });
        cargarClientes();
    }
}

cargarClientes();
