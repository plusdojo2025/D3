document.addEventListener("DOMContentLoaded", function () {
	const toggles = document.querySelectorAll('.accordion-toggle');
	toggles.forEach(toggle => {
		toggle.style.cursor = 'pointer';
		toggle.addEventListener('click', () => {
			const content = toggle.nextElementSibling;
			const isOpen = content.style.display === 'block';
			content.style.display = isOpen ? 'none' : 'block';
			toggle.classList.toggle('active', !isOpen);
		});
	});
});
