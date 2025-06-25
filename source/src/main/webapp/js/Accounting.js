function submitPayment() {
    const form = document.getElementById('paymentForm');
    const errorDiv = document.getElementById('errorMessage');

    // エラーメッセージをリセット
    errorDiv.textContent = '';

    // 支払い方法の選択チェック
    const selectedPayment = form.querySelector('input[name="payment_method"]:checked');
    if (!selectedPayment) {
        errorDiv.textContent = "※ 支払い方法を選択してください。";
        return;
    }

    // フォームデータの取得
    const formData = new FormData(form);

    // ポップアップ要素を取得
    const popupContent = document.getElementById('popupContent');
    const popup = document.getElementById('popup');
    const overlay = document.getElementById('overlay');

    if (!popupContent || !popup || !overlay) {
        alert("ポップアップの要素が存在しません。ページのHTMLを確認してください。");
        return;
    }

    const contextPath = document.body.dataset.context || '';

    fetch(contextPath + "/PaymentComplete", {
        method: 'POST',
        body: formData
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("サーバーエラーが発生しました。");
        }
        return response.text();
    })
    .then(html => {
        // ポップアップに結果を表示
        popupContent.innerHTML = html;
        popup.style.display = 'block';
        overlay.style.display = 'block';
    })
    .catch(error => {
        errorDiv.textContent = "送信エラー: " + error.message;
    });
}

function closePopup(visitId) {
    const popup = document.getElementById('popup');
    const overlay = document.getElementById('overlay');

    if (popup) popup.style.display = 'none';
    if (overlay) overlay.style.display = 'none';

    const contextPath = document.body.dataset.context || '';

	const form = document.createElement("form");
	form.method = "POST";
	form.action = `${contextPath}/AccountingServlet`;
	
	const input = document.createElement("input");
	input.type = "hidden"
	input.name = "visitId";
	input.value = visitId;
	
	form.appendChild(input);
	document.body.appendChild(form);
	form.submit();
}
