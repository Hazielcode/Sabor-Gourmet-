const API_URL = "/api/bitacora";
const tabla = document.querySelector("#tabla-bitacora tbody");
const btnRecargar = document.getElementById("btnRecargar");

async function cargarBitacora() {
    tabla.innerHTML = `
        <tr>
            <td colspan="5" class="text-center text-muted py-4">
                <div class="spinner-border text-secondary me-2" role="status"></div>
                Cargando registros...
            </td>
        </tr>`;

    try {
        const res = await fetch(API_URL);
        const data = await res.json();

        if (data.length === 0) {
            tabla.innerHTML = `
                <tr>
                    <td colspan="5" class="text-center text-muted py-4">
                        <i class="bi bi-info-circle me-1"></i> No hay registros en la bitácora actualmente.
                    </td>
                </tr>`;
            return;
        }

        tabla.innerHTML = data.map((b, index) => `
            <tr class="text-center">
                <td>${index + 1}</td>
                <td>${new Date(b.fechaHora).toLocaleString()}</td>
                <td>
                    <span class="badge px-3 py-2 ${b.accion === 'CREAR' ? 'bg-success' :
            b.accion === 'EDITAR' ? 'bg-warning text-dark' :
                b.accion === 'ELIMINAR' ? 'bg-danger' : 'bg-secondary'}">
                        ${b.accion}
                    </span>
                </td>
                <td>${b.entidad || 'N/A'}</td>
                <td><i class="bi bi-person-circle me-1 text-primary"></i>${b.usuario || 'Sistema'}</td>
            </tr>
        `).join('');

    } catch (error) {
        console.error("Error al cargar bitácora:", error);
        tabla.innerHTML = `
            <tr>
                <td colspan="5" class="text-center text-danger py-4">
                    <i class="bi bi-exclamation-triangle me-1"></i> Error al cargar la bitácora.
                </td>
            </tr>`;
    }
}

btnRecargar.addEventListener("click", cargarBitacora);
cargarBitacora();
