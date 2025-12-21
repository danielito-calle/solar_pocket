document.addEventListener('DOMContentLoaded', () => {
    const form = document.querySelector('form');
    // Asegúrate de añadir id="progressBar" a tu div de la barra de progreso en el HTML
    const progressBar = document.getElementById('progressBar');
    const inputs = form.querySelectorAll('input, select, textarea');

    const updateProgress = () => {
        const checkPoints = [
            '¿En qué tipo de zona vive actualmente?',
            '¿A qué comunidad o grupo pertenece?',
            '¿Tu acceso a la electricidad es constante?',
            'Frecuencia de desconexión por falta de carga:',
            '¿Qué características impulsan tu interés?',
            'Tu percepción sobre el funcionamiento:',
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
            document.getElementById('progressText').innerText = `${percentage}%`;
        }
    };

    // Escuchar cambios en cualquier input
    inputs.forEach(input => {
        input.addEventListener('change', updateProgress);
    });

    // Ejecutar una vez al cargar por si hay valores pre-rellenados
    updateProgress();
});