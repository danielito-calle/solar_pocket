// =========================
// ANIMACIONES SCROLL
// =========================
const elements = document.querySelectorAll('.fade-up');

const observer = new IntersectionObserver(entries => {
    entries.forEach(entry => {
        if (entry.isIntersecting) {
            entry.target.classList.add('show');
        }
    });
}, { threshold: 0.2 });

elements.forEach(el => observer.observe(el));


document.addEventListener('DOMContentLoaded', () => {
    const modal = document.getElementById("surveyModal");
    // Seleccionamos ambos botones por su ID
    const btnHero = document.getElementById("openSurveyModal");
    const btnBottom = document.getElementById("openSurveyModalBottom");

    const span = document.getElementsByClassName("close-modal")[0];

    // Función reutilizable para abrir
    const openModal = function() {
        modal.style.display = "block";
    }

    // Asignar el evento a ambos botones
    if(btnHero) btnHero.onclick = openModal;
    if(btnBottom) btnBottom.onclick = openModal;

    // Cerrar modal al dar click en (x)
    span.onclick = function() {
        modal.style.display = "none";
    }

    // Cerrar modal al dar click fuera de la caja blanca
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
});


function closeFeedback() {
    const modal = document.getElementById('feedbackModal');
    if (modal) {
        modal.style.opacity = '0';
        setTimeout(() => modal.remove(), 300); // Desvanece y luego elimina
    }
}

// Limpieza automática de espacios antes de enviar
document.querySelectorAll('.survey-form').forEach(form => {
    form.addEventListener('submit', function() {
        const inputs = this.querySelectorAll('input[type="text"], input[type="email"]');
        inputs.forEach(input => {
            input.value = input.value.trim();
        });
    });
});