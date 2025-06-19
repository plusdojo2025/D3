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

    // コンテキストパスの取得
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
        document.getElementById('popupContent').innerHTML = html;
        document.getElementById('popup').style.display = 'block';
        document.getElementById('overlay').style.display = 'block';
    })
    .catch(error => {
        errorDiv.textContent = "送信エラー: " + error.message;
    });
}

function closePopup() {
    document.getElementById('popup').style.display = 'none';
    document.getElementById('overlay').style.display = 'none';

    // コンテキストパスを使ってmenu.jspにリダイレクト
    const contextPath = document.body.dataset.context || '';
    window.location.href = contextPath + "/menu.jsp";
}
