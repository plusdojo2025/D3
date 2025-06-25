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

document.addEventListener('DOMContentLoaded', function () {

  // イベントフォーム送信時チェック
  const eventForm = document.querySelector("form[action$='StoreStaffServlet']:nth-of-type(1)");
  if (eventForm) {
    eventForm.addEventListener("submit", function (e) {
      const name = eventForm.querySelector("[name='event_name']").value.trim();
      const date = eventForm.querySelector("[name='event_date']").value;

      if (name === "" || date === "") {
        e.preventDefault();
        alert("イベント名と日付は必須です。");
      }
    });
  }

  // 業務連絡フォーム送信時チェック
  const infoForm = document.querySelector("form[action$='StoreStaffServlet']:nth-of-type(2)");
  if (infoForm) {
    infoForm.addEventListener("submit", function (e) {
      const remark = infoForm.querySelector("[name='store_remark']").value.trim();
      const date = infoForm.querySelector("[name='store_date']").value;

      if (remark === "" || date === "") {
        e.preventDefault();
        alert("連絡内容と日付は必須です。");
      }
    });
  }

});