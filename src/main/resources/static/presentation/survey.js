document.addEventListener('DOMContentLoaded', () => {
    const form = document.querySelector('form');
    // Asegúrate de añadir id="progressBar" a tu div de la barra de progreso en el HTML
    const progressBar = document.getElementById('progressBar');
    const inputs = form.querySelectorAll('input, select, textarea');

    const updateProgress = () => {
        // Definimos las preguntas clave para medir el progreso (una por sección)
        const checkPoints = [
            'Zona',                   // Sección 1
            'Grupo',                  // Sección 1
            'Acceso Electricidad',    // Sección 1
            'Frecuencia Dificultad',  // Sección 2
            'Facilidad',              // Sección 3
            'Utilidad',               // Sección 4
            'Interes_General'         // Sección 7
        ];

        const total = checkPoints.length;
        let completed = 0;

        checkPoints.forEach(name => {
            const input = form.querySelector(`[name="${name}"]:checked`) ||
                          form.querySelector(`select[name="${name}"]`);

            if (input && input.value) {
                completed++;
            }
        });

        // Actualización visual
        const percentage = (completed / total) * 100;
        if (progressBar) {
            progressBar.style.width = `${percentage}%`;
            // Cambia a verde cuando está completo
            progressBar.style.backgroundColor = percentage === 100 ? '#2ecc71' : '#f39c12';
        }
    };

    // Escuchar cambios en cualquier input
    inputs.forEach(input => {
        input.addEventListener('change', updateProgress);
    });

    // Ejecutar una vez al cargar por si hay valores pre-rellenados
    updateProgress();
});