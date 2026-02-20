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
}, { threshold: 0.15 }); // Reducido un poco para que cargue más rápido en móvil

elements.forEach(el => observer.observe(el));


// =========================
// LOGICA DE MODALES
// =========================
document.addEventListener('DOMContentLoaded', () => {
    const modal = document.getElementById("surveyModal");
    const btnHero = document.getElementById("openSurveyModal");
    const btnBottom = document.getElementById("openSurveyModalBottom");
    const span = document.getElementsByClassName("close-modal")[0];

    const openModal = function() {
        modal.style.display = "block";
        document.body.style.overflow = "hidden"; // Evita scroll de fondo
    }

    if(btnHero) btnHero.onclick = openModal;
    if(btnBottom) btnBottom.onclick = openModal;

    span.onclick = function() {
        modal.style.display = "none";
        document.body.style.overflow = "auto";
    }

    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
            document.body.style.overflow = "auto";
        }
    }
});

// Modal de Feedback
function closeFeedback() {
    const modal = document.getElementById('feedbackModal');
    if (modal) {
        modal.style.opacity = '0';
        setTimeout(() => modal.remove(), 300);
    }
}

// Limpieza de inputs del formulario
document.querySelectorAll('.survey-form').forEach(form => {
    form.addEventListener('submit', function() {
        const inputs = this.querySelectorAll('input[type="text"], input[type="email"]');
        inputs.forEach(input => {
            input.value = input.value.trim();
        });
    });
});


// =========================
// CARRUSEL SOLAR POCKET
// =========================
const track = document.querySelector('.carousel-track');
const dots = document.querySelectorAll('.dot');
let currentIndex = 0;

function updateCarousel(index) {
    if(!track) return; // Validación por si no carga
    track.style.transform = `translateX(-${index * 100}%)`;

    dots.forEach(dot => dot.classList.remove('active'));
    dots[index].classList.add('active');

    currentIndex = index;
}

dots.forEach((dot, index) => {
    dot.addEventListener('click', () => updateCarousel(index));
});

// Auto-play cada 5 segundos
setInterval(() => {
    if(dots.length > 0) {
        let nextIndex = (currentIndex + 1) % dots.length;
        updateCarousel(nextIndex);
    }
}, 5000);


// =========================
// CONTROL AUDIO VIDEO PRINCIPAL
// =========================
const video = document.getElementById('problemVideo');
const btn = document.getElementById('toggleAudio');
const icon = document.getElementById('audioIcon');

if(btn && video) {
    btn.addEventListener('click', () => {
        if (video.muted) {
            video.muted = false;
            icon.innerText = '🔊';
        } else {
            video.muted = true;
            icon.innerText = '🔇';
        }
    });
}