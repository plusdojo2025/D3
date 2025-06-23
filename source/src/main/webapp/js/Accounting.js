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

    // コンテキストパスの取得（JSPの<body>に data-context属性がある想定）
    const contextPath = document.body.dataset.context || '';

    // Fetch API で PaymentComplete にPOST送信
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

function closePopup() {
    const popup = document.getElementById('popup');
    const overlay = document.getElementById('overlay');

    if (popup) popup.style.display = 'none';
    if (overlay) overlay.style.display = 'none';

    // コンテキストパスを使って menu.jsp にリダイレクト
    const contextPath = document.body.dataset.context || '';
    window.location.href = contextPath + "/menu.jsp";
}
