document.addEventListener("DOMContentLoaded", function() {
	const toggles = document.querySelectorAll('.accordion-toggle');
	toggles.forEach(toggle => {
		toggle.addEventListener('click', () => {
			const content = toggle.nextElementSibling;
			const isOpen = content.style.display === 'block';
			content.style.display = isOpen ? 'none' : 'block';
		});
	});
});
