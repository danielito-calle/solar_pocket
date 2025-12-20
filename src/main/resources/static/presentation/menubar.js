(function() {
    const initMenu = () => {
        const toggleBtn = document.getElementById('toggleMenu');
        const sideMenu = document.getElementById('sideMenu');
        const overlay = document.getElementById('menuOverlay');

        if (toggleBtn && sideMenu && overlay) {
            const toggleHandler = () => {
                sideMenu.classList.toggle('active');
                overlay.classList.toggle('active');
            };

            toggleBtn.addEventListener('click', toggleHandler);
            overlay.addEventListener('click', toggleHandler);
        }
    };

    // Se ejecuta cuando el DOM está listo
    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', initMenu);
    } else {
        initMenu();
    }
})();